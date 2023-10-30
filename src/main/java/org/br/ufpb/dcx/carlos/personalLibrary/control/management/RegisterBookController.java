package org.br.ufpb.dcx.carlos.personalLibrary.control.management;

import org.br.ufpb.dcx.carlos.personalLibrary.control.management.useful.UsefulForRegisterAuthor;
import org.br.ufpb.dcx.carlos.personalLibrary.control.management.useful.UsefulForRegisterPages;
import org.br.ufpb.dcx.carlos.personalLibrary.control.management.useful.UsefulForYearOfReading;
import org.br.ufpb.dcx.carlos.personalLibrary.model.Author;
import org.br.ufpb.dcx.carlos.personalLibrary.model.Book;
import org.br.ufpb.dcx.carlos.personalLibrary.model.DataRecorder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class RegisterBookController implements ActionListener {
    UsefulForRegisterAuthor registerAuthor = new UsefulForRegisterAuthor();
    UsefulForRegisterPages registerPages = new UsefulForRegisterPages();
    UsefulForYearOfReading registerYearOfReading = new UsefulForYearOfReading();
    private final List<Book> bookList;
    private final DataRecorder dataRecorder;

    public RegisterBookController(List<Book> bookList, DataRecorder dataRecorder) {
        this.bookList = bookList;
        this.dataRecorder = dataRecorder;
    }

    public void registerBook(List<Book> bookList) {
        String bookTitle = JOptionPane.showInputDialog("Digite o título do livro: ");
        List<Author> authors = registerAuthor.registerAuthor();
        String bookGenre = JOptionPane.showInputDialog("Digite o gênero do livro: ");
        int pageCount = registerPages.enterPageCount();
        int yearOfReading = 0;
        String bookHasRead = registerYearOfReading.getYearOfReadingChoice();
        if (bookHasRead.equals("1")) {
            yearOfReading = registerYearOfReading.enterYearOfReading();
        }

        Book newBook = new Book(bookTitle, authors, bookGenre, pageCount, bookHasRead, yearOfReading);
        bookList.add(newBook);

        JOptionPane.showMessageDialog(null, "Livro registrado com sucesso!");

        try {
            dataRecorder.saveBookData(newBook);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar dados do livro: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        registerBook(bookList);
    }
}
