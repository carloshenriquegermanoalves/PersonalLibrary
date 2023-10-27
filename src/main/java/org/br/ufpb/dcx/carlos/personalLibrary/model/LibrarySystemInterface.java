package org.br.ufpb.dcx.carlos.personalLibrary.model;

import org.br.ufpb.dcx.carlos.personalLibrary.model.exceptions.BookNotFoundException;

import java.util.*;

public interface LibrarySystemInterface {

    List<Book> getBookList();

    void setBookList(List<Book> bookList);

    List<Book> loadAllBooks();

    boolean addBookToList(Book book);

    boolean removeBookFromList(String title);

    Book findBookInList(String titleToSearch) throws BookNotFoundException;

    List<Book> sortBooksAlphabetically();

    List<Book> sortBooksByAuthorsAlphabetically();

    List<Book> sortBooksByGenreAlphabetically();

    List<Book> sortBooksByGenreAndAuthorsAlphabetically();

    List<Book> findBooksByAuthorName(String authorToSearch);

    List<Book> findBooksByAuthorGender(String genderToSearch);

    List<Book> findBooksByAuthorsWithDifferentGenders();

    List<Book> findBooksByAuthorCountry(String countryToSearch);

    List<Book> findBooksByGenre(String genreToSearch);

    List<Book> findBooksByLessPageCount(int pageCount);

    List<Book> findBooksByMorePageCount(int pageCount);

    List<Book> findBooksByYearOfReading(int yearOfReading);

    List<Book> findUnreadBooks();

    List<Author> authorList();

    List<Author> femaleAuthorsList();

    List<Author> maleAuthorsList();

    List<Author> otherGenderAuthorsList();

    List<String> genreBooksList();

    int totalPageCount();

    void displayAllBooks();

    void displayBookList(List<Book> bookList);

    void displayAllAuthors();

    void displayAuthorList(List<Author> authorList);

    void displayAllGenres();
}
