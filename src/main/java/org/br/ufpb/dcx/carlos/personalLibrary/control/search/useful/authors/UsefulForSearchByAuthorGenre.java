package org.br.ufpb.dcx.carlos.personalLibrary.control.search.useful.authors;

import org.br.ufpb.dcx.carlos.personalLibrary.control.display.useful.UsefulDisplayAuthorList;
import org.br.ufpb.dcx.carlos.personalLibrary.control.display.useful.UsefulForDisplayBooksList;
import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

import javax.swing.*;

public class UsefulForSearchByAuthorGenre {
    private final LibrarySystem LIBRARYSYSTEM;


    public UsefulForSearchByAuthorGenre(LibrarySystem librarysystem) {
        LIBRARYSYSTEM = librarysystem;
    }

    public void searchBooksByMaleAuthors() {
        if (!LIBRARYSYSTEM.maleAuthorsList().isEmpty()) {
            UsefulDisplayAuthorList displayAuthorList = new UsefulDisplayAuthorList(LIBRARYSYSTEM.maleAuthorsList());
            JOptionPane.showMessageDialog(null, "Os Livros Escritos Por Autores são: ");
            displayAuthorList.displayAuthorsList();
        } else {
            JOptionPane.showMessageDialog(null, "Não Há Livros Escritos por Autores!");
        }
    }

    public void searchBooksByFemaleAuthors() {
        if (!LIBRARYSYSTEM.femaleAuthorsList().isEmpty()) {
            UsefulDisplayAuthorList displayAuthorList = new UsefulDisplayAuthorList(LIBRARYSYSTEM.femaleAuthorsList());
            JOptionPane.showMessageDialog(null, "Os Livros Escritos por Autoras: ");
            displayAuthorList.displayAuthorsList();
        } else {
            JOptionPane.showMessageDialog(null, "Não Há Livros Escritos por Autoras!");
        }
    }

}
