package org.br.ufpb.dcx.carlos.personalLibrary.control.display;

import org.br.ufpb.dcx.carlos.personalLibrary.model.Book;
import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DisplayAllBooksSubGenresController implements ActionListener {
    private final LibrarySystem LIBRARYSYSTEM;
    private JDialog subGenreDialog = null;

    public DisplayAllBooksSubGenresController(LibrarySystem librarySystem) {
        this.LIBRARYSYSTEM = librarySystem;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        displaySubGenres();
    }

    public void displaySubGenres() {
        if (isThereSubGenresInLibrary()) {
            List<List<Book>> subGenreBooksList = LIBRARYSYSTEM.findBooksBySubGenre();
            displayList(subGenreBooksList, "Subgêneros de Livros Cadastrados na Biblioteca");
        } else {
            JOptionPane.showMessageDialog(null, "Ainda não há subgêneros de livros cadastrados na biblioteca!");
        }
    }

    public void displayList(List<List<Book>> subGenreBooksList, String title) {
        DefaultListModel<String> listModel = new DefaultListModel<>();

        for (int i = 0; i < subGenreBooksList.size(); i++) {
            String subGenreName = LIBRARYSYSTEM.booksSubGenre().get(i);
            listModel.addElement(subGenreName);
        }

        JList<String> subGenreListDisplay = getjList(subGenreBooksList, listModel);

        JScrollPane scrollPane = new JScrollPane(subGenreListDisplay);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        if (subGenreDialog != null && subGenreDialog.isVisible()) {
            subGenreDialog.dispose();
        }

        subGenreDialog = new JDialog();
        subGenreDialog.setTitle(title);
        subGenreDialog.add(panel);
        subGenreDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        subGenreDialog.setPreferredSize(new Dimension(300, 300));
        subGenreDialog.pack();
        subGenreDialog.setLocationRelativeTo(null);
        subGenreDialog.setVisible(true);
    }

    private JList<String> getjList(List<List<Book>> subGenreBooksList, DefaultListModel<String> listModel) {
        JList<String> subGenreListDisplay = new JList<>(listModel);
        subGenreListDisplay.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        subGenreListDisplay.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedIndex = subGenreListDisplay.getSelectedIndex();
                if (selectedIndex >= 0) {
                    List<Book> books = subGenreBooksList.get(selectedIndex);
                    displayBooksInfo(books, listModel.get(selectedIndex));
                }
            }
        });
        return subGenreListDisplay;
    }

    private boolean isThereSubGenresInLibrary() {
        return !LIBRARYSYSTEM.findBooksBySubGenre().isEmpty();
    }

    private void displayBooksInfo(List<Book> books, String subGenreName) {
        JDialog booksDialog = new JDialog();
        booksDialog.setTitle("Livros de: " + subGenreName);

        DisplayAllBookGenresController.getJList(books, booksDialog);
        booksDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        booksDialog.setPreferredSize(new Dimension(400, 400));
        booksDialog.pack();
        booksDialog.setLocationRelativeTo(null);
        booksDialog.setVisible(true);
    }
}
