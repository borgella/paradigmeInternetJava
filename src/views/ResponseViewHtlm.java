
package views;


import java.io.PrintWriter;
import server.Error;
import server.Request;

/**
 *
 * @author Borgella
 */
public class ResponseViewHtlm {
   
    public void formatReponseHttp(int code, Request requette,PrintWriter output) {
        output.print("HTTP/1.1 " + code + " " + "\r\n");
        output.print("Content-Type: " + requette.getContentType() + "\r\n");
        output.print("Content-Length: " + requette.getBody().length() + "\r\n");
        output.print("\r\n");
        output.print(requette.getBody() + "\r\n");
        output.flush();
    }
    
    public void formatReponseErreur(int code, Error err, PrintWriter output) {
        String messageErreur = "<h1>" + err.getMessage() + "</h1>";
        output.print("HTTP/1.1 " + code + " " + "\r\n");
        output.print("Content-Type: text/html\r\n");
        output.print("Content-Length: " + messageErreur.length() + "\r\n");
        output.print("\r\n");
        output.print(messageErreur + "\r\n");
        output.flush();
    }
}
