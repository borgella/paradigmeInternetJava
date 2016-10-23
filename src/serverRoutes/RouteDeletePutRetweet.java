
package serverRoutes;

import model.DaoUtilisation;
import server.Error;
import server.Request;
import server.Router;

/**
 *
 * @author Borgella
 */
public class RouteDeletePutRetweet extends RouteControler{

    private DaoUtilisation user;
    
    public RouteDeletePutRetweet(DaoUtilisation user){
        this.user= user;
        uriType= URIFactory.getURYType("deleteputretweet");
    }
    
    /**
     *
     * @param router
     * @param requette
     */
    @Override
    public void execute(Router router, Request requette) {
        String [] table_ressource = requette.getURI().split("/");
        int id_utilisateur= Integer.parseInt(table_ressource[2]);
        int id_tweet= Integer.parseInt(table_ressource[4]);
        switch(requette.getVerb()){
            case "DELETE":
                try{router.JsonStringifyResponse(200, "ok", user.annulerRetweet(id_utilisateur,id_tweet).toJsonRetweets());
                }catch(Exception e){router.JsonParseResponseError(new Error(404,"Tweet inexistant."));}
                break;
            case "PUT":
                try{ router.JsonStringifyResponse(200, "ok", user.retweeter(id_utilisateur,id_tweet).toJsonRetweets());
                }catch(Exception e){ router.JsonParseResponseError(new Error(404,"Tweet inexistant."));}
                break;
            default:
                router.JsonParseResponseError(new Error(404,"Action non permise sur cette ressource"));
        }
    }


}
