package org.br.ufpb.dcx.carlos.personalLibrary.model;

import org.br.ufpb.dcx.carlos.personalLibrary.model.exceptions.BookNotFoundException;
import org.junit.jupiter.api.BeforeEach;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class LibrarySystemTest {

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

        Book book1 = new Book("Inglês Para Falar em Qualquer Situação", List.of(author1, author2), "Didático", 208, "Sim", 2022);
        Book book2 = new Book("A Casa do Penhasco", List.of(author3), "Romance Policial", 192, "Sim", 2018);

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
        Book newBook = new Book("O Peregrino", List.of(newAuthor), "Teologia", 288, "Sim", 2021);
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
        Book newBook = new Book("Harry Potter e a Pedra Filosofal", List.of(newAuthor), "Fantasia", 208, "Sim", 2021);

        librarySystem.addBookToList(newBook);

        assertTrue(librarySystem.getBookList().contains(newBook));
    }


    @org.junit.jupiter.api.Test
    void removeBookFromList() {
        Author author = new Author("Agatha Christie", "Feminino", "Inglaterra");
        Book book = new Book("A Casa do Penhasco", List.of(author), "Romance Policial", 192, "Sim", 2018);

        librarySystem.removeBookFromList("A Casa do Penhasco");

        assertFalse(librarySystem.getBookList().contains(book));
    }

    @org.junit.jupiter.api.Test
    void sortBooksAlphabetically() {
        List<Book> booksSortedAlphabeticallyList = librarySystem.sortBooksAlphabetically();

        List<String> sortedTitles = booksSortedAlphabeticallyList.stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());

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

    //TODO
    //Refatorar a partir daqui

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
        Author author1 = new Author("Nome", "Masculino", "País");
        Author author2 = new Author("Nome 2", "Feminino", "País 2");

        List<Author> authorBook1 = List.of(author1, author2);

        Book book1 = new Book("Livro 1", authorBook1, "Gênero 1", 424, "Sim", 2021);
        librarySystem.addBookToList(book1);

        List<Book> bookList = librarySystem.getBookList();
        List<Book> result = new ArrayList<>();

        for (Book book : bookList) {
            List<Author> authors = book.getAuthor();

            if (authors.size() > 1) {
                String firstGender = authors.get(0).getAuthorGender();
                boolean hasDifferentGenders = false;

                for (int i = 1; i < authors.size(); i++) {
                    if (!authors.get(i).getAuthorGender().equalsIgnoreCase(firstGender)) {
                        hasDifferentGenders = true;
                        break;
                    }
                }

                if (hasDifferentGenders) {
                    result.add(book);
                }
            }
        }

        assertEquals(1, result.size());
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
        Author author1 = new Author("Julio Verne", "Masculino", "França");
        Author author2 = new Author("Lee Strobel", "Masculino", "Estados Unidos");

        List<Author> authorBook1 = List.of(author1);
        List<Author> authorBook2 = List.of(author2);

        Book book1 = new Book("Da Terra a Lua", authorBook1, "Aventura", 192, "Sim", 2021);
        Book book2 = new Book("Em Defesa de Cristo", authorBook2, "Teologia", 368, "Sim", 2023);

        int pageLimit = 300;
        List<Book> bookList = new ArrayList<>();
        List<Book> result = new ArrayList<>();

        bookList.add(book1);
        bookList.add(book2);

        for (Book book : bookList) {
            if (book.getPageCount() >= pageLimit) {
                result.add(book);
            }
        }

        int expectedSize = 1;
        assertEquals(expectedSize, result.size());

        for (Book b : result) {
            assertTrue(b.getPageCount() >= pageLimit);
        }
    }

    @org.junit.jupiter.api.Test
    void findBooksByLessPageCount() {
        Author author1 = new Author("Julio Verne", "Masculino", "França");
        Author author2 = new Author("Lee Strobel", "Masculino", "Estados Unidos");

        List<Author> authorBook1 = List.of(author1);
        List<Author> authorBook2 = List.of(author2);

        Book book1 = new Book("Da Terra a Lua", authorBook1, "Aventura", 192, "Sim", 2021);
        Book book2 = new Book("Em Defesa de Cristo", authorBook2, "Teologia", 368, "Sim", 2023);

        int pageLimit = 300;
        List<Book> bookList = new ArrayList<>();
        List<Book> result = new ArrayList<>();

        bookList.add(book1);
        bookList.add(book2);

        for (Book book : bookList) {
            if (book.getPageCount() <= pageLimit) {
                result.add(book);
            }
        }

        int expectedSize = 1;
        assertEquals(expectedSize, result.size());

        for (Book b : result) {
            assertTrue(b.getPageCount() <= pageLimit);
        }
    }

    @org.junit.jupiter.api.Test
    void findBooksByYearOfReading() {
        Author author1 = new Author("Julio Verne", "Masculino", "França");
        Author author2 = new Author("Lee Strobel", "Masculino", "Estados Unidos");

        List<Author> authorBook1 = List.of(author1);
        List<Author> authorBook2 = List.of(author2);

        Book book1 = new Book("Da Terra a Lua", authorBook1, "Aventura", 192, "Sim", 2021);
        Book book2 = new Book("Em Defesa de Cristo", authorBook2, "Teologia", 368, "Sim", 2023);

        int yearOfReading = 2021;
        List<Book> bookList = librarySystem.getBookList();
        List<Book> result = new ArrayList<>();

        bookList.add(book1);
        bookList.add(book2);

        for (Book book : bookList) {
            if (book.getYearOfReading() == yearOfReading) {
                result.add(book);
            }
        }

        int expectedSize = 1;
        assertEquals(expectedSize, result.size());
    }


    @org.junit.jupiter.api.Test
    void findUnreadBooks() {
        Author author1 = new Author("Julio Verne", "Masculino", "França");
        Author author2 = new Author("Lee Strobel", "Masculino", "Estados Unidos");

        List<Author> authorBook1 = List.of(author1);
        List<Author> authorBook2 = List.of(author2);

        Book book1 = new Book("Da Terra a Lua", authorBook1, "Aventura", 192, "Não", 0);
        Book book2 = new Book("Em Defesa de Cristo", authorBook2, "Teologia", 368, "Sim", 2023);

        List<Book> bookList = librarySystem.getBookList();
        List<Book> result = new ArrayList<>();

        bookList.add(book1);
        bookList.add(book2);

        for (Book book : bookList) {
            if (book.getReadStatus().equalsIgnoreCase("não")) {
                result.add(book);
            }
        }

        int expectedSize = 1;
        assertEquals(expectedSize, result.size());
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
        Author author1 = new Author("Nome", "Masculino", "País");
        Author author2 = new Author("Nome 2", "Feminino", "País 2");
        Author author3 = new Author("Nome 3", "Outro Gênero", "País 3");

        Book book1 = new Book("Livro 1", List.of(author1, author2), "Gênero 1", 200, "Sim", 2022);
        Book book2 = new Book("Livro 2", List.of(author3), "Gênero 2", 250, "Sim", 2022);

        librarySystem.addBookToList(book1);
        librarySystem.addBookToList(book2);

        List<Author> otherGenderAuthors = librarySystem.otherGenderAuthorsList();

        assertEquals(1, otherGenderAuthors.size());
        assertEquals("Nome 3", otherGenderAuthors.get(0).getName());
    }



    @org.junit.jupiter.api.Test
    void genreBooksList() {
        Author author1 = new Author("Nome 1", "Masculino", "País 1");
        Author author2 = new Author("Nome 2", "Feminino", "País 2");

        Book book1 = new Book("Livro 1", List.of(author1, author2), "Gênero 1", 200, "Sim", 2022);
        Book book2 = new Book("Livro 2", List.of(author1), "Gênero 2", 250, "Sim", 2022);
        Book book3 = new Book("Livro 3", List.of(author2), "Gênero 1", 180, "Sim", 2022);

        librarySystem.addBookToList(book1);
        librarySystem.addBookToList(book2);
        librarySystem.addBookToList(book3);

        List<String> genreBooks = librarySystem.genreBooksList();

        assertEquals(2, genreBooks.size());
        assertTrue(genreBooks.contains(book1.getBookGenre()));
        assertTrue(genreBooks.contains(book3.getBookGenre()));
    }

    @org.junit.jupiter.api.Test
    void totalPageCount() {
        Author author1 = new Author("Nome 1", "Masculino", "País 1");
        Author author2 = new Author("Nome 2", "Feminino", "País 2");

        Book book1 = new Book("Livro 1", List.of(author1, author2), "Gênero 1", 200, "Sim", 2022);
        Book book2 = new Book("Livro 2", List.of(author1), "Gênero 2", 250, "Sim", 2022);
        Book book3 = new Book("Livro 3", List.of(author2), "Gênero 1", 180, "Sim", 2022);

        librarySystem.addBookToList(book1);
        librarySystem.addBookToList(book2);
        librarySystem.addBookToList(book3);

        int totalPageCount = librarySystem.totalPageCount();

        assertEquals(630, totalPageCount);
    }

}