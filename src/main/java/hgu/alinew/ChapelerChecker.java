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
	private String youtubeURL;
	private String chromeDriverPath;
	private String teamName;

	public ChapelerChecker(String youtubeURL, String chromeDriverPath, String teamName) {
		super();
		this.youtubeURL = youtubeURL;
		this.chromeDriverPath = chromeDriverPath;
		this.teamName = teamName;
	}

	public void run() throws IOException, InterruptedException {
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);

		WebDriver driver = new ChromeDriver();

		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Launch the application
		driver.get(youtubeURL);

		long lastPageHeight = (long) js.executeScript("return document.documentElement.scrollHeight");

		// Count the number of scroll down to check the range of the number of comments
		int numScrollDown = 0;

		while (true) {
			// This will scroll the web page till end.
			js.executeScript("document.body.scrollTop = document.body.scrollHeight;");
			js.executeScript("document.documentElement.scrollTop = document.documentElement.scrollHeight;");
			Thread.sleep(5000);

			numScrollDown++;

			long newPageHeight = (long) js.executeScript("return document.documentElement.scrollHeight");

			if (newPageHeight == lastPageHeight)
				break;

			lastPageHeight = newPageHeight;
		}

		// print out range of the number of comments to check whether network was fine during execution
		Utils.printRangeOfTheNumberOfComments(numScrollDown);

		List<WebElement> elems = driver.findElements(By.cssSelector("yt-formatted-string#content-text"));

		List<ChapelerInfo> chaplersList = new ArrayList<>();

		for (WebElement elem : elems) {
			String comment = elem.getText();

			// Check comment whether matches format or not
			boolean isCorrectFormat = Pattern.compile("(\\s*" + teamName + ".*\\/\\s*\\d{8}\\s*\\/.*)").matcher(comment).matches();

			if (!isCorrectFormat)
				continue;

			String[] chaplerInfoArr = comment.split("\\/");

			ChapelerInfo aChapelerInfo = new ChapelerInfo(chaplerInfoArr[0].trim(), chaplerInfoArr[1].trim(),chaplerInfoArr[2].trim());

			chaplersList.add(aChapelerInfo);
		}

		// write excel file
		Utils.storeResults(chaplersList, teamName);

		driver.close();
	}
}
