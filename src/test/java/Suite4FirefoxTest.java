import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.Assert.assertTrue;

public class Suite4FirefoxTest {
  private static final String BASE_URL = "https://xn----8sbbqoikx1fp7b1b.xn--p1ai/";
  private WebDriver driver; private JavascriptExecutor js;
  @Before public void setUp(){ driver=new FirefoxDriver(); js=(JavascriptExecutor)driver; driver.manage().window().maximize(); }
  @After public void tearDown(){ if(driver!=null) driver.quit(); }
  @Test public void navMainScrollsTop(){ open(); click("ГЛАВНАЯ"); assertNearTop(); }
  @Test public void navProductScrollsTop(){ open(); click("ПРОДУКТ"); assertNearTop(); }
  @Test public void navFarmScrollsTop(){ open(); click("О ФЕРМЕ"); assertNearTop(); }
  @Test public void navDeliveryScrollsTop(){ open(); click("ДОСТАВКА"); assertNearTop(); }
  @Test public void getButtonScrollsTop(){ open(); click("ПОЛУЧИТЬ"); assertNearTop(); }
  @Test public void orderButtonScrollsTop(){ open(); click("ЗАКАЗАТЬ ГИГАНТОВ"); assertNearTop(); }
  private void open(){ driver.get(BASE_URL); driver.navigate().refresh(); driver.navigate().refresh(); driver.navigate().refresh(); sleep(900); js.executeScript("window.scrollTo(0, document.body.scrollHeight);"); sleep(300); }
  private void click(String t){ WebElement e=driver.findElement(By.xpath("//*[self::a or self::button][contains(normalize-space(.),'"+t+"')]")); js.executeScript("arguments[0].click();", e); sleep(300); }
  private void assertNearTop(){ Number y=(Number)js.executeScript("return window.scrollY;"); assertTrue(y.doubleValue()<=20); }
  private void sleep(long ms){ try{Thread.sleep(ms);}catch(InterruptedException e){Thread.currentThread().interrupt(); throw new RuntimeException(e);} }
}
