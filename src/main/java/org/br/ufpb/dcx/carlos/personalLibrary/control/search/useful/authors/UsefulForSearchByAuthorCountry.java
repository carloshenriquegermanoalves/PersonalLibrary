package org.br.ufpb.dcx.carlos.personalLibrary.control.search.useful.authors;

import org.br.ufpb.dcx.carlos.personalLibrary.control.display.useful.UsefulForDisplayBooksList;
import org.br.ufpb.dcx.carlos.personalLibrary.model.Book;
import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

import javax.swing.*;
import java.util.List;

public class UsefulForSearchByAuthorCountry {
    private final LibrarySystem LIBRARYSYSTEM;

    public UsefulForSearchByAuthorCountry(LibrarySystem librarysystem) {
        LIBRARYSYSTEM = librarysystem;
    }

    public void searchBooksByAuthorNationality() {
        String authorNationalityForSearch = JOptionPane.showInputDialog("Digite o País de Nascimento do Autor: ");
        List<Book> booksByNationality = LIBRARYSYSTEM.findBooksByAuthorCountry(authorNationalityForSearch);
        if (!booksByNationality.isEmpty()) {
            UsefulForDisplayBooksList displayBooksList = new UsefulForDisplayBooksList(booksByNationality);
            JOptionPane.showMessageDialog(null, "Os livros escritos por autores que nasceram em: " + authorNationalityForSearch + " sao: ");
            displayBooksList.displayBooksList();
        } else {
            JOptionPane.showMessageDialog(null, "Não há livros escritos por autores que nasceram em: " + authorNationalityForSearch);
        }
    }
}
