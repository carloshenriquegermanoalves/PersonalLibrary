package org.br.ufpb.dcx.carlos.personalLibrary.control.display;

import org.br.ufpb.dcx.carlos.personalLibrary.control.display.useful.UsefulDisplayAuthorList;
import org.br.ufpb.dcx.carlos.personalLibrary.control.display.useful.UsefulForDisplayAllAuthors;
import org.br.ufpb.dcx.carlos.personalLibrary.control.display.useful.UsefulForDisplayBooksList;
import org.br.ufpb.dcx.carlos.personalLibrary.model.Author;
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isThereAnyAuthorsInLibrary()) {
            JOptionPane.showMessageDialog(null, "Ainda não há autores cadastrados na biblioteca!");
            return;
        }

        String authorDisplayMenuOption = JOptionPane.showInputDialog("""
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
            case "2" -> displayAuthorsByType(librarySystem.maleAuthorsList(), "autores");
            case "3" -> displayAuthorsByType(librarySystem.femaleAuthorsList(), "autoras");
            case "4" -> displayAuthorsByType(librarySystem.otherGenderAuthorsList(), "autores de outros gêneros");
            case "5" -> displayNumberOfBooksByAuthorGender("Masculino");
            case "6" -> displayNumberOfBooksByAuthorGender("Feminino");
            case "7" -> displayBooksByAuthorsWithDifferentGenders();
            default -> JOptionPane.showMessageDialog(null, "Opção inválida! Tente novamente.");
        }
    }

    private void displayAuthorsByType(List<Author> authors, String authorType) {
        if (isThereAnyAuthors(authors)) {
            JOptionPane.showMessageDialog(null, "Os " + authorType + " cadastrados são: ");
            displayAuthorsList(authors);
        } else {
            JOptionPane.showMessageDialog(null, "Ainda não há " + authorType + "!");
        }
    }

    private boolean isThereAnyAuthors(List<Author> authorsList) {
        return !authorsList.isEmpty();
    }

    private void displayAllAuthors() {
        if (isThereAnyAuthors(librarySystem.authorList())) {
            JOptionPane.showMessageDialog(null, "Todos os autores cadastrados na biblioteca são: ");
            UsefulForDisplayAllAuthors allAuthorsDisplayer = new UsefulForDisplayAllAuthors(librarySystem);
            allAuthorsDisplayer.displayAllAuthors();
        } else {
            JOptionPane.showMessageDialog(null, "Ainda não há livros cadastrados na biblioteca!");
        }
    }

    private void displayAuthorsList(List<Author> authors) {
        UsefulDisplayAuthorList listAuthorsDisplayer = new UsefulDisplayAuthorList(authors);
        listAuthorsDisplayer.displayAuthorsList();
    }

    private void displayNumberOfBooksByAuthorGender(String gender) {
        int numberOfBooks = librarySystem.findBooksByAuthorGender(gender).size();
        JOptionPane.showMessageDialog(null, "O número de livros por autores do gênero " + gender + " é: " + numberOfBooks);
    }

    private void displayBooksByAuthorsWithDifferentGenders() {
        if (!librarySystem.findBooksByAuthorsWithDifferentGenders().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Os livros escritos por autores de outros gêneros é: ");
            UsefulForDisplayBooksList booksDisplayer = new UsefulForDisplayBooksList(librarySystem.findBooksByAuthorsWithDifferentGenders());
            booksDisplayer.displayBooksList();
        } else {
            JOptionPane.showMessageDialog(null, "Ainda não há livros de autores de outros gêneros");
        }
    }

    private boolean isThereAnyAuthorsInLibrary() {
        return !librarySystem.getBookList().isEmpty();
    }
}
