package org.br.ufpb.dcx.carlos.personalLibrary.model.exceptions;

import java.io.IOException;

public class BookNotFoundException extends IOException {
    public BookNotFoundException(String message) {
        super(message);
    }
}
