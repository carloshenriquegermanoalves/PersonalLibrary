package org.br.ufpb.dcx.carlos.personalLibrary.control.display;

import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayAllBooksCount implements ActionListener {
    private final LibrarySystem LIBRARYSYSTEM;

    public DisplayAllBooksCount(LibrarySystem librarysystem) {
        LIBRARYSYSTEM = librarysystem;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        allPagesCount();
    }

    private void allPagesCount() {
        JOptionPane.showMessageDialog(null, "A quantidade total de páginas é: " + LIBRARYSYSTEM.getBookList().size());
    }
}
