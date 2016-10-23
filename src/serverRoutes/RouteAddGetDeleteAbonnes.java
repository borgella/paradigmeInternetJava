
package serverRoutes;

import model.DaoUtilisation;
import server.Error;
import server.Request;
import server.Router;

/**
 *
 * @author Borgella
 */
public class RouteAddGetDeleteAbonnes extends RouteControler{

    private DaoUtilisation user;
    
    public RouteAddGetDeleteAbonnes(DaoUtilisation user){
        this.user = user;
        uriType= URIFactory.getURYType("addgetdeleteabonne");
    }
    
    @Override
    public void execute(Router router, Request request) {
        String [] table_ressource = request.getURI().split("/");
        int id_utilisateur = Integer.parseInt(table_ressource[2]);
        int id_aAbonne = Integer.parseInt(table_ressource[4]);
        switch(request.getVerb()){
            case "PUT":
                try{router.JsonStringifyResponse(200, "ok", user.abonnerAUnUtilisateur(id_utilisateur, id_aAbonne).toJsonUtilisateur());
                }catch(Exception e){router.JsonParseResponseError(new Error(404,"Un des utilisateur n'existe pas."));}
                break;
            case "DELETE":
                try{ router.JsonStringifyResponse(200, "ok", user.seDesabonner(id_utilisateur, id_aAbonne).toJsonUtilisateur());
                }catch(Exception e){ router.JsonParseResponseError(new Error(404,"Un des utilisateur n'existe pas."));}
                break;
            case "GET":
                try{ router.JsonStringifyResponse(200, "ok", user.getUnSuiveur(id_utilisateur, id_aAbonne).toJsonUtilisateur());
                }catch(Exception e){ router.JsonParseResponseError(new Error(404,"Mauvais format de requette sur la ressource."));}
                break;
            default:
                router.JsonParseResponseError(new Error(404,"Action non permise sur cette ressource."));
        }
    }
}
