package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
       // options.addArguments("--remote-allow-origins=*");

       System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 
        System.setProperty(
            "webdriver.chrome.driver",
            "drivers/chromedriver-linux64/chromedriver"
        );
       
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }



@Test
public void testCase01() {

    // Create Wrappers object
    Wrappers wrapper = new Wrappers();

    // Navigate to Google Form
    driver.get("https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");

    // Enter Name
    wrapper.enterText(driver,By.xpath("//input[@aria-labelledby='i1 i4']"),"Crio Learner");


     // Second textbox - Automation reason
     long epochTime = Instant.now().getEpochSecond();

     String reason = "I want to be the best QA Engineer! " + epochTime;
 
     wrapper.enterText(
         driver,
         By.xpath("//textarea[@aria-labelledby='i6 i9']"),
         reason
     );


     // Select Automation Testing experience: 3-5
wrapper.selectRadioButton(
    driver,
    By.xpath("//div[@role='radio' and @data-value='3 - 5']")
);


// Select Java
wrapper.selectCheckbox(
    driver,
    By.xpath("//div[@role='checkbox' and @data-answer-value='Java']")
);

// Select Selenium
wrapper.selectCheckbox(
    driver,
    By.xpath("//div[@role='checkbox' and @data-answer-value='Selenium']")
);

// Select TestNG
wrapper.selectCheckbox(
    driver,
    By.xpath("//div[@role='checkbox' and @data-answer-value='TestNG']")
);



// Select Mr from the address dropdown
wrapper.selectDropdown(
    driver,
    By.xpath("//div[@aria-labelledby='i46 i49']"),
    "Mr"
);

wrapper.selectDate(
    driver,
    By.xpath("//input[@type='date']")
);

WebElement dateField = driver.findElement(By.xpath("//input[@type='date']"));

System.out.println("Date entered in form: " +
    dateField.getAttribute("value"));

    System.out.println("Year: " +
    driver.findElement(By.name("entry.1595031146_year")).getAttribute("value"));

System.out.println("Month: " +
    driver.findElement(By.name("entry.1595031146_month")).getAttribute("value"));

System.out.println("Day: " +
    driver.findElement(By.name("entry.1595031146_day")).getAttribute("value"));


// Enter hour
wrapper.enterText(
    driver,
    By.xpath("//input[@aria-label='Hour']"),
    "07"
);

// Enter minute
wrapper.enterText(
    driver,
    By.xpath("//input[@aria-label='Minute']"),
    "30"
);

driver.findElement(
    By.xpath("//div[@role='button' and @aria-label='Submit']")
).click();


WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

WebElement successMessage = wait.until(
    ExpectedConditions.visibilityOfElementLocated(
        By.xpath("//div[text()='Thanks for your response, Automation Wizard!']")
    )
);

System.out.println(successMessage.getText());

}

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}