package com.maverickshube.maverickshube.exceptions;

public class MediaUploadFailedException extends RuntimeException{
    public MediaUploadFailedException(String message){
        super(message);
    }
}
