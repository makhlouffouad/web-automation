package testCases;

import java.io.IOException;
import java.util.List;


import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.CommonAPI;
import reader.ReadNewsData;

public class SearchNewsFromExternalSource extends CommonAPI{
	
	ReadNewsData readNewsData = new ReadNewsData();
	
	@Parameters({"readFromExcelFile"})
    @Test
    public void searchNews(boolean readFromExcelFile) throws InterruptedException,IOException,Exception {
    	logger.info("running news search on cnn");
    	if(readFromExcelFile==true){
	       String [] data = readNewsData.getNewsFromExcelFile();
	        clickByCss("#search-button");
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
    	}else {
    		List<String> data = readNewsData.getNewsDataFromDB("cnnnewsvertical","NewTitle");
	        clickByCss("#search-button");
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
}
