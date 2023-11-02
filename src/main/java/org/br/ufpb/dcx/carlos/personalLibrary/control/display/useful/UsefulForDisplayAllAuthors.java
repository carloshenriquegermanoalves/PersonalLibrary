package org.br.ufpb.dcx.carlos.personalLibrary.control.display.useful;

import org.br.ufpb.dcx.carlos.personalLibrary.model.Author;
import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

import javax.swing.*;
import java.util.List;

public class UsefulForDisplayAllAuthors {
    private final LibrarySystem librarySystem;

    public UsefulForDisplayAllAuthors(LibrarySystem librarySystem) {
        this.librarySystem = librarySystem;
    }

    public void displayAllAuthors() {
        librarySystem.authorList().forEach(author -> JOptionPane.showMessageDialog(null, author.getName()));
    }

    public List<Author> authorList() {
        return librarySystem.authorList();
    }


}
