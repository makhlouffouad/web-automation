package util;

import java.util.List;

public class TestDBConnection {

	public static void main(String[] args) throws Exception{
		DBConnect dbConnet = new DBConnect();		
		List<String> newsList1 = dbConnet.readDataBase("CnnNewsVertical", "NewTitle");
		System.out.println("CnnNewsVertical table's Column Data");
		List<String> newsList = dbConnet.readDataBase("actionSteps", "items");
		System.out.println("actionSteps table's Column Data");
	}
	
}
