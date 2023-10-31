package org.br.ufpb.dcx.carlos.personalLibrary.model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibrarySystemTest {

    private LibrarySystem librarySystem;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        librarySystem = new LibrarySystem();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        //TODO
    }

    @org.junit.jupiter.api.Test
    void getBookList() {
        Author authorTest1 = new Author("Chris Tunwell", "Masculino", "Estados Unidos");
        Author authorTest2 = new Author("Fernando Acuña", "Masculino", "Desconhecido");

        List<Author> authorsBookTest = new ArrayList<>();
        authorsBookTest.add(authorTest1);
        authorsBookTest.add(authorTest2);

        Book bookTest = new Book("Inglês Para Falar em Qualquer Situação", authorsBookTest, "Didático", 208, "Sim", 2022);
        librarySystem.addBookToList(bookTest);

        List<Book> bookList = librarySystem.getBookList();
        assertEquals(1, bookList.size());
    }

    @org.junit.jupiter.api.Test
    void setBookList() {
        //TODO
    }

    @org.junit.jupiter.api.Test
    void getBookRecorder() {
        //TODO
    }

    @org.junit.jupiter.api.Test
    void loadAllBooks() {
        //TODO
    }

    @org.junit.jupiter.api.Test
    void findBookInList() {
        //TODO
    }

    @org.junit.jupiter.api.Test
    void findBookByTitleAndAuthor() {
        //TODO
    }

    @org.junit.jupiter.api.Test
    void addBookToList() {
        Author author = new Author("John Bunyan", "Masculino", "Inglaterra");
        Book book = new Book("O Peregrino", List.of(author), "Teologia", 200, "Sim", 2021);

        librarySystem.addBookToList(book);

        assertTrue(librarySystem.getBookList().contains(book));
    }

    @org.junit.jupiter.api.Test
    void removeBookFromList() {
        Author author = new Author("Agatha Christie", "Feminino", "Inglaterra");
        Book book = new Book("A Casa do Penhasco", List.of(author), "Romance Policial", 192, "Sim", 2018);

        librarySystem.addBookToList(book);
        librarySystem.removeBookFromList("A Casa do Penhasco");

        assertFalse(librarySystem.getBookList().contains(book));
    }

    @org.junit.jupiter.api.Test
    void sortBooksAlphabetically() {
        Author author1 = new Author("Stephen King", "Masculino", "Estados Unidos");
        Author author2 = new Author("Lee Strobel", "Masculino", "Estados Unidos");

        List<Author> authorBook1 = List.of(author1);
        List<Author> authorBook2 = List.of(author2);

        Book book1 = new Book("O Iluminado", authorBook1, "Terror", 424, "Sim", 2021);
        Book book2 = new Book("Em Defesa de Cristo", authorBook2, "Teologia", 368, "Sim", 2023);
        Book book3 = new Book("Sob a Redoma", authorBook1, "Thriller", 960, "Sim", 2023);

        librarySystem.addBookToList(book1);
        librarySystem.addBookToList(book2);
        librarySystem.addBookToList(book3);

        List<Book> booksSortedAlphabeticallyList = librarySystem.sortBooksAlphabetically();

        assertEquals("Em Defesa de Cristo", booksSortedAlphabeticallyList.get(0).getTitle());
        assertEquals("O Iluminado", booksSortedAlphabeticallyList.get(1).getTitle());
        assertEquals("Sob a Redoma", booksSortedAlphabeticallyList.get(2).getTitle());
    }

    @org.junit.jupiter.api.Test
    void sortBooksByAuthorsAlphabetically() {
        //TODO
    }

    @org.junit.jupiter.api.Test
    void sortBooksByGenreAlphabetically() {
        //TODO
    }

    @org.junit.jupiter.api.Test
    void sortBooksByGenreAndAuthorsAlphabetically() {
        //TODO
    }

    @org.junit.jupiter.api.Test
    void findBooksByAuthorName() {
        Author author1 = new Author("Stephen King", "Masculino", "Estados Unidos");
        Author author2 = new Author("Lee Strobel", "Masculino", "Estados Unidos");

        List<Author> authorBook1 = List.of(author1);
        List<Author> authorBook2 = List.of(author2);

        Book book1 = new Book("O Iluminado", authorBook1, "Terror", 424, "Sim", 2021);
        Book book2 = new Book("Em Defesa de Cristo", authorBook2, "Teologia", 368, "Sim", 2023);
        Book book3 = new Book("Sob a Redoma", authorBook1, "Thriller", 960, "Sim", 2023);

        librarySystem.addBookToList(book1);
        librarySystem.addBookToList(book2);
        librarySystem.addBookToList(book3);

        List<Book> booksByAuthor1 = librarySystem.findBooksByAuthorName("Stephen King");
        List<Book> booksByAuthor2 = librarySystem.findBooksByAuthorName("Lee Strobel");

        assertEquals(2, booksByAuthor1.size());
        assertEquals(1, booksByAuthor2.size());
    }

    @org.junit.jupiter.api.Test
    void findBooksByAuthorGender() {
        Author author1 = new Author("Stephen King", "Masculino", "Estados Unidos");
        Author author2 = new Author("Lee Strobel", "Masculino", "Estados Unidos");
        Author author3 = new Author("Agatha Christie", "Feminino", "Inglaterra");

        List<Author> authorBook1 = List.of(author1);
        List<Author> authorBook2 = List.of(author2);
        List<Author> authorBook3 = List.of(author3);

        Book book1 = new Book("O Iluminado", authorBook1, "Terror", 424, "Sim", 2021);
        Book book2 = new Book("Em Defesa de Cristo", authorBook2, "Teologia", 368, "Sim", 2023);
        Book book3 = new Book("Sob a Redoma", authorBook1, "Thriller", 960, "Sim", 2023);
        Book book4 = new Book("A Casa do Penhasco", authorBook3, "Romance Policial", 192, "Sim", 2018);

        librarySystem.addBookToList(book1);
        librarySystem.addBookToList(book2);
        librarySystem.addBookToList(book3);
        librarySystem.addBookToList(book4);

        List<Book> booksByMaleAuthors = librarySystem.findBooksByAuthorGender("Masculino");
        List<Book> booksByFemaleAuthors = librarySystem.findBooksByAuthorGender("Feminino");

        assertEquals(3, booksByMaleAuthors.size());
        assertEquals(1, booksByFemaleAuthors.size());
    }

    @org.junit.jupiter.api.Test
    void findBooksByAuthorsWithDifferentGenders() {
        //TODO
    }

    @org.junit.jupiter.api.Test
    void findBooksByAuthorCountry() {
        Author author1 = new Author("Stephen King", "Masculino", "Estados Unidos");
        Author author2 = new Author("Lee Strobel", "Masculino", "Estados Unidos");

        List<Author> authorBook1 = List.of(author1);
        List<Author> authorBook2 = List.of(author2);

        Book book1 = new Book("O Iluminado", authorBook1, "Terror", 424, "Sim", 2021);
        Book book2 = new Book("Em Defesa de Cristo", authorBook2, "Teologia", 368, "Sim", 2023);
        Book book3 = new Book("Sob a Redoma", authorBook1, "Thriller", 960, "Sim", 2023);

        librarySystem.addBookToList(book1);
        librarySystem.addBookToList(book2);
        librarySystem.addBookToList(book3);

        List<Book> booksByAmericanAuthors = librarySystem.findBooksByAuthorGender("Masculino");

        assertEquals(3, booksByAmericanAuthors.size());
    }

    @org.junit.jupiter.api.Test
    void findBooksByGenre() {
        Author author1 = new Author("Francis Chaeffer", "Masculino", "Estados Unidos");
        Author author2 = new Author("Lee Strobel", "Masculino", "Estados Unidos");
        Author author3 = new Author("Julio Verne", "Masculino", "França");

        List<Author> authorBook1 = List.of(author1);
        List<Author> authorBook2 = List.of(author2);
        List<Author> authorBook3 = List.of(author3);

        Book book1 = new Book("Viagem ao Centro da Terra", authorBook3, "Aventura", 368, "Sim", 2022);
        Book book2 = new Book("Em Defesa de Cristo", authorBook2, "Teologia", 368, "Sim", 2023);
        Book book3 = new Book("A Obra Consumada de Cristo", authorBook1, "Teologia", 960, "Sim", 2023);

        librarySystem.addBookToList(book1);
        librarySystem.addBookToList(book2);
        librarySystem.addBookToList(book3);

        List<Book> theologicalBooks = librarySystem.findBooksByGenre("Teologia");
        List<Book> adventureBooks = librarySystem.findBooksByGenre("Aventura");

        assertEquals(2, theologicalBooks.size());
        assertEquals(1, adventureBooks.size());
    }

    @org.junit.jupiter.api.Test
    void findBooksByMorePageCount() {
        //TODO
    }

    @org.junit.jupiter.api.Test
    void findBooksByLessPageCount() {
        //TODO
    }

    @org.junit.jupiter.api.Test
    void findBooksByYearOfReading() {
        //TODO
    }

    @org.junit.jupiter.api.Test
    void findUnreadBooks() {
        //TODO
    }

    @org.junit.jupiter.api.Test
    void authorList() {
        Author author = new Author("J. K. Rowling", "Feminino", "Estados Unidos");
        Book book = new Book("Harry Potter e a Pedra Filosofal", List.of(author), "Fantasia", 208, "Sim", 2022);

        librarySystem.addBookToList(book);
        List<Author> authorList = librarySystem.authorList();
        assertEquals(1, authorList.size());
    }

    @org.junit.jupiter.api.Test
    void femaleAuthorsList() {
        Author author = new Author("J. K. Rowling", "Feminino", "Estados Unidos");
        Book book = new Book("Harry Potter e a Pedra Filosofal", List.of(author), "Fantasia", 208, "Sim", 2022);

        librarySystem.addBookToList(book);
        List<Author> femaleAuthorsList = librarySystem.femaleAuthorsList();
        assertEquals(1, femaleAuthorsList.size());
    }

    @org.junit.jupiter.api.Test
    void maleAuthorsList() {
        Author author = new Author("John Bunyan", "Masculino", "Inglaterra");
        Book book = new Book("O Peregrino", List.of(author), "Teologia", 200, "Sim", 2021);

        librarySystem.addBookToList(book);
        List<Author> maleAuthorsList = librarySystem.maleAuthorsList();
        assertEquals(1, maleAuthorsList.size());
    }

    @org.junit.jupiter.api.Test
    void otherGenderAuthorsList() {
        //TODO
    }

    @org.junit.jupiter.api.Test
    void genreBooksList() {
        //TODO
    }

    @org.junit.jupiter.api.Test
    void totalPageCount() {
        //TODO
    }
}