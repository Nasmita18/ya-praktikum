package com.example.demo;

import com.example.demo.pageObjects.AccordionPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


class AccordionTest {

    private WebDriver driver;
    private AccordionPage accordionPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        accordionPage = new AccordionPage(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }
    @Test
    public void verifyAccordionText() {

        for (int i = 0; i < accordionPage.getAllAccordionItemButtons().size(); i++) {
            WebElement buttonElement = accordionPage.getAllAccordionItemButtons().get(i);
            WebElement panelElement = accordionPage.getAllAccordionItemPanels().get(i);

            // Получаем текст для элемента аккордеона
            String expectedButtonText = AccordionPage.getAccordionTestData()[i][0].toString();
            String expectedPanelText = AccordionPage.getAccordionTestData()[i][1].toString();

            // Кликаем на элемент
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", buttonElement);
            buttonElement.click();

            // Ждем чтоб панель развернулась
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(panelElement));

            // Получаем актуальный текст элементов аккордеона
            String actualButtonText = buttonElement.getText();
            String actualPanelText = panelElement.getText();

            // Проверяем совпадение ожидаемого и актуального текста
            Assertions.assertEquals(expectedButtonText, actualButtonText);
            Assertions.assertEquals(expectedPanelText, actualPanelText);
        }
    }


    @AfterEach
    public void quitBrowser() {
        // Закрываем браузер после завершения теста
        driver.quit();
    }
}
