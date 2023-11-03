package org.br.ufpb.dcx.carlos.personalLibrary.control.display;

import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DisplayAllBooksSubGenres implements ActionListener {
    private final LibrarySystem LIBRARYSYSTEM;

    public DisplayAllBooksSubGenres(LibrarySystem librarysystem) {
        LIBRARYSYSTEM = librarysystem;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        displaySubGenre();
    }

    private void displaySubGenre() {
        StringBuilder message = new StringBuilder();

        if (!isThereGenresInLibrary()) {
            message.append("Os gêneros dos livros cadastrados na biblioteca são:\n\n");

            List<String> subGenres = LIBRARYSYSTEM.booksSubGenre();
            for (String subGenre : subGenres) {
                message.append(subGenre).append("\n");
            }
        } else {
            message.append("Ainda não há gêneros de livros cadastrados na biblioteca!");
        }

        JOptionPane.showMessageDialog(null, message.toString());
    }

    private boolean isThereGenresInLibrary() {
        return LIBRARYSYSTEM.getBookList().isEmpty();
    }

}
