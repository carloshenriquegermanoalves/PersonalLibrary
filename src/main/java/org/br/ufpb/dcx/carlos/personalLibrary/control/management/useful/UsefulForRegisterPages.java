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
                if (pageCount > 0 && pageCount < 3000) {
                    validInput = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Para a quantidade de páginas digite números entre 1 e 3000");
                }
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null, "Digite números válidos para a quantidade de páginas do livro");
            }
        }

        return pageCount;
    }

}
