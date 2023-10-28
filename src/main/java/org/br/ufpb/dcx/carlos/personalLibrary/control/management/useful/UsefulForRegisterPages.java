package org.br.ufpb.dcx.carlos.personalLibrary.control.management.useful;

import javax.swing.*;

public class UsefulForRegisterPages {

    public int enterPageCount() {
        int pageCount = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                String pageCountString = JOptionPane.showInputDialog("Digite o número de páginas do livro: ");
                pageCount = Integer.parseInt(pageCountString);
                validInput = true;
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null, "Digite números válidos para a quantidade de páginas do livro");
            }
        }

        return pageCount;
    }

}
