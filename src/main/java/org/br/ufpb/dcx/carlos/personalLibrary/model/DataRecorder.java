package org.br.ufpb.dcx.carlos.personalLibrary.model;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataRecorder {

    private static final String MEMBER_FILE = "books.dat";

    public List<Book> retrieveBookData() throws IOException, ClassNotFoundException {
        List<Book> books = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(MEMBER_FILE))) {
            while (true) {
                try {
                    Object obj = ois.readObject();
                    if (obj instanceof Book book) {
                        books.add(book);
                    } else {
                        System.err.println("Object is not an instance of Book, skipping.");
                    }
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("The data file has not been initialized yet, as this is the first time using the program or the file has been deleted.");
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
            JOptionPane.showMessageDialog(null, "Data saved successfully!");
        } catch (IOException e) {
            System.err.println("Error saving system data: " + e.getMessage());
        }
    }
}
