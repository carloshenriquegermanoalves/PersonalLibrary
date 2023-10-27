package org.br.ufpb.dcx.carlos.personalLibrary.control.display;

import org.br.ufpb.dcx.carlos.personalLibrary.model.Book;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DisplayBooksListController implements ActionListener {
    private final List<Book> booksToDisplay;

    public DisplayBooksListController(List<Book> booksToDisplay) {
        this.booksToDisplay = booksToDisplay;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
     displayBooksList(booksToDisplay);
    }

    public void displayBooksList(List<Book> booksToDisplay) {
        booksToDisplay.forEach(book -> JOptionPane.showMessageDialog(null, book.getTitle()));
    }
}
