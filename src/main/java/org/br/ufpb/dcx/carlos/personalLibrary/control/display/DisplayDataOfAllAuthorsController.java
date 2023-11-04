package org.br.ufpb.dcx.carlos.personalLibrary.control.display;

import org.br.ufpb.dcx.carlos.personalLibrary.model.Author;
import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DisplayDataOfAllAuthorsController implements ActionListener {
    private final LibrarySystem LIBRARYSYSTEM;

    public DisplayDataOfAllAuthorsController(LibrarySystem librarySystem) {
        this.LIBRARYSYSTEM = librarySystem;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isThereAnyAuthorsInLibrary(LIBRARYSYSTEM.authorList())) {
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
            case "2" -> displayAuthorsByType(LIBRARYSYSTEM.maleAuthorsList(), "autores");
            case "3" -> displayAuthorsByType(LIBRARYSYSTEM.femaleAuthorsList(), "autores femininos");
            case "4" -> displayAuthorsByType(LIBRARYSYSTEM.otherGenderAuthorsList(), "autores de outros gêneros");
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
        if (isThereAnyAuthorsInLibrary(LIBRARYSYSTEM.authorList())) {
            displayList(LIBRARYSYSTEM.authorList(), "Todos os autores cadastrados na biblioteca são:");
        } else {
            showMessage("Ainda não há autores cadastrados na biblioteca!");
        }
    }

    private void displayAuthorsByType(List<Author> authors, String authorType) {
        if (isThereAnyAuthorsInLibrary(authors)) {
            displayList(authors, "Os " + authorType + " cadastrados são:");
        } else {
            showMessage("Ainda não há " + authorType + "!");
        }
    }

    private void displayNumberOfBooksByAuthorGender(String gender) {
        int numberOfBooks = LIBRARYSYSTEM.findBooksByAuthorGender(gender).size();
        showMessage("O número de livros por autores do gênero " + gender + " é: " + numberOfBooks);
    }

    private void displayNumberOfBooksByAuthorWithDifferentGender() {
        int numberOfBooks = LIBRARYSYSTEM.findBooksByAuthorsWithDifferentGenders().size();
        showMessage("O número de livros por autores de outro gênero é: " + numberOfBooks);
    }

    private void displayList(List<Author> authors, String message) {
        DefaultListModel<String> listModel = new DefaultListModel<>();

        for (Author author : authors) {
            listModel.addElement(author.getName());
        }

        JList<String> authorList = new JList<>(listModel);
        authorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(authorList);

        JDialog authorDialog = new JDialog();
        authorDialog.setTitle(message);
        displayJPane(scrollPane, authorDialog);
    }

    private void displayJPane(JScrollPane scrollPane, JDialog authorDialog) {
        authorDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        authorDialog.add(panel);
        authorDialog.setPreferredSize(new Dimension(300, 300));
        authorDialog.pack();
        authorDialog.setLocationRelativeTo(null);
        authorDialog.setVisible(true);
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
