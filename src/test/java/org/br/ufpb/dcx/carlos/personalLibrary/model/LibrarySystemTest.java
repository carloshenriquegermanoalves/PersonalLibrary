package org.br.ufpb.dcx.carlos.personalLibrary.model;

import org.br.ufpb.dcx.carlos.personalLibrary.model.exceptions.BookNotFoundException;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class LibrarySystemTest {
    private LibrarySystem librarySystem;

    @BeforeEach
    void setUp() {
        librarySystem = new LibrarySystem();
        createSampleBooksAndAuthors();
    }

    private void createSampleBooksAndAuthors() {
        Author author1 = new Author("Chris Tunwell", "Masculino", "Estados Unidos");
        Author author2 = new Author("Fernando Acuña", "Masculino", "Desconhecido");
        Author author3 = new Author("Agatha Christie", "Feminino", "Inglaterra");

        Book book1 = new Book("Inglês Para Falar em Qualquer Situação", List.of(author1, author2), "Didático", "Idiomas", 208, "Sim", 2022);
        Book book2 = new Book("A Casa do Penhasco", List.of(author3), "Romance Policial", "Detetive", 192, "Sim", 2018);

        librarySystem.addBookToList(book1);
        librarySystem.addBookToList(book2);
    }

    @org.junit.jupiter.api.Test
    void getBookList() {
        List<Book> bookList = librarySystem.getBookList();
        assertEquals(2, bookList.size());
    }

    @org.junit.jupiter.api.Test
    void setBookList() {
        Book existingBook = librarySystem.getBookList().get(1);
        Author newAuthor = new Author("John Bunyan", "Masculino", "Inglaterra");
        Book newBook = new Book("O Peregrino", List.of(newAuthor), "Teologia", "Alegoria Cristã",288, "Sim", 2021);
        List<Book> bookList = new ArrayList<>(librarySystem.getBookList());
        bookList.remove(existingBook);
        bookList.add(newBook);
        librarySystem.setBookList(bookList);
        assertEquals(2, librarySystem.getBookList().size());
        assertTrue(librarySystem.getBookList().contains(newBook));
    }


    @org.junit.jupiter.api.Test
    void getBookRecorder() {
        DataRecorder bookRecorder = librarySystem.getBookRecorder();
        assertNotNull(bookRecorder);
    }

    @org.junit.jupiter.api.Test
    void findBookInList() {
        try {
            Book foundBook = librarySystem.findBookInList("A Casa do Penhasco");
            assertNotNull(foundBook);
        } catch (BookNotFoundException e) {
            fail("Livro não encontrado na lista: " + e.getMessage());
        }
    }

    @org.junit.jupiter.api.Test
    void findBookByTitleAndAuthor() {
        try {
            Book foundBook = librarySystem.findBookByTitleAndAuthor("A Casa do Penhasco", "Agatha Christie");
            assertNotNull(foundBook);
        } catch (BookNotFoundException e) {
            fail("Livro não encontrado na lista: " + e.getMessage());
        }
    }

    @org.junit.jupiter.api.Test
    void addBookToList() {
        Author newAuthor = new Author("J. K. Rowling", "Feminino", "Estados Unidos");
        Book newBook = new Book("Harry Potter e a Pedra Filosofal", List.of(newAuthor), "Fantasia", "Harry Potter", 208, "Sim", 2021);

        librarySystem.addBookToList(newBook);

        assertTrue(librarySystem.getBookList().contains(newBook));
    }


    @org.junit.jupiter.api.Test
    void removeBookFromList() {
        Author author = new Author("Agatha Christie", "Feminino", "Inglaterra");
        Book book = new Book("A Casa do Penhasco", List.of(author), "Romance Policial", "Detetive", 192, "Sim", 2018);

        librarySystem.removeBookFromList("A Casa do Penhasco");

        assertFalse(librarySystem.getBookList().contains(book));
    }

    @org.junit.jupiter.api.Test
    void sortBooksAlphabetically() {
        List<Book> booksSortedAlphabeticallyList = librarySystem.sortBooksAlphabetically();

        List<String> sortedTitles = booksSortedAlphabeticallyList.stream().map(Book::getTitle).collect(Collectors.toList());

        List<String> expectedTitles = Arrays.asList("A Casa do Penhasco", "Inglês Para Falar em Qualquer Situação");

        assertEquals(expectedTitles, sortedTitles);
    }

    @org.junit.jupiter.api.Test
    void sortBooksByGenreAndAuthorsAlphabetically() {
        List<Book> bookList = librarySystem.getBookList();
        bookList.sort(Comparator.comparing(Book::getBookGenre).thenComparing(book -> book.getAuthor().stream().map(Author::getName).collect(Collectors.joining(", "))));
        List<Book> sortedList = librarySystem.sortBooksByGenreAndAuthorsAlphabetically();
        assertEquals(bookList, sortedList);
    }

    @org.junit.jupiter.api.Test
    void findBooksByAuthorName() {
        List<Book> booksByAuthor1 = librarySystem.getBookList().stream().filter(book -> book.getAuthor().stream().anyMatch(author -> author.getName().equals("Stephen King"))).toList();

        List<Book> booksByAuthor2 = librarySystem.getBookList().stream().filter(book -> book.getAuthor().stream().anyMatch(author -> author.getName().equals("Agatha Christie"))).toList();

        assertEquals(0, booksByAuthor1.size());
        assertEquals(1, booksByAuthor2.size());
    }

    @org.junit.jupiter.api.Test
    void findBooksByAuthorGender() {
        List<Book> booksByMaleAuthors = librarySystem.getBookList().stream().filter(book -> book.getAuthor().stream().anyMatch(author -> author.getAuthorGender().equals("Masculino"))).toList();

        List<Book> booksByFemaleAuthors = librarySystem.getBookList().stream().filter(book -> book.getAuthor().stream().anyMatch(author -> author.getAuthorGender().equals("Feminino"))).toList();

        assertEquals(1, booksByMaleAuthors.size());
        assertEquals(1, booksByFemaleAuthors.size());
    }

    @org.junit.jupiter.api.Test
    void findBooksByAuthorsWithDifferentGenders() {
        List<Book> result = librarySystem.getBookList().stream().filter(book -> {
            List<Author> authors = book.getAuthor();
            if (authors.size() > 1) {
                String firstGender = authors.get(0).getAuthorGender();
                return authors.stream().anyMatch(author -> !author.getAuthorGender().equalsIgnoreCase(firstGender));
            }
            return false;
        }).toList();

        assertEquals(0, result.size());
    }

    @org.junit.jupiter.api.Test
    void findBooksByAuthorCountry() {
        List<Book> booksByAmericanAuthors = librarySystem.getBookList().stream().filter(book -> book.getAuthor().stream().anyMatch(author -> author.getCountryOfBirth().equals("Estados Unidos"))).toList();

        assertEquals(1, booksByAmericanAuthors.size());
    }

    @org.junit.jupiter.api.Test
    void findBooksByGenre() {
        List<Book> detectiveBooks = librarySystem.getBookList().stream().filter(book -> book.getBookGenre().equals("Romance Policial")).toList();

        List<Book> studyingBooks = librarySystem.getBookList().stream().filter(book -> book.getBookGenre().equals("Didático")).toList();

        assertEquals(1, detectiveBooks.size());
        assertEquals(1, studyingBooks.size());
    }

    @org.junit.jupiter.api.Test
    void findBooksByMorePageCount() {
        int pageLimit = 150;
        List<Book> result = librarySystem.getBookList().stream().filter(book -> book.getPageCount() >= pageLimit).toList();

        int expectedSize = 2;
        assertEquals(expectedSize, result.size());

        for (Book b : result) {
            assertTrue(b.getPageCount() >= pageLimit);
        }
    }


    @org.junit.jupiter.api.Test
    void findBooksByLessPageCount() {
        int pageLimit = 300;
        List<Book> result = librarySystem.getBookList().stream().filter(book -> book.getPageCount() <= pageLimit).toList();

        int expectedSize = 2;
        assertEquals(expectedSize, result.size());

        result.forEach(book -> assertTrue(book.getPageCount() <= pageLimit));
    }

    @org.junit.jupiter.api.Test
    void findBooksByYearOfReading() {
        int yearOfReading = 2018;
        List<Book> result = librarySystem.getBookList().stream().filter(book -> book.getYearOfReading() == yearOfReading).toList();

        int expectedSize = 1;
        assertEquals(expectedSize, result.size());
    }

    @org.junit.jupiter.api.Test
    void findUnreadBooks() {
        List<Book> result = librarySystem.getBookList().stream().filter(book -> book.getReadStatus().equalsIgnoreCase("não")).toList();

        int expectedSize = 0;
        assertEquals(expectedSize, result.size());
    }


    @org.junit.jupiter.api.Test
    void authorList() {
        List<Author> authorList = librarySystem.authorList();
        assertEquals(3, authorList.size());
    }

    @org.junit.jupiter.api.Test
    void femaleAuthorsList() {
        List<Author> femaleAuthorsList = librarySystem.femaleAuthorsList();
        assertEquals(1, femaleAuthorsList.size());
    }

    @org.junit.jupiter.api.Test
    void maleAuthorsList() {
        List<Author> maleAuthorsList = librarySystem.maleAuthorsList();
        assertEquals(2, maleAuthorsList.size());
    }

    @org.junit.jupiter.api.Test
    void otherGenderAuthorsList() {
        List<Author> otherGenderAuthors = librarySystem.otherGenderAuthorsList();
        assertEquals(0, otherGenderAuthors.size());
    }

    @org.junit.jupiter.api.Test
    void genreBooksList() {
        List<String> genreBooks = librarySystem.genreBooksList();
        assertEquals(2, genreBooks.size());
        assertTrue(genreBooks.contains("Didático"));
        assertTrue(genreBooks.contains("Romance Policial"));
    }

    @org.junit.jupiter.api.Test
    void totalPageCount() {
        int totalPageCount = librarySystem.totalPageCount();
        assertEquals(400, totalPageCount);
    }
}