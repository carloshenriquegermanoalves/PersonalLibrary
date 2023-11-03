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
    private final List<Book> BOOKLIST;
    private final DataRecorder DATARECORDER;
    private final UsefulForRegisterAuthor REGISTERAUTHOR;
    private final UsefulForRegisterPages REGISTERPAGES = new UsefulForRegisterPages();
    private final UsefulForYearOfReading REGISTERYEAROFREADING = new UsefulForYearOfReading();

    public RegisterBookController(List<Book> bookList, DataRecorder dataRecorder) {
        this.BOOKLIST = bookList;
        this.DATARECORDER = dataRecorder;
        this.REGISTERAUTHOR = new UsefulForRegisterAuthor(BOOKLIST);
    }

    public void registerBook(List<Book> bookList) {
        String bookTitle = JOptionPane.showInputDialog("Digite o título do livro: ");
        List<Author> authors = REGISTERAUTHOR.registerAuthor();
        String bookGenre = JOptionPane.showInputDialog("Digite o gênero do livro: ");
        String bookSubGenre = JOptionPane.showInputDialog("Digite o subgênero do livro: ");
        int pageCount = REGISTERPAGES.enterPageCount();
        int yearOfReading = 0;
        String bookHasRead = REGISTERYEAROFREADING.getYearOfReadingChoice();
        if (bookHasRead.equals("1")) {
            yearOfReading = REGISTERYEAROFREADING.enterYearOfReading();
        }

        Book newBook = new Book(bookTitle, authors, bookGenre, bookSubGenre, pageCount, bookHasRead, yearOfReading);
        bookList.add(newBook);

        JOptionPane.showMessageDialog(null, "Livro registrado com sucesso!");

        try {
            DATARECORDER.saveBookData(newBook);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar dados do livro: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        registerBook(BOOKLIST);
    }
}
