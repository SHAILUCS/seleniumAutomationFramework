package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import common.seleniumExceptionHandling.SeleniumMethods;

public class Sequential {

	@Test(priority = 1, description = "1 + Test - S")
	public void Test1_S() {
		System.out.println("1 + Test - S");
		new SeleniumMethods().wait(5);
		Assert.fail();
	}
	
	@Test(priority = 2, description = "2 + Test - S",dependsOnMethods={"Test1_S"})
	public void Test2_S() {
		System.out.println("2 + Test - S");
		new SeleniumMethods().wait(5);
	}



}
