package org.br.ufpb.dcx.carlos.personalLibrary.view;

import org.br.ufpb.dcx.carlos.personalLibrary.control.display.DisplayAllBookGenresController;
import org.br.ufpb.dcx.carlos.personalLibrary.control.display.DisplayAllBooksController;
import org.br.ufpb.dcx.carlos.personalLibrary.control.display.DisplayBooksSortedAlphabetically;
import org.br.ufpb.dcx.carlos.personalLibrary.control.display.DisplayDataOfAllAuthorsController;
import org.br.ufpb.dcx.carlos.personalLibrary.control.management.EditBookController;
import org.br.ufpb.dcx.carlos.personalLibrary.control.management.RegisterBookController;
import org.br.ufpb.dcx.carlos.personalLibrary.control.management.RemoveBookController;
import org.br.ufpb.dcx.carlos.personalLibrary.control.search.SearchAuthorController;
import org.br.ufpb.dcx.carlos.personalLibrary.control.search.SearchBookController;
import org.br.ufpb.dcx.carlos.personalLibrary.control.search.SearchByDateControll;
import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

import javax.swing.*;
import java.awt.*;

import static java.awt.Color.*;

public class LibraryGUI extends JFrame {

    JLabel line1;
    LibrarySystem librarySystem = new LibrarySystem();
    JMenuBar menuBar = new JMenuBar();

    public LibraryGUI() {
        librarySystem.loadAllBooks();
        setTitle("Biblioteca Pessoal");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setResizable(false);

        ImageIcon imageIcon = new ImageIcon("src/main/java/org/br/ufpb/dcx/carlos/personalLibrary/view/Images/CarlosTeca.png");
        JLabel backgroundLabel = new JLabel(imageIcon);

        backgroundLabel.setLayout(new BorderLayout());

        this.setContentPane(backgroundLabel);

        createMainMenu(createDisplaySection(), createManagementSection(), createSearchSection());
    }


    public JMenu createDisplaySection() {
        JMenu menuDisplay = new JMenu("Área de Exibição");

        JMenuItem acquisitionOrder = new JMenuItem("Ordem de Aquisição");
        acquisitionOrder.addActionListener(new DisplayAllBooksController(librarySystem));

        JMenuItem alphabeticalOrder = new JMenuItem("Ordem Alfabética");
        alphabeticalOrder.addActionListener(new DisplayBooksSortedAlphabetically(librarySystem));

        JMenuItem genreList = new JMenuItem("Gêneros dos Livros");
        genreList.addActionListener(new DisplayAllBookGenresController(librarySystem));

        JMenuItem authorsList = new JMenuItem("Lista de Autores dos Livros");
        authorsList.addActionListener(new DisplayDataOfAllAuthorsController(librarySystem));

        JMenuItem alphabeticallySortedByGenres = new JMenuItem("Ordenados Alfabeticamente por Gêneros");
        alphabeticallySortedByGenres.addActionListener(new DisplayBooksSortedAlphabetically(librarySystem));

        menuDisplay.add(acquisitionOrder);
        menuDisplay.add(alphabeticalOrder);
        menuDisplay.add(genreList);
        menuDisplay.add(authorsList);
        menuDisplay.add(alphabeticallySortedByGenres);

        return menuDisplay;
    }

    public JMenu createManagementSection() {
        JMenu menuManagement = new JMenu("Área de Gerenciamento");

        JMenuItem registerBook = new JMenuItem("Cadastrar Livro");
        registerBook.addActionListener(new RegisterBookController(librarySystem.getBookList(), librarySystem.getBookRecorder()));

        JMenuItem removeBook = new JMenuItem("Remover Livro");
        removeBook.addActionListener(new RemoveBookController(librarySystem, librarySystem.getBookRecorder()));

        JMenuItem editBook = new JMenuItem("Alterar Livro");
        editBook.addActionListener(new EditBookController(librarySystem, librarySystem.getBookRecorder()));

        menuManagement.add(registerBook);
        menuManagement.add(removeBook);
        menuManagement.add(editBook);

        return menuManagement;
    }

    public JMenu createSearchSection() {
        JMenu searchMenu = new JMenu("Área de Pesquisa");

        JMenuItem searchBook = new JMenuItem("Pesquisar Livro");
        searchBook.addActionListener(new SearchBookController(librarySystem));

        JMenuItem searchAuthor = new JMenuItem("Pesquisar Autor");
        searchAuthor.addActionListener(new SearchAuthorController(librarySystem));

        JMenuItem searchYear = new JMenuItem("Pesquisar por data");
        searchYear.addActionListener(new SearchByDateControll(librarySystem));

        searchMenu.add(searchBook);
        searchMenu.add(searchAuthor);
        searchMenu.add(searchYear);

        return searchMenu;
    }

    public void createMainMenu(JMenu displaySection, JMenu registerSection, JMenu searchSection) {
        menuBar.add(displaySection);
        menuBar.add(registerSection);
        menuBar.add(searchSection);
        setJMenuBar(menuBar);
    }

}
