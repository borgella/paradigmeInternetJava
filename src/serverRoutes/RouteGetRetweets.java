
package serverRoutes;

import model.DaoUtilisation;
import server.Error;
import server.Request;
import server.Router;

/**
 *
 * @author Borgella
 */
public class RouteGetRetweets extends RouteControler {
    
    private DaoUtilisation user;
    
    public RouteGetRetweets(DaoUtilisation user){
        this.user= user;
        uriType= URIFactory.getURYType("getretweets");
    }
    
    @Override
    public void execute(Router router, Request requette) {
        String [] tableInfoRessource = requette.getURI().split("/");
        int id_utilisateur= Integer.parseInt(tableInfoRessource[2]);
        if(requette.getVerb().equals("GET")){
            try{router.JsonParseStringResponse(200, "ok", user.getUtilisateursRetweets(id_utilisateur));
            }catch(Exception e){router.JsonParseResponseError(new Error(404,"Retweet introuvable."));}
        }else
            router.JsonParseResponseError(new Error(404,"Ressource disponible en lecture seulement."));
    }
    
}