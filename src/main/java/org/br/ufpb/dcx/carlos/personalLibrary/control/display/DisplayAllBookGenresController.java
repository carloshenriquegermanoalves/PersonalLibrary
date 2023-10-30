package org.br.ufpb.dcx.carlos.personalLibrary.control.display;

import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayAllBookGenresController implements ActionListener {
    private final LibrarySystem librarySystem;

    public DisplayAllBookGenresController(LibrarySystem librarySystem) {
        this.librarySystem = librarySystem;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        displayAllGenres();
    }

    public void displayAllGenres() {
        if (isThereGenresInLibrary()) {
            JOptionPane.showMessageDialog(null, "Os gêneros dos livros cadastrados na biblioteca são: ");
            librarySystem.genreBooksList().forEach(genre -> JOptionPane.showMessageDialog(null, genre));
        } else {
            JOptionPane.showMessageDialog(null, "Ainda não há gêneros de livros cadastrados na biblioteca!");
        }
    }

    private boolean isThereGenresInLibrary() {
        return !librarySystem.genreBooksList().isEmpty();
    }


}
