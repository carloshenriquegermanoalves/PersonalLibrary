package org.br.ufpb.dcx.carlos.personalLibrary.control.display;

import org.br.ufpb.dcx.carlos.personalLibrary.model.Book;
import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DisplayAllBooksController implements ActionListener {
    private final LibrarySystem LIBRARYSYSTEM;

    public DisplayAllBooksController(LibrarySystem librarySystem) {
        this.LIBRARYSYSTEM = librarySystem;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        displayAllBooks();
    }

    public void displayAllBooks() {
        if (isThereAnyBooksInLibrary()) {
            List<Book> bookList = LIBRARYSYSTEM.getBookList();
            displayList(bookList, "Livros Cadastrados na Biblioteca");
        } else {
            JOptionPane.showMessageDialog(null, "Ainda não há livros cadastrados na biblioteca!");
        }
    }

    public static void displayList(List<Book> bookList, String title) {
        DefaultListModel<String> listModel = new DefaultListModel<>();

        for (Book book : bookList) {
            listModel.addElement(book.getTitle());
        }

        JList<String> bookListDisplay = getjList(bookList, listModel);

        JScrollPane scrollPane = new JScrollPane(bookListDisplay);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        getJPanel(title, panel);
    }

    private static JList<String> getjList(List<Book> bookList, DefaultListModel<String> listModel) {
        JList<String> bookListDisplay = new JList<>(listModel);
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
        return bookListDisplay;
    }

    static void getJPanel(String title, JPanel panel) {
        JDialog dialog = new JDialog();
        dialog.setTitle(title);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.add(panel);
        dialog.setPreferredSize(new Dimension(400, 400));
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    private boolean isThereAnyBooksInLibrary() {
        return !LIBRARYSYSTEM.getBookList().isEmpty();
    }

    private static void showBookInfo(Book book) {
        JOptionPane.showMessageDialog(null, book.toString(), "Informações do Livro", JOptionPane.INFORMATION_MESSAGE);
    }
}
