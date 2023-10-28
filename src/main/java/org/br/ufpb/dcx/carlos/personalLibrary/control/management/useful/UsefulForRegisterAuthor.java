package org.br.ufpb.dcx.carlos.personalLibrary.control.management.useful;

import org.br.ufpb.dcx.carlos.personalLibrary.model.Author;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class UsefulForRegisterAuthor {

    public List<Author> registerAuthor() {
        int authorsNumber = 0;
        boolean authorsNumberIsNumeric = false;
        authorsNumber = getAuthorsNumber(authorsNumberIsNumeric, authorsNumber);

        List<Author> authors = new ArrayList<>();
        for (int i = 0; i < authorsNumber; i++) {
            String authorName = JOptionPane.showInputDialog("Digite o nome do " + (i + 1) + "º autor: ");
            String authorGenre = JOptionPane.showInputDialog("Digite o gênero do " + (i + 1) + "º autor: ");

            String authorCountry;
            String knowNationality = getAuthorsNationalityChoice();
            authorCountry = knowNationality.equals("1") ?
                    JOptionPane.showInputDialog("Digite o país de nascimento do autor: ") : "Desconhecida";

            Author author = new Author(authorName, authorGenre, authorCountry);
            authors.add(author);
        }

        return authors;
    }

    private static int getAuthorsNumber(boolean authorsNumberIsNumeric, int authorsNumber) {
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

    private String getAuthorsNationalityChoice() {
        String choice = "";

        while (!choice.equals("1") && !choice.equals("2")) {
            choice = JOptionPane.showInputDialog("Você sabe a nacionalidade do autor?\n1.Sim\n2.Não");
        }

        return choice;
    }

}
