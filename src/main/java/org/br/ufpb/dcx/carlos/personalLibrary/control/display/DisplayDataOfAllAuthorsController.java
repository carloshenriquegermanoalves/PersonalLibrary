package org.br.ufpb.dcx.carlos.personalLibrary.control.display;

import org.br.ufpb.dcx.carlos.personalLibrary.control.display.useful.*;
import org.br.ufpb.dcx.carlos.personalLibrary.model.Author;
import org.br.ufpb.dcx.carlos.personalLibrary.model.Book;
import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DisplayDataOfAllAuthorsController implements ActionListener {
    private final LibrarySystem librarySystem;

    public DisplayDataOfAllAuthorsController(LibrarySystem librarySystem) {
        this.librarySystem = librarySystem;
    }

    private void displayAuthors() {
        String authorDisplayMenuOption = JOptionPane.showInputDialog(
                """
                        1. Display All Authors
                        2. Display Male Authors
                        3. Display Female Authors
                        4. Display Authors of Other Genders
                        5. Number of Books by Male Authors
                        6. Number of Books by Female Authors
                        7. Number of Books by Authors of Other Genders
                        """);

        switch (authorDisplayMenuOption) {
            case "1" -> displayAllAuthors();
            case "2" -> displayAuthorsList(librarySystem.maleAuthorsList());
            case "3" -> displayAuthorsList(librarySystem.femaleAuthorsList());
            case "4" -> displayAuthorsList(librarySystem.otherGenderAuthorsList());
            case "5" -> displayNumberOfBooksByAuthorGender("Masculino");
            case "6" -> displayNumberOfBooksByAuthorGender("Feminino");
            case "7" -> displayBooksByAuthorsWithDifferentGenders();
            default -> JOptionPane.showMessageDialog(null, "Invalid option! Please try again.");
        }
    }


    private void displayAllAuthors() {
        UsefulForDisplayAllAuthors allAuthorsDisplayer = new UsefulForDisplayAllAuthors(librarySystem);
        allAuthorsDisplayer.displayAllAuthors();
    }

    private void displayAuthorsList(List<Author> authors) {
        UsefulDisplayAuthorList listAuthorsDisplayer = new UsefulDisplayAuthorList(authors);
        listAuthorsDisplayer.displayAuthorsList();
    }

    private void displayNumberOfBooksByAuthorGender(String gender) {
        int numberOfBooks = librarySystem.findBooksByAuthorGender(gender).size();
        JOptionPane.showMessageDialog(null, "Number of books by " + gender + " authors: " + numberOfBooks);
    }

    private void displayBooksByAuthorsWithDifferentGenders() {
        List<Book> books = librarySystem.findBooksByAuthorsWithDifferentGenders();
        UsefulForDisplayBooksList booksDisplayer = new UsefulForDisplayBooksList(books);
        booksDisplayer.displayBooksList();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        displayAuthors();
    }

}
