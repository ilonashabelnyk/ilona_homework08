package Homework08Junit.Tests;

import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HeaderElements {
    WebDriver driver;
    String baseUrl = "https://www.bookdepository.com/";

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(baseUrl);
    }

    @Tag("Header Elements")
    @Test
    @DisplayName("Validate the title when clicking on Homepage Sign.")
    public void redirectOnHomePageSign() {
        String expectedPageTitle = "Book Depository: Free delivery worldwide on over 20 million books";
        By homePageSignLocator = By.cssSelector("div.user-nav-wrap  a.home-icon-link");

        WebElement homePageSign = driver.findElement(homePageSignLocator);
        homePageSign.click();

        Assert.assertEquals("Incorrect Page Tittle when clicking on Homepage Sign.", expectedPageTitle, driver.getTitle());
        System.out.println("Expected Page Title: " + expectedPageTitle + "; " + "Current Page Title: " + driver.getTitle());
    }

    @Tag("Header Elements")
    @Test
    @DisplayName("Validate the Redirect URL clicking on SignInJoin button.")
    public void redirectOnSignInJoin() {
        By signInJoinLocator = By.cssSelector("div.user-nav-wrap  i.icon-user");
        String expectedRedirectURLSignInJoin = "https://www.bookdepository.com/account/login/to/account";

        WebElement signInJoin = driver.findElement(signInJoinLocator);
        signInJoin.click();

        Assert.assertEquals("Incorrect redirection clicking on SignInJoin button.", expectedRedirectURLSignInJoin, driver.getCurrentUrl());
        System.out.println("Expected SignInJoin redirection: " + expectedRedirectURLSignInJoin + "; " + "Current SignInJoin redirection: " + driver.getCurrentUrl());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}