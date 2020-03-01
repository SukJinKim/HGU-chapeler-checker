package hgu.alinew;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import hgu.alinew.model.ChapelerInfo;
import hgu.alinew.util.Utils;

public class ChapelerChecker {

	private static final String URL = "https://www.youtube.com/watch?v=vW6dzGwpSTM";
	private static final String CHROME_DRIVER_LOCATION = "/Users/kimseokjin/Downloads/chromedriver";
	private static final String PROFESSOR_NAME = "아이들";

	public static void main(String[] args) throws InterruptedException, IOException {

		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_LOCATION);

		WebDriver driver = new ChromeDriver();

		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Launch the application
		driver.get(URL);

		long lastPageHeight = (long) js.executeScript("return document.documentElement.scrollHeight");

		while (true) {
			// This will scroll the web page till end.
			js.executeScript("document.body.scrollTop = document.body.scrollHeight;");
			js.executeScript("document.documentElement.scrollTop = document.documentElement.scrollHeight;");
			Thread.sleep(3000);

			long newPageHeight = (long) js.executeScript("return document.documentElement.scrollHeight");

			if (newPageHeight == lastPageHeight)
				break;

			lastPageHeight = newPageHeight;
		}

		List<WebElement> elems = driver.findElements(By.cssSelector("yt-formatted-string#content-text"));

		List<ChapelerInfo> chaplersList = new ArrayList<>();

		for (WebElement elem : elems) {
			String comment = elem.getText();

			// Check comment whether matches format or not
			boolean isCorrectFormat = Pattern.compile("(\\s*" + PROFESSOR_NAME + ".*\\/\\s*\\d{8}\\s*\\/.*\\/.*)")
					.matcher(comment).matches();

			if (!isCorrectFormat)
				continue;

			String[] chaplerInfoArr = comment.split("\\/");

			ChapelerInfo aChapelerInfo = new ChapelerInfo(chaplerInfoArr[0].trim(), chaplerInfoArr[1].trim(),chaplerInfoArr[2].trim());

			chaplersList.add(aChapelerInfo);
		}

		// write excel file
		Utils.storeResults(chaplersList, PROFESSOR_NAME);

		driver.close();
	}

}
