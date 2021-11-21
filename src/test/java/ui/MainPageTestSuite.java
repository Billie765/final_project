package ui;

import org.testng.annotations.Test;
import io.qameta.allure.Description;

import static ui.locators.MainPageSelenide.*;

public class MainPageTestSuite extends BaseTest {


//    @Test
//    @Description("Verify that the banner is present")
//    public void verifyThatBannerIsPresent() {
//        bannerIsDisplayed();
//    }

    @Test(dataProvider="customSearch")
    @Description("Verify that search works")
    public void customSearch(String query) {
        validateSearch(query);
    }

    @Test
    @Description("Verify that the language is changed")
    public void changedLanguage() {
        languageIsChanged();
    }

    @Test(dataProvider = "invalidCredentials")
    @Description("Verify that the error message is correct")
    public void invalidLogin(String email, String password) {
        login(email, password);
        errorMessageShown("Введен неверный адрес эл.почты или номер телефона");
    }

    @Test
    @Description("Validate that user can return to the main menu")
    public void returnMainMenu() {
        search("Samsung");
        returnToMainPage();
    }

    @Test
    @Description("Validate that product page title is correct")
    public void checkProductPage() {
        search("Samsung");
        openProductPage();
    }

    @Test
    @Description("Validate that the cart is displayed after adding item to the cart")
    public void cartWindowIsDisplayed() {
        search("Samsung");
        openProductPage();
        addItemToCart();
        checkCartWindow();
    }

    @Test
    @Description("Validate that the number of items in cart is changed")
    public void itemIsAdded() {
        search("Samsung");
        openProductPage();
        addItemToCart();
        addAnotherItem();
    }
}
