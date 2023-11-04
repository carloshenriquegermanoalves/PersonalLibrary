package org.br.ufpb.dcx.carlos.personalLibrary.control.management.useful;

import javax.swing.*;

public class UsefulForYearOfReading {

    public int enterYearOfReading() {
        int yearOfReading = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                String yearOfReadingString = JOptionPane.showInputDialog("Digite o ano de leitura: ");
                yearOfReading = Integer.parseInt(yearOfReadingString);
                validInput = true;
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null, "Digite números válidos para o ano de leitura.");
            }
        }

        return yearOfReading;
    }

    public String getYearOfReadingChoice() {
        String choice = "";

        while (!choice.equals("1") && !choice.equals("2")) {
            choice = JOptionPane.showInputDialog("""
                            Você já leu este livro?
                            1.Sim
                            2.Não
                            """);
        }

        return choice;
    }

}
