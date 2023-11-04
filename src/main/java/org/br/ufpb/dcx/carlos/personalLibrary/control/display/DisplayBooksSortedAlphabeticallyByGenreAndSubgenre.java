package org.br.ufpb.dcx.carlos.personalLibrary.control.display;

import org.br.ufpb.dcx.carlos.personalLibrary.model.Book;
import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DisplayBooksSortedAlphabeticallyByGenreAndSubgenre implements ActionListener {
    private final LibrarySystem LIBRARYSYSTEM;

    public DisplayBooksSortedAlphabeticallyByGenreAndSubgenre(LibrarySystem librarysystem) {
        this.LIBRARYSYSTEM = librarysystem;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        displayBooksSortedAlphabeticallyByGenreAndSubgenre();
    }

    private void displayBooksSortedAlphabeticallyByGenreAndSubgenre() {
        if (isThereAnyBooksInLibrary()) {
            JOptionPane.showMessageDialog(null, "A biblioteca está vazia. Não há livros para exibir.");
        } else {
            List<Book> sortedBooks = LIBRARYSYSTEM.booksSortedAlphabeticallyByGenreAndSubgenre();
            displayList(sortedBooks);
        }
    }

    public void displayList(List<Book> bookList) {
        DefaultListModel<String> listModel = new DefaultListModel<>();

        for (Book book : bookList) {
            listModel.addElement(book.getTitle() + " (" + book.getBookGenre() + ") (" + book.getBookSubGenre() + ")");
        }

        JList<String> bookListDisplay = new JList<>(listModel);
        bookListDisplay.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(bookListDisplay);

        JDialog bookListDialog = new JDialog();
        bookListDialog.setTitle("Livros Cadastrados na Biblioteca (Ordenados por Gênero e Subgênero)");
        displayList(scrollPane, bookListDialog);
    }

    static void displayList(JScrollPane scrollPane, JDialog bookListDialog) {
        bookListDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        bookListDialog.add(panel);
        bookListDialog.setPreferredSize(new Dimension(600, 600));
        bookListDialog.pack();
        bookListDialog.setLocationRelativeTo(null);
        bookListDialog.setVisible(true);
    }

    private boolean isThereAnyBooksInLibrary() {
        return LIBRARYSYSTEM.getBookList().isEmpty();
    }
}
