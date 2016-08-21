package testCases;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.Test;

import base.CommonAPI;
import reader.ReadNewsData;

public class SearchNewsFromDB extends CommonAPI{
	ReadNewsData readNewsData = new ReadNewsData();
    @Test
    public void searchNews() throws InterruptedException,IOException,Exception {
        List<String> data = readNewsData.getDataFromDB();
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
