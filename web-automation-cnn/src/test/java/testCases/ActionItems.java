package testCases;

import base.CommonAPI;
import org.testng.annotations.Test;
import reader.ReadNewsData;
import java.util.List;

/**
 * Created by rrt on 5/7/2016.
 */
public class ActionItems extends CommonAPI{
	 ReadNewsData readNewsData = new ReadNewsData();
	 
	    @Test
	    public void searchNews() throws Exception{
	        List<String> actionItems = readNewsData.getActionStepsDataFromDB("actionSteps","items");
	        List<String> newsData = readNewsData.getNewsDataFromDB("cnnnewsvertical","NewTitle");
	        for(String ai:actionItems) {
	        	
	            for(int i=0; i<newsData.size(); i++){
	            	chooseAction(ai, newsData.get(i));
	               // clearSearchField();
	          }
	        }
	    }
	/*    
	    public void clickAction(String action) throws InterruptedException{
	    	if(action == "clickOnSearch")
	    		clickOnSearch();
	    }*/

	    public void chooseAction(String action,String value)throws InterruptedException{
	        switch(action){
	            case "clickOnSearch": clickOnSearch();
	                break;
	            case "enterTextOnInputField": enterTextOnInputField(value);
	                break;
	            case "clearSearchField": clearSearchField();
	                break;
	            default:
	                System.out.println("Invalid Action item");
	                break;

	        }
	    }

	    public void clickOnSearch()throws InterruptedException{
	        clickByCss("#search-button");
	        sleepFor(2);
	    }

	    public void enterTextOnInputField(String vale) throws InterruptedException{
	        typeByCss("#search-input-field", vale);
	        sleepFor(2);
	        takeKeysEnter("#search-input-field");
	        sleepFor(2);
	        clearInputField("#searchInputTop");
	        sleepFor(2);
	    }
	    public void clearSearchField() throws InterruptedException{
	        clearInputField("#searchInputTop");
	        sleepFor(2);
	    }

}


    /*ReadNewsData readNewsData = new ReadNewsData();
    @Test
    public void searchNews() throws Exception{
        List<String> actionItems = readNewsData.getActionStepsDataFromDB("actionSteps","items");
        List<String> newsData = readNewsData.getNewsDataFromDB("cnnnewsvertical","NewTitle");
        int numItems = actionItems.size();
        System.out.println(numItems);
        
        for(int i=0; i<newsData.size(); i++){
        	System.out.println(newsData.get(i));
        }
        
        for(String ai:actionItems) {
            for(int i=0; i<newsData.size(); i++){
            	chooseAction(ai, newsData.get(i), numItems);
                //clearSearchField();
          }
        }
    }

    public void chooseAction(String action,String value, int numItems)throws InterruptedException{
        switch(action){
            case "clickOnSearch": 
            	clickOnSearch();
                break;
            case "enterTextOnInputField": 
            	enterTextOnInputField(numItems, value);
                break;
            case "clearSearchField": 
            	clearSearchField();
                break;
            default:
                System.out.println("Invalid Action item");
                break;

        }
    }

    public void clickOnSearch()throws InterruptedException{
        clickByCss("#search-button");
        sleepFor(2);
    }

    public void enterTextOnInputField(int numItems, String vale) throws InterruptedException{
    	
    	int count = 0;
    	while(count <= numItems){
    		if(count == 0){
    			typeByCss("#search-input-field", vale);
    			takeKeysEnter("#search-input-field");
    			clearInputField("#searchInputTop");
    			sleepFor(2);
    			count++;
    		}else {
    			typeByCss("#searchInputTop", vale);
                takeKeysEnter("#searchInputTop");
                sleepFor(2);
                clearInputField("#searchInputTop");
			}
    	}    
    }
    public void clearSearchField(){
        clearInputField("#searchInputTop");
    }
*/

