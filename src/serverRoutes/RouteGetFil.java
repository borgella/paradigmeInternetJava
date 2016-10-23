
package serverRoutes;

import model.DaoUtilisation;
import server.Error;
import server.Request;
import server.Router;

/**
 *
 * @author Borgella Jean Mary
 */
public class RouteGetFil extends RouteControler {
    
    private DaoUtilisation user;
    
    public RouteGetFil(DaoUtilisation user){
       
        this.user = user;
        uriType = URIFactory.getURYType("fil");
        
    }

    @Override
    public void execute(Router router, Request resquest) {
        String [] tableInfoURI = resquest.getURI().split("/");
        int id_utilisateur= Integer.parseInt(tableInfoURI[2]);
        if(resquest.getVerb().equals("GET")){
            try{router.JsonStringifyResponse(200,"ok",user.getFil(id_utilisateur));
            }catch(Exception e){router.JsonParseResponseError(new Error(404,"Fil non disponible pour cet utilisateur."));}
        }else
            router.JsonParseResponseError(new Error(404,"Ressource disponible en lecture seulement."));
    }
}
