package org.br.ufpb.dcx.carlos.personalLibrary.control.search;

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
        boolean yearOfReadingIsNumeric = false;
        int yearOfReadingToSearch = 0;

        while (!yearOfReadingIsNumeric) {
            try {
                String yearOfReadingString = JOptionPane.showInputDialog("Digite o ano de leitura: ");
                yearOfReadingToSearch = Integer.parseInt(yearOfReadingString);
                yearOfReadingIsNumeric = true;
            } catch (NumberFormatException exception) {
                showMessage("Digite apenas valores numéricos para o ano de leitura.");
            }
        }

        List<Book> booksReadInYear = LIBRARYSYSTEM.findBooksByYearOfReading(yearOfReadingToSearch);

        if (!booksReadInYear.isEmpty()) {
            StringBuilder message = new StringBuilder();
            message.append("Os Livros Lidos em ").append(yearOfReadingToSearch).append(" são:\n\n");
            for (Book book : booksReadInYear) {
                message.append(book.getTitle()).append("\n");
            }
            showMessage(message.toString());
        } else {
            showMessage("Não Há Livros Lidos em " + yearOfReadingToSearch);
        }
    }

    private void searchUnreadBooks() {
        List<Book> unreadBooks = LIBRARYSYSTEM.findUnreadBooks();

        if (!unreadBooks.isEmpty()) {
            StringBuilder message = new StringBuilder();
            message.append("Os Livros que Ainda Não Foram Lidos são:\n\n");
            for (Book book : unreadBooks) {
                message.append(book.getTitle()).append("\n");
            }
            showMessage(message.toString());
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
}
