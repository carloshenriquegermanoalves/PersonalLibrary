package org.br.ufpb.dcx.carlos.personalLibrary.control.search;

import org.br.ufpb.dcx.carlos.personalLibrary.control.search.useful.authors.UsefulForSearchByAuthorCountry;
import org.br.ufpb.dcx.carlos.personalLibrary.control.search.useful.authors.UsefulForSearchByAuthorGenre;
import org.br.ufpb.dcx.carlos.personalLibrary.control.search.useful.authors.UsefulForSearchByAuthorName;
import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchAuthorController implements ActionListener {
    private final LibrarySystem LIBRARYSYSTEM;

    public SearchAuthorController(LibrarySystem librarysystem) {
        LIBRARYSYSTEM = librarysystem;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        searchBooksByAuthor();
    }

    public void searchBooksByAuthor() {
        if (LIBRARYSYSTEM.getBookList().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ainda não há autores na biblioteca para pesquisa!");
            return;
        }

        UsefulForSearchByAuthorGenre searchByAuthorGenre = new UsefulForSearchByAuthorGenre(LIBRARYSYSTEM);
        UsefulForSearchByAuthorName searchByAuthorName = new UsefulForSearchByAuthorName(LIBRARYSYSTEM);
        UsefulForSearchByAuthorCountry searchByAuthorCountry = new UsefulForSearchByAuthorCountry(LIBRARYSYSTEM);

        String authorSearchMenuOption = JOptionPane.showInputDialog("""
                1. Pesquisar Livros de Autores
                2. Pesquisar Livros de Autoras
                3. Pesquisar Livros de um Autor
                4. Pesquisar por Nacionalidade do Autor""");

        switch (authorSearchMenuOption) {
            case "1" -> searchByAuthorGenre.searchBooksByMaleAuthors();
            case "2" -> searchByAuthorGenre.searchBooksByFemaleAuthors();
            case "3" -> searchByAuthorName.searchBooksByAuthorName();
            case "4" -> searchByAuthorCountry.searchBooksByAuthorNationality();
            default -> JOptionPane.showMessageDialog(null, "Digite apenas opções válidas!");
        }
    }

}