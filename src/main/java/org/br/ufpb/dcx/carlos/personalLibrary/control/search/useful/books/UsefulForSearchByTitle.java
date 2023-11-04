package org.br.ufpb.dcx.carlos.personalLibrary.control.search.useful.books;

import org.br.ufpb.dcx.carlos.personalLibrary.model.Book;
import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;
import org.br.ufpb.dcx.carlos.personalLibrary.model.exceptions.BookNotFoundException;

import javax.swing.*;

public class UsefulForSearchByTitle {
    private final LibrarySystem librarySystem;

    public UsefulForSearchByTitle(LibrarySystem librarySystem) {
        this.librarySystem = librarySystem;
    }

    public void searchByBookTitle() {
        String bookTitleForSearch = JOptionPane.showInputDialog("Digite o Título do Livro para Pesquisar: ");
        try {
            Book foundBook = librarySystem.findBookInList(bookTitleForSearch);
            if (foundBook != null) {
                JOptionPane.showMessageDialog(null, foundBook.toString());
            } else {
                JOptionPane.showMessageDialog(null, "O Livro " + bookTitleForSearch + " Não foi encontrado!");
            }
        } catch (BookNotFoundException e) {
            JOptionPane.showMessageDialog(null, "O Livro Pesquisado não foi Encontrado!!");
        }
    }

}
