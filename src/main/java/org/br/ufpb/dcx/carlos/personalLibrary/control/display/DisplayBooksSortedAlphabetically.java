package org.br.ufpb.dcx.carlos.personalLibrary.control.display;

import org.br.ufpb.dcx.carlos.personalLibrary.control.display.useful.UsefulForDisplayBooksList;
import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

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

    private void displayBooksSortedAlphabetically() {
        UsefulForDisplayBooksList listBooksDisplayer = new UsefulForDisplayBooksList(librarySystem.sortBooksAlphabetically());
        listBooksDisplayer.displayBooksList();
    }

}
