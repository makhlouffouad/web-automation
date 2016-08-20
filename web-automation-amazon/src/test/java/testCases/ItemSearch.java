package testCases;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import base.CommonAPI;

public class ItemSearch extends CommonAPI{

	@Test
	public void dropDownSearch() throws InterruptedException{
		List<WebElement> list = new ArrayList<WebElement>();
		list = getListOfElement("searchDropdownBox option");
		//System.out.println(list.size());
		
		List<String> items = getListOfString(list);
		
		
		WebElement element = driver.findElement(By.id("searchDropdownBox"));
		//select.selectByVisibleText("Beauty & Personal Care");
		/*for(int i=0; i<items.size(); i++){
			
			String item = items.get(i);
			driver.findElement(By.id("twotabsearchtextbox")).sendKeys(item, Keys.ENTER);
			System.out.println(item);
			Thread.sleep(3000);
			driver.findElement(By.id("twotabsearchtextbox")).clear();
			Thread.sleep(3000);
			driver.navigate().back();
			Thread.sleep(3000);
		}
*/		
		for(int i=1; i<list.size(); i++){
				selectOptionByVisibleText(element, list.get(i).getText());
				System.out.println(list.get(i).getText());
				takeKeysEnter("#twotabsearchtextbox");
				Thread.sleep(1000);
				element = driver.findElement(By.id("searchDropdownBox"));
				list = getListOfElement("searchDropdownBox option");
			}
		
	}
	
}
