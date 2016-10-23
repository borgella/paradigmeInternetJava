
package model;

import javax.json.JsonObject;


/**
 *
 * @author Borgella
 */
public interface UserDAO {
    
    public JsonObject getFil(int id_utilisateur);
        
    public String getListAbonnements(int id_utilistateur);
    
    public String getUtilisateursTweets(int id_utilisateur);
    
    public Tweet getUnTweetDonner(int id_utilisateur,int id_tweet);
    
    public String getUtilisateurRetweets(int id_utilisateur);
    
    public Tweet postUnTweet (int id_utilisateur,String texte);
    
    public Tweet supprimerTweet (int id_utlisateur,int tweet_id);
    
    public Retweet retweeter(int id_utilisateur,int id_tweet);
    
    public Retweet annulerRetweet(int id_utilisateur,int retweet_d);
         
    public User abonnerAUnUtilisateur(int id_utilisateur, int id_aAbonner); 
    
    public User seDesabonner(int id_utilisateur,int id_aDesabonner);
    
    public User getUnSuiveur(int id_utilisateur,int id_suiveur);
    
    public JsonObject getAppAllUsers(int id_utilisateur);
    
}
