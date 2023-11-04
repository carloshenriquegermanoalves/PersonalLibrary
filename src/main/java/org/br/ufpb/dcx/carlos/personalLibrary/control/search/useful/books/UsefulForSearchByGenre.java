package org.br.ufpb.dcx.carlos.personalLibrary.control.search.useful.books;

import org.br.ufpb.dcx.carlos.personalLibrary.control.search.useful.DisplayList;
import org.br.ufpb.dcx.carlos.personalLibrary.model.Book;
import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

import javax.swing.*;

import java.util.List;

public class UsefulForSearchByGenre {
    private final LibrarySystem LIBRARYSYSTEM;
    private final DisplayList DISPLAYLIST = new DisplayList();

    public UsefulForSearchByGenre(LibrarySystem librarysystem) {
        LIBRARYSYSTEM = librarysystem;
    }

    public void searchByBookGenre() {
        String genreForSearch = JOptionPane.showInputDialog("Digite o Gênero do Livro para Pesquisa:");
        List<Book> foundBooksByGenre = LIBRARYSYSTEM.findBooksByGenre(genreForSearch);
        DISPLAYLIST.displayList("Livros por gênero", foundBooksByGenre);
    }

}
