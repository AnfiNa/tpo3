import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertTrue;

public class KonamiThemeFirefoxTest {
  private static final String BASE_URL = "https://xn----8sbbqoikx1fp7b1b.xn--p1ai/";
  private WebDriver driver;
  private JavascriptExecutor js;

  @Before
  public void setUp() {
    driver = new FirefoxDriver();
    js = (JavascriptExecutor) driver;
  }

  @After
  public void tearDown() {
    if (driver != null) driver.quit();
  }

  @Test
  public void themeChangesAfterKonamiCodeWithFourReloads() {
    driver.get(BASE_URL);
    for (int i = 0; i < 4; i++) driver.navigate().refresh();
    sleep(1500);

    WebElement body = driver.findElement(By.tagName("body"));
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

  private void sleep(long ms) {
    try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
  }
}

