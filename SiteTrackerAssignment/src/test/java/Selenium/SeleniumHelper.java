package Selenium;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.datatable.DataTable;
import junit.framework.Assert;

public class SeleniumHelper {
	
	WebDriver driver;
	
	public void goToGivenUrl(String url) {
		createDriver();
		openBrowser(url);
	}

	private void createDriver() {
			
			String projectPath = System.getProperty("user.dir");
			System.setProperty("webdriver.chrome.driver",projectPath + "/src/test/resources/Driver/chromedriver 3");
			driver = new ChromeDriver();
			
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	
			driver.manage().window().maximize();
	}

	private void openBrowser(String url) {
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	}
	
	private String[] getIdentifierAndLocator(String expression) {
		return expression.split("#");
	}

	private WebElement getObject(String element) {

		String identifier = getIdentifierAndLocator(element)[0];
		String locator = getIdentifierAndLocator(element)[1];

		if (identifier.equalsIgnoreCase("xpath")) {
			return driver.findElement(By.xpath(locator));
		} else if (identifier.equalsIgnoreCase("id")) {
			return driver.findElement(By.id(locator));
		} else if (identifier.equalsIgnoreCase("tagName")) {
			return driver.findElement(By.tagName(locator));
		} else if (identifier.equalsIgnoreCase("css")) {
			return driver.findElement(By.cssSelector(locator));
		}

		return null;
	}

	private List<WebElement> getObjects(String expression) {

		String identifier = getIdentifierAndLocator(expression)[0];
		String locator = getIdentifierAndLocator(expression)[1];

		if (identifier.equalsIgnoreCase("xpath")) {
			return driver.findElements(By.xpath(locator));
		} else if (identifier.equalsIgnoreCase("id")) {
			return driver.findElements(By.id(locator));
		} else if (identifier.equalsIgnoreCase("tagName")) {
			return driver.findElements(By.tagName(locator));
		} else if (identifier.equalsIgnoreCase("css")) {
			return driver.findElements(By.cssSelector(locator));
		}
		
		return null;

	}

	public void clickOnGivenTab(String element) throws InterruptedException {
		WebElement object = getObject(element);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", object);
		Thread.sleep(3000);
	}
	
	public void enterAndSelectTheDataTable(String element, String selectedElement, String data) {
		WebElement quickFindSearchBox = getObject(element);
		quickFindSearchBox.sendKeys(data);
		List<WebElement> elements = getObjects(selectedElement);
		for (WebElement obj : elements) {
			if (obj.getText().equalsIgnoreCase(data)) {
				obj.click();
				break;
			}
		}
	}
	
	public void selectFromExampleDropdown(String element, String selectedElement, String data) {
		WebElement exampleTab = getObject(element);
		exampleTab.click();
		driver.findElement(By.xpath("//button[@name = 'example']")).click();
		List<WebElement> dropdownValues = getObjects(selectedElement);
		for (WebElement object : dropdownValues) {
			if (object.getText().trim().equalsIgnoreCase(data.trim())) {
				object.click();
				break;
			}
		}

	}
	
	public void updateTableValuesOfPreview(String element, Integer row, DataTable data) {
		Map<String, String> map = data.asMaps().get(0);
		WebElement td = null;
		WebElement object = null;
		String xpath = "//tr[" + row + "]";
		int size = driver.findElements(By.xpath("//iframe")).size();
		try {
			driver.switchTo().frame(0);
			object = getObject(element).findElement(By.xpath(xpath));
		} catch (Exception e) {
			driver.switchTo().defaultContent();
			driver.switchTo().frame(1);
			object = getObject(element).findElement(By.xpath(xpath));
		}

		List<WebElement> elements = object.findElements(By.tagName("td"));
		WebElement name = object.findElement(By.tagName("th"));
		WebElement checkbox = elements.get(1);
		checkbox.click();
		for (int i = 2; i <= elements.size(); i++) {
			td = elements.get(i);
			for (String key : map.keySet()) {
				if (td.getAttribute("data-label").equals(key)) {
					td.findElement(By.tagName("Button")).click();
					td.findElement(By.tagName("input")).sendKeys(map.get(key));
					break;
				}

			}

		}

	}

	public void validateDataOfthePreviewTable(String element, DataTable data, Integer row) {
		String xpath = "//tr[" + row + "]";

		WebElement object = getObject(element).findElement(By.xpath(xpath));

		List<String> list = new ArrayList();

		String name = object.findElement(By.tagName("th")).getText();
		list.add(name);
		List<WebElement> elements = object.findElements(By.tagName("td"));
		for (int i = 0; i < elements.size(); i++) {
			if (elements.get(i).getText() != null) {
				list.add(elements.get(i).getText());
			}
		}
		List<String> actual = data.asList();

		Assert.assertEquals(list, actual);

	}


}
