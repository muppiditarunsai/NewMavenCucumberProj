package firstCucumberPackage.feature;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumConnector {

	WebDriver driver;
	Properties OR=null;
	Properties CONFIG=null;
			
	//Designed a constructor to invoke all required property files
	public SeleniumConnector()
	{
		 if (OR==null)
		 {
			 // initialize OR
				 OR=new Properties();
		 }
		 try 
		 {
				 
			//Two ways of reading a file
//			FileInputStream fs=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\project\\config\\Locator.properties");
//			OR.load(fs); 
				FileReader file = new FileReader(System.getProperty("user.dir")+"/src/main/java/configurations/Locator.properties");
				OR.load(file);
		 
			//initialize CONFIG to corresponding env.
			
			CONFIG=new Properties();
			FileInputStream	fs=new FileInputStream(System.getProperty("user.dir")+"/src/main/java/configurations/Configuration.properties");
			CONFIG.load(fs);
		 }
		 catch (IOException e)
		  {
			 System.out.println("Error on initalizeing properties files");

		  }
		 
	 }
	
	//Function for opening browser
	public void openBrowser(String browser )
	{
		
		 if(CONFIG.getProperty(browser).equals("firefox")){
			
			driver=new FirefoxDriver();
			
		}else if(CONFIG.getProperty(browser).equals("chrome")){
		 
			System.setProperty("webdriver.chrome.driver","M:\\chromedriver_win32\\chromedriver.exe");
				driver=new ChromeDriver(); 
			 }
		 
			driver.manage().window().maximize();
	}
		
	//Function for getting URl
	public void loadingURL(String URL)
	{
		driver.get(CONFIG.getProperty(URL));
	}
	
	/* *************** Function for entering Text************** */
	public void enterText(String locator,String data)
	{
	  
	  checkFindElement(locator).sendKeys(data);
	}
   
	  
	  /* ****** Function for clicking on WebElementc******* */
	  public void click(String locator)
	  {
		  
		  checkFindElement(locator).click();
	  }
	  
	    
	  
	  // Function for getting a webElement based on locator
	  public WebElement checkFindElement(String locator)
	  {
		  WebElement element=null;
		  WebDriverWait wait=new WebDriverWait(driver,30);
		
		 
		  if(locator.endsWith("_id"))
	  {
		  
		 element= driver.findElement(By.id(OR.getProperty(locator)));
	  }
	  
	  else if(locator.endsWith("_name"))
	  {
		  
		  element= driver.findElement(By.name(OR.getProperty(locator)));
	  }
	  else if(locator.endsWith("_xpath"))
	  {
		  wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(OR.getProperty(locator)))));
		  element=driver.findElement(By.xpath(OR.getProperty(locator)));
	  }
	  
	  else if(locator.endsWith("_classname"))
	  {
		  
		  element=driver.findElement(By.className(OR.getProperty(locator)));
	  }
	  else if(locator.endsWith("_linktxt"))
	  {
		  
		  element=driver.findElement(By.linkText(OR.getProperty(locator)));
	  }
	 else if(locator.endsWith("_plinktxt"))
	 {
		  
		 element= driver.findElement(By.partialLinkText(OR.getProperty(locator)));
	  }
	 else if(locator.endsWith("_css"))
	 {
		  
		 element=driver.findElement(By.cssSelector(OR.getProperty(locator)));
	  }
	 else if(locator.endsWith("_tagname"))
	     {
			  
	    	 element= driver.findElement(By.tagName(OR.getProperty(locator)));
		  }
		return element;
	  }
	  
	  

		
		/* *********** Function for verifying presence of WebElement OR Text present on page ************ */
	public  void checkElementPresent(String Locator)
	{
		 if(checkFindElement(Locator).isDisplayed())
			{
			 	String ElmntNme = checkFindElement(Locator).getText();
				System.out.println(ElmntNme+" table is present");
			}
			else
			{
				System.out.println("Expected element is not displayed");
			
			}
		
	}

	

	public void CloseBrowser()
	{
		driver.close();
	}

}
