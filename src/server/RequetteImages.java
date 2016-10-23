
package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.util.Arrays;

/**
 *
 * @author Borgella
 */
class RequetteImages {
    
    private final String contentType;

    public RequetteImages(String ligne) throws MalformedURLException {
        contentType = (ligne.endsWith(".jpg"))? "image/jpg": "image/png";
    }

    @SuppressWarnings("empty-statement")
    public void sendImageFile(String fichier,PrintWriter output) throws IOException {
        String path = "public" + fichier;
        File fich = new File(path);
        byte[] encoded;
        try { encoded = Files.readAllBytes(fich.toPath());
        } catch (IOException ex) {return;}
        output.print("HTTP/1.1 " + 200 + " " + "\r\n");
        output.print("Content-Type: " + contentType + "\r\n");
        output.print("Content-Length: " + fich.length() + "\r\n");
        output.print("\r\n");
        output.write(Arrays.toString(encoded) + "\r\n");
        output.flush();
        output.close();
    }
    
    @SuppressWarnings("empty-statement")
    public void sendImageFile(String fichier,ObjectOutputStream canalEnvoi) throws IOException {
        String path = "public" + fichier;
        File file = new File(path);
        byte[] encoded = new byte[(int)file.length()];
        try (FileInputStream fi = new FileInputStream(file)){
            fi.read(encoded);
            int i = 0;
            while(i < encoded.length){
                System.out.println(path+ " = fichier "+encoded.length +" = length "+contentType+"\n");
            }
        } catch (IOException ex) {return;}
        canalEnvoi.writeObject("HTTP/1.1 " + 200 + " " + "\r\n");
        canalEnvoi.writeObject("Content-Type: " + contentType + "\r\n");
        canalEnvoi.writeObject("Content-Length: " + encoded.length + "\r\n");
        canalEnvoi.writeObject("\r\n");
        canalEnvoi.write(encoded);
        canalEnvoi.writeObject("\r\n");
        canalEnvoi.flush();
        canalEnvoi.close();
        
    }
 
}
