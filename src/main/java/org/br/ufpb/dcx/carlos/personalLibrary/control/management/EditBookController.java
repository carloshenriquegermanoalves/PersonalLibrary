package org.br.ufpb.dcx.carlos.personalLibrary.control.management;

import org.br.ufpb.dcx.carlos.personalLibrary.model.Author;
import org.br.ufpb.dcx.carlos.personalLibrary.model.Book;
import org.br.ufpb.dcx.carlos.personalLibrary.model.DataRecorder;
import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;
import org.br.ufpb.dcx.carlos.personalLibrary.model.exceptions.BookNotFoundException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class EditBookController implements ActionListener {
    private final LibrarySystem librarySystem;
    private final DataRecorder bookRecorder;

    public EditBookController(LibrarySystem librarySystem, DataRecorder bookRecorder) {
        this.librarySystem = librarySystem;
        this.bookRecorder = bookRecorder;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (librarySystem.getBookList().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ainda não há livros na biblioteca para serem removidos!");
            return;
        }

        try {
            String titleToEdit = JOptionPane.showInputDialog("Digite o título do livro que você quer editar: ");
            String authorNameToEdit = JOptionPane.showInputDialog("Digite o nome do autor do livro que você quer editar: ");

            Book bookToEdit = findBookByTitleAndAuthor(titleToEdit, authorNameToEdit);

            if (bookToEdit != null) {
                int fieldChoice = chooseFieldToEdit();

                switch (fieldChoice) {
                    case 0 -> editTitle(bookToEdit);
                    case 1 -> editAuthorName(bookToEdit, authorNameToEdit);
                    case 2 -> editAuthorGender(bookToEdit);
                    case 3 -> editAuthorCountry(bookToEdit);
                    case 4 -> editReadStatus(bookToEdit);
                    case 5 -> editYearOfReading(bookToEdit);
                    case 6 -> editGenre(bookToEdit);
                    case 7 -> editPageCount(bookToEdit);
                    default -> {
                        showInvalidChoiceMessage();
                        return;
                    }
                }

                showSuccessMessage();
            } else {
                throw new BookNotFoundException("O livro informado não foi encontrado na biblioteca.");
            }
        } catch (BookNotFoundException e1) {
            showBookNotFoundMessage();
        }
    }

    private Book findBookByTitleAndAuthor(String title, String authorName) {
        try {
            return librarySystem.findBookByTitleAndAuthor(title, authorName);
        } catch (BookNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Não existe livro com esse título e autor! Tente novamente!");
            return null;
        }
    }

    private int chooseFieldToEdit() {
        String[] options = {
                "Título do livro",
                "Nome do autor",
                "Gênero do autor",
                "País de nascimento do autor",
                "Status do livro",
                "Ano de leitura",
                "Gênero do livro",
                "Número de Páginas"
        };

        JList<String> list = new JList<>(options);
        JOptionPane.showMessageDialog(null, new JScrollPane(list), "Escolha o campo para editar:", JOptionPane.PLAIN_MESSAGE);

        int index = list.getSelectedIndex();
        return index >= 0 ? index : -1;
    }


    private void editTitle(Book book) {
        String newTitle = JOptionPane.showInputDialog("Digite o novo título do livro: ");
        book.setTitle(newTitle);
        saveBookData(book);
    }

    private void editAuthorName(Book book, String authorNameToEdit) {
        List<Author> authors = book.getAuthor();

        for (Author author : authors) {
            if (author.getName().equalsIgnoreCase(authorNameToEdit)) {
                String newAuthorName = JOptionPane.showInputDialog("Digite o novo nome do autor: ");
                author.setName(newAuthorName);
                JOptionPane.showMessageDialog(null, "Nome do autor atualizado com sucesso.");
                saveBookData(book);
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "O autor com o nome " + authorNameToEdit + " não foi encontrado.");
    }

    private void editAuthorGender(Book book) {
        String newAuthorGender = JOptionPane.showInputDialog("Digite o novo gênero do autor: ");
        List<Author> authors = book.getAuthor();
        authors.forEach(author -> author.setAuthorGender(newAuthorGender));
        JOptionPane.showMessageDialog(null, "Gênero do autor atualizado com sucesso.");
        saveBookData(book);
    }

    private void editAuthorCountry(Book book) {
        String newAuthorCountry = JOptionPane.showInputDialog("Digite o novo país de nascimento do autor: ");
        List<Author> authors = book.getAuthor();
        authors.forEach(author -> author.setCountryOfBirth(newAuthorCountry));
        JOptionPane.showMessageDialog(null, "País de nascimento do autor atualizado com sucesso.");
        saveBookData(book);
    }

    private void editReadStatus(Book book) {
        String newReadStatus = JOptionPane.showInputDialog("Você já leu o livro? (Sim or Não)");
        book.setReadStatus(String.valueOf(newReadStatus.equalsIgnoreCase("Sim")));
        saveBookData(book);
    }

    private void editYearOfReading(Book book) {
        int newYearOfReading = Integer.parseInt(JOptionPane.showInputDialog("Digite o novo ano de leitura: "));
        book.setYearOfReading(newYearOfReading);
        saveBookData(book);
    }

    private void editGenre(Book book) {
        String newGenre = JOptionPane.showInputDialog("Digite o novo gênero do livro: ");
        book.setBookGenre(newGenre);
        saveBookData(book);
    }

    private void editPageCount(Book book) {
        int newPageCount = Integer.parseInt(JOptionPane.showInputDialog("Digite o novo número de páginas: "));
        book.setPageCount(newPageCount);
        saveBookData(book);
    }

    private void showInvalidChoiceMessage() {
        JOptionPane.showMessageDialog(null, "Opção inválida! Tente novamente.");
    }

    private void showSuccessMessage() {
        JOptionPane.showMessageDialog(null, "Os dados do livro foram atualizados com sucesso.");
    }

    private void showBookNotFoundMessage() {
        JOptionPane.showMessageDialog(null, "Livro não encontrado. Por favor, verifique o título do livro e o nome do autor novamente.");
    }

    private void saveBookData(Book book) {
        try {
            bookRecorder.saveBookData(book);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar dados do livro: " + e.getMessage());
        }
    }
}
