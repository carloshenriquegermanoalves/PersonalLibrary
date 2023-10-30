package org.br.ufpb.dcx.carlos.personalLibrary.control.search.useful.authors;

import org.br.ufpb.dcx.carlos.personalLibrary.control.display.useful.UsefulForDisplayBooksList;
import org.br.ufpb.dcx.carlos.personalLibrary.model.Book;
import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

import javax.swing.*;
import java.util.List;

public class UsefulForSearchByAuthorName {
    private final LibrarySystem LIBRARYSYSTEM;


    public UsefulForSearchByAuthorName(LibrarySystem librarysystem) {
        LIBRARYSYSTEM = librarysystem;
    }

    public void searchBooksByAuthorName() {
        String authorNameForSearch = JOptionPane.showInputDialog("Digite o Nome do Autor para a Busca: ");
        List<Book> booksByAuthor = LIBRARYSYSTEM.findBooksByAuthorName(authorNameForSearch);
        if (!booksByAuthor.isEmpty()) {
            UsefulForDisplayBooksList displayBooksList = new UsefulForDisplayBooksList(LIBRARYSYSTEM.findBooksByAuthorName(authorNameForSearch));
            JOptionPane.showMessageDialog(null, "Os livros escritos por " + authorNameForSearch + " são: ");
            displayBooksList.displayBooksList();
        } else {
            JOptionPane.showMessageDialog(null, "Não foram encontrados livros escritos por " + authorNameForSearch);
        }
    }
}
