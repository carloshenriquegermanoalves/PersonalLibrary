package org.br.ufpb.dcx.carlos.personalLibrary.control.search.useful.authors;

import org.br.ufpb.dcx.carlos.personalLibrary.control.search.useful.DisplayList;
import org.br.ufpb.dcx.carlos.personalLibrary.model.Book;
import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

import java.util.List;

public class UsefulForSearchByAuthorGenre {
    private final LibrarySystem LIBRARYSYSTEM;
    private final DisplayList DISPLAYLIST = new DisplayList();

    public UsefulForSearchByAuthorGenre(LibrarySystem librarysystem) {
        LIBRARYSYSTEM = librarysystem;
    }

    public void searchBooksByMaleAuthors() {
        List<Book> booksByMaleAuthors = LIBRARYSYSTEM.findBooksByAuthorGender("Masculino");
        DISPLAYLIST.displayList("Os Livros Escritos Por Autores são: \n", booksByMaleAuthors);
    }

    public void searchBooksByFemaleAuthors() {
        List<Book> booksByFemaleAuthors = LIBRARYSYSTEM.findBooksByAuthorGender("Feminino");
        DISPLAYLIST.displayList("Os Livros Escritos Por Autoras são: \n", booksByFemaleAuthors);
    }

}
