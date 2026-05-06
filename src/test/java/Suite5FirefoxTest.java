import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.Assert.assertTrue;

public class Suite5FirefoxTest {
  private static final String BASE_URL = "https://xn----8sbbqoikx1fp7b1b.xn--p1ai/";
  private WebDriver driver; private JavascriptExecutor js;
  @Before public void setUp(){ driver=new FirefoxDriver(); js=(JavascriptExecutor)driver; driver.manage().window().maximize(); }
  @After public void tearDown(){ if(driver!=null) driver.quit(); }
  @Test public void startsAtTopAfterOpen(){ openPage(); assertNearTop(); }
  @Test public void navLegacyScrollsToLoreSection(){ openPage(); clickNavByHref("#lore"); assertSectionInView("lore"); }
  @Test public void navEmblemsScrollsToAchievementsSection(){ openPage(); clickNavByHref("#achievements"); assertSectionInView("achievements"); }
  @Test public void navGloryScrollsToLeaderboardSection(){ openPage(); clickNavByHref("#leaderboard"); assertSectionInView("leaderboard"); }
  @Test public void navTrialsScrollsToAllGamesAnchor(){ openPage(); clickNavByHref("#all-games"); assertElementVisibleInViewport(By.id("all-games")); }
  @Test public void navLogoReturnsToTop(){ openPage(); clickNavByHref("#all-games"); Number before=(Number)js.executeScript("return window.scrollY;"); clickNavByHref("#hero"); Number after=(Number)js.executeScript("return window.scrollY;"); assertTrue(after.doubleValue() < before.doubleValue()*0.5); assertElementVisibleInViewport(By.id("hero")); }
  private void openPage(){ driver.get(BASE_URL); driver.navigate().refresh(); driver.navigate().refresh(); driver.navigate().refresh(); driver.navigate().refresh(); sleep(1800); }
  private void clickNavByHref(String href){ WebElement a=driver.findElement(By.cssSelector("nav a[href='"+href+"']")); js.executeScript("arguments[0].click();", a); sleep(700); }
  private void assertNearTop(){ Number y=(Number)js.executeScript("return window.scrollY;"); assertTrue(y.doubleValue()<=20); }
  private void assertSectionInView(String id){ assertElementNearViewportTop(By.id(id),220); }
  private void assertElementNearViewportTop(By loc,double tol){ WebElement el=driver.findElement(loc); Number top=(Number)js.executeScript("return arguments[0].getBoundingClientRect().top;",el); assertTrue(Math.abs(top.doubleValue())<=tol); }
  private void assertElementVisibleInViewport(By loc){ WebElement el=driver.findElement(loc); Boolean v=(Boolean)js.executeScript("const r=arguments[0].getBoundingClientRect(); return r.top < window.innerHeight && r.bottom > 0;",el); assertTrue(Boolean.TRUE.equals(v)); }
  private void sleep(long ms){ try{Thread.sleep(ms);}catch(InterruptedException e){Thread.currentThread().interrupt(); throw new RuntimeException(e);} }
}
