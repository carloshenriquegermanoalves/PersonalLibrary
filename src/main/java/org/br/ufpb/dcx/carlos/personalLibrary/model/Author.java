package org.br.ufpb.dcx.carlos.personalLibrary.model;

import java.io.Serializable;
import java.util.Objects;

public class Author implements Comparable<Author>, Serializable {

    private String name;
    private String gender;
    private String countryOfBirth;

    public Author(String name, String gender, String countryOfBirth) {
        this.name = name;
        this.gender = gender;
        this.countryOfBirth = countryOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorGender() {
        return gender;
    }

    public void setAuthorGender(String gender) {
        this.gender = gender;
    }

    public String getCountryOfBirth() {
        return countryOfBirth;
    }

    public void setCountryOfBirth(String countryOfBirth) {
        this.countryOfBirth = countryOfBirth;
    }

    @Override
    public String toString() {
        return "O nome do autor é: " + name + "\nO gênero do autor é: " + gender + "\nO país de nascimento do autor é: "
                + countryOfBirth;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, countryOfBirth, gender);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Author other = (Author) obj;
        return Objects.equals(name, other.name) && Objects.equals(countryOfBirth, other.countryOfBirth)
                && Objects.equals(gender, other.gender);
    }

    @Override
    public int compareTo(Author otherAuthor) {
        return this.name.compareTo(otherAuthor.name);
    }
}
