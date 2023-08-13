package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppOrderNegativeTest {

    private WebDriver driver;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }


    @BeforeEach
    void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }

    @AfterEach
    void teardown() {
        driver.quit();
        driver = null;
    }

    @Test
    public void shouldBeFailedIncorrectNameAppOrderNegativeTest(){
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Ilya");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+78005553535");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("[button.button]")).click();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", driver.findElement(By.xpath("[data-test-id='name'].input_invalid .input__sub")).getText().trim());
    }
    @Test
    public void shouldBeFailedEmptyNameInput(){
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+78005553535");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("[button.button]")).click();
        assertEquals("Поле обязательно для заполнения", driver.findElement(By.xpath("[data-test-id=name].input_invalid .input__sub")).getText().trim());
    }
    @Test
    public void shouldBeFailedcorrectPhoneInput(){
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иваныч-Ивановичев Иван");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("NIkita");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("[button.button]")).click();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79812345678.", driver.findElement(By.xpath("[data-test-id=phone].input_invalid .input__sub")).getText().trim());
    }
    @Test
    public void shouldBeFailedEmptyPhoneInput(){
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иваныч-Ивановичев Иван");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("[button.button]")).click();
        assertEquals("Поле обязательно для заполнения", driver.findElement(By.xpath("[data-test-id=phone].input_invalid .input__sub")).getText().trim());
    }
    @Test
    public void shouldBeFailedUncheckedCheckbox(){
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иваныч-Ивановичев Иван");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+78005553535");
        driver.findElement(By.cssSelector("[button.button]")).click();
        assertTrue(driver.findElement(By.cssSelector("[data-test-id=agreement].input_invalid")).isDisplayed());
    }

}










