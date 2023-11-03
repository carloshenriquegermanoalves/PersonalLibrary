package org.br.ufpb.dcx.carlos.personalLibrary.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Book implements Comparable<Book>, Serializable {

    private final List<Author> AUTHOR;
    private String title;
    private String bookGenre;
    private String bookSubGenre;
    private int pageCount;
    private String readStatus;
    private int yearOfReading;

    public Book(String title, List<Author> author, String bookGenre, String bookSubGenre, int pageCount, String readStatus, int yearOfReading) {
        this.title = title;
        this.AUTHOR = author;
        this.bookGenre = bookGenre;
        this.bookSubGenre = bookSubGenre;
        this.pageCount = pageCount;
        this.readStatus = readStatus;
        this.yearOfReading = yearOfReading;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthor() {
        return this.AUTHOR;
    }

    public String getBookGenre() {
        return this.bookGenre;
    }

    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
    }

    public String getBookSubGenre() {
        return bookSubGenre;
    }

    public void setBookSubGenre(String bookSubGenre) {
        this.bookSubGenre = bookSubGenre;
    }

    public int getPageCount() {
        return this.pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getReadStatus() {
        return this.readStatus;
    }

    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus;
    }

    public int getYearOfReading() {
        return yearOfReading;
    }

    public void setYearOfReading(int yearOfReading) {
        this.yearOfReading = yearOfReading;
    }

    @Override
    public String toString() {
        StringBuilder authorsString = new StringBuilder();
        for (int i = 0; i < this.AUTHOR.size(); i++) {
            authorsString.append(this.AUTHOR.get(i).getName());
            if (i < this.AUTHOR.size() - 1) {
                authorsString.append(", ");
            }
        }

        return "O Título do Livro é: " + this.title + "\nO(s) Autor(es) do Livro é: " + authorsString + "\nO Gênero do Livro é: " + this.bookGenre + "\nO Número de Páginas é: " + this.pageCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(AUTHOR, bookGenre, pageCount, title);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Book other = (Book) obj;
        return Objects.equals(AUTHOR, other.AUTHOR) && Objects.equals(bookGenre, other.bookGenre) && pageCount == other.pageCount && Objects.equals(title, other.title);
    }

    @Override
    public int compareTo(Book otherBook) {
        return this.title.compareTo(otherBook.title);
    }
}
