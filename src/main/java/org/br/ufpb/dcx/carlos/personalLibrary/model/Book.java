package org.br.ufpb.dcx.carlos.personalLibrary.model;

import java.util.Objects;
import java.util.List;

public class Book implements Comparable<Book> {

    private String title;
    private List<Author> author;
    private String bookGenre;
    private int pageCount;
    private String readStatus;
    private int yearOfReading;

    public Book(String title, List<Author> author, String bookGenre, int pageCount, String readStatus, int yearOfReading) {
        this.title = title;
        this.author = author;
        this.bookGenre = bookGenre;
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
        return this.author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }

    public String getBookGenre() {
        return this.bookGenre;
    }

    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
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
        return "The title of the book is: " + this.title + "\nThe author of the book is: " + this.author.get(0).getName() + "\nThe genre of the book is: " + this.bookGenre + "\nThe number of pages is: "
                + this.pageCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, bookGenre, pageCount, title);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Book other = (Book) obj;
        return Objects.equals(author, other.author) && Objects.equals(bookGenre, other.bookGenre)
                && pageCount == other.pageCount && Objects.equals(title, other.title);
    }

    @Override
    public int compareTo(Book otherBook) {
        return this.title.compareTo(otherBook.title);
    }
}