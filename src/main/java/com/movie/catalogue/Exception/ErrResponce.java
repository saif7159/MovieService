package com.movie.catalogue.Exception;

public class ErrResponce {

	private int status;
	private String message;
	public ErrResponce(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	public ErrResponce() {
		super();
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
