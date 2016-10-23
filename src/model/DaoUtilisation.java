
package model;

import javax.json.JsonObject;

/**
 * This class is the DAO Controller, don't depend on any technologies for the BD
 * @author Borgella
 */
public class DaoUtilisation  {

    private UserDAO dao_tweeter;
    
    public DaoUtilisation(UserDAO dao_tweeter){
        
        this.dao_tweeter = dao_tweeter;
        
    }
    
    public JsonObject getFil(int id_utilisateur){
        
       return dao_tweeter.getFil(id_utilisateur);
       
    }
    
    public String getUtilisateursTweets(int id_utilisateur){
        
        return dao_tweeter.getUtilisateursTweets(id_utilisateur);
        
    }
    
    public String getUtilisateursRetweets(int id_utilisateur){
        
        return dao_tweeter.getUtilisateurRetweets(id_utilisateur);
        
    }
    
    public String getListAbonnements(int id_utilisateur){
        
        return dao_tweeter.getListAbonnements(id_utilisateur);
    }
    
    public Tweet postUnTweet (int id_utilisateur,String texte){
 
        return dao_tweeter.postUnTweet(id_utilisateur, texte);    
    }
    
    public Tweet supprimerTweet (int id_utlisateur,int tweet_id){
        
        return dao_tweeter.supprimerTweet(id_utlisateur,tweet_id);
        
    }
    
    public Retweet retweeter(int id_utilisateur,int id_tweet){
    
        return dao_tweeter.retweeter(id_utilisateur, id_tweet);
        
    }
            
    public User abonnerAUnUtilisateur (int id_utilisateur, int id_aAbonner){
    
        return dao_tweeter.abonnerAUnUtilisateur(id_utilisateur, id_aAbonner);
        
    }
    
    public User seDesabonner (int id_utilisateur,int id_aDesabonner){
    
       return dao_tweeter.seDesabonner(id_utilisateur,id_aDesabonner);
        
    }
    
    public Retweet annulerRetweet (int id_utilisateur,int retweet_d){
    
        return dao_tweeter.annulerRetweet(id_utilisateur,retweet_d);
        
    }
    
    public User getUnSuiveur(int id_utilisateur, int id_suiveur){
    
        return dao_tweeter.getUnSuiveur(id_utilisateur, id_suiveur);
        
    }
    
    public Tweet getUnTweetDonner(int id_utilisateur, int id_tweet) {
        
        return dao_tweeter.getUnTweetDonner(id_utilisateur, id_tweet);
        
    }
    
     public JsonObject getAppAllUsers(int id_utilisateur){
         
         return dao_tweeter.getAppAllUsers(id_utilisateur);
     
     }
}
