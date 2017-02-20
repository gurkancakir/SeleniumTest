import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by gurkan on 20.02.2017.
 */
public class ApplicationTest {

    @Test
    public void iettTest() {
        System.setProperty("webdriver.gecko.driver","/usr/bin/geckodriver");
        WebDriver webDriver = new FirefoxDriver();
        webDriver.get("http://www.iett.istanbul/");

        WebElement searchbox = webDriver.findElement(By.id("select2-searchShortcutsLine-container"));
        searchbox.click();

        WebElement fillSearchBox = webDriver.findElement(By.className("select2-search__field"));
        fillSearchBox.sendKeys("14");

        WebDriverWait webDriverWait = new WebDriverWait(webDriver,5);
        webDriverWait.until(ExpectedConditions.invisibilityOfElementWithText(By.className("select2-results__option"),
                webDriver.findElement(By.className("select2-results__option")).getText()));

        List<WebElement> searchResults = webDriver.findElements(By.className("select2-results__option"));
        searchResults.get(0).click();

        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("button--success")));

        WebElement allInfos = webDriver.findElement(By.className("button--success"));
        allInfos.click();

        webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebElement stations = webDriver.findElement(By.cssSelector("a[href*='#LineStation']"));
        stations.click();

    }

    @Test
    public void mimcreaTest() {
        System.setProperty("webdriver.gecko.driver","/usr/bin/geckodriver");
        WebDriver webDriver = new FirefoxDriver();
        webDriver.get("http://mimcrea.com/#contact");

        WebElement nameSurname = webDriver.findElement(By.id("icon_prefixx"));
        WebElement email = webDriver.findElement(By.id("icon_emaill"));
        WebElement message = webDriver.findElement(By.id("icon_prefixx2"));


        nameSurname.sendKeys("gurkan cakir");
        email.sendKeys("test@test.com");
        message.sendKeys("test message");

        SearchContext searchContext = webDriver.findElement(By.cssSelector("form[action*='http://mimcrea.com/post-contact']"));
        WebElement sendButton = searchContext.findElement(By.cssSelector("button"));
        sendButton.click();

    }
}
