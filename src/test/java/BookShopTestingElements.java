import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class BookShopTestingElements {
    public static void main(String[] args) throws InterruptedException {
        By navigationMenuLocator = By.cssSelector("div.secondary-header-wrap li.category-dropdown");
        By logoLocator = By.cssSelector("div.brand-wrap img[alt='Bookdepository.com']");
        By homePageSignLocator = By.cssSelector("div.user-nav-wrap  a.home-icon-link");
        By signInJoinLocator = By.cssSelector("div.user-nav-wrap  i.icon-user");
        By searchInputLocator = By.name("searchTerm");
        By searchBtnLocator = By.xpath("//form[@id='book-search-form']//button[.='Search']");
        By searchResultsLocator = By.xpath("//*[contains(@class, 'book-item')]");
        By addToBasketBtnLocator = By.cssSelector("div.item-tools a.add-to-basket");
        By successMsgOfAddToCartLocator = By.className("modal-title");
        By basketCheckoutBtnLocator = By.xpath("//div[@class='modal-body']//a[contains(@class,'continue-to-basket')]");
        By presenceItemInTheBasketLocator = By.cssSelector("div.checkout-head-wrap div.basket-msg");

        String baseUrl = "https://www.bookdepository.com/";
        WebDriver driver = new ChromeDriver();
        driver.get(baseUrl);

        //Validation of navigation menu
        WebElement navigationMenu = driver.findElement(navigationMenuLocator);
        navigationMenu.click();
        System.out.println("Navigation menu element is located.");

        //Validation of logo
        driver.get(baseUrl);
        WebElement logo = driver.findElement(logoLocator);
        logo.click();
        if (driver.getTitle().equals("Book Depository: Free delivery worldwide on over 20 million books")) {
            System.out.println("Logo element is located. On click we are back at Book Depository homepage.");
        } else {
            System.out.println("Logo element is located. On click we are NOT in Book Depository homepage: " + driver.getTitle());
        }

        //Validation of home page sign
        driver.get(baseUrl);
        WebElement homePageSign = driver.findElement(homePageSignLocator);
        homePageSign.click();
        if (driver.getTitle().equals("Book Depository: Free delivery worldwide on over 20 million books")) {
            System.out.println("Home page sign element is located. On click we are back at Book Depository homepage.");
        } else {
            System.out.println("Home page sign element is located. On click we are NOT in Book Depository homepage: " + driver.getTitle());
        }

        //Validation of Sign in/Join element
        WebElement signInJoin = driver.findElement(signInJoinLocator);
        signInJoin.click();

        String expectedURL = "https://www.bookdepository.com/account/login/to/account";
        String redirectURL = driver.getCurrentUrl();

        if (expectedURL.equals(redirectURL)) {
            System.out.println("Sign in/Join element is located. On click the redirect link is correct: " + driver.getCurrentUrl());
        } else {
            System.out.println("Sign in/Join element is located. On click the redirect link is NOT correct: " + driver.getCurrentUrl());
        }

        //Validation of placeholder in search input field
        WebElement searchInput = driver.findElement(searchInputLocator);

        String expectedPlaceholder = "Search for books by keyword / title / author / ISBN";
        String currentPlaceholder = searchInput.getAttribute("placeholder");

        if (expectedPlaceholder.equals(currentPlaceholder)) {
            System.out.println("Search field element is located. Placeholder text for search input field is correct.");
        } else {
            System.out.println("Search field element is located. Placeholder text for search input field is NOT correct: " + currentPlaceholder);
        }

        //Performing search by term
        searchInput.sendKeys("Zaratustra");
        WebElement searchBtn = driver.findElement(searchBtnLocator);
        searchBtn.click();

        //Counting the search results
        List<WebElement> searchResults = driver.findElements(searchResultsLocator);
        if (searchResults.size() > 0) {
            System.out.println("Search results number by requested term: " + searchResults.size());
        } else {
            System.out.println("No search results!");
        }

        //Validation of "add to cart" flow
        searchResults.get(0).click();
        System.out.println("One element from search results is located and opened. The title is: " + driver.getTitle());
        WebElement addToBasketBtn = driver.findElement(addToBasketBtnLocator);
        addToBasketBtn.click();

        //Validation of success message of adding to cart on the modal page
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(successMsgOfAddToCartLocator));
        WebElement successMsgOfAddToCart = driver.findElement(successMsgOfAddToCartLocator);
        String expectedSuccessAddToCartMsg = "Item added to your basket";
        new WebDriverWait(driver, 20).until(ExpectedConditions.textToBePresentInElement(successMsgOfAddToCart, expectedSuccessAddToCartMsg));
        String currentAddToCartMsg = successMsgOfAddToCart.getText();

        if (expectedSuccessAddToCartMsg.equals(currentAddToCartMsg)) {
            System.out.println("The message after clicking 'Add to basket' button: " + expectedSuccessAddToCartMsg);
        } else {
            System.out.println("The message after clicking 'Add to basket' button: " + currentAddToCartMsg);
        }

        //Validation of checkout flow
       new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(basketCheckoutBtnLocator));
        WebElement basketCheckoutBtn = driver.findElement(basketCheckoutBtnLocator);
        basketCheckoutBtn.click();

        //Validation the presence of text that item is in the basket
        WebElement presenceItemInTheBasket = driver.findElement(presenceItemInTheBasketLocator);
        System.out.println("Basket text element is located. The text is: " + presenceItemInTheBasket.getText());

        driver.quit();
    }
}