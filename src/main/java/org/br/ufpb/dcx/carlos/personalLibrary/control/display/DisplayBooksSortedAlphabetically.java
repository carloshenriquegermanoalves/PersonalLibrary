package org.br.ufpb.dcx.carlos.personalLibrary.control.display;

import org.br.ufpb.dcx.carlos.personalLibrary.control.display.useful.UsefulForDisplayBooksList;
import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayBooksSortedAlphabetically implements ActionListener {
    private final LibrarySystem librarySystem;

    public DisplayBooksSortedAlphabetically(LibrarySystem librarySystem) {
        this.librarySystem = librarySystem;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        displayBooksSortedAlphabetically();
    }

    public void displayBooksSortedAlphabetically() {
        if (isThereAnyBooksInLibrary()) {
            JOptionPane.showMessageDialog(null, "Os livros cadastrados na biblioteca, em ordem alfabética, são: ");
            UsefulForDisplayBooksList listBooksDisplayer = new UsefulForDisplayBooksList(librarySystem.sortBooksAlphabetically());
            listBooksDisplayer.displayBooksList();
        } else {
            JOptionPane.showMessageDialog(null, "Ainda não há livros cadastrados na biblioteca!");
        }
    }

    private boolean isThereAnyBooksInLibrary() {
        return !librarySystem.sortBooksAlphabetically().isEmpty();
    }

}
