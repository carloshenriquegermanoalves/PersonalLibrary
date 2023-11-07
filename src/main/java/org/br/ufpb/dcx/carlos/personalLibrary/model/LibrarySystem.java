package org.br.ufpb.dcx.carlos.personalLibrary.model;

import org.br.ufpb.dcx.carlos.personalLibrary.model.exceptions.BookNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LibrarySystem implements LibrarySystemInterface {

    private final DataRecorder BOOKRECORDER;
    private List<Book> bookList;

    public LibrarySystem() {
        this.bookList = new ArrayList<>();
        this.BOOKRECORDER = new DataRecorder();
    }

    @Override
    public List<Book> getBookList() {
        return this.bookList;
    }

    @Override
    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public DataRecorder getBookRecorder() {
        return BOOKRECORDER;
    }

    @Override
    public void loadAllBooks() {
        try {
            this.bookList = BOOKRECORDER.retrieveBookData();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book findBookInList(String titleToSearch) throws BookNotFoundException {
        return bookList.stream().filter(book -> {
                    String title = book.getTitle();
                    return title != null && title.equalsIgnoreCase(titleToSearch);
                }).findFirst().orElseThrow(() -> new BookNotFoundException("Livro não encontrado com o título: " + titleToSearch));
    }


    @Override
    public Book findBookByTitleAndAuthor(String title, String authorName) throws BookNotFoundException {
        List<Book> foundBooks = bookList.stream().filter(book -> book.getTitle().equalsIgnoreCase(title)).filter(book -> book.getAuthor().stream().anyMatch(author -> author.getName().equalsIgnoreCase(authorName))).toList();
        if (foundBooks.isEmpty()) {throw new BookNotFoundException("Livro não encontrado com título: " + title + " e autor: " + authorName);}
        return foundBooks.get(0);
    }

    @Override
    public void addBookToList(Book book) {
        bookList.add(book);
    }

    @Override
    public boolean removeBookFromList(String title) {
        return bookList.removeIf(book -> title.equalsIgnoreCase(book.getTitle()));
    }

    @Override
    public List<Book> sortBooksAlphabetically() {
        return bookList.stream().sorted(Comparator.comparing(book -> book.getTitle().toLowerCase())).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Override
    public List<Book> sortBooksByGenreAndAuthorsAlphabetically() {
        return bookList.stream().sorted(Comparator.comparing(Book::getBookGenre).thenComparing(book -> book.getAuthor().getClass().getName().toLowerCase())).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Override
    public List<Book> booksSortedAlphabeticallyByGenreAndSubgenre() {
        List<Book> books = getBookList();
        books.sort(Comparator.comparing(Book::getBookGenre).thenComparing(Book::getBookSubGenre).thenComparing(Book::getTitle));
        return books;
    }

    @Override
    public List<String> booksSubGenre() {
        return bookList.stream().map(Book::getBookSubGenre).distinct().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Override
    public List<Book> findBooksByAuthorName(String nameToSearch) {
        return bookList.stream().filter(book -> book.getAuthor().stream().anyMatch(author -> author.getName().equalsIgnoreCase(nameToSearch))).collect(Collectors.toList());
    }

    @Override
    public List<Book> findBooksByAuthorGender(String genderToSearch) {
        return bookList.stream().filter(book -> book.getAuthor().stream().anyMatch(author -> author.getAuthorGender().equalsIgnoreCase(genderToSearch))).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Override
    public List<Book> findBooksByAuthorsWithDifferentGenders() {
        return bookList.stream().filter(book -> book.getAuthor().stream().anyMatch(author -> !author.getAuthorGender().equalsIgnoreCase("Masculino") && !author.getAuthorGender().equalsIgnoreCase("Feminino"))).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Override
    public List<Book> findBooksByAuthorCountry(String countryToSearch) {
        return bookList.stream().filter(book -> book.getAuthor().stream().anyMatch(author -> author.getCountryOfBirth().equalsIgnoreCase(countryToSearch))).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Override
    public List<Book> findBooksByGenre(String genreToSearch) {
        return bookList.stream().filter(book -> book.getBookGenre().equalsIgnoreCase(genreToSearch)).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Override
    public List<Book> findBooksByMorePageCount(int pageCount) {
        return bookList.stream().filter(book -> book.getPageCount() > pageCount).toList();
    }

    @Override
    public List<Book> findBooksByLessPageCount(int pageCount) {
        return bookList.stream().filter(book -> book.getPageCount() < pageCount).toList();
    }

    @Override
    public List<Book> findBooksByYearOfReading(int readYear) {
        return bookList.stream().filter(book -> book.getYearOfReading() == readYear).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Override
    public List<Book> findUnreadBooks() {
        return bookList.stream().filter(book -> book.getReadStatus().equalsIgnoreCase("2")).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Override
    public List<List<Book>> findBooksBySubGenre() {
        List<String> uniqueSubGenres = bookList.stream().map(Book::getBookSubGenre).distinct().toList();
        return uniqueSubGenres.stream().map(subGenre -> bookList.stream().filter(book -> subGenre.equals(book.getBookSubGenre())).collect(Collectors.toList())).toList();
    }

    @Override
    public List<Author> authorList() {
        return bookList.stream().flatMap(book -> book.getAuthor().stream()).distinct().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Override
    public List<Author> femaleAuthorsList() {
        return authorList().stream().filter(author -> author.getAuthorGender().equalsIgnoreCase("feminino")).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Override
    public List<Author> maleAuthorsList() {
        return authorList().stream().filter(author -> author.getAuthorGender().equalsIgnoreCase("masculino")).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Override
    public List<Author> otherGenderAuthorsList() {
        return authorList().stream().filter(author -> !author.getAuthorGender().equalsIgnoreCase("masculino") && !author.getAuthorGender().equalsIgnoreCase("feminino")).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Override
    public List<String> genreBooksList() {
        return bookList.stream().map(Book::getBookGenre).distinct().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Override
    public int totalPageCount() {
        return bookList.stream().mapToInt(Book::getPageCount).sum();
    }
}