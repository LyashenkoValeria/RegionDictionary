package org.lyashenko.regiondict.exception;

public class RegionAlreadyExists extends RuntimeException{

    public RegionAlreadyExists(int code) {
        super(String.format("Region %d already exists", code));
    }
}
