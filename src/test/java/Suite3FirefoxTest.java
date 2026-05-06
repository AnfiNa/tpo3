import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.Assert.assertTrue;

public class Suite3FirefoxTest {
  private static final String BASE_URL = "https://xn----8sbbqoikx1fp7b1b.xn--p1ai/";
  private WebDriver driver; private JavascriptExecutor js;
  @Before public void setUp(){ driver=new FirefoxDriver(); js=(JavascriptExecutor)driver; driver.manage().window().maximize(); }
  @After public void tearDown(){ if(driver!=null) driver.quit(); }
  @Test public void nestScrollsToTopAfterDoubleReload(){ open(); clickNavByIndex(1); assertNearTop(); }
  @Test public void sizesScrollsToTopAfterDoubleReload(){ open(); clickNavByIndex(2); assertNearTop(); }
  @Test public void takeItScrollsToTopAfterDoubleReload(){ open(); clickNavByIndex(3); assertNearTop(); }
  @Test public void tiktokAfterDoubleReload(){ open(); clickSocialByIndex(2); assertAndCloseAlert(); }
  @Test public void telegramAfterDoubleReload(){ open(); clickSocialByIndex(1); assertAndCloseAlert(); }
  private void open(){ driver.get(BASE_URL); driver.navigate().refresh(); driver.navigate().refresh(); sleep(700); }
  private void clickNavByIndex(int index){ WebElement link=driver.findElement(By.xpath("(//header//*[contains(@class,'nav')]//a[@href='#'])["+index+"]")); link.click(); sleep(700); }
  private void clickSocialByIndex(int index){ WebElement icon=driver.findElement(By.xpath("(//*[contains(@class,'social-icon')])["+index+"]")); icon.click(); }
  private void assertAndCloseAlert(){ Alert a=new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.alertIsPresent()); assertTrue(a.getText()!=null && !a.getText().isBlank()); a.accept(); }
  private void assertNearTop(){ Number y=(Number)js.executeScript("return window.scrollY;"); assertTrue(y.doubleValue()<=20); }
  private void sleep(long ms){ try{Thread.sleep(ms);}catch(InterruptedException e){Thread.currentThread().interrupt(); throw new RuntimeException(e);} }
}