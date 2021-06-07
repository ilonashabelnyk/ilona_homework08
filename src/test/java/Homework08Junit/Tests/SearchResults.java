package Homework08Junit.Tests;

import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class SearchResults {
    WebDriver driver;
    String baseUrl = "https://www.bookdepository.com/";

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(baseUrl);
    }

    @Tag("Search")
    @Test
    @DisplayName("Validate that Search is performed by term and search results are not empty (more than 0).")
    public void countSearchResults() {
        int emptySearchResults = 0;
        By searchInputLocator = By.name("searchTerm");
        By searchBtnLocator = By.xpath("//form[@id='book-search-form']//button[.='Search']");
        By searchResultsLocator = By.xpath("//*[contains(@class, 'book-item')]");

        WebElement searchInput = driver.findElement(searchInputLocator);
        searchInput.sendKeys("Zaratustra");

        WebElement searchBtn = driver.findElement(searchBtnLocator);
        searchBtn.click();

        List<WebElement> searchResults = driver.findElements(searchResultsLocator);
        int currentSearchResults = searchResults.size();

        Assert.assertTrue("Search results are empty.", currentSearchResults > emptySearchResults);
        System.out.println("Expected: the search results are not empty; current size of search results by term: " + currentSearchResults);
    }

    @Tag("Search")
    @Test
    @DisplayName("Validate the Placeholder of the search input field.")
    public void getSearchPlaceholderValue() {
        String expectedSearchPlaceholder = "Search for books by keyword / title / author / ISBN";
        By searchInputLocator = By.name("searchTerm");

        WebElement searchInput = driver.findElement(searchInputLocator);

        String currentSearchPlaceholder = searchInput.getAttribute("placeholder");

        Assert.assertEquals("Incorrect placeholder for search input field.", expectedSearchPlaceholder, currentSearchPlaceholder);
        System.out.println("Expected search placeholder: " + expectedSearchPlaceholder + "; " + "Current search placeholder: " + currentSearchPlaceholder);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}