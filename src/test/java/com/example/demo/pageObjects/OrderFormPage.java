package com.example.demo.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderFormPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public OrderFormPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //принимаем куки
    public void acceptCookies() {
        WebElement acceptCookiesButton = driver.findElement(By.xpath("//*[@id='rcc-confirm-button']"));
        acceptCookiesButton.click();
    }

    //кнопка "заказать"
    public void clickOrderButton() {
        WebElement orderButton = driver.findElement(By.xpath("//button[contains(text(),'Заказать')]"));
        orderButton.click();
    }

    //имя
    public void enterFirstName(String firstName) {
        WebElement firstNameInput = driver.findElement(By.xpath("//input[@placeholder='* Имя']"));
        firstNameInput.sendKeys(firstName);
    }

    //фамилия
    public void enterLastName(String lastName) {
        WebElement lastNameInput = driver.findElement(By.xpath("//input[@placeholder='* Фамилия']"));
        lastNameInput.sendKeys(lastName);
    }

    //адрес
    public void enterAddress(String address) {
        WebElement addressInput = driver.findElement(By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']"));
        addressInput.sendKeys(address);
    }

    //станция метро
    public void clickMetroStationInput() {
        WebElement metroStationInput = driver.findElement(By.xpath("//div[@class='select-search']//input[@placeholder='* Станция метро']"));
        metroStationInput.click();
    }

    //нметод для нажатия первого элемента в списке метро
    public void clickFirstMetroStation() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='select-search has-focus']//div[@class='select-search__select']//li[1]")));
        WebElement firstMetroStation = driver.findElement(By.xpath("//div[@class='select-search has-focus']//div[@class='select-search__select']//li[1]"));
        firstMetroStation.click();
    }

    //номер телефона
    public void enterPhone(String phoneNumber) {
        WebElement phoneInput = driver.findElement(By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']"));
        phoneInput.sendKeys(phoneNumber);
    }

    //кнопка "далее" чтобы перейти к другому заказу
    public void clickNextButton() {
        WebElement nextButton = driver.findElement(By.xpath("//button[contains(@class, 'Button_Button__ra12g') and text()='Далее']"));
        nextButton.click();
    }

    //дата когда привезти самокат
    public void enterDatePicker(String date) {
        WebElement datePickerInput = driver.findElement(By.xpath("//input[@placeholder='* Когда привезти самокат']"));
        datePickerInput.sendKeys(date);
        datePickerInput.sendKeys(Keys.ENTER);
    }

    //срок аренды
    public void clickRentPeriodDropdown() {
        WebElement rentPeriodDropdown = driver.findElement(By.xpath("//div[@class='Dropdown-placeholder' and text()='* Срок аренды']"));
        rentPeriodDropdown.click();
    }

    //выпадающий список срока аренды
    public void selectRentPeriodOption() {
        WebElement rentPeriodOption = driver.findElement(By.xpath("//div[@role='option' and text()='шестеро суток']"));
        rentPeriodOption.click();
    }

    //выбор цвета самоката
    public void clickBlackColorCheckbox() {
        WebElement blackColorCheckbox = driver.findElement(By.id("black"));
        blackColorCheckbox.click();
    }

    //комментарий для курьера
    public void enterCourierComment(String comment) {
        WebElement courierCommentInput = driver.findElement(By.xpath("//input[@placeholder='Комментарий для курьера']"));
        courierCommentInput.sendKeys(comment);
    }

    //кнопка заказать
    public void clickFinalOrderButton() {
        WebElement finalOrderButton = driver.findElement(By.xpath("//button[contains(text(),'Заказать')]"));
        finalOrderButton.click();
    }
}
