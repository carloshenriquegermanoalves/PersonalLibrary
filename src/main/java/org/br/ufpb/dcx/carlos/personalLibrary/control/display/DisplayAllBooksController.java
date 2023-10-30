package org.br.ufpb.dcx.carlos.personalLibrary.control.display;

import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayAllBooksController implements ActionListener {
    private final LibrarySystem librarySystem;

    public DisplayAllBooksController(LibrarySystem librarySystem) {
        this.librarySystem = librarySystem;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        displayAllBooks();
    }

    public void displayAllBooks() {
        if (isThereAnyBooksInLibrary()) {
            JOptionPane.showMessageDialog(null, "Os livros cadastrados na biblioteca são: ");
            librarySystem.getBookList().forEach(book -> JOptionPane.showMessageDialog(null, book.getTitle()));
        } else {
            JOptionPane.showMessageDialog(null, "Ainda não há livros cadastrados na biblioteca!");
        }
    }

    private boolean isThereAnyBooksInLibrary() {
        return !librarySystem.getBookList().isEmpty();
    }

}
