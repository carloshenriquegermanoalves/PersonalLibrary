package org.br.ufpb.dcx.carlos.personalLibrary.control.display;

import org.br.ufpb.dcx.carlos.personalLibrary.model.Book;
import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DisplayBooksSortedAlphabeticallyByGenre implements ActionListener {
    private final LibrarySystem LIBRARYSYSTEM;

    public DisplayBooksSortedAlphabeticallyByGenre(LibrarySystem librarySystem) {
        this.LIBRARYSYSTEM = librarySystem;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        displayBooksSortedAlphabetically();
    }

    public void displayBooksSortedAlphabetically() {
        StringBuilder message = new StringBuilder();

        if (isThereAnyBooksInLibrary()) {
            message.append("Os livros cadastrados na biblioteca, em ordem alfabética por gênero, são: \n\n");
            List<Book> sortedBooks = LIBRARYSYSTEM.sortBooksAlphabetically();

            for (Book book : sortedBooks) {
                message.append(book.getTitle()).append(" (").append(book.getBookGenre()).append(") \n");
            }
        } else {
            message.append("Ainda não há livros cadastrados na biblioteca!");
        }

        JOptionPane.showMessageDialog(null, message.toString());
    }

    private boolean isThereAnyBooksInLibrary() {
        return !LIBRARYSYSTEM.sortBooksAlphabetically().isEmpty();
    }

}
