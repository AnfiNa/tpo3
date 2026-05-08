import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertTrue;

public class KonamiThemeChromeTest {
  private static final String BASE_URL = "https://xn----8sbbqoikx1fp7b1b.xn--p1ai/";
  private WebDriver driver;
  private JavascriptExecutor js;

  @Before
  public void setUp() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage");
    driver = new ChromeDriver(options);
    js = (JavascriptExecutor) driver;
  }

  @After
  public void tearDown() {
    if (driver != null) driver.quit();
  }

  @Test
  public void themeChangesAfterKonamiCodeWithFourReloads() {
    driver.get(BASE_URL);
    for (int i = 0; i < 4; i++) safeRefresh();
    sleep(1500);

    WebElement body = driver.findElement(By.xpath("//body"));
    body.click();
    body.sendKeys(
      Keys.ARROW_UP, Keys.ARROW_UP, Keys.ARROW_DOWN, Keys.ARROW_DOWN,
      Keys.ARROW_LEFT, Keys.ARROW_RIGHT, Keys.ARROW_LEFT, Keys.ARROW_RIGHT,
      "b", "a"
    );
    sleep(300);

    String filter = (String) js.executeScript("return document.body.style.filter;");
    assertTrue("Expected hue-rotate theme effect", filter != null && filter.contains("hue-rotate"));
  }

  private void safeRefresh() {
    try {
      driver.navigate().refresh();
    } catch (NoSuchWindowException e) {
      driver.get(BASE_URL);
    }
  }

  private void sleep(long ms) {
    try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
  }
}



