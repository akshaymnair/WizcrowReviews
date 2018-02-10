package com.prodreview.pojo;

import java.util.List;

public class ProdCommentsRatings {
	
	private int productId;
	
	private List<ProductReviews> prodRevList = null;
	
	private String errMsg;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public List<ProductReviews> getProdRevList() {
		return prodRevList;
	}

	public void setProdRevList(List<ProductReviews> prodRevList) {
		this.prodRevList = prodRevList;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	 
}
