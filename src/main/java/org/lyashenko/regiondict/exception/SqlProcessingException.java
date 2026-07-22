package org.lyashenko.regiondict.exception;

public class SqlProcessingException extends RuntimeException{

    public SqlProcessingException(Throwable e) {
        super(e);
    }
}
