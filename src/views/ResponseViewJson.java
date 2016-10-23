
package views;

import java.io.PrintWriter;
import javax.json.Json;
import javax.json.JsonObject;
import server.Error;

/**
 *
 * @author Borgella
 */
public class ResponseViewJson {
    
    private String version;
    private Integer status;
    private String body;
    private Error error;

    public ResponseViewJson(String version, Integer status, JsonObject json) {
        this.version = version;
        this.status = status;
        this.body = json.toString();
    }
    
    public ResponseViewJson(String version, Integer status) {
        this.version = version;
        this.status = status;
        this.body = new String();
        this.error = null;
    }
    
    public ResponseViewJson(String version, Integer status,String body){
        this(version,status);
        this.body = body;
        this.error = null;
    }
    
    public ResponseViewJson(Error err) {
        this("HTTP/1.1 ",err.getCode());
        this.error = err;
    }
    
    public void JsonResponseformat(PrintWriter output) {
        output.print(version +  status + " " + "\r\n");
        output.print("Content-Type: " + "text/json" + "\r\n");
        output.print("Content-Length: " + body.length() + "\r\n");
        output.print("\r\n");
        output.print(body);
        output.flush();
    }
    
    public void JsonResponseFormatError(PrintWriter output) {
        JsonObject json = Json.createObjectBuilder()
                .add("Code Statut", error.getCode())
                .add("Erreur", error.getMessage()).build();
        output.print(version + status + " " + "\r\n");
        output.print("Content-Type: "+ "text/json" + "\r\n");
        output.print("\r\n");
        output.print(json.toString() + "\r\n");
        output.flush();
    }
    
}

