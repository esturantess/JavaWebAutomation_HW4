package org.example.pom.elements;

import com.codeborne.selenide.SelenideElement;

import java.io.File;
import java.util.Objects;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ProfilePage {
    private final SelenideElement fullNameInAdditionalInfo = $x("//h3/following-sibling::div" +
            "//div[contains(text(), 'Full name')]/following-sibling::div");
    private final SelenideElement fullNameInAvatarSection = $("div.mdc-card h2");

    private final SelenideElement editIconInAvatarSection = $("div.mdc-card div.mdc-card__action-icons button[title='More options']");


    private final SelenideElement newAvatarFieldOnEditingPopup = $x("//form[@id='update-item']//span[contains(text(), 'New Avatar')]/following-sibling::input");

    private final SelenideElement usernameLinkInNavBar = $("nav li.mdc-menu-surface--anchor a");

    private final SelenideElement birthDateFieldOnEditingPopup = $x("//*[@id=\"update-item\"]/div[3]/label/input");

    private final SelenideElement saveButtonOnEditingPopup = $x("//*[@id=\"update-item\"]/div[8]/button");
    private final SelenideElement closeIconOnEditingPopup = $x("//*[@id=\"app\"]/main/div/div/div[2]/div[2]/div/div[1]/button");

    private final SelenideElement dateOfBirthFieldInAdditionalInfo = $x("//*[@id=\"app\"]/main/div/div/div[1]/div/div[2]/div/div[2]/div[2]");


    public void clickEditIconInAvatarSection() {
        editIconInAvatarSection.shouldBe(visible).click();
    }

    public void uploadPictureFileToAvatarField(String filePath) {
        newAvatarFieldOnEditingPopup.shouldBe(visible).uploadFile(new File(filePath));
    }

    public String getAvatarInputValueOnSettingPopup() {
        String inputValue = newAvatarFieldOnEditingPopup.getValue();
        return Objects.requireNonNull(inputValue)
                .substring(inputValue.lastIndexOf("\\") + 1);
    }

    public String getFullNameFromAdditionalInfo() {
        return fullNameInAdditionalInfo.shouldBe(visible).text();
    }

    public String getFullNameFromAvatarSection() {
        return fullNameInAvatarSection.shouldBe(visible).text();
    }

    public void updateBirthDate(String day, String month, String year) {
        birthDateFieldOnEditingPopup.shouldBe(visible).setValue(day + month + year);
    }

    public void saveChangesInEditingPopup() {
        saveButtonOnEditingPopup.shouldBe(visible).click();
    }

    public void closeEditingPopup() {
        closeIconOnEditingPopup.shouldBe(visible).click();
        closeIconOnEditingPopup.shouldNotBe(visible);
    }

    public String getBirthDateFromAdditionalInfo() {
        return dateOfBirthFieldInAdditionalInfo.shouldBe(visible).getText();
    }
}
