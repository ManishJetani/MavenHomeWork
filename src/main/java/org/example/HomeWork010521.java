package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class HomeWork010521
{   //instance variable
    protected static WebDriver driver;
    @BeforeMethod
    public void openBrowser()
{
    //setting up chromedriver path from Resources
    System.setProperty("webdriver.chrome.driver","src\\test\\Resources\\Browser\\chromedriver.exe");
    //creating object for driver
    driver = new ChromeDriver();
    //maximize the browser window
    driver.manage().window().maximize();
    //applying implicitly wait to driver object
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    //open URL
    driver.get("https://demo.nopcommerce.com/");

}
    @Test
    public void userShouldBeAbleToRegisterSuccessfully()
    {   //timestamp for current time - to create unique email value each time we run the program
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //click on Register button
        driver.findElement(By.linkText("Register")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //click on Male or Female button
        driver.findElement(By.id("gender-male")).click();
        //Type FirstName
        driver.findElement(By.id("FirstName")).sendKeys("Micheal");
        //Type LastName
        driver.findElement(By.id("LastName")).sendKeys("Gove");
        //Select Day of Date of Birth
        Select selectDay = new Select(driver.findElement(By.name("DateOfBirthDay")));
        selectDay.selectByValue("14");
        //Select Month of Birth
        Select selectMonth = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        selectMonth.selectByVisibleText("March");
        //Select Year of Birth
        Select selectYear = new Select(driver.findElement(By.name("DateOfBirthYear")));
        selectYear.selectByIndex(60);
        //Type Email address
        driver.findElement(By.id("Email")).sendKeys("michealgove"+timestamp.getTime()+"@gmail.com");
        //Type Company name
        driver.findElement(By.name("Company")).sendKeys("Conservative Plc.");
        //Untick Newsletter options
        driver.findElement(By.xpath("//input[@type='checkbox']")).click();
        //Type Password
        driver.findElement(By.name("Password")).sendKeys("123123");
        //Confirm Password
        driver.findElement(By.name("ConfirmPassword")).sendKeys("123123");
        //Click on Register
        driver.findElement(By.id("register-button")).click();
        //locating actual title for Assert
        String actualTitle = driver.findElement(By.xpath("//div[@class='result']")).getText();
        //expected title as per requirement
        String expectedTitle = "Your registration completed";
        //Asserting actual with expected result
        Assert.assertEquals(actualTitle,expectedTitle,"Your registration successful");
        //message print
        System.out.println("Your registration successful");
    }
    @Test
    public void userShouldBeAbleToVerifyAppleMacBookProPrice()
    {
//        driver.findElement(By.linkText("Apple MacBook Pro 13-inch"));
        //locating actual price
        driver.findElement(By.xpath("//div[@class='item-box'][2]//span[@class='price actual-price']"));
        //locating actual price for comparision with expected price
        String actualPrice = driver.findElement(By.xpath("//div[@class='item-box'][2]//span[@class='price actual-price']")).getText();
        //expected price
        String expectedPrice = "$2,000.00";
        //Asserting actual price with expected result
        Assert.assertEquals(actualPrice,expectedPrice,"Price not matched as per requirement");

    }
    @Test
    public void buildYourOwnComputer()
    {
        //clicking on add to cart button of product
        driver.findElement(By.xpath("//div[@class='item-box'][1]//button[@type='button'][1]")).click();
        //selecting different processor
        Select processor = new Select(driver.findElement(By.id("product_attribute_1")));
        processor.selectByIndex(1);
        //selecting different RAM
        Select rAM = new Select(driver.findElement(By.name("product_attribute_2")));
        rAM.selectByValue("5");
        //selecting HDD 400GB
        driver.findElement(By.id("product_attribute_3_7")).click();
        //selecting OS vista premium
        driver.findElement(By.id("product_attribute_4_9")).click();
        //adding Acrobat Reader into build
        driver.findElement(By.id("product_attribute_5_11")).click();
        //adding Total Commander into build
        driver.findElement(By.id("product_attribute_5_12")).click();
        //clicking add to cart button to add into shopping cart
        driver.findElement(By.id("add-to-cart-button-1")).click();
        //clicking shopping cart button to see checkout page
        driver.findElement(By.linkText("Shopping cart")).click();
        //getting actual product name to verify with expected product name
        String actualProduct = driver.findElement(By.linkText("Build your own computer")).getText();
        //typing expected product name to match with actual product
        String expectedProduct = "Build your own computer";
        //asserting actual product with expected product
        Assert.assertEquals(actualProduct,expectedProduct,"Product added successfully");
        //message print
        System.out.println("Product added successfully");
    }
    @Test
    public void compareTwoProducts()
    {
        //adding Apple MacBook Pro for comparison
        driver.findElement(By.xpath("//div[@class='item-box'][2]//button[@title='Add to compare list']")).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //adding HTC One M8 for comparison
        driver.findElement(By.xpath("//div[@class='item-box'][3]//button[@title='Add to compare list']")).click();
        //comparing two products
        driver.findElement(By.linkText("product comparison")).click();
        //getting actual title to verify with expected title
        String actualResult = driver.findElement(By.xpath("//div[@class='page-title']/h1")).getText();
        //typing expected title name to match with actual name
        String expectedResult = "Compare products";
        //asserting actual title name with expected title name
        Assert.assertEquals(actualResult,expectedResult,"Comparison successful");
        //message print
        System.out.println("Comparison successful");
//        WebDriverWait wait = new WebDriverWait(driver,10);
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='clear-list']")));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //clicking on clear list to remove selected products
        driver.findElement(By.xpath("//a[@class='clear-list']")).click();
        //getting actual title to verify with expected title
        String actualOutcome = driver.findElement(By.xpath("//div[@class='no-data']")).getText();
        //typing expected title name to match with actual name
        String expectedOutcome = "You have no items to compare.";
        //asserting actual title name with expected title name
        Assert.assertEquals(actualOutcome,expectedOutcome,"Successfully cleared products");
        //message print
        System.out.println("You have no items to compare");
    }
    @AfterMethod
    public void closeBrowser()
    {   //closing browser after test
        driver.quit();
    }
}
