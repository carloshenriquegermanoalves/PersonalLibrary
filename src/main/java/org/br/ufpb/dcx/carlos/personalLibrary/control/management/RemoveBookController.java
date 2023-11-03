package org.br.ufpb.dcx.carlos.personalLibrary.control.management;

import org.br.ufpb.dcx.carlos.personalLibrary.model.DataRecorder;
import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class RemoveBookController implements ActionListener {
    private final LibrarySystem LIBRARYSYSTEM;
    private final DataRecorder BOOKRECORDER;

    public RemoveBookController(LibrarySystem librarySystem, DataRecorder bookRecorder) {
        this.LIBRARYSYSTEM = librarySystem;
        this.BOOKRECORDER = bookRecorder;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (LIBRARYSYSTEM.getBookList().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ainda não há livros na biblioteca para serem removidos!");
            return;
        }

        String titleToRemove = JOptionPane.showInputDialog("Digite o Título do Livro para Remover: ");

        if (titleToRemove != null) {
            boolean removed = LIBRARYSYSTEM.removeBookFromList(titleToRemove);
            if (removed) {
                try {
                    BOOKRECORDER.saveBookList(LIBRARYSYSTEM.getBookList());
                    JOptionPane.showMessageDialog(null, "Livro Removido com Sucesso.");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Não foi possível salvar os dados!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "O Livro '" + titleToRemove + "' Não Foi Encontrado na Biblioteca.");
            }
        }
    }
}
