package baitap;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

@Test
public class testcase03 {
    public static void testTC3() {
        WebDriver driver = driverFactory.getChromeDriver();

        try{
            // Step 1: Go to link
            driver.get("http://live.techpanda.org/");

            // Step 2: Click on 'MOBILE' menu
            driver.findElement(By.linkText("MOBILE")).click();
            Thread.sleep(2000);

            // Step 3: Click on 'ADD TO CART' for Sony Xperia
            WebElement addToCartButton = driver.findElement(By.xpath("//li[2]//div[1]//div[3]//button[1]//span[1]//span[1]"));
            addToCartButton.click();
            Thread.sleep(2000);

            // Step 4: Change 'QTY' to 1000 and click 'UPDATE' button
            WebElement qtyInput = driver.findElement(By.xpath("//input[@title='Qty']"));
            qtyInput.clear();
            qtyInput.sendKeys("499");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//button[@title='Update']//span//span[contains(text(),'Update')]")).click();

            // Step 5: Verify the error message
            String expectedErrorMessage = "The requested quantity for 'Sony Xperia' is not available";
            WebElement actualErrorMessage = driver.findElement(By.xpath("//p[@class='item-msg error']"));
            Assert.assertEquals(actualErrorMessage.getText(), expectedErrorMessage);
            Thread.sleep(2000);

            // Step 6: Click on 'EMPTY CART' link
            driver.findElement(By.xpath("//span[contains(text(),'Empty Cart')]")).click();
            Thread.sleep(2000);

            // Step 7: Verify cart is empty
            WebElement emptyCartMessage = driver.findElement(By.xpath("//div[@class='page-title']/h1"));
            String emptyCart = "SHOPPING CART IS EMPTY";
            Assert.assertEquals(emptyCartMessage.getText(), emptyCart);
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}

