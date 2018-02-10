package com.prod.review;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prodreview.pojo.ProdCommentsRatings;
import com.prodreview.pojo.ProdDetList;
import com.prodreview.pojo.ProdDetails;
import com.prodreview.pojo.ProdReview;
import com.prodreview.pojo.ProductReviews;
import com.prodreview.pojo.ReviewAddResp;

/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping(value="/getProducts")
public class HomeController implements ApplicationContextAware{
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private String prod_sql = "select * from hackathon_prods";
	
	private String prodRevAddQuery = "insert into hackathon_prodreviews(PRODUCTID,USERID,RATING,COMMENTS) values(?,?,?,?)";
	
	private String getProdRatingsQry = "select USERID,RATING,COMMENTS from hackathon_prodreviews where PRODUCTID=? ORDER BY ID DESC FETCH FIRST 10 ROWS ONLY";
	
	private ApplicationContext context;
	
	@Autowired
	ReviewAddResp reviewResp;
	
	@Autowired
	ProdCommentsRatings prodRatings;
	
	@Autowired
	ProdDetList prodDList;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/display", method = RequestMethod.GET, produces="application/json")
	public ProdDetList home() {
		logger.info("Welcome home!");
		List<ProdDetails> prodDetsList = null;
		ProdDetails prodDets = null;
		List<Map<String,Object>> prodList = jdbcTemplate.queryForList(prod_sql);
		if(null != prodList && !prodList.isEmpty()) {
			prodDetsList = new ArrayList<ProdDetails>();
			Iterator<Map<String,Object>> prodListItr = prodList.iterator();
			while(prodListItr.hasNext()) {
				prodDets = (ProdDetails) context.getBean("prodDets");
				Map<String,Object> prodDetsMap = prodListItr.next();
				prodDets.setProductid(String.valueOf(prodDetsMap.get("PRODUCTID")));
				prodDets.setProdName((String) prodDetsMap.get("NAME"));
				prodDets.setImageurl((String) prodDetsMap.get("IMAGE"));
				prodDets.setAvg_rating((String) prodDetsMap.get("AVG_RATING"));
				prodDetsList.add(prodDets);
			}
			prodDList.setProdDetList(prodDetsList);
		}
		
		return prodDList;
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		// TODO Auto-generated method stub
		this.context = arg0;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST,produces="application/json",consumes="application/json")
	public ReviewAddResp addReviewComments(@RequestBody ProdReview prodRev)
	{
		try {
			int status = 0;
			status = jdbcTemplate.update(prodRevAddQuery, prodRev.getProductId(),prodRev.getUserid(),prodRev.getRating(),prodRev.getComment());
			if(status == 1) {
				reviewResp.setSuccess(true);
			}else {
				reviewResp.setSuccess(false);
				reviewResp.setErrMsg("Error while inserting into DB");
			}
		} catch(Exception e) {
			reviewResp.setSuccess(false);
			reviewResp.setErrMsg(e.getMessage());
		}
		
		return reviewResp;
	}
	
	@RequestMapping(value = "/rating", method = RequestMethod.GET, produces="application/json")
	public ProdCommentsRatings getRatingsOfProd(@RequestParam(value="prodId") int productId)
	{
		try {
			List<ProductReviews> prodRevs = null;
			prodRatings.setProductId(productId);
			List<Map<String,Object>> prodCommntsList = jdbcTemplate.queryForList(getProdRatingsQry, productId);
			if(null != prodCommntsList && !prodCommntsList.isEmpty()) {
				prodRevs = new ArrayList<ProductReviews>();
				ProductReviews prodRev = null;
				Iterator<Map<String,Object>> prodCmntsItr = prodCommntsList.iterator();
				while(prodCmntsItr.hasNext()) {
					Map<String,Object> prodCmntsMap = prodCmntsItr.next();
					prodRev = (ProductReviews) context.getBean("prodReviews");
					prodRev.setUserId(String.valueOf(prodCmntsMap.get("USERID")));
					prodRev.setRating(String.valueOf(prodCmntsMap.get("RATING")));
					prodRev.setComment(String.valueOf(prodCmntsMap.get("COMMENTS")));
					prodRevs.add(prodRev);
				}
				prodRatings.setProdRevList(prodRevs);
			} else {
				prodRatings.setErrMsg("There are no products with this productId");
			}
			
		} catch(Exception e) {
			prodRatings.setErrMsg(e.getMessage());
		}
		
		return prodRatings;
	}
	
}
