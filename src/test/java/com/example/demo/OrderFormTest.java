package com.example.demo;


import com.example.demo.pageObjects.OrderFormPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderFormTest {


    private WebDriver driver;

    @BeforeEach
    public void setUp() {

//        driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void completeScooterOrder() {
        OrderFormPage orderFormPage = new OrderFormPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //принимаем куки, чтобы не закрывал пол экрана
        orderFormPage.acceptCookies();

        // Нажимаю на кнопку "Заказать"
        orderFormPage.clickOrderButton();

        // Заполняю данные на странице заказа
        orderFormPage.enterFirstName("Иван");
        orderFormPage.enterLastName("Иванов");
        orderFormPage.enterAddress("ул. Пушкина, д. 10");

        // Нахожу поле ввода для станции метро и кликаю на него
        orderFormPage.clickMetroStationInput();
        // Теперь нахожу первый элемент списка и кликаю на него
        orderFormPage.clickFirstMetroStation();

        // ввожу номер телефона
        orderFormPage.enterPhone("+71111234567");


        //нажимаем далее
        orderFormPage.clickNextButton();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='* Когда привезти самокат']")));

        // Заполняю данные на странице аренды
        orderFormPage.enterDatePicker("05/29/2024");
        orderFormPage.clickRentPeriodDropdown();
        orderFormPage.selectRentPeriodOption();
        orderFormPage.clickBlackColorCheckbox();
        orderFormPage.enterCourierComment("Приветствую! У нас Шлакбаум, как только подьедете - позвоните мне, я сообщу в КПП и вас пропустят! Спасибо!");

        // Нажимаем на кнопку "Заказать"
        orderFormPage.clickFinalOrderButton();

        // Ждем когда появится окно с подтверждением заказа
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']")));

        //подтверждаем заказ
        orderFormPage.clickOrderConfirmationYesButton();


        WebElement successfulOrder = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Order_ModalHeader__3FDaJ']")));

        //проверяем что заказ успешно оформлен
        assertTrue(successfulOrder.getText().contains("Заказ оформлен"));
    }


    @AfterEach
    public void tearDown() {
        // Закрытие браузера после выполнения теста
        driver.quit();
    }
}


