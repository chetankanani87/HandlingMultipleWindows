package multipleWindow;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.BrowserDriverUtility;

import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HandlingMultipleWindow {
	static WebDriver dr;
	static WebElement ele;
	
	@BeforeMethod
	public static void startBrowser() {
		dr = BrowserDriverUtility.InvokeBrowser("webdriver.chrome.driver", "./webdrivers/chromedriver.exe",
				"http://www.naukri.com/");
	}
	@Test
	public void TestPopUp() throws InterruptedException {

		// It will return the parent window name as a String
		String parent = dr.getWindowHandle();

		// This will return the number of windows opened by Webdriver and will return Set of St//rings
		Set<String> s1 = dr.getWindowHandles();

		// Now we will iterate using Iterator
		Iterator<String> I1 = s1.iterator();

		while (I1.hasNext()) {
			String child_window = I1.next();

			// Here we will compare if parent window is not equal to child window then we will close
			if (!parent.equals(child_window)) {
				dr.switchTo().window(child_window);
				System.out.println(dr.switchTo().window(child_window).getTitle());
				dr.close();
			}
		}
		// once all pop up closed now switch to parent window
		dr.switchTo().window(parent);
	}
	
	@AfterMethod
	public void tearDown() {
		dr.close();
	}
}