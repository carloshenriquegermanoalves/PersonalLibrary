package org.br.ufpb.dcx.carlos.personalLibrary.control.display.useful;

import org.br.ufpb.dcx.carlos.personalLibrary.model.Book;

import javax.swing.*;

import java.util.List;

public class UsefulForDisplayBooksList {
    private final List<Book> booksToDisplay;

    public UsefulForDisplayBooksList(List<Book> booksToDisplay) {
        this.booksToDisplay = booksToDisplay;
    }

    public void displayBooksList() {
        booksToDisplay.forEach(book -> JOptionPane.showMessageDialog(null, book.getTitle()));
    }
}
