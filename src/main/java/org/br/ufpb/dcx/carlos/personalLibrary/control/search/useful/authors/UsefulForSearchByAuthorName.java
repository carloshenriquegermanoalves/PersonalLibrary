package org.br.ufpb.dcx.carlos.personalLibrary.control.search.useful.authors;

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
            StringBuilder message = new StringBuilder("Os livros escritos por ").append(authorNameForSearch).append(" são: \n\n");

            for (Book book : booksByAuthor) {
                message.append(book.getTitle()).append("\n");
            }

            showMessage(message.toString());
        } else {
            showMessage("Não foram encontrados livros escritos por " + authorNameForSearch);
        }
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
