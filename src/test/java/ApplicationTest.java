import org.junit.Test;
import org.openqa.selenium.*;
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

        //select first car
        webDriverWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("div[data-item*='journey']"),1));
        List<WebElement> ticketList = webDriver.findElements(By.cssSelector("div[data-item*='journey']"));
        ticketList.get(0).click();

        // select seat number
        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='bus-layout-wrap']/*[name()='svg']/*[name()='a']")));
        List<WebElement> numbers = webDriver.findElements(By.xpath("//div[@class='bus-layout-wrap']/*[name()='svg']/*[name()='a']"));
        WebElement number = numbers.get(0);
        for (WebElement e: numbers) {
            SearchContext searchContext = e;
            if (searchContext.findElement(By.className("s-seat-n")).getText().trim().equals("7")) {
                number = e;
                System.out.println("if : "+searchContext.findElement(By.className("s-seat-n")).getText());
                break;
            }
        }
        ((JavascriptExecutor) webDriver).executeScript("var evt = document.createEvent('MouseEvents');" +
                "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);" +
                "arguments[0].dispatchEvent(evt);", number);

        // select gender
        webDriverWait.until(ExpectedConditions.invisibilityOfElementWithText(By.className("popover"),
                webDriver.findElement(By.className("popover")).getText()));
        SearchContext genderArea = webDriver.findElement(By.className("popover"));
        WebElement gender = genderArea.findElement(By.cssSelector("a[data-gender*='M']"));
        gender.click();

        // click next button
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.className("btn-next")));
        WebElement nextBtn = webDriver.findElement(By.className("btn-next"));
        nextBtn.click();

        // phone
        WebElement phone = webDriver.findElement(By.id("Phone"));
        phone.sendKeys("5555555555");

        // Email
        WebElement email = webDriver.findElement(By.id("Email"));
        email.sendKeys("test@test.com");

        //Name
        WebElement name = webDriver.findElement(By.cssSelector("input[id*='Name']"));
        name.sendKeys("test yolcu");

        // non turkish
        WebElement nonTurk = webDriver.findElement(By.cssSelector("input[id*='IsNonTurkish']"));
        nonTurk.click();

        // credit card
        WebElement creditCard = webDriver.findElement(By.id("CardNumber"));
        creditCard.sendKeys("5571");
        creditCard.sendKeys("1355");
        creditCard.sendKeys("7113");
        creditCard.sendKeys("5575");

        // Expiration
        WebElement expiration = webDriver.findElement(By.id("Expiration"));
        expiration.sendKeys("12");
        expiration.sendKeys("18");

        // CVV
        WebElement cvv = webDriver.findElement(By.id("CV2"));
        cvv.sendKeys("000");

        // submit button
        WebElement submitButton = webDriver.findElement(By.className("btn-success"));
        submitButton.click();


    }

    @Test
    public void linkedinTest() {
        System.setProperty("webdriver.gecko.driver","/usr/bin/geckodriver");
        WebDriver webDriver = new FirefoxDriver();
        webDriver.get("https://www.linkedin.com/");

        WebElement email = webDriver.findElement(By.id("login-email"));
        WebElement password = webDriver.findElement(By.id("login-password"));
        WebElement btnLogin = webDriver.findElement(By.id("login-submit"));

        email.sendKeys("gurkan.cakir@engineer.com");
        password.sendKeys("test"); //sifre guncelle
        btnLogin.click();


    }
}
