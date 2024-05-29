package org.example.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.example.pom.LoginPage;
import org.example.pom.MainPage;
import org.example.pom.elements.ProfilePage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GeekBrainsStandTests {

    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;
    private MainPage mainPage;

    private static String USERNAME;
    private static String PASSWORD;

    @BeforeAll
    public static void setupClass() {

        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.browser = "chrome";
        Configuration.browserVersion = "125";
        Map<String, Object> options = new HashMap<>();
        options.put("enableVNC", true);
        options.put("enableLog", true);
        Configuration.browserCapabilities.setCapability("selenoid:options", options);

        USERNAME = System.getProperty("geekbrains_username", System.getenv("geekbrains_username"));
        PASSWORD = System.getProperty("geekbrains_password", System.getenv("geekbrains_password"));
    }

    @BeforeEach
    public void setupTest() {
        Selenide.open("https://test-stand.gb.ru/login");
        driver = WebDriverRunner.getWebDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loginPage = new LoginPage();
    }

    @Test
    public void testLoginWithEmptyFields() {
        loginPage.clickLoginButton();
        Selenide.sleep(6000);
        assertEquals("401 Invalid credentials.", loginPage.getErrorBlockText());
    }

    @Test
    public void testBirthDateChange() {
        loginPage.login(USERNAME, PASSWORD);
        mainPage = Selenide.page(MainPage.class);
        assertTrue(mainPage.getUsernameLabelText().contains(USERNAME));
        mainPage.clickUsernameLabel();
        mainPage.clickProfileLink();
        ProfilePage profilePage = Selenide.page(ProfilePage.class);
        profilePage.clickEditIconInAvatarSection();
        profilePage.updateBirthDate("10", "05", "2010");
        Selenide.sleep(5000);
        profilePage.saveChangesInEditingPopup();
        profilePage.closeEditingPopup();
        assertEquals(profilePage.getBirthDateFromAdditionalInfo(), "05.10.2010");
    }

//    @Disabled
//    @Test
//    public void testAddingGroupOnMainPage() {
//        loginPage.login(USERNAME, PASSWORD);
//        mainPage = new MainPage(driver, wait);
//        assertTrue(mainPage.getUsernameLabelText().contains(USERNAME));
//        String groupTestName = "New Test Group " + System.currentTimeMillis();
//        mainPage.createGroup(groupTestName);
//        assertTrue(mainPage.waitAndGetGroupTitleByText(groupTestName).isDisplayed());
//    }

//    @Disabled
//    @Test
//    public void testArchiveGroupOnMainPage() {
//        loginPage.login(USERNAME, PASSWORD);
//        mainPage = new MainPage(driver, wait);
//        assertTrue(mainPage.getUsernameLabelText().contains(USERNAME));
//        String groupTestName = "New Test Group " + System.currentTimeMillis();
//        mainPage.createGroup(groupTestName);
//        mainPage.closeCreateGroupModalWindow();
//        assertEquals("active", mainPage.getStatusOfGroupWithTitle(groupTestName));
//        mainPage.clickTrashIconOnGroupWithTitle(groupTestName);
//        assertEquals("inactive", mainPage.getStatusOfGroupWithTitle(groupTestName));
//        mainPage.clickRestoreFromTrashIconOnGroupWithTitle(groupTestName);
//        assertEquals("active", mainPage.getStatusOfGroupWithTitle(groupTestName));
//    }
//
//    @Disabled
//    @Test
//    void testBlockingStudentInTableOnMainPage() throws InterruptedException {
//        loginPage.login(USERNAME, PASSWORD);
//        mainPage = new MainPage(driver, wait);
//        assertTrue(mainPage.getUsernameLabelText().contains(USERNAME));
//        String groupTestName = "New Test Group " + System.currentTimeMillis();
//        mainPage.createGroup(groupTestName);
//        mainPage.closeCreateGroupModalWindow();
//        mainPage.clickAddStudentsIconOnGroupWithTitle(groupTestName);
//        mainPage.typeAmountOfStudentsInCreateStudentsForm(3);
//        mainPage.clickSaveButtonOnCreateStudentsForm();
//        mainPage.closeCreateStudentsModalWindow();
//        mainPage.clickZoomInIconOnGroupWithTitle(groupTestName);
//        String firstGeneratedStudentName = mainPage.getFirstGeneratedStudentName();
//        assertEquals("active", mainPage.getStatusOfStudentWithName(firstGeneratedStudentName));
//        mainPage.clickTrashIconOnStudentWithName(firstGeneratedStudentName);
//        assertEquals("block", mainPage.getStatusOfStudentWithName(firstGeneratedStudentName));
//        mainPage.clickRestoreFromTrashIconOnStudentWithName(firstGeneratedStudentName);
//        assertEquals("active", mainPage.getStatusOfStudentWithName(firstGeneratedStudentName));
//    }

    @AfterEach
    public void teardown() {
        WebDriverRunner.closeWebDriver();
    }

}

