package locators;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class MainPageSelenide {

    static String title = "Интернет-магазин ROZETKA™: официальный сайт самого популярного онлайн-гипермаркета в Украине";
    static By languageSelector = By.xpath("//*[text()=' UA ']");
    static By banner = By.cssSelector("a.exponea-popup-banner");
    static By emailField = By.id("auth_email");
    static By passwordField = By.id("auth_pass");
    static By profileButton = By.xpath("/html/body/app-root/div/div/rz-header/header/div/div/ul/li[3]/rz-user/button");
    static By loginButton = By.cssSelector("button.auth-modal__submit");
    static By errorMessage = By.cssSelector("p.error-message");
    static By catalogButton = By.cssSelector("button.menu__toggle");
    static By headerLogo = By.cssSelector("a.header__logo");
    static By cartWindow = By.cssSelector("single-modal-window.ng-star-inserted");
    static By cartButton = By.cssSelector("button.header__button[opencart]");
    static By phoneLink = By.partialLinkText("Samsung Galaxy A52");
    static String  phoneTitle = "ROZETKA | Мобильный телефон Samsung Galaxy A52 4/128GB Blue. Цена, купить Мобильный телефон Samsung Galaxy A52 4/128GB Blue в Киеве, Харькове, Днепре, Одессе, Запорожье, Львове. Мобильный телефон Samsung Galaxy A52 4/128GB Blue: обзор, описание, продажа";
    static By buyButton = By.cssSelector("button.button_size_large");
    static By addItemButton = By.cssSelector("button[aria-label=\"Добавить ещё один товар\"]");
    static By numOfItems = By.cssSelector("input.cart-counter__input");
    static By searchField = By.name("search");
    static By searchButton = By.cssSelector("button.search-form__submit");
    static By premium = By.xpath("//p[text()=\" Спробуйте \"]");


    public static void changeLanguage() {
        Configuration.timeout = 10000;
        $(languageSelector).click();
    }

    public static void languageIsChanged() {
        changeLanguage();
        $(premium).should(Condition.visible);
    }

    public static void bannerIsDisplayed() {
        $(banner).should(Condition.visible);
    }

    public static void login(String email, String password) {
        $(profileButton).click();
        $(emailField).sendKeys(email);
        $(passwordField).sendKeys(password);
        $(loginButton).click();
    }

    public static void errorMessageShown(String message) {
        $(errorMessage).should(Condition.appear);
        $(errorMessage).should(Condition.ownText(message));
    }

    public static void returnToMainPage() {
        $(headerLogo).click();
        $("title").shouldHave(attribute("text", title));
    }

    public static void openCart() {
        $(cartButton).click();
        $(cartWindow).should(Condition.visible);
    }

    public static void openProductPage() {
        $(phoneLink).click();
        $("title").shouldHave(attribute("text", phoneTitle));
    }

    public static void search(String query) {
        $(searchField).sendKeys(query);
        $(searchButton).click();
    }

    public static void validateSearch(String query) {
        search(query);
        $("h1").shouldHave(text(query));
    }

    public static void addItemToCart() {
        $(buyButton).click();
    }

    public static void checkCartWindow() {
        $(cartWindow).shouldHave(Condition.visible);
    }

    public static void addAnotherItem() {
        int numItems = Integer.valueOf($(numOfItems).getValue());
        $(addItemButton).click();
        $(numOfItems).shouldHave(Condition.value(String.valueOf(numItems + 1)));
    }
}