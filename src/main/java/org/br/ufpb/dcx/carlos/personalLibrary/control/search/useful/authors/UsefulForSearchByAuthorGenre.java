package org.br.ufpb.dcx.carlos.personalLibrary.control.search.useful.authors;

import org.br.ufpb.dcx.carlos.personalLibrary.control.display.useful.UsefulDisplayAuthorList;
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
            JOptionPane.showMessageDialog(null, "Books written by male authors are: ");
            displayAuthorList.displayAuthorsList();
        } else {
            JOptionPane.showMessageDialog(null, "There are no books written by male authors!");
        }
    }

    public void searchBooksByFemaleAuthors() {
        if (!LIBRARYSYSTEM.femaleAuthorsList().isEmpty()) {
            UsefulDisplayAuthorList displayAuthorList = new UsefulDisplayAuthorList(LIBRARYSYSTEM.femaleAuthorsList());
            JOptionPane.showMessageDialog(null, "Books written by female authors are: ");
            displayAuthorList.displayAuthorsList();
        } else {
            JOptionPane.showMessageDialog(null, "There are no books written by female authors!");
        }
    }

}
