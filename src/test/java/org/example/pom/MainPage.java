package org.example.pom;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.example.pom.elements.GroupTableRow;
import org.example.pom.elements.StudentTableRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {

    private final SelenideElement usernameLinkInNavBar = $("nav li.mdc-menu-surface--anchor a");
    private final SelenideElement profileLinkInNavBar = $("nav li.mdc-menu-surface--anchor div li");

//    private final SelenideElement createGroupButton =$("create-btn");
//    private final SelenideElement groupNameField = $x("//form//span[contains(text(), 'Group name')]/following-sibling::input");
//    private final SelenideElement submitButtonOnModalWindow = $("form div.submit button");
//    private final SelenideElement closeCreateGroupIcon = $x("//span[text()='Creating Study Group']" +
//            "//ancestor::div[contains(@class, 'form-modal-header')]//button");
    private final SelenideElement createStudentFormInput = $("div#generateStudentsForm-content input");
    private final SelenideElement saveCreateStudentsForm = $("div#generateStudentsForm-content div.submit button");
    private final SelenideElement closeCreateStudentsFormIcon = $x("//h2[@id='generateStudentsForm-title']/../button");

//    @FindBy(xpath = "//table[@aria-label='Tutors list']/tbody/tr")
//    private List<WebElement> rowsInGroupTable;
//    @FindBy(xpath = "//table[@aria-label='User list']/tbody/tr")
//    private List<WebElement> rowsInStudentTable;
//
//        public MainPage(WebDriver driver, WebDriverWait wait) {
//        this.wait = wait;
//        PageFactory.initElements(driver, this);
//    }

//    public WebElement waitAndGetGroupTitleByText(String title) {
//        String xpath = String.format("//table[@aria-label='Tutors list']/tbody//td[text()='%s']", title);
//        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
//    }

//    public void createGroup(String groupName) {
//        createGroupButton.shouldBe(visible).click();
//        groupNameField.shouldBe(visible).sendKeys(groupName);
//        submitButtonOnModalWindow.click();
//        waitAndGetGroupTitleByText(groupName);
//    }
//
//    public void closeCreateGroupModalWindow() {
//        closeCreateGroupIcon.shouldBe(visible).click();
//        closeCreateGroupIcon.shouldNotBe(visible);
//    }

    public void typeAmountOfStudentsInCreateStudentsForm(int amount) {
        createStudentFormInput.shouldBe(visible).sendKeys(String.valueOf(amount));
    }

    public void clickSaveButtonOnCreateStudentsForm() throws InterruptedException {
        saveCreateStudentsForm.shouldBe(visible).click();
        Selenide.sleep(5000);
    }

    public void closeCreateStudentsModalWindow() {
        closeCreateStudentsFormIcon.click();
        closeCreateStudentsFormIcon.shouldNotBe(visible);
    }

    public String getUsernameLabelText() {
        return usernameLinkInNavBar.shouldBe(visible)
                .getText().replace("\n", " ");
    }

//    // Group Table Section
//    public void clickTrashIconOnGroupWithTitle(String title) {
//        getGroupRowByTitle(title).clickTrashIcon();
//    }
//
//    public void clickRestoreFromTrashIconOnGroupWithTitle(String title) {
//        getGroupRowByTitle(title).clickRestoreFromTrashIcon();
//    }
//
//    public void clickAddStudentsIconOnGroupWithTitle(String title) {
//        getGroupRowByTitle(title).clickAddStudentsIcon();
//    }
//
//    public void clickZoomInIconOnGroupWithTitle(String title) {
//        getGroupRowByTitle(title).clickZoomInIcon();
//    }
//
//    public String getStatusOfGroupWithTitle(String title) {
//        return getGroupRowByTitle(title).getStatus();
//    }
//
//    private GroupTableRow getGroupRowByTitle(String title) {
//        return rowsInGroupTable.stream()
//                .map(GroupTableRow::new)
//                .filter(row -> row.getTitle().equals(title))
//                .findFirst().orElseThrow();
//    }
//
//    // Students Table Section
//
//    public void clickTrashIconOnStudentWithName(String name) {
//        getStudentRowByName(name).clickTrashIcon();
//    }
//
//    public void clickRestoreFromTrashIconOnStudentWithName(String name) {
//        getStudentRowByName(name).clickRestoreFromTrashIcon();
//    }
//
//    public String getStatusOfStudentWithName(String name) {
//        return getStudentRowByName(name).getStatus();
//    }
//
//    public String getFirstGeneratedStudentName() {
//        wait.until(ExpectedConditions.visibilityOfAllElements(rowsInStudentTable));
//        return rowsInStudentTable.stream()
//                .map(StudentTableRow::new)
//                .findFirst().orElseThrow().getName();
//    }
//
//    private StudentTableRow getStudentRowByName(String name) {
//        wait.until(ExpectedConditions.visibilityOfAllElements(rowsInStudentTable));
//        return rowsInStudentTable.stream()
//                .map(StudentTableRow::new)
//                .filter(row -> row.getName().equals(name))
//                .findFirst().orElseThrow();
//    }

    public void clickUsernameLabel() {
        usernameLinkInNavBar.shouldBe(visible).click();
    }

    public void clickProfileLink() {
        profileLinkInNavBar.shouldBe(visible).click();
    }
}
