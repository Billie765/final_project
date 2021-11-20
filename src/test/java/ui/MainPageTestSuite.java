package ui;

import org.testng.annotations.Test;
import ui.BaseTest;

import static ui.locators.MainPageSelenide.*;

public class MainPageTestSuite extends BaseTest {


    @Test
    public void verifyThatBannerIsPresent() {
        bannerIsDisplayed();
    }

    @Test(dataProvider="customSearch")
    public void customSearch(String query) {
        validateSearch(query);
    }

    @Test
    public void changedLanguage() {
        languageIsChanged();
    }

    @Test(dataProvider = "invalidCredentials")
    public void invalidLogin(String email, String password) {
        login(email, password);
        errorMessageShown("Введен неверный адрес эл.почты или номер телефона");
    }

    @Test(dataProvider = "customSearch")
    public void checkSearch(String query) {
        validateSearch(query);
    }

    @Test
    public void returnMainMenu() {
        search("Samsung");
        returnToMainPage();
    }

    @Test
    public void checkProductPage() {
        search("Samsung");
        openProductPage();
    }

    @Test
    public void cartWindowIsDisplayed() {
        search("Samsung");
        openProductPage();
        addItemToCart();
        checkCartWindow();
    }

    @Test
    public void itemIsAdded() {
        search("Samsung");
        openProductPage();
        addItemToCart();
        addAnotherItem();
    }
}
