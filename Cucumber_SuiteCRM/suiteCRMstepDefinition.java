package suiteCRMStepDefn;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class suiteCRMstepDefinition {
	 WebDriver driver;
	 WebDriverWait wait;
	 Actions act;
	 List<WebElement> dashlets;
	 
	 @Before
	 public void Login() {
		 driver = new FirefoxDriver();
	     wait = new WebDriverWait(driver, 20);
	     act = new Actions(driver);
		 driver.get("https://alchemy.hguy.co/crm");
		 driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		 driver.findElement(By.id("user_name")).sendKeys("admin");
		 driver.findElement(By.id("username_password")).sendKeys("pa$$w0rd");
		 driver.findElement(By.id("bigbutton")).click();
	 }
     
	//============== SCENARIO 1 ===================================================================================
	 
	 @Given ("^user captures all the Dashlets$")
	 public void captureDashlet() {
		 dashlets = driver.findElements(By.xpath("//*[@id='toolbar']/ul/li[contains(@class,'topnav')]"));
	 }
	 
	 @When ("^user prints counts the Dashlets$")
	 public void countsDashlet() {
		 System.out.println("Number of Dashlets = "+ dashlets.size());
	 }
	 
	 @Then ("^user prints title of each Dashlet$")
	 public void printsDashlet() {
		 System.out.println("Title of each dashlet is as below: ");
		 for (WebElement x:dashlets) {
			 System.out.println(x.getText());
		 }
	 }
	 
	 //============== SCENARIO 2 ===================================================================================
	 
	 @Given ("^user navigates to Sales -> Leads -> Create Lead$")
	 public void navigateToCreateLeads() {
		 act.moveToElement(driver.findElement(By.id("grouptab_0")))
		 	.moveToElement(driver.findElement(By.id("moduleTab_9_Leads")))
		 	.click()
		 	.build()
		 	.perform();
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\'content\']/div[1]/h2")));
		 driver.findElement(By.xpath("//*[@id='actionMenuSidebar']/ul/li[1]/a/div[2]")).click();
	 }
	 
	 @When ("^user fills in Leads details with lastName \"(.*)\"$")
	 public void enterLeads(String lastName) {
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='last_name']")));
		 driver.findElement(By.xpath("//input[@name='last_name']")).click();
		 driver.findElement(By.xpath("//input[@name='last_name']")).sendKeys(lastName);
	 }
	 
	 @And ("^user clicks save leads$")
	 public void saveLeads() {
		 driver.findElement(By.id("SAVE")).click();
	 }
	 
	 @Then ("^user navigates to View Leads page to see results$")
	 public void verifyLeads() {
		 driver.findElement(By.xpath("//*[@id='actionMenuSidebar']/ul/li[3]/a/div[2]")).click();
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[@class='module-title-text']")));
		 String name = driver.findElement(By.xpath("//*[@id='MassUpdate']/div[3]/table/tbody/tr[1]/td[3]")).getText();
		 System.out.println("Successfully added Leads with name : "+name);
	 }
	 
	 //============== SCENARIO 3 ===================================================================================
	 
	 @Given ("^user navigates to Activities -> Meetings -> Schedule a Meeting$")
	 public void navigateToScheduleMeeting() {
		 act.moveToElement(driver.findElement(By.id("grouptab_3")))
		 	.moveToElement(driver.findElement(By.id("moduleTab_9_Meetings")))
		 	.click()
		 	.build()
		 	.perform();
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[@class='module-title-text']")));
		 driver.findElement(By.xpath("//div[contains(text(),'Schedule Meeting')]")).click();
	 }
	 
	 @When ("^user fills meeting details with a \"(.*)\",\"(.*)\",\"(.*)\"$")
	 public void enterMeetingDetails(String meetingSub, String startTime, String endTime) {
		 //Enter meeting Subject
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='name']")));
		 driver.findElement(By.xpath("//input[@name='name']")).click();
		 driver.findElement(By.xpath("//input[@name='name']")).sendKeys(meetingSub);
		 
		 //Select Start / End time
		 Select st = new Select(driver.findElement(By.id("date_start_hours")));
		 st.selectByVisibleText(startTime);
		 
		 Select en = new Select(driver.findElement(By.id("date_end_hours")));
		 en.selectByVisibleText(endTime);
	 }
	 
	 @And ("^user searches and adds members with last names \"(.*)\" and \"(.*)\"$")
	 public void addMembers(String lName1, String lName2) {
		 driver.findElement(By.id("search_last_name")).sendKeys(lName1);
		 driver.findElement(By.id("invitees_search")).click();
		 driver.findElement(By.id("invitees_add_1")).click();
		 
		 driver.findElement(By.id("search_last_name")).sendKeys(lName2);
		 driver.findElement(By.id("invitees_search")).click();
		 driver.findElement(By.id("invitees_add_1")).click();
		 
	 }
	 
	 @And ("^user clicks save the meeting$")
	 public void saveMeeting() {
		 driver.findElement(By.id("SAVE_HEADER")).click();
	 }
	 
	 @Then ("^user navigates to View Meetings page to confirm meeting schedule$")
	 public void confirmMeetingSchedule() {
		 driver.findElement(By.xpath("//*[@id='actionMenuSidebar']/ul/li[2]/a/div[2]")).click();
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[@class='module-title-text']")));
		 String name = driver.findElement(By.xpath("//*[@id='MassUpdate']/div[3]/table/tbody/tr[1]/td[4]")).getText();
		 System.out.println("Meeting titled "+name+" is successfully saved");
	 }
	
	//============== SCENARIO 4 ===================================================================================
	 
	 @Given ("^user navigates to  All -> Products-> Create Product$")
	 public void navigateToProd() {
		 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		 act.moveToElement(driver.findElement(By.id("grouptab_5")))
		 	.build()
		 	.perform();
		 
		 driver.findElement(By.xpath("//a[@href='?action=ajaxui#ajaxUILoc=index.php%3Fmodule%3DAOS_Products%26action%3Dindex%26parentTab%3DAll'][contains(.,'Products')]")).click();
		 
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[@class='module-title-text']")));
		 driver.findElement(By.xpath("//div[contains(text(),'Create Product')]")).click();
	 }
	 
	 @When ("^user enters product details with product name \"(.*)\", price \"(.*)\"$")
	 public void retrieveProdInfo(String prodName, String price) {
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='name']")));
		 
		 driver.findElement(By.xpath("//input[@name='name']")).click();
		 driver.findElement(By.xpath("//input[@name='name']")).sendKeys(prodName);
		 
		 driver.findElement(By.id("price")).sendKeys(price);
	 }
	 
	 @And ("^user clicks save product$")
	 public void saveProd() {
		 driver.findElement(By.id("SAVE")).click();
	 }
	 
	 @Then ("^user navigates to View Products page to verify added product$")
	 public void verifySavedProd() {
		 driver.findElement(By.xpath("//*[@id='actionMenuSidebar']/ul/li[2]/a/div[2]")).click();
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[@class='module-title-text']")));
		 String name = driver.findElement(By.xpath("//*[@id='MassUpdate']/div[3]/table/tbody/tr[1]/td[3]")).getText();
		 System.out.println("Product "+name+" is successfully saved");
	 }
	 
	 @After
     public void closeOpenBrowser() {
     	//driver.close();
     }
}
