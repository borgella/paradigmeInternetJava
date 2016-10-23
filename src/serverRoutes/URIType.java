
package serverRoutes;
import server.Request;

/**
 *
 * @author Borgella
 * Implementation of the Strategy pattern,each Routes will define what kind of 
 * URYType they are by implementing the itMatches function. 
 * The URYType field is only define in the Abstract Class RoutesControler.
 */
public interface URIType {
    
   public boolean itMatches(Request resquest);
    
}


/**
 * The FIL class decided what kind of request it will execute
 * If the client's request for a resource is "/utilisateurs/[0-9]+/fil"
 * then the itMatches method will return true otherwise false.
 * @author Borgella
 */
class Fil implements URIType{
    
    private static Fil filinstance = null;
    
    public static Fil getFilInstance(){
        if(filinstance == null)
            filinstance = new Fil();
        
        return filinstance;
    }
    
    @Override
    public boolean itMatches(Request request) {
        String uri = "/utilisateurs/[0-9]+/fil";
        return request.getURI().matches(uri);
    }
    
}


/**
 * The Abonnements class decided what kind of request it will execute
 * If the client's request for a resource is "/utilisateurs/[0-9]+/abonnements"
 * then the itMatches method will return true otherwise false.
 * @author Borgella
 */
class Abonnements implements URIType{
    
    private static Abonnements abonnements_instance = null; 
    
    
    public static Abonnements getAbonnementsInstance(){
        if(abonnements_instance == null)
            abonnements_instance = new Abonnements();
        
        return abonnements_instance;
    }
    
    @Override
    public boolean itMatches(Request request) {
        String uri = "/utilisateurs/[0-9]+/abonnements";
        return request.getURI().matches(uri);
    }
}


/**
 * The GetPostTweets class decided what kind of request it will execute
 * If the client's request for a resource is "/utilisateurs/[0-9]+/tweets"
 * then the itMatches method will return true otherwise false.
 * @author Borgella
 */   
class GetPostTweets implements URIType{
    
    private static GetPostTweets getposttweets_instance = null;
    
    public static GetPostTweets GetPostTweetsInstance(){
        if(getposttweets_instance == null)
            getposttweets_instance = new GetPostTweets();
        
        return getposttweets_instance;
    }

    @Override
    public boolean itMatches(Request resquet) {
        String uri = "/utilisateurs/[0-9]+/tweets";
        return resquet.getURI().matches(uri);
    }
} 
  

/**
 * The GetDeleteATWeet class decided what kind of request it will execute
 * If the client's request for a resource is "/utilisateurs/[0-9]+/tweets/[0-9]+"
 * then the itMatches method will return true otherwise false.
 * @author Borgella
 */
class GetDeleteATweet implements URIType{
    
    private static GetDeleteATweet getdelete_instance = null;
    
    public static GetDeleteATweet getDeleteATweetInstance(){
        if(getdelete_instance == null)
            getdelete_instance = new GetDeleteATweet();
             
        return getdelete_instance;
    }

    @Override
    public boolean itMatches(Request request) {
        String uri = "/utilisateurs/[0-9]+/tweets/[0-9]+";
        return request.getURI().matches(uri);
    }

}


/**
 * The GetRetweets class decided what kind of request it will execute
 * If the client's request for a resource is "/utilisateurs/[0-9]+/retweets"
 * then the itMatches method will return true otherwise false.
 * @author Borgella
 */
class GetRetweets implements URIType{
    
    private static GetRetweets getretweets = null;
    
    public static GetRetweets getRetweetsInstance(){
        if(getretweets == null)
            getretweets = new GetRetweets();
        
        return getretweets;
    }

    @Override
    public boolean itMatches(Request request) {
        String uri = "/utilisateurs/[0-9]+/retweets";
        return request.getURI().matches(uri);
    }
    
}


/**
 * The DeletePutRetweet class decided what kind of request it will execute
 * If the client's request for a resource is "/utilisateurs/[0-9]+/retweets/[0-9]+"
 * then the itMatches method will return true otherwise false.
 * @author Borgella
 */
class DeletePutRetweet implements URIType{
    
    private static DeletePutRetweet deleteputretweet = null;
    
    public static DeletePutRetweet getDeletePutRetweet(){
    
        if(deleteputretweet == null)
            deleteputretweet = new DeletePutRetweet();
        
        return deleteputretweet;
    }

    @Override
    public boolean itMatches(Request request) {
        String uri = "/utilisateurs/[0-9]+/retweets/[0-9]+";
       return request.getURI().matches(uri);
    }
}



/**
 * The AddGetDeleteAbonnes class decided what kind of request it will execute
 * If the client's request for a resource is "/utilisateurs/[0-9]+/abonnements/[0-9]+"
 * then the itMatches method will return true otherwise false.
 * @author Borgella
 */
class AddGetDeleteAbonnes implements URIType{
    
    private static AddGetDeleteAbonnes addgetdeleteabonnesInstance = null;
    
    public static AddGetDeleteAbonnes getAddGetDeleteAbonnesInstance(){
        if(addgetdeleteabonnesInstance == null)
            addgetdeleteabonnesInstance = new AddGetDeleteAbonnes();
        
        return addgetdeleteabonnesInstance;
    }

    @Override
    public boolean itMatches(Request request) {
        String uri = "/utilisateurs/[0-9]+/abonnements/[0-9]+";
        return request.getURI().matches(uri);
    }
}



/**
 * The GetAllAppUsers class decided what kind of request it will execute
 * if the client's request for a resource is "/users/[0-9]+"
 * then the itMatches method will return true otherwise false.
 * @author Borgella
 */
class GetAllAppUsers implements URIType{
    
    private static GetAllAppUsers getallappusers = null;
    
    public static GetAllAppUsers getAllAppUsersInstance(){
        
        if(getallappusers == null)
            getallappusers = new GetAllAppUsers();
        
        return getallappusers;
    }

    @Override
    public boolean itMatches(Request request) {
        String uri = "/users/[0-9]+";
        return request.getURI().matches(uri);
    }
}