package org.br.ufpb.dcx.carlos.personalLibrary.control.search.useful.books;

import org.br.ufpb.dcx.carlos.personalLibrary.control.display.useful.UsefulForDisplayBooksList;
import org.br.ufpb.dcx.carlos.personalLibrary.model.Book;
import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

import javax.swing.*;
import java.util.List;

public class UsefulForSearchByPages {
    private final LibrarySystem librarySystem;

    public UsefulForSearchByPages(LibrarySystem librarySystem) {
        this.librarySystem = librarySystem;
    }

    public void searchByPageCount() {
        String pageCountSearchOption = JOptionPane.showInputDialog(
                "1. Search books with more pages than specified\n"
                        + "2. Search books with fewer pages than specified");
        switch (pageCountSearchOption) {
            case "1", "2" -> {
                int pageCountForSearch = getInputForPageCount();
                if (pageCountSearchOption.equals("1")) {
                    searchBooksByPageCount(pageCountForSearch, 1);
                } else {
                    searchBooksByPageCount(pageCountForSearch, 2);
                }
            }
            default -> JOptionPane.showMessageDialog(null, "Please enter only the available options!");
        }
    }

    public int getInputForPageCount() {
        int pageCountForSearch = 0;
        boolean pageCountIsNumeric = false;
        while (!pageCountIsNumeric) {
            try {
                String pageCountString = JOptionPane
                        .showInputDialog("Enter the page count for search:");
                pageCountForSearch = Integer.parseInt(pageCountString);
                pageCountIsNumeric = true;
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null, "Please enter only numeric values for page count.");
            }
        }
        return pageCountForSearch;
    }

    public void searchBooksByPageCount(int pageCountForSearch, int searchType) {
        List<Book> foundBooks = (searchType == 1) ?
                librarySystem.findBooksByMorePageCount(pageCountForSearch) :
                librarySystem.findBooksByLessPageCount(pageCountForSearch);

        if (!foundBooks.isEmpty()) {
            UsefulForDisplayBooksList listBooksController = new UsefulForDisplayBooksList(foundBooks);
            String comparison = (searchType == 1) ? "or more" : "or fewer";
            JOptionPane.showMessageDialog(null, "Books with " + pageCountForSearch + " " + comparison + " pages are: ");
            listBooksController.displayBooksList();
        } else {
            String comparison = (searchType == 1) ? "or more" : "or fewer";
            JOptionPane.showMessageDialog(null, "There are no books with " + pageCountForSearch + " " + comparison + " pages in the library!");
        }
    }

}
