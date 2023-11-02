package org.br.ufpb.dcx.carlos.personalLibrary.control.search.useful.books;

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
        String pageCountSearchOption = JOptionPane.showInputDialog("1. Pesquisar Livros com ou mais Páginas do que o informado\n" + "2. Pesquisar Livros com ou menos páginas do que o informado");
        switch (pageCountSearchOption) {
            case "1", "2" -> {
                int pageCountForSearch = getInputForPageCount();
                if (pageCountSearchOption.equals("1")) {
                    searchBooksByPageCount(pageCountForSearch, 1);
                } else {
                    searchBooksByPageCount(pageCountForSearch, 2);
                }
            }
            default -> JOptionPane.showMessageDialog(null, "Por favor! Digite apenas opções válidas!");
        }
    }

    public int getInputForPageCount() {
        int pageCountForSearch = 0;
        boolean pageCountIsNumeric = false;
        while (!pageCountIsNumeric) {
            try {
                String pageCountString = JOptionPane.showInputDialog("Digite a Quantidade de Páginas para Pesquisa:");
                pageCountForSearch = Integer.parseInt(pageCountString);
                pageCountIsNumeric = true;
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null, "Digite apenas números válidos para a pesquisa.");
            }
        }
        return pageCountForSearch;
    }

    public void searchBooksByPageCount(int pageCountForSearch, int searchType) {
        List<Book> foundBooks = (searchType == 1) ? librarySystem.findBooksByMorePageCount(pageCountForSearch) : librarySystem.findBooksByLessPageCount(pageCountForSearch);
        StringBuilder message = new StringBuilder();

        String comparison = (searchType == 1) ? "ou mais" : "ou menos";
        if (!foundBooks.isEmpty()) {
            message.append("Livros com ").append(pageCountForSearch).append(" ").append(comparison).append(" páginas são: \n\n");

            for (Book book : foundBooks) {
                message.append(book.getTitle()).append("\n");
            }
        } else {
            message.append("Não há livros com ").append(pageCountForSearch).append(" ").append(comparison).append(" páginas na biblioteca!!");
        }

        showMessage(message.toString());
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
