package objectRepository.common;

import java.util.ArrayList;

public enum CalculationType{
	SingleRateEffectiveVoiceSMSData("Single Rate Effective (Voice, SMS, Data)"),
	AllYouCanEatVoiceSMSDataAllServices("All You Can Eat (Voice, SMS, Data, All Services)"),
	BacktoFirstVoiceSMSData("Back to First (Voice, SMS, Data)"),
	ContributiontoGroupShortfallVoiceSMSData("Contribution to Group Shortfall (Voice, SMS, Data)"),
	SendorPayFinancialAllServices("Send or Pay Financial (All Services)"),
	SendorPayTrafficVoiceSMSData("Send or Pay Traffic (Voice, SMS, Data)"),
	SteppedTieredVoiceSMSData("Stepped/Tiered (Voice, SMS, Data)"),
	AllYouCanEatMonthlyDataonly("All You Can Eat Monthly (Data only)"),
	SteppedTieredMonthlyDataonly("Stepped/Tiered Monthly (Data only)");
	
	public String value;  
	private CalculationType(String value){  
		this.value=value;  
	}
	public static ArrayList<String> getValues(){
		ArrayList<String> list=new ArrayList<>();
		for (CalculationType item : CalculationType.values()) {
			list.add(item.value);
		}
		return list;
	}
}
