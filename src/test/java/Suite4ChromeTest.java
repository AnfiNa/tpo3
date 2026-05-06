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

public class Suite4ChromeTest {
  private static final String BASE_URL = "https://xn----8sbbqoikx1fp7b1b.xn--p1ai/";
  private WebDriver driver; private JavascriptExecutor js;
  @Before public void setUp(){ ChromeOptions o=new ChromeOptions(); o.addArguments("--disable-gpu","--no-sandbox","--disable-dev-shm-usage"); driver=new ChromeDriver(o); js=(JavascriptExecutor)driver; driver.manage().window().maximize(); }
  @After public void tearDown(){ if(driver!=null) driver.quit(); }
  @Test public void navMainScrollsTop(){ open(); clickNavByIndex(1); assertNearTop(); }
  @Test public void navProductScrollsTop(){ open(); clickNavByIndex(2); assertNearTop(); }
  @Test public void navFarmScrollsTop(){ open(); clickNavByIndex(3); assertNearTop(); }
  @Test public void navDeliveryScrollsTop(){ open(); clickNavByIndex(4); assertNearTop(); }
  @Test public void getButtonScrollsTop(){ open(); clickHeroButtonByIndex(1); assertNearTop(); }
  @Test public void orderButtonScrollsTop(){ open(); clickHeroButtonByIndex(2); assertNearTop(); }
  private void open(){ driver.get(BASE_URL); driver.navigate().refresh(); driver.navigate().refresh(); driver.navigate().refresh(); sleep(900); js.executeScript("window.scrollTo(0, document.body.scrollHeight);"); sleep(300); }
  private void clickNavByIndex(int index){ WebElement e=driver.findElement(By.xpath("(//nav//a[@href='#'])["+index+"]")); js.executeScript("arguments[0].click();", e); sleep(300); }
  private void clickHeroButtonByIndex(int index){ WebElement e=driver.findElement(By.xpath("(//a[contains(@class,'btn') and @href='#'])["+index+"]")); js.executeScript("arguments[0].click();", e); sleep(300); }
  private void assertNearTop(){ Number y=(Number)js.executeScript("return window.scrollY;"); assertTrue(y.doubleValue()<=20); }
  private void sleep(long ms){ try{Thread.sleep(ms);}catch(InterruptedException e){Thread.currentThread().interrupt(); throw new RuntimeException(e);} }
}

