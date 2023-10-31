package org.br.ufpb.dcx.carlos.personalLibrary.control.search.useful.authors;

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
            UsefulForDisplayBooksList displayBooksList = new UsefulForDisplayBooksList(LIBRARYSYSTEM.findBooksByAuthorGender("Masculino"));
            JOptionPane.showMessageDialog(null, "Os Livros Escritos Por Autores são: ");
            displayBooksList.displayBooksList();
        } else {
            JOptionPane.showMessageDialog(null, "Não Há Livros Escritos por Autores!");
        }
    }

    public void searchBooksByFemaleAuthors() {
        if (!LIBRARYSYSTEM.femaleAuthorsList().isEmpty()) {
            UsefulForDisplayBooksList displayBooksList = new UsefulForDisplayBooksList(LIBRARYSYSTEM.findBooksByAuthorGender("Feminino"));
            JOptionPane.showMessageDialog(null, "Os Livros Escritos Por Autoras são: ");
            displayBooksList.displayBooksList();
        } else {
            JOptionPane.showMessageDialog(null, "Não Há Livros Escritos por Autoras!");
        }
    }

}
