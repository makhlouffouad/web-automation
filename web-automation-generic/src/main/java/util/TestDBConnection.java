package util;

import java.util.List;

public class TestDBConnection {

	public static void main(String[] args) throws Exception{
		DBConnect dbConnet = new DBConnect();
		//dbConnet.connectToDataBase();
		List<String> newsList = dbConnet.readDataBase("CnnNewsVertical", "NewTitle");
		
	}
	
}
