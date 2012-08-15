package kurosawa.dictionary.main.server;

public interface DictionaryModel {
	
	DictionaryModelResponse search(String english);
	
	DictionaryModelResponse add(String english, String japanese);
	
	DictionaryModelResponse delete(String english);

}
