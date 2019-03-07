package objectRepository.common;

import java.util.ArrayList;

public enum TrafficDirection {
	CustomerOutbound("Customer/Outbound"),
	VisitorInbound("Visitor/Inbound"),
	BiDirectional("Bi-Directional");
	
	public String value;
	private TrafficDirection(String value){
		this.value=value;
	}

	public static ArrayList<String> getValues(){
		ArrayList<String> list=new ArrayList<>();
		for (TrafficDirection item : TrafficDirection.values()) {
			list.add(item.value);
		}
		return list;
	}
}
