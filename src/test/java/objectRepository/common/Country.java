package objectRepository.common;

import java.util.ArrayList;

public enum Country {
	Afghanistan("Afghanistan"),
	Austria("Austria"), 
	UnitedKingdom("United Kingdom"),
	;
	
	
	public String value;  
	private Country(String value){  
		this.value=value;  
	} 
	public static ArrayList<String> getValues(){
		ArrayList<String> list=new ArrayList<>();
		for (Currency item : Currency.values()) {
			list.add(item.value);
		}
		return list;
	}
}
