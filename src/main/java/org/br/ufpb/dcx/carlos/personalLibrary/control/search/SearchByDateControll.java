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
        if (LIBRARYSYSTEM.getBookList().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ainda não há livros na biblioteca para pesquisa!");
            return;
        }

        String dateSearchMenuOption = JOptionPane.showInputDialog("1. Pesquisar por Livros lidos em um determinado ano\n2. Pesquisar por Livros não lidos");

        switch (dateSearchMenuOption) {
            case "1" -> searchBooksReadInYear();
            case "2" -> searchUnreadBooks();
            default -> JOptionPane.showMessageDialog(null, "Digite apenas opções válidas!");
        }
    }

    private void searchBooksReadInYear() {
        boolean yearOfReadingIsNumeric = false;
        int yearOfReadingToSearch = 0;

        while (!yearOfReadingIsNumeric) {
            try {
                String yearOfReadingString = JOptionPane.showInputDialog("Digite o ano de leitura: ");
                yearOfReadingToSearch = Integer.parseInt(yearOfReadingString);
                yearOfReadingIsNumeric = true;
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null, "Digite apenas valores numéricos para o ano de leitura.");
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
            JOptionPane.showMessageDialog(null, "Parabéns! Não há livros não lidos! Você Leu Todos os seus Livros!");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        searchBooksByDate();
    }
}

