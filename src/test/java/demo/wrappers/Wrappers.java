package demo.wrappers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */


    public void enterText(ChromeDriver driver, By locator, String value) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    
        WebElement element = wait.until(
            ExpectedConditions.visibilityOfElementLocated(locator)
        );
    
       // element.clear();
        element.sendKeys(value);
    }

    public void selectRadioButton(ChromeDriver driver, By locator) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    
        WebElement element = wait.until(
            ExpectedConditions.elementToBeClickable(locator)
        );
    
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void selectCheckbox(ChromeDriver driver, By locator) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    
        WebElement element = wait.until(
            ExpectedConditions.elementToBeClickable(locator)
        );
    
       // if (!element.isSelected()) {
          //  element.click();
        //}

        if (!"true".equals(element.getAttribute("aria-checked"))) {
            element.click();
        }
    }

    
    public void selectDropdown(
        ChromeDriver driver, By locator, String option) {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    WebElement dropdown = wait.until(
        ExpectedConditions.elementToBeClickable(locator)
    );

    dropdown.click();

    WebElement optionElement = wait.until(
        ExpectedConditions.elementToBeClickable(
            By.xpath("//div[@role='option' and @data-value='" + option + "']")
        )
    );

    optionElement.click();
}

public void selectDate(ChromeDriver driver, By locator) {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    WebElement element = wait.until(
        ExpectedConditions.visibilityOfElementLocated(locator)
    );

    LocalDate date = LocalDate.now().minusDays(7);

    String formattedDate = date.format(
        DateTimeFormatter.ofPattern("yyyy-MM-dd")
    );

    driver.executeScript(
        "arguments[0].value = arguments[1];" +
        "arguments[0].dispatchEvent(new Event('input', {bubbles:true}));" +
        "arguments[0].dispatchEvent(new Event('change', {bubbles:true}));",
        element,
        formattedDate
    );

    System.out.println("Date after entry: " +
        element.getAttribute("value"));
}
}
