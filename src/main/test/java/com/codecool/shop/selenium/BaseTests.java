package com.codecool.shop.selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BaseTests {

    private static WebDriver driver;

    private By categories = By.id("categories");
    private By supplier = By.id("suppliers");

    @BeforeClass
    protected void setUp(){
        System.setProperty("webdriver.chrome.driver",
                "/home/emis/Projects/codecool/oop/week6/codecool-shop-2-java-kisfelix1/src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://localhost:8080");
        driver.manage().window().maximize();
    }


    @Test
    public void testGetTitle(){
        String title = "Codecool Shasdop";
        assertEquals(driver.getTitle(), title, "Title is not matching");
        System.out.println(driver.getTitle() + " : driver.getTitle() result.");
        System.out.println(title + " : our result.");
        driver.quit();
    }


    @Test
    public void testSelectOption(){
        String option = "NFT";
        findDropDownElement(categories).selectByVisibleText(option);
        var selectedOptions = getSelectedOption();
        assertTrue(selectedOptions.contains(option), "Option not selected");
        driver.quit();

    }

    private List<String> getSelectedOption(){
        List<WebElement> selectedElements =  findDropDownElement(categories).getAllSelectedOptions();
        return selectedElements.stream().map(e -> e.getText()).collect(Collectors.toList());
    }

    private Select findDropDownElement(By dropDownMenu){
        return new Select(driver.findElement(dropDownMenu));
    }


    @Test
    public void testAddItemToCartAndClickViewButton() throws InterruptedException {
        String option = "NFT";
        findDropDownElement(categories).selectByVisibleText(option);
        By toxiDogoButton = By.xpath("//button[@data-btn-id='15']");
        driver.findElement(toxiDogoButton).click();
        driver.findElement(By.id("cart")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[text()='Close']")).click();
        String text = driver.findElement(By.id("product-name")).getText();
        assertEquals(text, "Toxic doge art", "Results text incorrect");
        driver.quit();
    }


    @Test
    public void testAddItemToCartAndClickToCheckoutAndFillAllInputFields() throws InterruptedException {
        String option = "OpenSea";
        findDropDownElement(supplier).selectByVisibleText(option);
        By coolBombArt = By.xpath("//button[@data-btn-id='12']");
        driver.findElement(coolBombArt).click();
        driver.findElement(By.id("cart")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("cart-checkout")).click();

        By emailInput = By.id("credit-email");
        By cardNumber = By.id("card-number");
        By MMYY = By.id("months-year");
        By cvvCode = By.id("cvv-code");
        By nameOnTheCard = By.id("name-on-the-card");
        By paymentButton = By.id("payment-button");

        driver.findElement(emailInput).sendKeys("emistest10@gmail.com");
        driver.findElement(cardNumber).sendKeys("123456789012");
        driver.findElement(MMYY).sendKeys("729");
        driver.findElement(cvvCode).sendKeys("123");
        driver.findElement(nameOnTheCard).sendKeys("Bence Szabó");

        Thread.sleep(3000);
        driver.findElement(paymentButton).click();
        driver.quit();
    }

    @Test
    public void testLogin() throws InterruptedException {

        By loginButton = By.id("login");
        By userNameText = By.id("userNamePlace");
        By loginInputEmail = By.id("login-email");
        By loginInputPassword = By.id("login-password");
        By loginAlertButton = By.id("login-btn");

        driver.findElement(loginButton).click();
        Thread.sleep(1000);
        driver.findElement(loginInputEmail).sendKeys("emistest10@gmail.com");
        driver.findElement(loginInputPassword).sendKeys("sajt123");
        Thread.sleep(1000);
        driver.findElement(loginAlertButton).click();
        Thread.sleep(1000);

        assertEquals(driver.findElement(userNameText).getText(), "Bence Szabo","Username is not valid" );

        System.out.println(driver.findElement(userNameText).getText());
        System.out.println("Bence Szabo");

        driver.quit();
    }

    @Test
    public void testRegistrationAndLogin() throws InterruptedException {

        By registrationButton = By.id("register");
        By registerName = By.id("register-name");
        By registerEmail = By.id("register-email");
        By registerPassword = By.id("register-password");
        By registrationAlertButton = By.id("register-btn");
        By registrationAlertCloseButton = By.id("modal-close");

        driver.findElement(registrationButton).click();
        Thread.sleep(1000);
        driver.findElement(registerName).sendKeys("Bence Szabo");
        driver.findElement(registerEmail).sendKeys("emistest10@gmail.com");
        driver.findElement(registerPassword).sendKeys("sajt123");
        Thread.sleep(1000);
        driver.findElement(registrationAlertButton).click();
        Thread.sleep(1000);
        driver.findElement(registrationAlertCloseButton).click();

       testLogin();

    }


}
