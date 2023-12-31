package org.br.ufpb.dcx.carlos.personalLibrary.model;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataRecorder {

    private static final String MEMBER_FILE = "livros.dat";

    public List<Book> retrieveBookData() throws IOException, ClassNotFoundException {
        List<Book> books = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(MEMBER_FILE))) {
            while (true) {
                try {
                    Object obj = ois.readObject();
                    if (obj instanceof Book book) {
                        books.add(book);
                    } else {
                        System.err.println("O objeto não é uma instância de livro. Pulando...");
                    }
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Seja bem-vindo à biblioteca! Ainda não há arquivos cadastrados!");
        }
        return books;
    }

    public void saveBookData(Book book) throws IOException {
        List<Book> books = null;
        try {
            books = retrieveBookData();
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        assert books != null;
        books.add(book);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(MEMBER_FILE))) {
            for (Book b : books) {
                oos.writeObject(b);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar os dados do sistema: " + e.getMessage());
        }
    }

    public void saveBookList(List<Book> bookList) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(MEMBER_FILE))) {
            for (Book book : bookList) {
                oos.writeObject(book);
            }
            JOptionPane.showMessageDialog(null, "Lista de livros salva com sucesso!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar a lista de livros: " + e.getMessage());
        }
    }
}