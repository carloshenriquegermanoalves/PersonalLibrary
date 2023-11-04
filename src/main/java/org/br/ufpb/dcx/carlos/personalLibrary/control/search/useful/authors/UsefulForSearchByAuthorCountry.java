package org.br.ufpb.dcx.carlos.personalLibrary.control.search.useful.authors;

import org.br.ufpb.dcx.carlos.personalLibrary.control.search.useful.DisplayList;
import org.br.ufpb.dcx.carlos.personalLibrary.model.Book;
import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

import javax.swing.*;
import java.util.List;

public class UsefulForSearchByAuthorCountry {
    private final LibrarySystem LIBRARYSYSTEM;
    private final DisplayList DISPLAYLIST = new DisplayList();

    public UsefulForSearchByAuthorCountry(LibrarySystem librarysystem) {
        LIBRARYSYSTEM = librarysystem;
    }

    public void searchBooksByAuthorNationality() {
        String authorNationalityForSearch = JOptionPane.showInputDialog("Digite o País de Nascimento do Autor: ");
        List<Book> booksByNationality = LIBRARYSYSTEM.findBooksByAuthorCountry(authorNationalityForSearch);
        DISPLAYLIST.displayList("Livros por país de autor", booksByNationality);
    }

}