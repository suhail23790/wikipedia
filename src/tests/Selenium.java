package tests;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wiki.pageObject.WikipediaPO;
import org.wiki.setDriver.SetWebDriver;

public class Selenium {
	
	WebDriver driver = null;
	SetWebDriver setWebDriver;
	WikipediaPO wikipediaPO;
	
	String url = "https://en.wikipedia.org/wiki/Selenium";
	
	/*
	 * Here 
	 * 1. we are setting up the browser and pageObject class
	 * 2. we are navigating to the url
	 * 3. Clicking on External Link in Contents
	 */
	@BeforeClass
	public void setUp() {
		setWebDriver = new SetWebDriver();
		driver = setWebDriver.setWebDriver();
		wikipediaPO = new WikipediaPO(driver);	
		driver.get(url);
		wikipediaPO.externalLinkInContent().click();
	}
	
	
	/*
	 * in this method we are verifying with all the 6 links in External Links session
	 * 1. we are getting total links in External Links session
	 * 2. we are clicking on each link and getting its url and validating it
	 * 3. we are navigating back to 1st page
	 * 4. again performing Step 2 and 3 for other remaining links
	 */
	@Test(priority = 1)
	public void verifyThatLinksInExternalLinksWork() {
		for (int i = 1; i < 7; i++) {
			wikipediaPO.listOfLinksInExternalLinks().get(i).click();
			if (i == 1){
				Assert.assertEquals(driver.getCurrentUrl(), "http://www.periodicvideos.com/videos/034.htm");
			}
			if (i == 2) {
				Assert.assertEquals(driver.getCurrentUrl(),
						"https://ods.od.nih.gov/factsheets/selenium-HealthProfessional/");
			}
			if (i == 3) {
				Assert.assertEquals(driver.getCurrentUrl(), "http://www.sas-centre.org/assays/trace-elements/selenium");
			}
			if (i == 4) {
				Assert.assertEquals(driver.getCurrentUrl(), "https://www.atsdr.cdc.gov/toxprofiles/tp92.html");
			}
			if (i == 5) {
				Assert.assertEquals(driver.getCurrentUrl(), "https://www.cdc.gov/niosh/npg/npgd0550.html");
			}
			if (i == 6) {
				Assert.assertEquals(driver.getCurrentUrl(), "https://elements.vanderkrogt.net/element.php?sym=Se");
			}
			driver.navigate().back();
		}
	}

	/*
	 * NOTE- here I have written a generic locator which can identify all the
	 * elements in periodic table, ww just need to pass the element symbol for
	 * oxygen we need to pass "O" Here we are doing following steps 1. click on
	 * Oxygen element in period table 2. we are getting the page title and
	 * validating whether it is Oxygen elements page or not
	 */
	@Test(priority = 2)
	public void clickOnOxygenLinkInPeriodTblAndValidateThatItIsFeaturedArticle() {
		wikipediaPO.elementInPeriodicTbl("O").click();
		Assert.assertEquals(driver.getTitle(), "Oxygen - Wikipedia");
	}
	
	@Test(priority=3)
	public void takeScreenshotOfRightHandBox() throws IOException, RasterFormatException {
		 // Take screen shot of whole web page
        File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
 
        // Calculate the width and height of the drop down element
        Point p = wikipediaPO.infoTbl().getLocation();
        int width = wikipediaPO.infoTbl().getSize().getWidth();
        int height = wikipediaPO.infoTbl().getSize().getHeight();
 
        // Create Rectangle of same width as of drop down Web Element
        Rectangle rect = new Rectangle(width, height);
 
        BufferedImage img = null;
        img = ImageIO.read(screenShot);
 
        //Crop Image of drop down web element from the screen shot
        BufferedImage dest = img.getSubimage(p.getX(), p.getY(), rect.width, rect.height);
 
        // write cropped image into File Object
        ImageIO.write(dest, "png", screenShot);
 
        //Copy Image into particular directory
        FileUtils.copyFile(screenShot,
                new File(System.getProperty("user.dir") + "//WebElementScreenShot.png"));
    }

	@Test(priority=4)
	public void countOfPDFLinksInReferences() {
		int i = 0;
		String txt = null;
		wikipediaPO.listOfPDFLinksInReferences().size();
		for (int j = 0; j < wikipediaPO.listOfPDFLinksInReferences().size(); j++) {
			txt = wikipediaPO.listOfPDFLinksInReferences().get(j).getAttribute("href");
			if (txt.contains(".pdf")) {
				i = i+1;
			}
		}
		System.out.println("i outside for loop - " + i);
		Assert.assertEquals(i, 9);
	}
	
	@Test(priority=5)
	public void verifySecondOptionIsPlutoniumInSearchBox() {
		String txt = null;
		wikipediaPO.searchTxtBox().clear();
		wikipediaPO.searchTxtBox().sendKeys("pluto");
		txt = wikipediaPO.suggectionInSearchBox().get(1).getText();
		Assert.assertEquals(txt, "Plutonium");
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
