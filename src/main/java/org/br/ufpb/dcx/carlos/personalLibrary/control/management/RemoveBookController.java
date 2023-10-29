package org.br.ufpb.dcx.carlos.personalLibrary.control.management;

import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveBookController implements ActionListener {
    private final LibrarySystem librarySystem;

    public RemoveBookController(LibrarySystem librarySystem) {
        this.librarySystem = librarySystem;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String titleToRemove = JOptionPane.showInputDialog("Enter the title of the book to remove: ");

        if (librarySystem.removeBookFromList(titleToRemove)) {
            JOptionPane.showMessageDialog(null, "Book removed successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "The book '" + titleToRemove + "' was not found in the library.");
        }
    }
}
