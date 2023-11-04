package org.br.ufpb.dcx.carlos.personalLibrary.control.display;

import org.br.ufpb.dcx.carlos.personalLibrary.model.Book;
import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class DisplayAllBookGenresController implements ActionListener {
    private final LibrarySystem LIBRARYSYSTEM;
    private JDialog genreListDialog;

    public DisplayAllBookGenresController(LibrarySystem librarySystem) {
        this.LIBRARYSYSTEM = librarySystem;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        displayAllGenres();
    }

    public void displayAllGenres() {
        if (isThereGenresInLibrary()) {
            List<String> genres = LIBRARYSYSTEM.genreBooksList();

            DefaultListModel<String> listModel = new DefaultListModel<>();
            for (String genre : genres) {
                listModel.addElement(genre);
            }

            JList<String> genreListDisplay = new JList<>(listModel);
            genreListDisplay.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            JScrollPane scrollPane = new JScrollPane(genreListDisplay);

            JPanel panel = new JPanel(new BorderLayout());
            panel.add(scrollPane, BorderLayout.CENTER);

            genreListDialog = new JDialog();
            genreListDialog.setTitle("Gêneros de Livros Cadastrados na Biblioteca");
            genreListDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            genreListDialog.add(panel);
            genreListDialog.setPreferredSize(new Dimension(400, 400));
            genreListDialog.pack();
            genreListDialog.setLocationRelativeTo(null);

            genreListDisplay.addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    int selectedIndex = genreListDisplay.getSelectedIndex();
                    if (selectedIndex >= 0) {
                        String selectedGenre = listModel.getElementAt(selectedIndex);
                        genreListDialog.dispose();
                        displayGenreBooks(selectedGenre);
                    }
                }
            });

            genreListDialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Ainda não há gêneros de livros cadastrados na biblioteca!");
        }
    }

    private void displayGenreBooks(String selectedGenre) {
        List<String> genres = LIBRARYSYSTEM.genreBooksList();
        Map<String, Integer> genreCounts = genres.stream().collect(Collectors.toMap(
                genre -> genre,
                genre -> LIBRARYSYSTEM.findBooksByGenre(genre).size()
        ));

        int bookCount = genreCounts.get(selectedGenre);
        List<Book> books = LIBRARYSYSTEM.findBooksByGenre(selectedGenre);

        AtomicReference<StringBuilder> message = new AtomicReference<>(new StringBuilder());
        message.get().append("Gênero: ").append(selectedGenre).append("\n");
        message.get().append("Quantidade de Livros: ").append(bookCount).append("\n\n");
        message.get().append("Livros:\n");

        for (Book book : books) {
            message.get().append(book).append("\n");
        }

        JDialog genreBooksDialog = new JDialog();
        genreBooksDialog.setTitle("Livros do Gênero: " + selectedGenre);
        genreBooksDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        getJList(books, genreBooksDialog);
        genreBooksDialog.setPreferredSize(new Dimension(400, 400));
        genreBooksDialog.pack();
        genreBooksDialog.setLocationRelativeTo(null);

        genreBooksDialog.setVisible(true);
    }

    static void getJList(List<Book> books, JDialog genreBooksDialog) {
        DefaultListModel<String> bookListModel = new DefaultListModel<>();
        for (Book book : books) {
            bookListModel.addElement(book.getTitle());
        }

        var bookListDisplay = new JList<>(bookListModel);
        bookListDisplay.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane bookScrollPane = new JScrollPane(bookListDisplay);

        JPanel bookPanel = new JPanel(new BorderLayout());
        bookPanel.add(bookScrollPane, BorderLayout.CENTER);

        genreBooksDialog.add(bookPanel);
    }

    private boolean isThereGenresInLibrary() {
        return !LIBRARYSYSTEM.genreBooksList().isEmpty();
    }
}
