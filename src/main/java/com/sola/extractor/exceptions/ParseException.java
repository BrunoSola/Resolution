package com.sola.extractor.exceptions;

import java.io.Serial;

public class ParseException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = -9146848404798258524L;

    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
