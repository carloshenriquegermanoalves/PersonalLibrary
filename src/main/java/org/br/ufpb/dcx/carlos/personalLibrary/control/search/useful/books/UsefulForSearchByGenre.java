package org.br.ufpb.dcx.carlos.personalLibrary.control.search.useful.books;

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
        StringBuilder message = new StringBuilder();

        if (!foundBooksByGenre.isEmpty()) {
            message.append("Os Livros do Gênero ").append(genreForSearch).append(" são: \n\n");

            for (Book book : foundBooksByGenre) {
                message.append(book.getTitle()).append("\n");
            }
        } else {
            message.append("Não Há Livros do Gênero ").append(genreForSearch).append(" na Biblioteca!");
        }

        showMessage(message.toString());
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
