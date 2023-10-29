package org.br.ufpb.dcx.carlos.personalLibrary.control.management;

import org.br.ufpb.dcx.carlos.personalLibrary.model.Author;
import org.br.ufpb.dcx.carlos.personalLibrary.model.LibrarySystem;
import org.br.ufpb.dcx.carlos.personalLibrary.model.Book;
import org.br.ufpb.dcx.carlos.personalLibrary.model.exceptions.BookNotFoundException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EditBookController implements ActionListener {
    private final LibrarySystem librarySystem;

    public EditBookController(LibrarySystem librarySystem) {
        this.librarySystem = librarySystem;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String titleToEdit = JOptionPane.showInputDialog("Enter the title of the book to edit: ");
            String authorNameToEdit = JOptionPane.showInputDialog("Enter the author's name of the book to edit: ");

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
                throw new BookNotFoundException("The book was not found in the library.");
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
                "Title of the book",
                "Author's name",
                "Author's gender",
                "Author's country of birth",
                "Has the book been read?",
                "Year of reading",
                "Genre of the book",
                "Number of pages"
        };
        return JOptionPane.showOptionDialog(null, "Choose the field to edit:", "Edit Book",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }

    private void editTitle(Book book) {
        String newTitle = JOptionPane.showInputDialog("Enter the new title of the book: ");
        book.setTitle(newTitle);
    }

    private void editAuthorName(Book book, String authorNameToEdit) {
        List<Author> authors = book.getAuthor();

        for (Author author : authors) {
            if (author.getName().equalsIgnoreCase(authorNameToEdit)) {
                String newAuthorName = JOptionPane.showInputDialog("Enter the new name for the author: ");
                author.setName(newAuthorName);
                JOptionPane.showMessageDialog(null, "Author's name updated successfully.");
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Author with the name " + authorNameToEdit + " not found.");
    }


    private void editAuthorGender(Book book) {
        String newAuthorGender = JOptionPane.showInputDialog("Enter the new author's gender: ");
        List<Author> authors = book.getAuthor();
        authors.forEach(author -> author.setAuthorGender(newAuthorGender));
        JOptionPane.showMessageDialog(null, "Author's gender updated successfully.");
    }

    private void editAuthorCountry(Book book) {
        String newAuthorCountry = JOptionPane.showInputDialog("Enter the new author's country of birth: ");
        List<Author> authors = book.getAuthor();

        authors.forEach(author -> author.setCountryOfBirth(newAuthorCountry));
        JOptionPane.showMessageDialog(null, "Author's country of birth updated successfully.");
    }


    private void editReadStatus(Book book) {
        String newReadStatus = JOptionPane.showInputDialog("Has the book been read? (Yes or No)");
        book.setReadStatus(String.valueOf(newReadStatus.equalsIgnoreCase("Yes")));
    }

    private void editYearOfReading(Book book) {
        int newYearOfReading = Integer.parseInt(JOptionPane.showInputDialog("Enter the new year of reading: "));
        book.setYearOfReading(newYearOfReading);
    }

    private void editGenre(Book book) {
        String newGenre = JOptionPane.showInputDialog("Enter the new genre of the book: ");
        book.setBookGenre(newGenre);
    }

    private void editPageCount(Book book) {
        int newPageCount = Integer.parseInt(JOptionPane.showInputDialog("Enter the new number of pages: "));
        book.setPageCount(newPageCount);
    }

    private void showInvalidChoiceMessage() {
        JOptionPane.showMessageDialog(null, "Invalid choice. No changes were made.");
    }

    private void showSuccessMessage() {
        JOptionPane.showMessageDialog(null, "Book information updated successfully.");
    }

    private void showBookNotFoundMessage() {
        JOptionPane.showMessageDialog(null, "Book not found. Please check the title and author's name.");
    }
}
