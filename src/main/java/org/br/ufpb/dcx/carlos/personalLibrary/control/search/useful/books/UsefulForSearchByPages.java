package org.br.ufpb.dcx.carlos.personalLibrary.control.search.useful.books;

import org.br.ufpb.dcx.carlos.personalLibrary.control.search.useful.DisplayList;
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

        JScrollPane scrollPane = getjScrollPane(pageCountForSearch, searchType, foundBooks);

        JDialog resultDialog = new JDialog();
        resultDialog.setTitle("Resultados da Pesquisa");
        DisplayList.getPanel(scrollPane, resultDialog);
    }

    private static JScrollPane getjScrollPane(int pageCountForSearch, int searchType, List<Book> foundBooks) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        String comparison = (searchType == 1) ? "ou mais" : "ou menos";

        if (!foundBooks.isEmpty()) {
            for (Book book : foundBooks) {
                listModel.addElement(book.getTitle());
            }
        } else {
            listModel.addElement("Não há livros com " + pageCountForSearch + " " + comparison + " páginas na biblioteca!!");
        }

        JList<String> resultList = new JList<>(listModel);
        resultList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        return new JScrollPane(resultList);
    }
}
