package pl.pawelczak.solaris.webapp.common.image;

public class ImageUploadException extends RuntimeException {
    public ImageUploadException(String string){
        super(string);
    }
   
    public ImageUploadException(String message, Throwable cause){
        super(message, cause);
    }
}
