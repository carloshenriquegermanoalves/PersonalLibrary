package org.br.ufpb.dcx.carlos.personalLibrary.control.management.useful;

import org.br.ufpb.dcx.carlos.personalLibrary.model.Author;
import org.br.ufpb.dcx.carlos.personalLibrary.model.Book;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class UsefulForRegisterAuthor {
    private final List<Book> BOOKLIST;

    public UsefulForRegisterAuthor(List<Book> bookList) {
        this.BOOKLIST = bookList;
    }

    public List<Author> registerAuthor() {
        int authorsNumber = getAuthorsNumber();

        List<Author> authors = new ArrayList<>();
        List<Author> existingAuthors = getExistingAuthors();

        for (int i = 0; i < authorsNumber; i++) {
            String authorName = getAuthorName(i + 1);
            Author existingAuthor = findAuthorByName(existingAuthors, authorName);

            String authorGenre;
            String authorCountry;

            if (existingAuthor != null) {
                authorGenre = existingAuthor.getAuthorGender();
                authorCountry = existingAuthor.getCountryOfBirth();
            } else {
                authorGenre = getAuthorGenre(i + 1);
                authorCountry = getAuthorCountry();
            }

            Author author = new Author(authorName, authorGenre, authorCountry);
            authors.add(author);
        }

        return authors;
    }

    private int getAuthorsNumber() {
        int authorsNumber = 0;
        boolean authorsNumberIsNumeric = false;
        while (!authorsNumberIsNumeric) {
            try {
                String authorsNumberString = JOptionPane.showInputDialog("Digite a quantidade de autores do livro: ");
                authorsNumber = Integer.parseInt(authorsNumberString);
                authorsNumberIsNumeric = true;
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null, "Digite um número válido para a quantidade de autores: ");
            }
        }
        return authorsNumber;
    }

    private String getAuthorName(int authorIndex) {
        return JOptionPane.showInputDialog("Digite o nome do " + authorIndex + "º autor: ");
    }

    private String getAuthorGenre(int authorIndex) {
        return JOptionPane.showInputDialog("Digite o gênero do " + authorIndex + "º autor: ");
    }

    private String getAuthorCountry() {
        String knowNationality = getAuthorsNationalityChoice();
        return knowNationality.equals("1") ? JOptionPane.showInputDialog("Digite o país de nascimento do autor: ") : "Desconhecida";
    }

    private Author findAuthorByName(List<Author> authors, String authorName) {
        for (Author author : authors) {
            if (author.getName().equalsIgnoreCase(authorName)) {
                return author;
            }
        }
        return null;
    }

    private List<Author> getExistingAuthors() {
        List<Author> existingAuthors = new ArrayList<>();

        for (Book book : BOOKLIST) {
            for (Author author : book.getAuthor()) {
                if (!authorAlreadyExists(existingAuthors, author)) {
                    existingAuthors.add(author);
                }
            }
        }

        return existingAuthors;
    }

    private boolean authorAlreadyExists(List<Author> existingAuthors, Author author) {
        for (Author existingAuthor : existingAuthors) {
            if (existingAuthor.getName().equalsIgnoreCase(author.getName())) {
                return true;
            }
        }
        return false;
    }

    private String getAuthorsNationalityChoice() {
        String choice = "";
        while (!choice.equals("1") && !choice.equals("2")) {
            choice = JOptionPane.showInputDialog("""
                    Você sabe a nacionalidade do autor?
                    1. Sim
                    2. Não
                    """);
        }
        return choice;
    }
}
