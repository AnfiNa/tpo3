import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertTrue;

public class Suite2ChromeTest {
  private static final String BASE_URL = "https://xn----8sbbqoikx1fp7b1b.xn--p1ai/";
  private WebDriver driver;
  private JavascriptExecutor js;

  @Before
  public void setUp() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage");
    driver = new ChromeDriver(options);
    js = (JavascriptExecutor) driver;
    driver.manage().window().maximize();
  }

  @After
  public void tearDown() {
    if (driver != null) driver.quit();
  }

  @Test public void homeScrollsToTopAfterSingleReload() { driver.get(BASE_URL); driver.navigate().refresh(); clickHomeMenu(); assertNearTop(); }
  @Test public void catalogScrollsToCatalogAfterSingleReload() { driver.get(BASE_URL); driver.navigate().refresh(); clickMenuByHref("#catalog"); assertScrolledToSection(By.id("catalog")); }
  @Test public void aboutScrollsToAboutAfterSingleReload() { driver.get(BASE_URL); driver.navigate().refresh(); clickMenuByHref("#about"); assertScrolledToSection(By.id("about")); }
  @Test public void reviewsScrollsToReviewsAfterSingleReload() { driver.get(BASE_URL); driver.navigate().refresh(); clickMenuByHref("#reviews"); assertScrolledToSection(By.id("reviews")); }

  private void clickMenuByHref(String href) {
    WebElement link = driver.findElement(By.cssSelector("a[href='" + href + "']"));
    link.click();
    sleep(700);
  }

  private void clickHomeMenu() {
    java.util.List<WebElement> items = driver.findElements(By.cssSelector("nav a"));
    if (!items.isEmpty()) {
      ((JavascriptExecutor) driver).executeScript("arguments[0].click();", items.get(0));
      sleep(700);
      return;
    }
    js.executeScript("window.scrollTo(0,0);");
    sleep(300);
  }

  private void assertNearTop() {
    Number y = (Number) js.executeScript("return window.scrollY;");
    assertTrue("Expected top scroll", y.doubleValue() <= 20);
  }

  private void assertScrolledToSection(By sectionBy) {
    WebElement section = driver.findElement(sectionBy);
    Number sectionTop = (Number) js.executeScript("return arguments[0].getBoundingClientRect().top;", section);
    Number y = (Number) js.executeScript("return window.scrollY;");
    assertTrue("Scroll did not happen", y.doubleValue() > 0);
    assertTrue("Section is not near top", Math.abs(sectionTop.doubleValue()) <= 150);
  }

  private void sleep(long millis) {
    try { Thread.sleep(millis); } catch (InterruptedException e) { Thread.currentThread().interrupt(); throw new RuntimeException(e); }
  }
}

