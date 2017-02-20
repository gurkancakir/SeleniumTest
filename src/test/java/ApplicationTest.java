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
        fillSearchBox.sendKeys("141");

        WebDriverWait webDriverWait = new WebDriverWait(webDriver,15);
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

        message.sendKeys("test message");
        nameSurname.sendKeys("gurkan cakir");
        email.sendKeys("test@test.com");

        SearchContext searchContext = webDriver.findElement(By.cssSelector("form[action*='http://mimcrea.com/post-contact']"));
        WebElement sendButton = searchContext.findElement(By.cssSelector("button"));
        sendButton.click();

    }

    @Test
    public void obiletTest() {
        System.setProperty("webdriver.gecko.driver","/usr/bin/geckodriver");
        WebDriver webDriver = new FirefoxDriver();
        webDriver.get("https://www.obilet.com/");

        // from
        SearchContext fromField = webDriver.findElement(By.className("from"));
        WebElement from = fromField.findElement(By.className("select2-selection--single"));
        from.click();

        WebElement fromSearchBox = webDriver.findElement(By.className("select2-search"));
        fromSearchBox.sendKeys("ankara");

        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 15);

        webDriverWait.until(ExpectedConditions.invisibilityOfElementWithText(By.className("select2-results__option"),
                webDriver.findElement(By.className("select2-results__option")).getText()));

        List<WebElement> fromList = webDriver.findElements(By.className("select2-results__option"));
        fromList.get(0).click();

        //to
        webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebElement toSearchBox = webDriver.findElement(By.className("select2-search__field"));
        toSearchBox.sendKeys("istanbul");

        webDriverWait.until(ExpectedConditions.invisibilityOfElementWithText(By.className("select2-results__option"),
                webDriver.findElement(By.className("select2-results__option")).getText()));

        List<WebElement> toList = webDriver.findElements(By.className("select2-results__option"));
        toList.get(0).click();

        // date
        webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebElement date = webDriver.findElement(By.className("date"));
        date.click();

        List<WebElement> allDates = webDriver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//td"));

        for(WebElement ele:allDates) {
            String dateNumber = ele.getText();
            if(dateNumber.equalsIgnoreCase("28")) {
                ele.click();
                break;
            }
        }

        // find ticket
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement findTicket = webDriver.findElement(By.id("btn-find-ticket"));
        findTicket.click();

    }
}
