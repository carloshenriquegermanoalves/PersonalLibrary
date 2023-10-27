package org.br.ufpb.dcx.carlos.personalLibrary;

import org.br.ufpb.dcx.carlos.personalLibrary.view.LibraryGUI;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame window = new LibraryGUI();
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}