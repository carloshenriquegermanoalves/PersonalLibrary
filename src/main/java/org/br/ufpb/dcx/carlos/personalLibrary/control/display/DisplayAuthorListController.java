package org.br.ufpb.dcx.carlos.personalLibrary.control.display;

import org.br.ufpb.dcx.carlos.personalLibrary.model.Author;
import org.br.ufpb.dcx.carlos.personalLibrary.model.Book;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DisplayAuthorListController implements ActionListener {
    private final List<Author> authorsToDisplay;

    public DisplayAuthorListController(List<Author> authorsToDisplay) {
        this.authorsToDisplay = authorsToDisplay;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        displayAuthorsList(authorsToDisplay);
    }

    public void displayAuthorsList(List<Author> booksToDisplay) {
        booksToDisplay.forEach(author -> JOptionPane.showMessageDialog(null, author.getName()));
    }

}
