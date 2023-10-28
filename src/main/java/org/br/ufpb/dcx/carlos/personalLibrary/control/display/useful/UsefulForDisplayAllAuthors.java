package org.br.ufpb.dcx.carlos.personalLibrary.control.display.useful;

import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

import javax.swing.*;

public class UsefulForDisplayAllAuthors {
    private final LibrarySystem librarySystem;

    public UsefulForDisplayAllAuthors(LibrarySystem librarySystem) {
        this.librarySystem = librarySystem;
    }

    public void displayAllAuthors() {
        librarySystem.authorList().forEach(author -> JOptionPane.showMessageDialog(null, author.getName()));
    }
}
