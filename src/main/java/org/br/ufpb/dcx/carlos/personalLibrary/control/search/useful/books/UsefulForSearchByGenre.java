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
        String genreForSearch = JOptionPane.showInputDialog("Digite o Gênero do Livro para Pesquisa: ");
        List<Book> foundBooksByGenre = LIBRARYSYSTEM.findBooksByGenre(genreForSearch);
        if (!foundBooksByGenre.isEmpty()) {
            UsefulForDisplayBooksList listBooksController = new UsefulForDisplayBooksList(foundBooksByGenre);
            JOptionPane.showMessageDialog(null, "Os Livros do Gênero " + genreForSearch + " são: ");
            listBooksController.displayBooksList();
        } else {
            JOptionPane.showMessageDialog(null, "Não Há Livros do Gênero " + genreForSearch + " na Biblioteca!");
        }
    }

}
