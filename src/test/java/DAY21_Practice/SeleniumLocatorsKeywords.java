package DAY21_Practice;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumLocatorsKeywords {

	public static WebDriver driver;
	public static Actions actions;
	
	// Window Handling Method
	public static void switchingWindow(WebDriver driver) {
		String parent = driver.getWindowHandle();
		Set<String> allwindows = driver.getWindowHandles();
		for (String window : allwindows) {
			if (!window.equals(parent)) {
				driver.switchTo().window(window);	
			}	
		}	
	}
	
	// Frames Handling Method
	public static void switchingToFrame(WebDriver driver, WebElement frameelement) {
		driver.switchTo().frame(frameelement);	
	}
		
	// DropDown Handling Method
	public static void dropDownsHandling(WebElement element, String value) {
		Select s = new Select(element);
		s.selectByVisibleText(value);
	}
	
	// Alert Handling Method
	public static void alertsHandling(String alertName) {
		List<WebElement>ls = driver.findElements(By.cssSelector("input.btn-style"));
		ls.stream().forEach(p->System.out.println(p.getAttribute("id")));
		ls.stream().forEach((p)->{
			if (p.getAttribute("id").contains(alertName)) {
				System.out.println("Clicked Succesfully!");
				p.click();
				driver.switchTo().alert().accept();
			}
		});	
	}
	
	public static void main(String[] args) throws Exception {
		driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		System.out.println("Title is : "+driver.getTitle());
		System.out.println("Current URL is : "+driver.getCurrentUrl());
		
		// Locator Id
		driver.findElement(By.id("autocomplete")).sendKeys("ind");
		
		// Locator ClassName
		List<WebElement> menuitems = driver.findElements(By.className("ui-menu-item"));
		menuitems.stream().filter(a -> a.getText().equalsIgnoreCase("India")).findFirst().orElse(null).click();
		
		// Locator Name
		driver.findElement(By.name("radioButton")).click();
		
		// Locator Xpath
		driver.findElement(By.xpath("//input[@value='option2']")).click();
		
		// Locator TagName
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("Total Links: " + links.size());
		
		WebElement drop = driver.findElement(By.id("dropdown-class-example"));
		dropDownsHandling(drop, "Option2");
		
		// Alert Handling
        //	driver.findElement(By.xpath("//input[@value='Alert']")).click();
        //	Alert a = driver.switchTo().alert();
        //	System.out.println("ALERT: "+a.getText());
        //	a.accept();
		alertsHandling("confirmbtn");
		
		// JavascriptExecutor to Scroll
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,900)");
		
		// Action Class Mouse Hover
		WebElement hover = driver.findElement(By.id("mousehover"));
		Actions a1 = new Actions(driver);
		a1.moveToElement(hover).build().perform();
		a1.click(driver.findElement(By.cssSelector("a[href='#top']"))).build().perform();
		
		// Switching Frames
		WebElement element = driver.findElement(By.id("courses-iframe"));
		switchingToFrame(driver, element);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter your name']"))).sendKeys("SaiDheeraj");
		driver.switchTo().defaultContent();
		
		// Locator CSS Selector
		driver.findElement(By.cssSelector("#openwindow")).click();
		switchingWindow(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='button float-left']"))).click();
		// actions.sendKeys(Keys.ALT,Keys.F4).build().perform();
		System.out.println(driver.getWindowHandles().size());
		switchingWindow(driver); 
		
		// String parent =switchToWindow(driver);
		// driver.switchTo().window(parent);
		
		// Locator LinkText
		driver.findElement(By.cssSelector("a[href='https://rahulshettyacademy.com/documents-request']")).click();
		Thread.sleep(5000);
				
		// Selenium Navigation Keyword
		driver.navigate().back();
				
		// Locator Partial LinkText
		driver.findElement(By.partialLinkText("Free Access to")).click();
		Thread.sleep(3000);
		driver.navigate().back();
				
		// Navigation Methods
		driver.navigate().refresh();
		Thread.sleep(3000);
		driver.navigate().back();
		Thread.sleep(3000);
		driver.navigate().forward();
	}

}
