package org.br.ufpb.dcx.carlos.personalLibrary.control.display;

import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
        StringBuilder message = new StringBuilder();

        if (isThereGenresInLibrary()) {
            message.append("Os gêneros dos livros cadastrados na biblioteca são:\n\n");

            List<String> genres = librarySystem.genreBooksList();
            for (String genre : genres) {
                message.append(genre).append("\n");
            }
        } else {
            message.append("Ainda não há gêneros de livros cadastrados na biblioteca!");
        }

        JOptionPane.showMessageDialog(null, message.toString());
    }


    private boolean isThereGenresInLibrary() {
        return !librarySystem.genreBooksList().isEmpty();
    }


}
