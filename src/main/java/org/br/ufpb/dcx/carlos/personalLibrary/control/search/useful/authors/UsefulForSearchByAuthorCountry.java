package org.br.ufpb.dcx.carlos.personalLibrary.control.search.useful.authors;

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
        StringBuilder message = new StringBuilder();

        if (!booksByNationality.isEmpty()) {
            message.append("Os livros escritos por autores que nasceram em ").append(authorNationalityForSearch).append(" são: \n\n");

            for (Book book : booksByNationality) {
                message.append(book.getTitle()).append("\n");
            }
        } else {
            message.append("Não há livros escritos por autores que nasceram em: ").append(authorNationalityForSearch);
        }

        showMessage(message.toString());
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
