package org.br.ufpb.dcx.carlos.personalLibrary.control.search.useful.authors;

import org.br.ufpb.dcx.carlos.personalLibrary.model.Book;
import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

import javax.swing.*;
import java.util.List;

public class UsefulForSearchByAuthorGenre {
    private final LibrarySystem LIBRARYSYSTEM;

    public UsefulForSearchByAuthorGenre(LibrarySystem librarysystem) {
        LIBRARYSYSTEM = librarysystem;
    }

    public void searchBooksByMaleAuthors() {
        List<Book> booksByMaleAuthors = LIBRARYSYSTEM.findBooksByAuthorGender("Masculino");
        displayBooks("Os Livros Escritos Por Autores são: \n", booksByMaleAuthors);
    }

    public void searchBooksByFemaleAuthors() {
        List<Book> booksByFemaleAuthors = LIBRARYSYSTEM.findBooksByAuthorGender("Feminino");
        displayBooks("Os Livros Escritos Por Autoras são: \n", booksByFemaleAuthors);
    }

    private void displayBooks(String messagePrefix, List<Book> books) {
        if (!books.isEmpty()) {
            StringBuilder message = new StringBuilder(messagePrefix).append("\n");

            for (Book book : books) {
                message.append(book.getTitle()).append("\n");
            }

            showMessage(message.toString());
        } else {
            showMessage("Não Há Livros Correspondentes!");
        }
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
