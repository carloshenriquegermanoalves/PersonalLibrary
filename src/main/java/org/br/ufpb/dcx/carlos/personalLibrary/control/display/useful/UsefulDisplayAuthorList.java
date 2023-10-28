package org.br.ufpb.dcx.carlos.personalLibrary.control.display.useful;

import org.br.ufpb.dcx.carlos.personalLibrary.model.Author;

import javax.swing.*;

import java.util.List;

public class UsefulDisplayAuthorList {
    private final List<Author> authorsToDisplay;

    public UsefulDisplayAuthorList(List<Author> authorsToDisplay) {
        this.authorsToDisplay = authorsToDisplay;
    }

    public void displayAuthorsList() {
        authorsToDisplay.forEach(author -> JOptionPane.showMessageDialog(null, author.getName()));
    }

}
