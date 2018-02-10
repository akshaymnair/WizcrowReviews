package com.javatpoint;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationRestController {
	
	@RequestMapping(value = "submitForm.html", method = RequestMethod.POST)
	public ModelAndView createUser(@ModelAttribute("student") Student student) throws Exception {

		final String uri = "http://9.193.125.112:8080/controller/register/add";
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        Map<String,String> map = new HashMap<String, String>();
        map.put("Content-Type", "application/json");
        map.put("Accept", "application/json");

        headers.setAll(map);

        Map<String,String> userObj = new HashMap<String,String>();
        userObj.put("name", student.getName());
		userObj.put("password", student.getPassword());
		userObj.put("confpwd", student.getConfpassword());
		userObj.put("emailId", student.getEmail());
		userObj.put("country", student.getAddress().getCountry());
		userObj.put("mobileNumber", student.getMobile());
		userObj.put("pincode", student.getAddress().getPincode());
		userObj.put("street", student.getAddress().getStreet());
		userObj.put("city", student.getAddress().getCity());

        HttpEntity<?> request = new HttpEntity<>(userObj, headers);

        ResponseEntity<?> response = new RestTemplate().postForEntity(uri, request, UserRegistrationVO.class);
        UserRegistrationVO entityResponse = (UserRegistrationVO) response.getBody();
        
	    
	    if (!entityResponse.isSuccess()) {
			ModelAndView model = new ModelAndView("AdmissionForm");
			model.addObject("errmsg",entityResponse.getErrorMsg());
			return model;
		}
	    
	    ModelAndView model = new ModelAndView("AdmissionSuccess");
	    model.addObject("student",student);
		return model;
	   
	}
	
	@RequestMapping(value = "submitratingsForm.html", method = RequestMethod.POST)
	public ModelAndView addReview(@ModelAttribute("review") ReviewRating rating) throws Exception {

		final String uri = "http://9.202.46.109:8080/review/getProducts/add";
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        Map<String,String> map = new HashMap<String, String>();
        map.put("Content-Type", "application/json");
        map.put("Accept", "application/json");

        headers.setAll(map);

        Map<String,Object> userObj = new HashMap<String,Object>();
        userObj.put("productId", Integer.parseInt(rating.getProductId()));
		userObj.put("userid", rating.getUserId());
		userObj.put("rating", rating.getRating());
		userObj.put("comment", rating.getComment());
		

        HttpEntity<?> request = new HttpEntity<>(userObj, headers);

        ResponseEntity<?> response = new RestTemplate().postForEntity(uri, request, ReviewAddResp.class);
        ReviewAddResp entityResponse = (ReviewAddResp) response.getBody();
	    
	    if (!entityResponse.isSuccess()) {
			ModelAndView model = new ModelAndView("ProductRating");
			model.addObject("errmsg",entityResponse.getErrMsg());
			return model;
		}
	    
	    ModelAndView model = ratingDetails(rating.getProductId());
		return model;
	   
	}
	
	@RequestMapping(value = "loginSuccess.html", method = RequestMethod.POST)
	public ModelAndView verifyUser(@ModelAttribute("user") User user, HttpServletResponse servletResp) throws Exception {

		final String uri = "http://9.193.125.112:8080/controller/register/validate";
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        Map<String,String> map = new HashMap<String, String>();
        map.put("Content-Type", "application/json");
        map.put("Accept", "application/json");

        headers.setAll(map);

        Map<String,String> userObj = new HashMap<String,String>();
        userObj.put("emailId", user.getEmail());
		userObj.put("password", user.getPassword());
		

        HttpEntity<?> request = new HttpEntity<>(userObj, headers);

        ResponseEntity<?> response = new RestTemplate().postForEntity(uri, request, LogonSuccessVO.class);
        LogonSuccessVO entityResponse = (LogonSuccessVO) response.getBody();
        
	    if (!entityResponse.isSuccess()) {
			ModelAndView model = new ModelAndView("LoginForm");
			model.addObject("errmsg",entityResponse.getErrorMsg());
			return model;
		}
	    
	    String userId = entityResponse.getUserId();
        
        Cookie cookie = new Cookie("userId", userId);
        cookie.setMaxAge(60*60); //1 hour
        servletResp.addCookie(cookie);
	    
	    ModelAndView model = new ModelAndView("LoginSuccess");
	    model.addObject("loginmsg",entityResponse.getUserName());
		return model;
	   
	}
	
	@RequestMapping(value = "/productdetails", method = RequestMethod.GET)
	public ModelAndView productDetails() throws Exception {

		final String getUri = "http://9.202.46.109:8080/review/getProducts/display";
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<?> response = restTemplate.getForEntity(getUri, ProdDetList.class);
		ProdDetList entityResponse = (ProdDetList) response.getBody();
	    
	    /*if (!entityResponse.isSuccess()) {
			ModelAndView model = new ModelAndView("LoginForm");
			model.addObject("errmsg",entityResponse.getErrorMsg());
			return model;
		}*/
	    
	    ModelAndView model = new ModelAndView("ProductDetails");
	    model.addObject("productdetails",entityResponse.getProdDetList());
		return model;
	   
	}
	
	@RequestMapping(value = "/rating/{productId}", method = RequestMethod.GET)
	public ModelAndView ratingDetails(@PathVariable("productId") String productId) throws Exception {

		final String getUri = "http://9.202.46.109:8080/review/getProducts/rating?prodId="+productId;
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<?> response = restTemplate.getForEntity(getUri, ProdCommentsRatings.class);
		ProdCommentsRatings entityResponse = (ProdCommentsRatings) response.getBody();
	    
	    /*if (!entityResponse.isSuccess()) {
			ModelAndView model = new ModelAndView("LoginForm");
			model.addObject("errmsg",entityResponse.getErrorMsg());
			return model;
		}*/
	    
	    ModelAndView model = new ModelAndView("ProductRating");
	    model.addObject("productRatings",entityResponse);
		return model;
	   
	}
	
	@RequestMapping(value = "registrationForm.html", method = RequestMethod.GET)
	public ModelAndView getAdmissionForm() throws Exception {

		ModelAndView model = new ModelAndView("AdmissionForm");
		return model;
	}
	
	@RequestMapping(value = "rating.html", method = RequestMethod.GET)
	public ModelAndView getRatingForm() throws Exception {

		ModelAndView model = new ModelAndView("ProductRating");
		return model;
	}
	
	@RequestMapping(value = "login.html", method = RequestMethod.GET)
	public ModelAndView getloginForm() throws Exception {

		ModelAndView model = new ModelAndView("LoginForm");
		return model;
	}
	
	@RequestMapping(value = "Face.html", method = RequestMethod.GET)
	public ModelAndView getFaceForm() throws Exception {

		ModelAndView model = new ModelAndView("FaceDet");
		return model;
	}

}
