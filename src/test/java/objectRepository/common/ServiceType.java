package objectRepository.common;

import java.util.ArrayList;

public enum ServiceType{
	VOICE("Voice"),
	SMS("SMS"),
	DATA("Data"),
	ALL("All");
	public String value;
	private ServiceType(String value){
		this.value=value;
	}
	
	public static ArrayList<String> getValues(){
		ArrayList<String> list=new ArrayList<>();
		for (ServiceType item : ServiceType.values()) {
			list.add(item.value);
		}
		return list;
	} 
};