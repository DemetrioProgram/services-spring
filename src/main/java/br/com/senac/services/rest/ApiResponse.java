package br.com.senac.services.rest;

public class ApiResponse {
	
	private String message;
	private Object data;
	
	public ApiResponse() {
		
	}

	public ApiResponse(String message) {
		this.message = message;
	}

	public ApiResponse(String message, Object data) {
		this.message = message;
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public ApiResponse setMessage(String message) {
		this.message = message;
		return this;
	}

	public Object getData() {
		return data;
	}

	public ApiResponse setData(Object data) {
		this.data = data;
		return this;
	}

}
