package org.br.ufpb.dcx.carlos.personalLibrary.control.display;

import org.br.ufpb.dcx.carlos.personalLibrary.model.Book;
import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DisplayBooksSortedAlphabetically implements ActionListener {
    private final LibrarySystem LIBRARYSYSTEM;

    public DisplayBooksSortedAlphabetically(LibrarySystem librarySystem) {
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
        JScrollPane scrollPane = getjScrollPane(bookList);

        JDialog bookListDialog = new JDialog();
        bookListDialog.setTitle("Livros Cadastrados na Biblioteca");
        displayJPane(scrollPane, bookListDialog);
    }

    static void displayJPane(JScrollPane scrollPane, JDialog bookListDialog) {
        DisplayBooksSortedAlphabeticallyByGenre.displayList(scrollPane, bookListDialog);
    }

    private JScrollPane getjScrollPane(List<Book> bookList) {
        DefaultListModel<String> listModel = new DefaultListModel<>();

        for (Book book : bookList) {
            listModel.addElement(book.getTitle());
        }

        var bookListDisplay = new JList<>(listModel);
        bookListDisplay.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        bookListDisplay.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedIndex = bookListDisplay.getSelectedIndex();
                if (selectedIndex >= 0) {
                    Book selectedBook = bookList.get(selectedIndex);
                    showBookInfo(selectedBook);
                }
            }
        });

        return new JScrollPane(bookListDisplay);
    }

    private boolean isThereAnyBooksInLibrary() {
        return !LIBRARYSYSTEM.sortBooksAlphabetically().isEmpty();
    }

    private void showBookInfo(Book book) {
        JOptionPane.showMessageDialog(null, book.toString(), "Informações do Livro", JOptionPane.INFORMATION_MESSAGE);
    }
}
