package serverRoutes;

/**
 *  The factory class is useful for the creation of the right URYType
 * @author Borgella
 */
public class URIFactory {
    
    /**
     *
     * @param name
     * @return
     */
    public static URIType getURYType(String name){
        
        switch(name){
            case "fil":
                return Fil.getFilInstance();
            case "abonnements":
                return Abonnements.getAbonnementsInstance();
            case "getposttweets":
                return GetPostTweets.GetPostTweetsInstance();
            case "getdeleteatweet":
                return GetDeleteATweet.getDeleteATweetInstance();
            case "addgetdeleteabonne":
                return AddGetDeleteAbonnes.getAddGetDeleteAbonnesInstance();
            case "getretweets" :
                return GetRetweets.getRetweetsInstance();
            case "deleteputretweet":
                return DeletePutRetweet.getDeletePutRetweet();
            case "getallappusers" :
                return GetAllAppUsers.getAllAppUsersInstance();
            default: 
                throw new IllegalArgumentException(" Unknow URIType of :" + name);
        }
    }
    
}
