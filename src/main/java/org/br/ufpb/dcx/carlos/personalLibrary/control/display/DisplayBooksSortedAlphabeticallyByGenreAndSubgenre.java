package org.br.ufpb.dcx.carlos.personalLibrary.control.display;

import org.br.ufpb.dcx.carlos.personalLibrary.model.Book;
import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DisplayBooksSortedAlphabeticallyByGenreAndSubgenre implements ActionListener {
    private final LibrarySystem LIBRARYSYSTEM;

    public DisplayBooksSortedAlphabeticallyByGenreAndSubgenre(LibrarySystem librarysystem) {
        this.LIBRARYSYSTEM = librarysystem;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        displayBooksSortedAlphabeticallyByGenreAndSubgenre();
    }

    private void displayBooksSortedAlphabeticallyByGenreAndSubgenre() {
        if (isThereAnyBooksInLibrary()) {
            JOptionPane.showMessageDialog(null, "A biblioteca está vazia. Não há livros para exibir.");
        } else {
            StringBuilder message = new StringBuilder();
            message.append("Os livros cadastrados na biblioteca, em ordem alfabética por gênero e subgênero dos livros, são: \n\n");
            List<Book> sortedBooks = LIBRARYSYSTEM.booksSortedAlphabeticallyByGenreAndSubgenre();
            for (Book book : sortedBooks) {
                message.append(book.getTitle()).append(" (").append(book.getBookGenre()).append(") (").append(book.getBookSubGenre()).append(") \n");
            }
            JOptionPane.showMessageDialog(null, message.toString());
        }
    }

    private boolean isThereAnyBooksInLibrary() {
        return LIBRARYSYSTEM.getBookList().isEmpty();
    }
}
