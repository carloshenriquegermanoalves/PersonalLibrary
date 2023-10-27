package org.br.ufpb.dcx.carlos.personalLibrary.control.display;

import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayAllAuthorsController implements ActionListener {
    private final LibrarySystem librarySystem;

    public DisplayAllAuthorsController(LibrarySystem librarySystem) {
        this.librarySystem = librarySystem;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        displayAllAuthors();
    }

    public void displayAllAuthors() {
        librarySystem.authorList().forEach(author -> JOptionPane.showMessageDialog(null, author.getName()));
    }
}
