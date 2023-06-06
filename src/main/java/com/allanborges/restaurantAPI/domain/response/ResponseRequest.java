package com.allanborges.restaurantAPI.domain.response;

import java.util.List;

import com.allanborges.restaurantAPI.domain.dtos.RequestDTO;

/*
 * Response Body for Request Objects following REST good practices with request details 
 */
public class ResponseRequest {
	
	private String status;
    private String sentOn;
    private String statusCode;
    private String transactionId;
    private String msg;
    private List<RequestDTO> resValues;
	
    public ResponseRequest() {
		super();
	}
	public ResponseRequest(String status, String sentOn, String statusCode, String transactionId, String msg,
			List<RequestDTO> resValues) {
		super();
		this.status = status;
		this.sentOn = sentOn;
		this.statusCode = statusCode;
		this.transactionId = transactionId;
		this.msg = msg;
		this.resValues = resValues;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSentOn() {
		return sentOn;
	}
	public void setSentOn(String sentOn) {
		this.sentOn = sentOn;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<RequestDTO> getResValues() {
		return resValues;
	}
	public void setResValues(List<RequestDTO> resValues) {
		this.resValues = resValues;
	}
	
	@Override
	public String toString() {
		return "ResponseRequest [status=" + status + ", sentOn=" + sentOn + ", statusCode=" + statusCode
				+ ", transactionId=" + transactionId + ", msg=" + msg + ", resValues=" + resValues + "]";
	}
}