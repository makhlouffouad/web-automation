package base;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class CommonAPI {
	
public WebDriver driver = null;
	
	@Parameters({"useCloud","userName","accessKey","os","browserName","browserVersion","url"})
	@BeforeMethod
	public void setUp(@Optional("false") boolean useCloud,
						@Optional("kamalfouad") String userName, 
						@Optional("") String accessKey,
						@Optional("windows 10")String os, 
						@Optional (" ") String browserName,
						@Optional ("46")String browserVersion,
						@Optional (" ") String url) throws IOException{
		
		if(useCloud==true){
			//run in cloud
			getCloudDriver(userName, accessKey, os, browserName, browserVersion);
		}
		else{
			//run in local
			getLocalDriver(browserName);
		}	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(url);
		driver.manage().window().maximize();
	}
	
	public WebDriver getLocalDriver(String browserName){
		
		if(browserName.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", "C:\\users\\dell\\Practice\\web-automation\\web-automation-generic\\browser-driver\\chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("fireFox")){
			driver = new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("ie")){
			System.setProperty("webdriver.ie.driver","C:\\users\\dell\\Practice\\web-automation\\web-automation-generic\\browser-driver\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		return driver;
	}
	
	public WebDriver getCloudDriver(String userName, String accessKey, 
									String os, String browserName, 
									String browserVersion) throws IOException{
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName", os);
		cap.setBrowserName(browserName);
		cap.setCapability("Version", browserVersion);
		driver = new RemoteWebDriver(new URL("http://"+userName+":"+accessKey+"@ondemand.saucelabs.com:80/wd/hub"), cap);
		
		return driver;
		
	}
	
	@AfterMethod
	public void cleanUp(){
		driver.quit();
	}
	
	 public void clickByCss(String locator) {
		 driver.findElement(By.cssSelector(locator)).click();
	 }

	 public void clickByXpath(String locator) {
		 driver.findElement(By.xpath(locator)).click();
	 }

	 public void typeByCss(String locator, String value) {

	     driver.findElement(By.cssSelector(locator)).sendKeys(value);
	 }

	 public void typeById(String locator, String value){
	     driver.findElement(By.id(locator)).sendKeys(value);
	 }

	 public void typeByClassName(String locator, String value){
	     driver.findElement(By.className(locator));
	 }

	 public void typeByXpath(String locator, String value) {
	     driver.findElement(By.xpath(locator)).sendKeys(value);
	 }

	 public void takeKeysEnter(String locator) {
	     driver.findElement(By.cssSelector(locator)).sendKeys(Keys.ENTER);
	 }
	
     public void clearInputField(String locator){
        driver.findElement(By.cssSelector(locator)).clear();
    }
     public void navigateBack(){
         driver.navigate().back();
     }
     public void navigateForward(){
         driver.navigate().forward();
     }
     public String getTextByCss(String locator){
         String st = driver.findElement(By.cssSelector(locator)).getText();
         return st;
     }
     public String getTextByXpath(String locator){
         String st = driver.findElement(By.xpath(locator)).getText();
         return st;
     }
     public String getTextById(String locator){
         return driver.findElement(By.id(locator)).getText();
     }
     public String getTextByName(String locator){
         String st = driver.findElement(By.name(locator)).getText();
         return st;
     }

	public List<WebElement> getListOfElement(String locator){
		
		List<WebElement> list = new ArrayList<WebElement>();
		list = driver.findElements(By.id(locator));
		return list;
	}
	
	public List<String> getListOfString(List<WebElement> list){
		
		List<String> items = new ArrayList<String>();
		for(WebElement element: list){
			items.add(element.getText());
		}
		
		return items;
	}
	
	public void selectOptionByVisibleText(WebElement element, String value){
		Select select = new Select(element);
		select.selectByVisibleText(value);
	}
	
	public void sleepFor(int sec)throws InterruptedException{
        Thread.sleep(sec * 1000);
    }
    public void mouseHoverByCSS(String locator){
        try {
            WebElement element = driver.findElement(By.cssSelector(locator));
            Actions action = new Actions(driver);
            Actions hover = action.moveToElement(element);
        }catch(Exception ex){
            System.out.println("First attempt has been done, This is second try");
            WebElement element = driver.findElement(By.cssSelector(locator));
            Actions action = new Actions(driver);
            action.moveToElement(element).perform();

        }

    }
    public void mouseHoverByXpath(String locator){
        try {
            WebElement element = driver.findElement(By.xpath(locator));
            Actions action = new Actions(driver);
            Actions hover = action.moveToElement(element);
        }catch(Exception ex){
            System.out.println("First attempt has been done, This is second try");
            WebElement element = driver.findElement(By.cssSelector(locator));
            Actions action = new Actions(driver);
            action.moveToElement(element).perform();

        }

    }
    //handling Alert
    public void okAlert(){
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
    public void cancelAlert(){
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    //iFrame Handle
    public void iframeHandle(WebElement element){
        driver.switchTo().frame(element);
    }

    public void goBackToHomeWindow(){
        driver.switchTo().defaultContent();
    }

    //get Links
    public void getLinks(String locator){
        driver.findElement(By.linkText(locator)).findElement(By.tagName("a")).getText();
    }

    //Taking Screen shots
    public void takeScreenShot()throws IOException {
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,new File("screenShots.png"));
    }
    
    public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{
		//Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot =((TakesScreenshot)webdriver);
		//Call getScreenshotAs method to create image file
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		//Move image file to new destination 
		File DestFile=new File(fileWithPath);
		//Copy file at destination
		FileUtils.copyFile(SrcFile, DestFile);		
	}
    //Synchronization
    public void waitUntilClickAble(By locator){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public void waitUntilVisible(By locator){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public void waitUntilSelectable(By locator){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        boolean element = wait.until(ExpectedConditions.elementToBeSelected(locator));
    }
    public String getTimeStamp() {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        return timestamp;
    }



}





