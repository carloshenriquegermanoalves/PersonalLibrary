package org.br.ufpb.dcx.carlos.personalLibrary.control.search;

import org.br.ufpb.dcx.carlos.personalLibrary.control.search.useful.books.UsefulForSearchByGenre;
import org.br.ufpb.dcx.carlos.personalLibrary.control.search.useful.books.UsefulForSearchByPages;
import org.br.ufpb.dcx.carlos.personalLibrary.control.search.useful.books.UsefulForSearchByTitle;
import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchBookController implements ActionListener {
    private final LibrarySystem LIBRARYSYSTEM;

    public SearchBookController(LibrarySystem librarySystem) {
        this.LIBRARYSYSTEM = librarySystem;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        searchBooks();
    }

    public void searchBooks() {
        if (LIBRARYSYSTEM.getBookList().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ainda não há livros na biblioteca para pesquisa!");
            return;
        }

        UsefulForSearchByTitle usefulForSearchByTitle = new UsefulForSearchByTitle(LIBRARYSYSTEM);
        UsefulForSearchByPages usefulForSearchByPages = new UsefulForSearchByPages(LIBRARYSYSTEM);
        UsefulForSearchByGenre usefulForSearchByGenre = new UsefulForSearchByGenre(LIBRARYSYSTEM);

        String searchMenuOption = JOptionPane.showInputDialog("1. Pesquisa por Título do Livro\n2. Pesquisa por Gênero do Livro\n3. Pesquisa por Quantidade de Páginas");

        switch (searchMenuOption) {
            case "1" -> usefulForSearchByTitle.searchByBookTitle();
            case "2" -> usefulForSearchByGenre.searchByBookGenre();
            case "3" -> usefulForSearchByPages.searchByPageCount();
            default -> JOptionPane.showMessageDialog(null, "Digite apenas opções válidas!");
        }
    }


}
