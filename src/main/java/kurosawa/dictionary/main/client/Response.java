package kurosawa.dictionary.main.client;

import java.io.Serializable;

public class Response implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String function;
	private String message;
	private String english;
	private String japanese;
	
	public Response(){
	}
	
	public Response(String function, String message, String english, String japanese){
		this.function = function;
		this.message = message;
		this.english = english;
		this.japanese = japanese;
	}
	
	public String getFunction() {
		return function;
	}

	public String getMessage() {
		return message;
	}

	public String getEnglish() {
		return english;
	}

	public String getJapanese() {
		return japanese;
	}

}
