package sauceDemo.cucumber.StepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static org.openqa.selenium.By.xpath;

public class stepDefinition {
    static WebDriver driver;
    static String base_url = "https://www.saucedemo.com/";

    @Given("User open login page")
    public static void userOpenLoginPage(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();
        driver.get(base_url);
    }

    @When("User input (.*) and (.*) info$")
    public static void userInputUsernameAndPasswordInfo(String username, String password) {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @And("User click login button")
    public static void userClickLoginButton() {
        driver.findElement(By.id("login-button")).click();
    }

    @Then("Login has (.*) status$")
    public void loginHasInfoStatus(String status) {
        if(status.equals("success")){
            Assert.assertTrue(driver.findElement(xpath("//span[text()='Products']")).isDisplayed());
        } else if (status.equals("fail")) {
            String error_message = "//div[contains(@class, 'error-message-container')]/h3[text()='Epic sadface: Username and password do not match any user in this service']";
            Assert.assertTrue(driver.findElement(xpath(error_message)).isDisplayed());
        }
    }

    @Given("User is already login with correct username and password")
    public void userIsAlreadyLoginWithCorrectUsernameAndPassword() {
        stepDefinition.userOpenLoginPage();
        stepDefinition.userInputUsernameAndPasswordInfo("standard_user", "secret_sauce");
        stepDefinition.userClickLoginButton();
    }

    @Then("User click Add to cart button")
    public void userClickAddToCartButton() {
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
    }

    @And("User go to cart page")
    public void userGoToCartPage() {
        driver.findElement(By.id("shopping_cart_container")).click();
    }

    @Then("User should see added product in cart")
    public void userShouldSeeAddedProductInCart() {
        Assert.assertTrue(driver.findElement(xpath("//div[text()='Sauce Labs Bike Light']")).isDisplayed());
        Assert.assertTrue(driver.findElement(xpath("//div[contains(text(), \"A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included.\")]")).isDisplayed());
        Assert.assertTrue(driver.findElement(xpath("//div[text()='9.99']")).isDisplayed());
    }

    @Then("User click checkout button")
    public void userClickCheckoutButton() {
        driver.findElement(By.id("checkout")).click();
    }

    @And("User fill shipping information")
    public void userFillShippingInformation() {
        driver.findElement(By.id("first-name")).sendKeys("standard");
        driver.findElement(By.id("last-name")).sendKeys("user");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
    }

    @And("User click continue button")
    public void userClickContinueButton() {
        driver.findElement(By.id("continue")).click();
    }

    @Then("User should see detail order")
    public void userShouldSeeDetailOrder() {
        Assert.assertTrue(driver.findElement(xpath("//div[text()='Free Pony Express Delivery!']")).isDisplayed());
        String total_price = driver.findElement(xpath("//div[contains(@class,'summary_total_label')]")).getText();
        Assert.assertEquals("Total: $10.79", total_price);
    }

    @Then("User click finish button")
    public void userClickFinishButton() {
        driver.findElement(By.id("finish")).click();
    }

    @Then("User should see success order page")
    public void userShouldSeeSuccessOrderPage() {
        Assert.assertTrue(driver.findElement(xpath("//h2[text()='Thank you for your order!']")).isDisplayed());
    }

    @Then("User logout")
    public void userLogout() {
        driver.findElement(By.id("react-burger-menu-btn")).click();
        driver.findElement(By.id("logout_sidebar_link")).click();
    }

    @Then("User should see login page")
    public void userShouldSeeLoginPage() {
        Assert.assertTrue(driver.findElement(By.id("login-button")).isDisplayed());
    }
}
