
package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import javax.json.JsonObject;
import views.ResponseViewJson;
import views.ResponseViewHtlm;
import serverRoutes.Route;

/**
 *
 * @author Borgella
 */
public class Router {
    private BufferedReader input;
    private PrintWriter output;
    private Socket socket;
    private Request request;
    private final ArrayList<Route> routes;

    public Router(ArrayList<Route> routes){
        input = null;
        output = null;
        socket = null;
        request = null;
        this.routes = routes;
    }

    public void listen(int port) throws IOException, Error {
        try (ServerSocket serversocket = new ServerSocket(port)) {
            while(true){
                acceptConnexion(serversocket);
                getBuffersReady(socket);
                handleHttpRequest();
            }
        } finally {
            closeBuffers();
        }
    }

    private void acceptConnexion(ServerSocket serversocket) throws IOException {
        socket = serversocket.accept();
    }

    private void getBuffersReady(Socket socket) throws IOException {
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);
    }

   
    private void handleHttpRequest() throws IOException, Error {
        String line = input.readLine();
        if (line != null) {
            String[] table = line.split(" ");
            
            if ((table[1].equalsIgnoreCase("/index.html") || table[1].endsWith(".js")) || table[1].endsWith(".css")) {
                request = new Request(table[1]);
                ResponseViewHtlm response = new ResponseViewHtlm();
                request.readFile(request.getUriRessource());
                response.formatReponseHttp(200,request,output);
            } else if(table[1].endsWith(".jpg") || table[1].endsWith(".png")){
                    RequetteImages image = new RequetteImages(table[1]);
                    image.sendImageFile(table[1],output); 
                    
            }else{
                HashMap<String,String> header = handleHeaders(input);
                StringBuilder requestBody = handleRequestBody(input);
                request = new Request(table[0],table[1],table[2],header,requestBody);
                if(!(isURI(request)))
                    JsonParseResponseError(new Error(404,"URI not found...")); 
            }
        }
        closeBuffers();
    }
    
     private HashMap<String, String> handleHeaders(BufferedReader canal_in) throws IOException {
        HashMap<String,String> header = new HashMap();
        String line;
        String [] table;
        while (canal_in.ready() && !(line = canal_in.readLine()).isEmpty()) {
            table = line.split(": ");
            if (table.length != 2) {
                JsonParseResponseError(new Error(404,"Bad headers format."));
            }
            header.put(table[0], table[1]);
        }
        return header;
    }
     
    private StringBuilder handleRequestBody(BufferedReader input) throws IOException {
       StringBuilder body = new StringBuilder();
       while(input.ready())
            body.append((char)input.read());
       
       return body;
    }
    
    private void closeBuffers() throws IOException {
        socket.close();
        input.close();
        output.close();
    }
    
    public void JsonStringifyResponse(Integer status,String reason,JsonObject json) {
        ResponseViewJson reponse = new ResponseViewJson("HTTP/1.1 ",status,json);
        reponse.JsonResponseformat(output);
    }
    
    public void JsonParseStringResponse(Integer status,String reason, String body) {
        ResponseViewJson reponse = new ResponseViewJson("HTTP/1.1 ",status,body);
        reponse.JsonResponseformat(output);
    }
    
    
    public void JsonParseResponseError(Error err) {
        ResponseViewJson reponse = new ResponseViewJson(err);
        reponse.JsonResponseFormatError(output);
    }
    
    private boolean isURI(Request request){
        for(Route route: routes){
            if(route.acceptUrl(request)){
                route.execute(this, request);
                return true;
            }
        }
        return false;
    }    
}
