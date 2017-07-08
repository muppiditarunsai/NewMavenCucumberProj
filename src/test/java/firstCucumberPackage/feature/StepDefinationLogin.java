package firstCucumberPackage.feature;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinationLogin {

	SeleniumConnector SCobj = new SeleniumConnector();
		
	@Given("I go to \"([^\"]*)\" with \"([^\"]*)\"")
		public void launchingURl(String qaurl,String browser){
		SCobj.openBrowser(browser);
		SCobj.loadingURL(qaurl);
	}
	@When("I enter \"([^\"]*)\" in the \"([^\"]*)\"")
	public void entertext(String data,String locator)
	{
		SCobj.enterText(locator, data);
 	}
	
	@And("I click on \"([^\"]*)\" button")
	public void click(String locator)
	{
		
		SCobj.click(locator);
	}
 
	@Then("I validate that \"([^\"]*)\" is displayed")
	public void verifytext(String Locator)
	{
		SCobj.checkElementPresent(Locator);
	}
	@And("I logout from the application")
	public void logout()
	{
		SCobj.click("LogOut_id");
	}
	
	@Then("I close broswer")
	public void closeBr()
	{
		SCobj.CloseBrowser();
	}
}
