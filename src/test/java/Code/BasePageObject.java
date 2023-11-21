package Code;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import utils.Screenshots;

public class BasePageObject {
 protected WebDriver driver;
 protected ExtentTest extentTest;
 
 protected BasePageObject(WebDriver webDriver) {
	 this.driver = webDriver;
	 this.extentTest = extentTest;
	 PageFactory.initElements(driver, this);
	 
 }
 protected void testStepPass(String elementDescription) {
	 try {
         String stepDescription = "Succesfully executed '" + elementDescription + "'";
         Screenshots.addStepWithScreenshotInReport(driver, stepDescription);
         extentTest.pass(stepDescription, MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot()).build());
     } catch (Exception e) {
         String stepDescription = "Failed to execute '" + elementDescription + "'";
         Screenshots.addStepWithScreenshotInReport(driver, stepDescription);
     }
 }
 protected void clickElement(final String locator, String elementDescription) throws IOException {
//	 try {
         driver.findElement(By.xpath(locator)).click();
         System.out.println("clicked");
         String stepDescription = "Clicked on '" + elementDescription + "'";
//         Screenshots.addStepWithScreenshotInReport(driver, stepDescription);
//         extentTest.pass(stepDescription, MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot()).build());
//     } catch (Exception e) {
//         String stepDescription = "Failed to click on '" + elementDescription + "'";
//         System.out.println();
//         Screenshots.addStepWithScreenshotInReport(driver, stepDescription);
//         extentTest.fail(stepDescription, MediaEntityBuilder.createScreenCaptureFromBase64String(captureScreenshot()).build());
//     }
 }

 private String captureScreenshot() {
	    try {
	        TakesScreenshot ts = (TakesScreenshot) driver;
	        return ts.getScreenshotAs(OutputType.BASE64);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }}
}
