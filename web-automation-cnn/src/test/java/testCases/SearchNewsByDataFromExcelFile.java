package testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import base.CommonAPI;
import reader.ReadNewsData;

public class SearchNewsByDataFromExcelFile extends CommonAPI{
	ReadNewsData readNewsData = new ReadNewsData();
    @Test
    public void searchNews() throws InterruptedException,IOException{
        String [] data = readNewsData.getDataFromExcelFile();
        clickByCss("#search-button");
        //clickByXpath("//*[@id='search-button']");
        Thread.sleep(1000);
        int counter = 0;
        for(String st:data){
            if(counter==0) {
                typeByCss("#search-input-field", st);
                takeKeysEnter("#search-input-field");
                clearInputField("#searchInputTop");
                Thread.sleep(1000);
                counter++;
            } else {
                typeByCss("#searchInputTop", st);
                takeKeysEnter("#searchInputTop");
                Thread.sleep(1000);
                clearInputField("#searchInputTop");
            }

        }
    }
}
