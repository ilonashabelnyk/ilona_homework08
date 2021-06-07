package Homework08Junit.Tests;

import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasketTotalCompareToCheckoutTotal {
    WebDriver driver;
    String baseUrl = "https://www.bookdepository.com/";

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(baseUrl);
    }

    @Tag("Total")
    @Test
    @DisplayName("Validate that Basket Total is equal to Checkout total.")
    public void compareBasketTotalToCheckoutTotal() {
        By searchInputLocator = By.name("searchTerm");
        By searchBtnLocator = By.xpath("//form[@id='book-search-form']//button[.='Search']");
        By searchResultsLocator = By.xpath("//*[contains(@class, 'book-item')]");
        By addToBasketBtnLocator = By.cssSelector("div.item-tools a.add-to-basket");
        By basketCheckoutBtnLocator = By.xpath("//div[@class='modal-body']//a[contains(@class,'continue-to-basket')]");
        By basketTotalLocator = By.cssSelector("p.item-total");
        By checkoutBtnLocator = By.xpath("//*[@class='checkout-btn btn']");
        By checkoutTotalLocator = By.cssSelector("div.sidebar.right div div.wrapper dl:nth-child(5) dd");

        WebElement searchInput = driver.findElement(searchInputLocator);

        searchInput.sendKeys("Zaratustra");
        WebElement searchBtn = driver.findElement(searchBtnLocator);
        searchBtn.click();

        List<WebElement> searchResults = driver.findElements(searchResultsLocator);

        searchResults.get(0).click();
        WebElement addToBasketBtn = driver.findElement(addToBasketBtnLocator);
        addToBasketBtn.click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(basketCheckoutBtnLocator));
        WebElement basketCheckoutBtn = driver.findElement(basketCheckoutBtnLocator);
        basketCheckoutBtn.click();

        WebElement basketTotal = driver.findElement(basketTotalLocator);
        String basketTotal1 = basketTotal.getText();

        WebElement checkoutBtn = driver.findElement(checkoutBtnLocator);
        checkoutBtn.click();

        WebElement checkoutTotal = driver.findElement(checkoutTotalLocator);
        String checkoutTotal2 = checkoutTotal.getText();
        Assert.assertEquals("Basket total should be equal to Checkout total as the delivery is free.", basketTotal1, checkoutTotal2);
        System.out.println("Basket Total: " + basketTotal1 + "; " + "Checkout Total: " + checkoutTotal2);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}