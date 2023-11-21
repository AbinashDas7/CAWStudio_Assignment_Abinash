package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshots {
	
	    public static void addStepWithScreenshotInReport(WebDriver driver, String stepDescription) {
	        try {
	            String screenshotBase64 = captureScreenshot(driver);

	            // Log the step with screenshot
	            // This is where we typically integrate with your reporting tool
	            System.out.println("Step: " + stepDescription);
	            System.out.println("Screenshot: " + screenshotBase64);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

    private static String captureScreenshot(WebDriver driver) {
        try {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
