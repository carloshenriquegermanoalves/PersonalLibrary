package org.br.ufpb.dcx.carlos.personalLibrary.control.search;

import org.br.ufpb.dcx.carlos.personalLibrary.control.display.useful.UsefulForDisplayBooksList;
import org.br.ufpb.dcx.carlos.personalLibrary.model.Book;
import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SearchByDateControll implements ActionListener {
    private final LibrarySystem LIBRARYSYSTEM;


    public SearchByDateControll(LibrarySystem librarysystem) {
        LIBRARYSYSTEM = librarysystem;
    }

    public void searchBooksByDate() {
        String dateSearchMenuOption = JOptionPane.showInputDialog(
                "1. Search for books read in a given year\n2. Search for unread books");

        switch (dateSearchMenuOption) {
            case "1" -> searchBooksReadInYear();
            case "2" -> searchUnreadBooks();
            default -> JOptionPane.showMessageDialog(null, "Please enter only the available options!");
        }
    }

    private void searchBooksReadInYear() {
        boolean yearOfReadingIsNumeric = false;
        int yearOfReadingToSearch = 0;

        while (!yearOfReadingIsNumeric) {
            try {
                String yearOfReadingString = JOptionPane.showInputDialog("Enter the year of reading: ");
                yearOfReadingToSearch = Integer.parseInt(yearOfReadingString);
                yearOfReadingIsNumeric = true;
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null, "Please enter only numeric values for the year of reading.");
            }
        }

        List<Book> booksReadInYear = LIBRARYSYSTEM.findBooksByYearOfReading(yearOfReadingToSearch);

        if (!booksReadInYear.isEmpty()) {
            UsefulForDisplayBooksList displayBooksList = new UsefulForDisplayBooksList(LIBRARYSYSTEM.findBooksByYearOfReading(yearOfReadingToSearch));
            JOptionPane.showMessageDialog(null, "Os Livros Lidos em " + yearOfReadingToSearch + " são: ");
            displayBooksList.displayBooksList();
        } else {
            JOptionPane.showMessageDialog(null, "Não Há Livros Lidos em " + yearOfReadingToSearch);
        }
    }

    private void searchUnreadBooks() {
        List<Book> unreadBooks = LIBRARYSYSTEM.findUnreadBooks();

        if (!unreadBooks.isEmpty()) {
            UsefulForDisplayBooksList displayBooksList = new UsefulForDisplayBooksList(LIBRARYSYSTEM.findUnreadBooks());
            JOptionPane.showMessageDialog(null, "Os Livros que Ainda Não Foram Lidos são: ");
            displayBooksList.displayBooksList();
        } else {
            JOptionPane.showMessageDialog(null, "Parabéns! Você Leu Todos os seus Livros!");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        searchBooksByDate();
    }
}

