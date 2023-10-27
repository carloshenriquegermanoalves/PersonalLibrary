package org.br.ufpb.dcx.carlos.personalLibrary.model;

import org.br.ufpb.dcx.carlos.personalLibrary.model.exceptions.BookNotFoundException;

import java.io.IOException;
import java.util.*;

import javax.swing.JOptionPane;

public class LibrarySystem implements LibrarySystemInterface {

    private List<Book> bookList;
    private final DataRecorder bookRecorder;

    public LibrarySystem() {
        this.bookList = new ArrayList<>();
        this.bookRecorder = new DataRecorder();
    }

    @Override
    public List<Book> getBookList() {
        return this.bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public List<Book> loadAllBooks() {
        try {
            this.bookList = bookRecorder.retrieveBookData();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
        return this.bookList;
    }

    @Override
    public Book findBookInList(String titleToSearch) throws BookNotFoundException {
        return bookList.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(titleToSearch))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("Livro não encontrado com o título: " + titleToSearch));
    }

    @Override
    public boolean addBookToList(Book book) {
        return bookList.add(book);
    }

    @Override
    public boolean removeBookFromList(String title) {
        return bookList.removeIf(book -> title.equalsIgnoreCase(book.getTitle()));
    }

    @Override
    public List<Book> sortBooksAlphabetically() {
        return bookList.stream()
                .sorted(Comparator.comparing(book -> book.getTitle().toLowerCase()))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Override
    public List<Book> sortBooksByAuthorsAlphabetically() {
        List<Book> sortedList = new ArrayList<>(bookList);
        sortedList.sort((book1, book2) -> book1.getAuthor().getClass().getName().compareToIgnoreCase(book2.getAuthor().getClass().getName()));
        return sortedList;
    }

    @Override
    public List<Book> sortBooksByGenreAlphabetically() {
        List<Book> sortedList = new ArrayList<>(bookList);
        sortedList.sort((book1, book2) -> book1.getBookGenre().compareToIgnoreCase(book2.getBookGenre()));
        return sortedList;
    }

    @Override
    public List<Book> sortBooksByGenreAndAuthorsAlphabetically() {
        return bookList.stream()
                .sorted(Comparator.comparing(Book::getBookGenre)
                            .thenComparing(book -> book.getAuthor().getClass().getName().toLowerCase()))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Override
    public List<Book> findBooksByAuthorName(String nameToSearch) {
        return bookList.stream()
                .filter(book -> book.getAuthor().getClass().getName().equalsIgnoreCase(nameToSearch))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Override
    public List<Book> findBooksByAuthorGender(String genderToSearch) {
        return bookList.stream()
                .filter(book -> book.getAuthor().stream().anyMatch(author -> author.getAuthorGender().equalsIgnoreCase(genderToSearch)))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Override
    public List<Book> findBooksByAuthorsWithDifferentGenders() {
        return bookList.stream()
                .filter(book -> book.getAuthor().stream().anyMatch(author ->
                        !author.getAuthorGender().equalsIgnoreCase("Male") && !author.getAuthorGender().equalsIgnoreCase("Female")))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Override
    public List<Book> findBooksByAuthorCountry(String countryToSearch) {
        return bookList.stream()
                .filter(book -> book.getAuthor().stream().anyMatch(author -> author.getCountryOfBirth().equalsIgnoreCase(countryToSearch)))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Override
    public List<Book> findBooksByGenre(String genreToSearch) {
        return bookList.stream()
                .filter(book -> book.getBookGenre().equalsIgnoreCase(genreToSearch))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Override
    public List<Book> findBooksByPageCount(int pageCount, int searchType) {
        return bookList.stream()
                .filter(book -> (searchType == 1 && book.getPageCount() > pageCount) || (searchType == 2 && book.getPageCount() < pageCount))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Override
    public List<Book> findBooksByYearOfReading(int readYear) {
        return bookList.stream()
                .filter(book -> book.getYearOfReading() == readYear)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Override
    public List<Book> findUnreadBooks() {
        return bookList.stream()
                .filter(book -> book.getReadStatus().equalsIgnoreCase("Não"))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Override
    public List<Author> authorList() {
        return bookList.stream()
                .flatMap(book -> book.getAuthor().stream())
                .distinct()
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }


    @Override
    public List<Author> femaleAuthorsList() {
        return authorList().stream()
                .filter(author -> author.getAuthorGender().equalsIgnoreCase("feminino"))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Override
    public List<Author> maleAuthorsList() {
        return authorList().stream()
                .filter(author -> author.getAuthorGender().equalsIgnoreCase("masculino"))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Override
    public List<Author> otherGenderAuthorsList() {
        return authorList().stream()
                .filter(author -> !author.getAuthorGender().equalsIgnoreCase("masculino") && !author.getAuthorGender().equalsIgnoreCase("feminino"))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Override
    public List<String> genreBooksList() {
        return bookList.stream()
                .map(Book::getBookGenre)
                .distinct()
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Override
    public int totalPageCount() {
        return bookList.stream().mapToInt(Book::getPageCount).sum();
    }

    @Override
    public void displayAllBooks() {
        bookList.forEach(book -> JOptionPane.showMessageDialog(null, book.getTitle()));
    }

    @Override
    public void displayBookList(List<Book> booksToDisplay) {
        booksToDisplay.forEach(book -> JOptionPane.showMessageDialog(null, book.getTitle()));
    }

    @Override
    public void displayAllAuthors() {
        authorList().forEach(author -> JOptionPane.showMessageDialog(null, author.getName()));
    }

    @Override
    public void displayAuthorList(List<Author> authorList) {
        authorList.forEach(author -> JOptionPane.showMessageDialog(null, author.getName()));
    }

    @Override
    public void displayAllGenres() {
        genreBooksList().forEach(genre -> JOptionPane.showMessageDialog(null, genre));
    }

}
