package com.appiumtest;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AppiumDemoApp {

	public static AppiumDriver<MobileElement> driver;

	public static void main(String[] args) {

	}

	@Test
	public void BasicFunctionality() {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "android");
		caps.setCapability("latformVersion", 11);
		caps.setCapability("deviceName", "Pixe1");
		caps.setCapability("automationName", "UIAutomator2");
		// caps.setCapability("udid", "emulator-5554");
		caps.setCapability("app", "/Users/petergaevoy/Documents/DemoApps/e-com.ver.1.apk");
		caps.setCapability("appPackage", "com.justai.aimybox.vtb.demo");
		caps.setCapability("appActivity", "com.justai.aimybox.assistant.ui.MainActivity");
		caps.setCapability("noReset", true);

		try {

			// Check for voices changes

			// Setup
			URL url = new URL("http://127.0.0.1:4723/wd/hub/");

			driver = new AppiumDriver<MobileElement>(url, caps);

			WebDriverWait wait = new WebDriverWait(driver, 20);

			// click on profile settings button
			WebElement profileButton = driver.findElementByXPath(
					"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]/android.view.View[4]");
			profileButton.click();

			// wait for profile to load
			wait.until(
					ExpectedConditions.presenceOfElementLocated(By.id("com.justai.aimybox.vtb.demo:id/tvHeaderTitle")));

			// changing assistants voice to Valeriy
			WebElement voiceOfValeriyButton = driver.findElementById("com.justai.aimybox.vtb.demo:id/rbValeriy");
			voiceOfValeriyButton.click();

			// Test if Valeriy is checked
			Object valeriyIsChecked = voiceOfValeriyButton.getAttribute("checked").equals("true");

			if ((boolean) (valeriyIsChecked = true)) {

				assertTrue(true);
			}

			else {
				assertTrue(false);
			}

			// changing assistants voice to Tanya
			WebElement voiceOfTanya = driver.findElementById("com.justai.aimybox.vtb.demo:id/rbTanya");
			voiceOfTanya.click();

			// Test if Tanya is checked
			Object tanyaIsChecked = voiceOfTanya.getAttribute("checked").equals("true");

			if ((boolean) (tanyaIsChecked = true)) {

				assertTrue(true);
			}

			else {
				assertTrue(false);
			}

			// back to home
			WebElement backButton = driver.findElementById("com.justai.aimybox.vtb.demo:id/ivBack");
			backButton.click();

			// wait for homePage to load
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View")));

			// Check for dialog to begin
			WebElement toGpDialog = driver.findElementByXPath(
					"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View");
			toGpDialog.click();

			// wait for permission to show
			wait.until(ExpectedConditions
					.presenceOfElementLocated(By.id("com.android.permissioncontroller:id/permission_message")));

			// deny mic use
			WebElement denyMicUse = driver
					.findElementById("com.android.permissioncontroller:id/permission_allow_one_time_button");
			denyMicUse.click();

			// wait dialog to load
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.justai.aimybox.vtb.demo:id/hello_text")));

			// opening dialog from bot
			String botMessage1 = driver.findElementById("com.justai.aimybox.vtb.demo:id/aimybox_response_textView")
					.getAttribute("text");
			String bMessage1 = ("Я ваш голосовой помощник, София. Со мной быстро и просто сделать список покупок. Спросите, что я умею, чтобы лучше познакомиться.");
			if (botMessage1.matches(bMessage1)) {
				assertTrue(true);
			} else {
				assertTrue(false);
			}

			// open keyboard
			WebElement openKeyboard = driver.findElementById("com.justai.aimybox.vtb.demo:id/ivKeyboard");
			openKeyboard.click();

			// send message
			WebElement textBox = driver.findElementById("com.justai.aimybox.vtb.demo:id/assistant_edit_text");
			textBox.sendKeys("Привет!");

			// press send
			WebElement sendButton = driver.findElementById("com.justai.aimybox.vtb.demo:id/fabMicroKeyboard");
			sendButton.click();

			// presence of user responce
			String userMessage1 = driver.findElementById("com.justai.aimybox.vtb.demo:id/aimybox_recognition_textView")
					.getAttribute("text");
			String uMessage1 = ("Привет!");
			if (userMessage1.matches(uMessage1)) {
				assertTrue(true);
			} else {
				assertTrue(false);
			}

			// bot responce to user message
			String botMessage2 = driver.findElementByXPath(
					"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[3]/android.widget.TextView")
					.getAttribute("text");
			String bMessage2 = ("Вы что-то сказали.");
			if (botMessage2.matches(bMessage2)) {
				assertTrue(true);
			} else {
				assertTrue(false);
			}

			// checking functionality of a button with message (hint request)
			WebElement hintButton1 = driver.findElementByXPath(
					"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.TextView");
			hintButton1.click();

			// Check if hintButton sent request and displayed in chat
			String hintMessage1 = driver.findElementByXPath(
					"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[4]/android.widget.TextView")
					.getAttribute("text");
			String hMessage1 = ("Что ты умеешь?");
			System.out.println(hintMessage1);

			if (hintMessage1.contains(hMessage1)) {
				assertTrue(true);
			} else {
				assertTrue(false);
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
