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
                        1. Exibir Todos os Autores
                        2. Exibir Autores
                        3. Exibir Autoras
                        4. Exibir Autores de Outros Gêneros
                        5. Número de Livros por Autores
                        6. Número de Livros por Autoras
                        7. Número de Livros por Autores de Outros Gêneros
                        """);

        switch (authorDisplayMenuOption) {
            case "1" -> displayAllAuthors();
            case "2" -> displayAuthorsList(librarySystem.maleAuthorsList());
            case "3" -> displayAuthorsList(librarySystem.femaleAuthorsList());
            case "4" -> displayAuthorsList(librarySystem.otherGenderAuthorsList());
            case "5" -> displayNumberOfBooksByAuthorGender("Masculino");
            case "6" -> displayNumberOfBooksByAuthorGender("Feminino");
            case "7" -> displayBooksByAuthorsWithDifferentGenders();
            default -> JOptionPane.showMessageDialog(null, "Opção inválida! Tente novamente.");
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
        JOptionPane.showMessageDialog(null, "Número de livros por autores do gênero " + gender + " são: " + numberOfBooks);
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
