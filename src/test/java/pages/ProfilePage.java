package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePage {
    private final SelenideElement userName = $("#userName-value"),
            deleteBook = $("#delete-record-undefined"),
            confirm = $("#closeSmallModal-ok"),
            booksList = $(".ReactTable");

    @Step("Открытие страницы профиля пользователя")
    public ProfilePage openProfilePage() {
        open("/profile");

        return this;
    }

    @Step("Пользователь авторизован - имя пользователя отображается на странице")
    public ProfilePage checkUserName(String username) {
        userName.shouldHave(text(username));

        return this;
    }

    @Step("Удаление книги")
    public ProfilePage deleteBook() {
        deleteBook.click();
        confirm.click();

        return this;
    }

    @Step("Проверка наличия книги в списке пользователя")
    public ProfilePage checkHaveBook(String bookTitle) {
        booksList.shouldHave(text(bookTitle));

        return this;
    }

    @Step("Проверка отсутствия книги в профиле(корзине) клиента")
    public void checkBookDeleted(String bookTitle) {
        booksList.shouldNotHave(text(bookTitle));
    }
}
