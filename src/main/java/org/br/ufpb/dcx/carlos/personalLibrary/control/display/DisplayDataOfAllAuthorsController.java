package org.br.ufpb.dcx.carlos.personalLibrary.control.display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

import org.br.ufpb.dcx.carlos.personalLibrary.model.Author;
import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

public class DisplayDataOfAllAuthorsController implements ActionListener {
    private final LibrarySystem librarySystem;

    public DisplayDataOfAllAuthorsController(LibrarySystem librarySystem) {
        this.librarySystem = librarySystem;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isThereAnyAuthorsInLibrary(librarySystem.authorList())) {
            showMessage("Ainda não há autores cadastrados na biblioteca!");
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
            case "3" -> displayAuthorsByType(librarySystem.femaleAuthorsList(), "autores femininos");
            case "4" -> displayAuthorsByType(librarySystem.otherGenderAuthorsList(), "autores de outros gêneros");
            case "5" -> displayNumberOfBooksByAuthorGender("Masculino");
            case "6" -> displayNumberOfBooksByAuthorGender("Feminino");
            case "7" -> displayNumberOfBooksByAuthorWithDifferentGender();
            default -> showMessage("Opção inválida! Tente novamente.");
        }
    }

    private boolean isThereAnyAuthorsInLibrary(List<Author> authorsList) {
        return !authorsList.isEmpty();
    }

    private void displayAllAuthors() {
        if (isThereAnyAuthorsInLibrary(librarySystem.authorList())) {
            showAuthorsList(librarySystem.authorList(), "Todos os autores cadastrados na biblioteca são: \n\n");
        } else {
            showMessage("Ainda não há livros cadastrados na biblioteca!");
        }
    }

    private void displayAuthorsByType(List<Author> authors, String authorType) {
        if (isThereAnyAuthorsInLibrary(authors)) {
            showAuthorsList(authors, "Os " + authorType + " cadastrados são: \n\n");
        } else {
            showMessage("Ainda não há " + authorType + "!");
        }
    }

    private void displayNumberOfBooksByAuthorGender(String gender) {
        int numberOfBooks = librarySystem.findBooksByAuthorGender(gender).size();
        showMessage("O número de livros por autores do gênero " + gender + " é: " + numberOfBooks);
    }

    private void displayNumberOfBooksByAuthorWithDifferentGender() {
        int numberOfBooks = librarySystem.findBooksByAuthorsWithDifferentGenders().size();
        showMessage("O número de livros por autores de outro gênero é: " + numberOfBooks);
    }

    private void showAuthorsList(List<Author> authors, String message) {
        StringBuilder builder = new StringBuilder(message);
        for (Author author : authors) {
            builder.append(author.getName()).append("\n");
        }
        showMessage(builder.toString());
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
