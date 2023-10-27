package org.br.ufpb.dcx.carlos.personalLibrary.view;

import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

import javax.swing.*;
import java.awt.*;

import static java.awt.Color.*;

public class LibraryGUI extends JFrame {

    JLabel line1;
    LibrarySystem librarySystem = new LibrarySystem();
    JMenuBar menuBar = new JMenuBar();

    public LibraryGUI() {
        setTitle("Biblioteca Pessoal");
        setSize(800,800);
        setLocationRelativeTo(null);
        setResizable(false);
        setBackground(WHITE);
        getContentPane().setBackground(BLACK);
        line1 = new JLabel("Sistema de Gerenciamento Pessoal de Livros");
        line1.setForeground(YELLOW);
        line1.setFont(new Font("Serif", Font.BOLD, 24));
        setLayout(new GridLayout(3, 1));
        add(line1);
        add(new JLabel());
        createMainMenu(createDisplaySection(), createManagementSection(), createSearchSection());

    }

    public JMenu createDisplaySection() {
        JMenu menuDisplay = new JMenu("Área de Exibição");

        JMenuItem acquisitionOrder = new JMenuItem("Ordem de Aquisição");
        JMenuItem alphabeticalOrder = new JMenuItem("Ordem Alfabética");
        JMenuItem genreList = new JMenuItem("Gêneros dos Livros");
        JMenuItem authorList = new JMenuItem("Autores dos Livros");
        JMenuItem alphabeticallySortedByGenres = new JMenuItem("Ordenados Alfabeticamente por Gêneros");

        menuDisplay.add(acquisitionOrder);
        menuDisplay.add(alphabeticalOrder);
        menuDisplay.add(genreList);
        menuDisplay.add(authorList);
        menuDisplay.add(alphabeticallySortedByGenres);

        return menuDisplay;
    }

    public JMenu createManagementSection() {
        JMenu menuManagement = new JMenu("Área de Gerenciamento");

        JMenuItem registerBook = new JMenuItem("Cadastrar Livro");
        JMenuItem removeBook = new JMenuItem("Remover Livro");
        JMenuItem setBook = new JMenuItem("Alterar Livro");

        menuManagement.add(registerBook);
        menuManagement.add(removeBook);
        menuManagement.add(setBook);

        return menuManagement;
    }

    public JMenu createSearchSection() {
        JMenu searchMenu = new JMenu("Área de Pesquisa");

        JMenuItem searchBook = new JMenuItem("Pesquisar Livro");
        JMenuItem searchAuthor = new JMenuItem("Pesquisar Autor");
        JMenuItem searchYear = new JMenuItem("Pesquisar por data");

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
