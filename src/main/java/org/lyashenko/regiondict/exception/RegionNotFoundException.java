package org.lyashenko.regiondict.exception;

public class RegionNotFoundException extends RuntimeException{

    public RegionNotFoundException(int code) {
        super(String.format("Region %d not found", code));
    }
}
