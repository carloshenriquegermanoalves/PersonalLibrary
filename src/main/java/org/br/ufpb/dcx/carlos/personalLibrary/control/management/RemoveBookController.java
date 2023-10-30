package org.br.ufpb.dcx.carlos.personalLibrary.control.management;

import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;
import org.br.ufpb.dcx.carlos.personalLibrary.model.DataRecorder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class RemoveBookController implements ActionListener {
    private final LibrarySystem librarySystem;
    private final DataRecorder bookRecorder;

    public RemoveBookController(LibrarySystem librarySystem, DataRecorder bookRecorder) {
        this.librarySystem = librarySystem;
        this.bookRecorder = bookRecorder;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String titleToRemove = JOptionPane.showInputDialog("Digite o Título do Livro para Remover: ");

        if (librarySystem.removeBookFromList(titleToRemove)) {
            JOptionPane.showMessageDialog(null, "Livro Removido com Sucesso.");
            try {
                bookRecorder.saveBookList(librarySystem.getBookList());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Não foi possível salvar os dados!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "O Livro '" + titleToRemove + "' Não Foi Encontrado na Biblioteca.");
        }
    }
}
