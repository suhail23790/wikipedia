package org.wiki.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WikipediaPO {

	WebDriver driver = null;
	WebElement ele = null;
	List<WebElement> list = null;

	public WikipediaPO(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement externalLinkInContent() {
		ele = driver.findElement(By.xpath("//a[@href=\"#External_links\"]"));
		return ele;
	}

	public WebElement elementInPeriodicTbl(String elementSymbol) {
		ele = driver.findElement(By.xpath("//span[text()=\"" + elementSymbol + "\"]"));
		return ele;
	}
	
	public List<WebElement> listOfLinksInExternalLinks() {
		list = driver.findElements(By.xpath("//div[@id=\"mw-content-text\"]//ul/li/a[@class=\"external text\"]"));
		return list;
	}
	
	public WebElement infoTbl() {
		ele = driver.findElement(By.xpath("(//table[@class=\"infobox\"]//tr[3])[1]"));
		return ele;
	}
	
	public List<WebElement> listOfPDFLinksInReferences() {
		list = driver.findElements(By.xpath("(//div[@class=\"reflist columns references-column-width\"])[2]/ol/li//a"));
		return list;
	}
	
	public WebElement searchTxtBox() {
		ele = driver.findElement(By.xpath("//input[@id=\"searchInput\"]"));
		return ele;
	}
	public List<WebElement> suggectionInSearchBox() {
		list = driver.findElements(By.xpath("//div[@class=\"suggestions-results\"]/a"));
		return list;
	}
}
