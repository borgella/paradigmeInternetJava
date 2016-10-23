/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverRoutes;

import java.util.ArrayList;
import model.DaoUtilisation;
import model.User;
import server.Error;
import server.Request;
import server.Router;

/**
 *
 * @author Borgella
 */
public class RouteGetAppUsers extends RouteControler{
    
    private DaoUtilisation user;
    
    public RouteGetAppUsers(DaoUtilisation user,ArrayList<User>allUsers){
        this.user = user;
        uriType = new GetAllAppUsers();
    }
    
    
    @Override
    public void execute(Router router, Request requette) {
        String [] tableInfoRessource = requette.getURI().split("/");
        int id_utilisateur= Integer.parseInt(tableInfoRessource[2]);
        if(requette.getVerb().equals("GET")){
            try{router.JsonStringifyResponse(200, "ok", user.getAppAllUsers(id_utilisateur));
            }catch(Exception e){router.JsonParseResponseError(new Error(404,"Retweet introuvable."));}
        }else
            router.JsonParseResponseError(new Error(404,"Ressource disponible en lecture seulement."));
    }
    
    }
    
