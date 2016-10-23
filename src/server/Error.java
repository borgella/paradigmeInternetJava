
package server;

/**
 *
 * @author Borgella
 */
public final class Error extends Exception{
    private int code;
    private String message;
   
    public Error(int codeStatut, String messageErreur) {
        code = codeStatut;
        message = messageErreur;
       
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
