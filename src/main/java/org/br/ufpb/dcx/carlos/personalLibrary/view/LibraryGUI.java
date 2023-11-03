package org.br.ufpb.dcx.carlos.personalLibrary.view;

import org.br.ufpb.dcx.carlos.personalLibrary.control.display.*;
import org.br.ufpb.dcx.carlos.personalLibrary.control.management.*;
import org.br.ufpb.dcx.carlos.personalLibrary.control.search.*;
import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

import javax.swing.*;
import java.awt.*;

public class LibraryGUI extends JFrame {
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

        JMenuItem subGenreList = new JMenuItem("Subgênero dos Livros");
        subGenreList.addActionListener(new DisplayAllBooksSubGenres(librarySystem));

        JMenuItem authorsList = new JMenuItem("Lista de Autores dos Livros");
        authorsList.addActionListener(new DisplayDataOfAllAuthorsController(librarySystem));

        JMenuItem alphabeticallySortedByGenres = new JMenuItem("Ordenados Alfabeticamente por Gêneros");
        alphabeticallySortedByGenres.addActionListener(new DisplayBooksSortedAlphabeticallyByGenre(librarySystem));

        JMenuItem alphabeticallySortedByGenresAndSubgenres = new JMenuItem("Ordenados Alfabeticamente por Gênero e Subgênero");
        alphabeticallySortedByGenresAndSubgenres.addActionListener(new DisplayBooksSortedAlphabeticallyByGenreAndSubgenre(librarySystem));

        JMenuItem allPageCount = new JMenuItem("Quantidade Total de Páginas");
        allPageCount.addActionListener(new DisplayAllPagesCount(librarySystem));

        menuDisplay.add(acquisitionOrder);
        menuDisplay.add(alphabeticalOrder);
        menuDisplay.add(genreList);
        menuDisplay.add(subGenreList);
        menuDisplay.add(authorsList);
        menuDisplay.add(alphabeticallySortedByGenres);
        menuDisplay.add(alphabeticallySortedByGenresAndSubgenres);
        menuDisplay.add(allPageCount);

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
        searchYear.addActionListener(new SearchByDateController(librarySystem));

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