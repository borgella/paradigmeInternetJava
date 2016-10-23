
package server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author Borgella
 */
public class Request {
    private String verb;
    private String uri;
    private String version;
    private HashMap <String,String> header;
    private StringBuilder body;
    private String contentType;
    private String uriRessource;
    
    public Request(String verb,String uri,String version,HashMap header,StringBuilder body){
        this.verb = verb;
        this.uri = uri;
        this.version = version;
        this.header = header;
        this.body = body;
        this.contentType = "application/json";
        
    }
    
    public Request(String ligne) throws Error {
        uriRessource = ligne;
        body = new StringBuilder();
        contentType = (uriRessource.endsWith(".html")) ? "text/html" : "text/js";
        if(uriRessource.endsWith(".css"))
            contentType ="text/css";
    }

    public void readFile(String fichier) throws FileNotFoundException {
        String path = "public" + fichier;
        try (BufferedReader lecture = new BufferedReader(new FileReader(path))) {
                while (lecture.ready()) 
                    body = body.append(lecture.readLine()).append("\n");
        } catch (IOException ficherNexistepas) {ficherNexistepas.getMessage();}
    }

    public String getVerb() {
        return verb;
    }
    public String getURI() {
        return uri;
    }
    public String getVersion() {
        return version;
    }
    
    public StringBuilder getBody() {
        return body;
    }
    
    public String getUriRessource() {
        return uriRessource;
    }

    public String getContentType() {
        return contentType;
    }
    
    public HashMap<String,String> getEntete(){
        return header;
    }

    public void setBody(String corps) {
        body.append(corps);
    }
}
