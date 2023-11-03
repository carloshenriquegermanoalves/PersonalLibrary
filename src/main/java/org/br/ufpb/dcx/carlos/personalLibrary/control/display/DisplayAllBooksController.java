package org.br.ufpb.dcx.carlos.personalLibrary.control.display;

import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        StringBuilder sb = new StringBuilder();
        if (isThereAnyBooksInLibrary()) {
            sb.append("Os livros cadastrados na biblioteca, em ordem de aquisição, são: \n\n");
            LIBRARYSYSTEM.getBookList().forEach(book -> sb.append(book.getTitle()).append("\n"));
        } else {
            sb.append("Ainda não há livros cadastrados na biblioteca!");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private boolean isThereAnyBooksInLibrary() {
        return !LIBRARYSYSTEM.getBookList().isEmpty();
    }

}
