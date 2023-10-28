package org.br.ufpb.dcx.carlos.personalLibrary.control.search.useful.books;

import org.br.ufpb.dcx.carlos.personalLibrary.control.display.useful.UsefulForDisplayBooksList;
import org.br.ufpb.dcx.carlos.personalLibrary.model.Book;
import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

import javax.swing.*;
import java.util.List;

public class UsefulForSearchByGenre {
    private final LibrarySystem LIBRARYSYSTEM;


    public UsefulForSearchByGenre(LibrarySystem librarysystem) {
        LIBRARYSYSTEM = librarysystem;
    }

    public void searchByBookGenre() {
        String genreForSearch = JOptionPane.showInputDialog("Enter the genre for search: ");
        List<Book> foundBooksByGenre = LIBRARYSYSTEM.findBooksByGenre(genreForSearch);
        if (!foundBooksByGenre.isEmpty()) {
            UsefulForDisplayBooksList listBooksController = new UsefulForDisplayBooksList(foundBooksByGenre);
            JOptionPane.showMessageDialog(null, "Books in the genre " + genreForSearch + " are: ");
            listBooksController.displayBooksList();
        } else {
            JOptionPane.showMessageDialog(null, "There are no books in the genre " + genreForSearch + " in the library!");
        }
    }

}
