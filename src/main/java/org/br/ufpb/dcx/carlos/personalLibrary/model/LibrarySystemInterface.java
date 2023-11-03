package org.br.ufpb.dcx.carlos.personalLibrary.model;

import org.br.ufpb.dcx.carlos.personalLibrary.model.exceptions.BookNotFoundException;

import java.util.List;

public interface LibrarySystemInterface {

    List<Book> getBookList();

    void setBookList(List<Book> bookList);

    DataRecorder getBookRecorder();

    void loadAllBooks();

    void addBookToList(Book book);

    boolean removeBookFromList(String title);

    Book findBookInList(String titleToSearch) throws BookNotFoundException;

    Book findBookByTitleAndAuthor(String titleToSearch, String authorNameToSearch) throws BookNotFoundException;

    List<Book> sortBooksAlphabetically();
    List<Book> sortBooksByGenreAndAuthorsAlphabetically();

    List<Book> booksSortedAlphabeticallyByGenreAndSubgenre();

    List<String> booksSubGenre();

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
}
