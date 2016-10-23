
package serverRoutes;

import model.DaoUtilisation;
import server.Error;
import server.Request;
import server.Router;

/**
 *
 * @author Borgella
 */
public class RouteGetDeleteATweet extends RouteControler{
    
    private DaoUtilisation user;
    
    public RouteGetDeleteATweet (DaoUtilisation user){
        this.user= user;
        uriType= URIFactory.getURYType("getdeleteatweet");
    }
    
    @Override
    public void execute(Router router, Request requette) {
        String [] tableInfoRessource = requette.getURI().split("/");
        int id_utilisateur = Integer.parseInt(tableInfoRessource[2]);
        int id_tweet= Integer.parseInt(tableInfoRessource[4]);
        switch(requette.getVerb()){
            case "GET":
                try{ router.JsonStringifyResponse(200,"Ressource Supprimee.",user.getUnTweetDonner(id_utilisateur, id_tweet).toJsonTweets());
                }catch(Exception e){ router.JsonParseResponseError(new Error(404,"Tweet introuvable pour cet utilisateur."));}
                break;
            case "DELETE":
                try{ router.JsonStringifyResponse(200,"Ressource Supprimee.",user.supprimerTweet(id_utilisateur, id_tweet).toJsonTweets());
                }catch(Exception e){ router.JsonParseResponseError(new Error(404,"Tweet introuvable pour cet utilisateur."));}
                break;
            default: 
                router.JsonParseResponseError(new Error(404,"Mauvais format de Requette sur la ressource."));
            }
           
    } 
}
