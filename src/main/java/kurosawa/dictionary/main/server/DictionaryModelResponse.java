package kurosawa.dictionary.main.server;

public class DictionaryModelResponse {
	private final String function;
	private final String message;
	private final String english;
	private final String japanese;
	
	public DictionaryModelResponse(String function, String message, String english, String japanese){
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
