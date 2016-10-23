
package serverRoutes;

import model.DaoUtilisation;
import server.Error;
import server.Request;
import server.Router;

/**
 *
 * @author Borgella
 */
public class RouteGetAbonnements extends RouteControler{

    private DaoUtilisation user;
    
    public RouteGetAbonnements(DaoUtilisation user){
        this.user= user;
        uriType= URIFactory.getURYType("abonnements");
    }
    
    @Override
    public void execute(Router router, Request requette) {
        String [] tableInfoRessource = requette.getURI().split("/");
        int id_utilisateur= Integer.parseInt(tableInfoRessource[2]);
        if(requette.getVerb().equals("GET"))
            router.JsonParseStringResponse(200, "ok", user.getListAbonnements(id_utilisateur));
        else
            router.JsonParseResponseError(new Error(404,"Ressource disponible en lecture seulement."));
    }
    
}
