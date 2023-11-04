package org.br.ufpb.dcx.carlos.personalLibrary.control.search;

import org.br.ufpb.dcx.carlos.personalLibrary.control.search.useful.DisplayList;
import org.br.ufpb.dcx.carlos.personalLibrary.model.Book;
import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SearchByDateController implements ActionListener {
    private final LibrarySystem LIBRARYSYSTEM;
    private final DisplayList DISPLAYLIST = new DisplayList();

    public SearchByDateController(LibrarySystem librarysystem) {
        LIBRARYSYSTEM = librarysystem;
    }

    public void searchBooksByDate() {
        if (LIBRARYSYSTEM.getBookList().isEmpty()) {
            showMessage("Ainda não há livros na biblioteca para pesquisa!");
            return;
        }
        String dateSearchMenuOption = JOptionPane.showInputDialog("1. Pesquisar por Livros lidos em um determinado ano\n2. Pesquisar por Livros não lidos");
        switch (dateSearchMenuOption) {
            case "1" -> searchBooksReadInYear();
            case "2" -> searchUnreadBooks();
            default -> showMessage("Digite apenas opções válidas!");
        }
    }

    private void searchBooksReadInYear() {
        int yearOfReadingToSearch = getInputForYearOfReading();
        List<Book> booksReadInYear = LIBRARYSYSTEM.findBooksByYearOfReading(yearOfReadingToSearch);
        if (!booksReadInYear.isEmpty()) {
            DISPLAYLIST.displayList("Livros Lidos em " + yearOfReadingToSearch, booksReadInYear);
        } else {
            showMessage("Não Há Livros Lidos em " + yearOfReadingToSearch);
        }
    }

    private void searchUnreadBooks() {
        List<Book> unreadBooks = LIBRARYSYSTEM.findUnreadBooks();
        if (!unreadBooks.isEmpty()) {
            DISPLAYLIST.displayList("Livros que Ainda Não Foram Lidos", unreadBooks);
        } else {
            showMessage("Parabéns! Não há livros não lidos! Você Leu Todos os seus Livros!");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        searchBooksByDate();
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    private int getInputForYearOfReading() {
        int yearOfReadingToSearch;
        while (true) {
            try {
                String yearOfReadingString = JOptionPane.showInputDialog("Digite o ano de leitura: ");
                yearOfReadingToSearch = Integer.parseInt(yearOfReadingString);
                break;
            } catch (NumberFormatException exception) {
                showMessage("Digite apenas valores numéricos para o ano de leitura.");
            }
        }
        return yearOfReadingToSearch;
    }

}
