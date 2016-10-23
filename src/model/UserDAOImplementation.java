
package model;

import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

/**
 * This class will interact with the BD or whatever type of storage we will decide
 * to use.
 * @author Borgella
 */
public class UserDAOImplementation implements UserDAO{
    
    ArrayList<User> list_users;
    
    public UserDAOImplementation(ArrayList<User> users){
        
        this.list_users = users;
        
    }

    @Override
    public JsonObject getFil(int id_utilisateur) {
        User user = findOne(id_utilisateur);
        if(user != null){ 
            JsonObject json = Json.createObjectBuilder()
                .add("Tweets",user.jsonArrayTweets())
                .add("Retweets",user.jsonArrayRetweets()).build();
            return json;
        }
        return null;
    }
    
    @Override
    public String getListAbonnements(int id_utilisateur) {
        User user = findOne(id_utilisateur);
        if(user != null ){
            JsonObject json = Json.createObjectBuilder()
                .add("List_Abonnements", user.jsonArraySuiveursJson()).build();
             return json.toString();
        }
        return null;
    }

    @Override
    public String getUtilisateursTweets(int id_utilisateur) {
        User user = findOne(id_utilisateur);
        JsonObject json = Json.createObjectBuilder()
            .add("List_Tweets",user.jsonArrayTweets()).build();
        return json.toString();
        
    }
    
    
    @Override
    public String getUtilisateurRetweets(int id_utilisateur) {
       User user = findOne(id_utilisateur);
        JsonObject json = Json.createObjectBuilder()
            .add("List_ReTweets", user.jsonArrayRetweets()).build();
        return json.toString();
    }
    
    
    @Override
    public Tweet postUnTweet(int id_utilisateur,String texte) {
        User user = findOne(id_utilisateur);
        Tweet tweet = new Tweet(texte);
        user.addTweet(tweet);
        return user.getOneTweet(tweet.getTweetId());
    }
    
    @Override
    public Tweet supprimerTweet(int id_utilisateur,int tweet_id) {
       User user = findOne(id_utilisateur);
       return user.supprimerTweet(tweet_id);
    }

    @Override
    public Retweet retweeter(int id_utilisateur, int id_tweet) {
        User user = findOne(id_utilisateur);
        return user.addRetweet(id_tweet);
    }

    @Override
    public User abonnerAUnUtilisateur(int id_utilisateur, int id_aAbonner) {
        User user = findOne(id_utilisateur);
        User aAbonner = findOne(id_aAbonner);
        user.addSuiveurs(aAbonner);
        return aAbonner;
    }

    @Override
    public User seDesabonner(int id_utilisateur,int id_aDesabonner) {
        User user = findOne(id_utilisateur);
        if(user != null)
           return user.supprimerUnSuiveur(id_aDesabonner);
        return null;
    }

    @Override
    public Retweet annulerRetweet(int id_utilisateur, int retweet_d) {
        User user = findOne(id_utilisateur);
        if(user != null) 
           return user.annulerRetweet(retweet_d);
        return null;
    }

    @Override
    public User getUnSuiveur(int id_utilisateur, int id_suiveur) {
        User user = findOne(id_utilisateur);
        if(user != null) 
           return user.trouverUtilisateurSuivi(id_suiveur);
        return null;
    }

    @Override
    public Tweet getUnTweetDonner(int id_utilisateur, int id_tweet) {
       User user = findOne(id_utilisateur);
        if(user != null) 
           return user.getOneTweet(id_tweet); 
       return null;
    }
      
    @Override
    public JsonObject getAppAllUsers(int id_utilisateur) {
        User theUser = findOne(id_utilisateur);
        JsonArrayBuilder array = Json.createArrayBuilder();
        for(User user : this.list_users ){
            if(((theUser.getUserId()!= user.getUserId())&& !(theUser.getListSuiveurs().contains(user))))
                array.add(user.toJsonUtilisateur());
        }
        JsonObject json = Json.createObjectBuilder()
            .add("Users", array).build();
        return json;
    }
    
    private User findOne(int id_utilisateur){
        for(User user : list_users){
            if(user.getUserId() == id_utilisateur){
                return user;
            }
        }
       return null;
    }

}
