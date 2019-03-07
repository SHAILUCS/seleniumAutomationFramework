package objectRepository.common;

import java.util.ArrayList;

public enum Currency {
	AUD("AUD"),
	EUR("EUR"), 
	SDR("SDR"),
	USD("USD");
	
	public String value;  
	private Currency(String value){  
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
