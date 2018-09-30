package openmap; 

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class TestLabels {
	WebDriver driver;
	@BeforeTest
	public void Setup() {
		
		System.setProperty("webdriver.chrome.driver", "H:\\Selenium Learning\\DRIVERS\\CHROMEDRIVER\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://openweathermap.org/");
		
		
		
}
	
	@Test //validation for invalid city in openweather and verification of 'not found' for the same
	public  void invalidCity() throws InterruptedException {
		
		driver.findElement(By.xpath("//*[@id='searchform']//input")).click();
		driver.findElement(By.xpath("//*[@id='searchform']//input")).sendKeys("invalidCity");
		driver.findElement(By.xpath("//*[@id='searchform']/button")).click();
		Thread.sleep(2000);
		assertTrue(driver.findElement(By.xpath("//*[text()='Not found']")).isDisplayed(),"Not found is not displayed");
		
	}
	
	@Test //validation for valid city in openweather and search results shows the proper validation result
	public  void validCity() throws InterruptedException {
		driver.get("https://openweathermap.org/");
		driver.findElement(By.xpath("//*[@id='searchform']//input")).click();
		driver.findElement(By.xpath("//*[@id='searchform']//input")).sendKeys("Bhubaneswar");
		driver.findElement(By.xpath("//*[@id='searchform']/button")).click();
		Thread.sleep(2000);
		assertTrue(driver.findElement(By.linkText("Bhubaneswar, IN")).isDisplayed(),"Bhubaneswar is not displayed");
		
	}
	
	
	
	@Test //verification of menu links and respective labels
	public void verifyHomePage() throws InterruptedException
	{
	     
		List<WebElement> menulinks=driver.findElements(By.cssSelector("ul.nav.navbar-nav.navbar-right"));
		List<WebElement> menulink=menulinks.get(0).findElements(By.className("nav__item"));
		for(int i=0;i<menulink.size();i++)
		{
			System.out.println(menulink.get(i).getText()+" link is verified");
			assertTrue(menulink.size()==8,"The size of menulinks is not 8");
			driver.findElement(By.linkText(menulink.get(0).getText())).click();
			assertEquals(driver.findElement(By.className("breadcrumb-title")).getText(),"Weather forecast");
			break;
			
		}
		driver.findElement(By.xpath("//img[@alt='openweathermap']")).click();
				Thread.sleep(2000);	
				driver.findElement(By.linkText("API")).click();
			assertEquals(driver.findElement(By.className("breadcrumb-title")).getText(),"Weather API");
			driver.findElement(By.linkText("Price")).click();
		assertEquals(driver.findElement(By.className("breadcrumb-title")).getText(),"Price");
		driver.findElement(By.linkText("Partners")).click();
		assertEquals(driver.findElement(By.className("breadcrumb-title")).getText(),"Partners and solutions");
		driver.findElement(By.linkText("Stations")).click();
		assertEquals(driver.findElement(By.className("breadcrumb-title")).getText(),"Weather stations");
		driver.findElement(By.linkText("Widgets")).click();
		assertEquals(driver.findElement(By.className("breadcrumb-title")).getText(),"Widgets constructor");
		Actions action= new Actions(driver);
		action.moveToElement(driver.findElement(By.linkText("Maps"))).click().build().perform();
		driver.findElement(By.linkText("Current satellite maps")).click();
		Thread.sleep(3000);	
	
	}
		
		@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
