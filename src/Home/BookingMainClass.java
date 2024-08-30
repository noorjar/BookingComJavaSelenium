package Home;

import java.security.PublicKey;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import net.bytebuddy.build.Plugin.Factory.UsingReflection.Priority;

public class BookingMainClass extends BookingParametrsClass {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	@BeforeTest
	public void mysetup() {
		GeneralSetUp();
		closePopupIfPresent();
	}
//-------------------------------------------------------------------------------------------------------------------

	@Test(priority = 1,enabled = false)
	public void VerifyDefaultLanguage() {
		closePopupIfPresent();
		boolean IslangEn = driver.findElement(By.tagName("html")).getAttribute("lang").contains("en");
		Assert.assertEquals(IslangEn, ExpectedLang);
		
	}
//-------------------------------------------------------------------------------------------------------------------
	@Test(priority = 2,enabled = false)
	public void VerifyDefaultCurrency() {
		closePopupIfPresent();
		WebElement currencyPickerButton = driver.findElement(By.cssSelector("button[data-testid='header-currency-picker-trigger']"));
		String actualCurrency  = currencyPickerButton.getAttribute("aria-label");
		Assert.assertEquals(actualCurrency , expectedCurrency);
		
	}
//-------------------------------------------------------------------------------------------------------------------
	@Test(priority = 3,enabled = false)
	public void FlightsTabIsNotSelected() {
		closePopupIfPresent();
		WebElement ULContainerNavList = driver
				.findElement(By.cssSelector("nav[aria-label='What are you looking for?'] ul[class='d1b2041e44']"));
		List<WebElement> listItems = ULContainerNavList.findElements(By.tagName("li"));

		for (WebElement listItem : listItems) {
			// Check if the list item contains the text "Flights"
			if (listItem.getText().contains("Flights")) {
				// Check if the list item does not have the 'f62c02908f' class
				if (!listItem.getAttribute("class").contains("f62c02908f")) {
					flightsTabNotSelected = true;
					break; 
				}
			}
		}
		Assert.assertEquals(flightsTabNotSelected, true);
		
	}
//-------------------------------------------------------------------------------------------------------------------
	@Test(priority = 4,enabled = false)
	public void VerifyCustomerSupportlinkinHeader() {
		closePopupIfPresent();
		WebElement customerSupportLink = driver.findElement(By.cssSelector("a[aria-label='Customer support']"));
		String actualCustomerSupportLink =  customerSupportLink.getAttribute("aria-label");
		Assert.assertEquals(actualCustomerSupportLink, expectedCustomerSupportLink );
		
	}
//-------------------------------------------------------------------------------------------------------------------
	@Test(priority = 5,enabled = false)
	public void ChangeLanguage() {
		closePopupIfPresent();
		WebElement languagePickerButton = driver. findElement(By.cssSelector("button[data-testid='header-language-picker-trigger']"));
		languagePickerButton.click();
		WebElement languageOptionsContainer =driver.findElement(By.xpath("//ul[@style='--bui_stack_spaced_gap--s: 4;']"));
		List<WebElement> languageList  = languageOptionsContainer.findElements(By.tagName("li"));
		int randomNumbertoSelectLanguage =rand.nextInt(languageList.size());
		languageList.get(randomNumbertoSelectLanguage).click();
		closePopupIfPresent();
		}
//-------------------------------------------------------------------------------------------------------------------
	@Test(priority = 7,enabled = true)
	public void SearchforHotels() throws InterruptedException {
	closePopupIfPresent();
	WebElement HotelsTab = driver.findElement(By.cssSelector(".a7dc8ec444"));
	HotelsTab.click();
	
	WebElement searchInput  = driver.findElement(By.xpath("//input[@id=':rh:']"));
	searchInput .clear();
	searchInput .sendKeys("New York");
	
	Thread.sleep(5000);


	WebElement suggestionsContainer = driver.findElement(By.id("autocomplete-results"));
	List<WebElement> suggestionList  = suggestionsContainer .findElements(By.tagName("li"));
	
	WebElement thirdSuggestion  =  suggestionList .get(2);
	thirdSuggestion .click();

		}
//-------------------------------------------------------------------------------------------------------------------
	@Test(priority = 6,enabled = true)
	public void CheckInCheckOut() throws InterruptedException {
		closePopupIfPresent();
		
		driver.findElement(By.className("c3953d2910")).click();
		
		
		while (true) {
			String month = driver.findElement(By.xpath("(//h3[@aria-live = 'polite'])[1]")).getText();
			if (month.equals(myMonth)){
				break;
				
			}
			else {
				driver.findElement(By.cssSelector("button[aria-label='Next month']")).click();
			}
			WebElement calendarTable = driver.findElement(By.xpath("(//table[@class = 'c0d46ba7c8'])[1]"));
			List<WebElement> calendarRows  = calendarTable .findElements(By.tagName("td"));
			for(WebElement element:calendarRows) {
				String date = element.getText();
				if (date.equals(myDate)) {
					element.click();
					break;
				}
			}
		}
		/////////////////////////////////////////////////////////////////////////////////////////////
		while (true) {
			String month1 = driver.findElement(By.xpath("(//h3[@aria-live = 'polite'])[2]")).getText();
			if (month1.equals(myMonth1)){
				break;
				
			}
			else {
				driver.findElement(By.cssSelector("button[aria-label='Next month']")).click();
			}
			WebElement calendarTable1 = driver.findElement(By.xpath("(//table[@class = 'c0d46ba7c8'])[2]"));
			List<WebElement> calendarRows1  = calendarTable1 .findElements(By.tagName("td"));
			for(WebElement element1:calendarRows1) {
				String date1 = element1.getText();
				if (date1.equals(myDate1)) {
					element1.click();
					break;
				}
			}
		}
		

	}
//-------------------------------------------------------------------------------------------------------------------
	@Test (priority = 8,enabled = true)
	public void	 SelectRooms() throws InterruptedException {
		closePopupIfPresent();
		WebElement RoomsTab = driver.findElement(By.className("a6391e882c"));
		RoomsTab.click();
	
		
		
		while (true) {
			String Adults = driver.findElement(By.xpath("(//span[@class = 'fb7047f72a'])[1]")).getText();
			if (Adults.equals(myAdults)){
				break;
				
			}
			else {
				driver.findElement(By.xpath("(//button[@tabindex = '-1'])[2]")).click();
			}
		}
		while (true) {
			String Rooms = driver.findElement(By.xpath("(//span[@class = 'fb7047f72a'])[3]")).getText();
			if (Rooms.equals(myRooms)){
				break;
				
			}
			else {
				driver.findElement(By.xpath("(//button[@tabindex = '-1'])[6]")).click();
			}
		}
		while (true) {
			String Child = driver.findElement(By.xpath("(//span[@class = 'fb7047f72a'])[2]")).getText();
			if (Child.equals(myChildren)){
				break;
				
			}
			else {
				driver.findElement(By.xpath("(//button[@tabindex = '-1'])[4]")).click();
			}
			
		}
		WebElement SelectTag = driver.findElement(By.className("ef7e348457"));
		Select select = new Select(SelectTag);
		
		int totalOptions = select.getOptions().size();
		int randomNumber = rand.nextInt(totalOptions - 1) + 1;
		select.selectByIndex(randomNumber);
}
//-------------------------------------------------------------------------------------------------------------------
	
	@Test(priority = 10,enabled = true)
	public void Search () {
	WebElement SearchButton =driver.findElement(By.cssSelector("button[type='submit']"));
	SearchButton.click();
}
//-------------------------------------------------------------------------------------------------------------------
	
	
}