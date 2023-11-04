package org.br.ufpb.dcx.carlos.personalLibrary.control.search.useful.authors;

import org.br.ufpb.dcx.carlos.personalLibrary.control.search.useful.DisplayList;
import org.br.ufpb.dcx.carlos.personalLibrary.model.Book;
import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

import javax.swing.*;
import java.util.List;

public class UsefulForSearchByAuthorName {
    private final LibrarySystem LIBRARYSYSTEM;
    private final DisplayList DISPLAYLIST = new DisplayList();

    public UsefulForSearchByAuthorName(LibrarySystem librarysystem) {
        LIBRARYSYSTEM = librarysystem;
    }

    public void searchBooksByAuthorName() {
        String authorNameForSearch = JOptionPane.showInputDialog("Digite o Nome do Autor para a Busca: ");
        List<Book> booksByAuthor = LIBRARYSYSTEM.findBooksByAuthorName(authorNameForSearch);
        DISPLAYLIST.displayList("Resultados da Busca para " + authorNameForSearch, booksByAuthor);
    }

}