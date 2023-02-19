package assignPages;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assign1Page {
	static WebDriver driver;

	public Assign1Page() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//nav[@class='css-7stz2q css-1aai96l']/descendant::button/div")
	private List<WebElement> headings;
	@FindBy(xpath = "//nav[@class='css-79txt3']/a[text()='Docs']")
	private WebElement clickDocs;

	@FindBy(xpath = "(//a[@class='css-hobwqm'])[2]")
	private WebElement tutorialTab;
	@FindBy(xpath = "//div[@class='css-124oy3v']/descendant::h2")
	private List<WebElement> scrollHeading;
	@FindBy(xpath = "//li[@class='css-atv6j6']/a/span")
	private WebElement highLight;

	public WebElement getHighLight() {
		return highLight;
	}

	public WebElement getClickDocs() {
		return clickDocs;
	}

	public List<WebElement> getScrollHeading() {
		return scrollHeading;
	}

	public List<WebElement> getHeadings() {
		return headings;
	}
	public WebElement getTutorialTab() {
		return tutorialTab;
	}

	public static void launch() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.navigate().to("https://reactjs.org/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	public void getTextDynamically() throws InterruptedException, IOException {
		getClickDocs().click();
		for (int i = 0; i < headings.size(); i++) {
			WebElement heading = headings.get(i);
			String headingT = heading.getText();
			if (i > 0 && i <= headings.size()) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView(true);", heading);

				js.executeScript("arguments[0].click();", heading);
			}
			System.out.println(headingT);
			String xpath = "//nav[@class='css-7stz2q css-1aai96l']/descendant::button[" + (i + 1)
					+ "]/following-sibling::ul/li/a";
			List<WebElement> options = driver.findElements(By.xpath(xpath));
			for (int j = 0; j < options.size(); j++) {
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				String text = options.get(j).getText();
				System.out.println(text);

			}

		}
		driver.quit();

	}

	public void VerifyScroll() throws InterruptedException {
		tutorialTab.click();
		List<WebElement> scrollHeading2 = getScrollHeading();

		int size = scrollHeading2.size();
		for (int i = 0; i < size; i++) {
			WebElement webElement = scrollHeading2.get(i);
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("arguments[0].scrollIntoView(true);", webElement);
			Thread.sleep(2000);

		}
		for (int i = size - 1; i >= 0; i--) {
			WebElement webElement = scrollHeading2.get(i);
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("arguments[0].scrollIntoView(true);", webElement);
			Thread.sleep(2000);
		}

	}

	public void VerifyScrollHighlight() throws InterruptedException {
		List<WebElement> scrollHeading2 = getScrollHeading();
		for (int i = 0; i < 4; i++) {
			WebElement webElement = scrollHeading2.get(i);
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("arguments[0].scrollIntoView(true);", webElement);
			String cssValue = getHighLight().getCssValue("padding");
			String cssValueMargin = getHighLight().getCssValue("margin");
			Assert.assertEquals("Verify Css value Highlight padding", cssValue, "0px 0px 0px 16px");
			Assert.assertEquals("Verify Css value Highlight Margin", cssValueMargin, "-3px 0px 0px");
			System.out.println("Css value Highlight padding==" + cssValue);
			System.out.println("Css value Highlight Margin==" + cssValueMargin);
			Thread.sleep(2000);
		}
		driver.quit();

	}

}
