package com.capside.enterpriseseminar;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author ciberado
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
// @ActiveProfiles("dev")   // Beware: this profile will use a local database
public class DemoUIIT {

    @Value("${local.server.port}")
    private int port;
    
    private WebDriver driver;
    private Wait<WebDriver> wait;

    @Before
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 30);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void performSearch() throws IOException {
        final String firstTeamName = "R. Madrid";
        final String secondTeamName = "Barcelona";
        
        driver.get("http://localhost:" + port);

        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.findElement(By.id("firstTeamInput")).sendKeys(firstTeamName);
        driver.findElement(By.id("secondTeamInput")).sendKeys(secondTeamName);
        driver.findElement(By.id("queryCommand")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("results-data")));
        
        String firstTeamWins = driver.findElement(By.id("firstTeamWins")).getText();
        String secondTeamWins = driver.findElement(By.id("secondTeamWins")).getText();
        
        assertEquals("First team wins one match.", "1", firstTeamWins);
        assertEquals("Second team wins one match.", "1", secondTeamWins);
    }

}
