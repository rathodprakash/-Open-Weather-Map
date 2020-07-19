package com.pr.example.weather.payload;
/**
 * 
 * @author Prakash Rathod
 * 
 */
public class ApiResponse {
    private String message;
    private String description;
	private int status;
	private Object data;
	
	public ApiResponse(String message, String description, int status, Object data) {
		this.message = message;
		this.description = description;
		this.status = status;
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
