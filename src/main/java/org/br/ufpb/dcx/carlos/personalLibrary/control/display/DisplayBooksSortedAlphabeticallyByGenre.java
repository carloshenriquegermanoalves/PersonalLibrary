package org.br.ufpb.dcx.carlos.personalLibrary.control.display;

import org.br.ufpb.dcx.carlos.personalLibrary.model.Book;
import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DisplayBooksSortedAlphabeticallyByGenre implements ActionListener {
    private final LibrarySystem LIBRARYSYSTEM;

    public DisplayBooksSortedAlphabeticallyByGenre(LibrarySystem librarySystem) {
        this.LIBRARYSYSTEM = librarySystem;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        displayBooksSortedAlphabetically();
    }

    public void displayBooksSortedAlphabetically() {
        if (isThereAnyBooksInLibrary()) {
            List<Book> sortedBooks = LIBRARYSYSTEM.sortBooksAlphabetically();
            displayList(sortedBooks);
        } else {
            JOptionPane.showMessageDialog(null, "Ainda não há livros cadastrados na biblioteca!");
        }
    }

    public void displayList(List<Book> bookList) {
        DefaultListModel<String> listModel = new DefaultListModel<>();

        for (Book book : bookList) {
            listModel.addElement(book.getTitle() + " (" + book.getBookGenre() + ")");
        }

        JList<String> bookListDisplay = new JList<>(listModel);
        bookListDisplay.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(bookListDisplay);

        JDialog bookListDialog = new JDialog();
        bookListDialog.setTitle("Livros Cadastrados na Biblioteca (Ordenados por Gênero)");
        displayList(scrollPane, bookListDialog);
    }

    static void displayList(JScrollPane scrollPane, JDialog bookListDialog) {
        DisplayBooksSortedAlphabeticallyByGenreAndSubgenre.displayList(scrollPane, bookListDialog);
    }

    private boolean isThereAnyBooksInLibrary() {
        return !LIBRARYSYSTEM.sortBooksAlphabetically().isEmpty();
    }
}
