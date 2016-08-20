package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import base.CommonAPI;

public class Search extends CommonAPI{

	
	@Test
	public void search() throws InterruptedException{
		clickByCss("#search-button");
		Thread.sleep(1000);
		typeByCss("#search-input-field", "Politics");
		takeKeysEnter("#search-input-field");
		Thread.sleep(1000);
	}
}
