
package serverRoutes;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import model.DaoUtilisation;
import server.Error;
import server.Request;
import server.Router;

/**
 *
 * @author Borgella
 */
public class RouteGetPostTweets extends RouteControler {
    
    private DaoUtilisation user;
    
    public RouteGetPostTweets(DaoUtilisation user){
        this.user = user;
        uriType = URIFactory.getURYType("getposttweets");
    }

    @Override
    public void execute(Router router, Request requette) {
        String [] tableInfoRessource = requette.getURI().split("/");
        int id_utilisateur = Integer.parseInt(tableInfoRessource[2]);
        switch(requette.getVerb()){
            case "GET":
                try{ router.JsonParseStringResponse(200, "ok", user.getUtilisateursTweets(id_utilisateur));
                }catch(Exception e){ router.JsonParseResponseError(new Error(404,"Tweet introuvable."));}
                break;
            case "POST":
                postTweet(id_utilisateur,router,requette.getBody().toString());
                break;
            default:
                router.JsonParseResponseError(new Error(404,"Mauvais format de Requette sur la ressource."));
        }
    }
        
    private void postTweet(int id_utilisateur,Router router, String texte) {
        String message;
        JsonReader lire = Json.createReader(new StringReader(texte));
        JsonObject json ;
        try{
            json = lire.readObject();
            message = json.getString("Tweet");
            if(message.length() <= 140){
                router.JsonParseStringResponse(200, "Ressource cree ",user.postUnTweet(id_utilisateur, message).toJsonTweets().toString());
            }else router.JsonParseResponseError(new Error(404,"Le Tweet ne dois pas depasser 140 caracteres."));
        }catch(Exception e){ router.JsonParseResponseError(new Error(404,"Exception liee a la lecture du stream."));} 
    }
}
