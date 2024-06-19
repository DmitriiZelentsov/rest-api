package tests;

import api.spec.BookApi;
import helpers.WithLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

import static api.AuthApi.authorizeRequest;
import static testdata.TestData.bookName;
import static testdata.TestData.username;

@DisplayName("Удаление книги из корзины")
public class BooksTest extends TestBase{
    ProfilePage profilePage = new ProfilePage();
    BookApi bookApi = new BookApi();

    @Test
    @Tag("deleteBook")
    @WithLogin
    @DisplayName("Удаление книги из корзины онлайн магазина")
    void deleteAllBooksTest() {
        bookApi.deleteAllBooks(authorizeRequest().getUserId());
        bookApi.addBooks();
        profilePage.openProfilePage()
                .checkUserName(username)
                .checkHaveBook(bookName)
                .deleteBook()
                .checkBookDeleted(bookName);
    }
}
