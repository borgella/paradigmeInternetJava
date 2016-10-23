
package model;

/**
 *
 * @author Borgella
 */
import java.util.ArrayList;
import java.util.Objects;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

public class User{
    private String user_name;
    private String user_firstname;
    private double user_id;
    private static double unique_id = 0.0;//Math.random();
    private ArrayList<Tweet> userTweets;
    private ArrayList<Retweet> userRetweets;
    private ArrayList<User> subscribers;
    private ArrayList<User> followers;
    

    public User(String nom,String prenom){
        user_name = nom;
        user_firstname = prenom;
        userTweets = new ArrayList(1);
        userRetweets = new ArrayList(1);
        subscribers = new ArrayList(1);
        followers = new ArrayList(1);
        user_id = ++unique_id ;
    }
    
    public void addTweet(Tweet tweet){
        userTweets.add(0,tweet);
    }
    
    public Retweet addRetweet(int tweet_id){
        for(User user : followers){
            if(user.getOneTweet(tweet_id)!= null){
                Retweet retweet = new Retweet(user.getOneTweet(tweet_id));
                userRetweets.add(0,retweet);
                return retweet;
            }
        }
        return null;
    }

    public User addSuiveurs(User user){
        if(user != this && userDontExistYet(user)){
            followers.add(user);
            user.subscribers.add(this);
        }
        return user;
    }
    
    public Tweet supprimerTweet(int tweet_id){
        for(Tweet tweet: userTweets){
            if(tweet.getTweetId() == tweet_id){
                userTweets.remove(tweet);
                return tweet;
            }
        }
        return null;
    }
    
    public Retweet annulerRetweet(int retweet_id){
        for(Retweet retweet: userRetweets){
            if(retweet.getRetweetId() == retweet_id){
                userRetweets.remove(retweet);
                return retweet;
            }
        } 
        return null;
    }
    
    public User supprimerUnAbonneur(int user_id){
        User user = trouverUtilisateurSuivi(user_id);
        if(user!=null){
            user.subscribers.remove(this);
            supprimerUnSuiveur(user_id);
            return user;
        }
        return null;
    }
    
    public User trouverUtilisateurSuivi(int user_id){
        for(User user : followers){
            if(user.user_id == user_id){
                return user;
            }
        }
        return null;
    }
    
        
    public User supprimerUnSuiveur(int user_id){
        for(User user : followers){
            if(user.user_id == user_id){
                this.followers.remove(user);
                return user;
            }
        } 
        return null;
    }

    public JsonArray getTweetsJson(User user){
        JsonArray jsonArray = Json.createArrayBuilder().build();
        for(Tweet tweet : user.userTweets){
            jsonArray.add(tweet.toJsonTweets());
        }
        return jsonArray;
    }
        
    public Tweet getOneTweet(int tweet_id){
        for(Tweet tweet: userTweets){
            if(tweet.getTweetId() == tweet_id)
                return tweet;
        } 
        return null;
    }
        
    public JsonObject toJsonUtilisateur(){
        JsonObject json = Json.createObjectBuilder()
                .add("Id_User",user_id)
                .add("Nom", user_name)
                .add("Prenom", user_firstname)
                .add("Tweets", jsonArrayTweets())
                .add("Retweets",jsonArrayRetweets())
                .add("Abonnes",jsonArrayAbonneJson())
                .add("Suiveur",jsonArraySuiveursJson())
                .build();
        return json;
    
    }
    
    public JsonArrayBuilder jsonArrayTweets(){
        JsonArrayBuilder jsonArray = Json.createArrayBuilder();
        for(Tweet u : userTweets){
            jsonArray.add(u.toJsonTweets());
        }
        return jsonArray;
    }
    
    public JsonArrayBuilder jsonArrayRetweets(){
        JsonArrayBuilder jsonArray = Json.createArrayBuilder();
        for(Retweet u : userRetweets){
            jsonArray.add(u.toJsonRetweets());
        }
        return jsonArray;
    }
    
    public JsonArrayBuilder jsonArrayAbonneJson(){
        JsonArrayBuilder jsonArray = Json.createArrayBuilder();
        for(User user : subscribers){
            jsonArray.add(jsonUnUtilisateur(user));
        }
        return jsonArray;
    }
    
    public JsonArrayBuilder jsonArraySuiveursJson(){
        JsonArrayBuilder jsonArray = Json.createArrayBuilder();
        for(User u : followers){
            jsonArray.add(jsonUnUtilisateur(u));
        }
        return jsonArray;
    }
    
    private JsonObject jsonUnUtilisateur(User user){
        JsonObject json = Json.createObjectBuilder()
                .add("Id_User",user.user_id)
                .add("Nom", user.user_name)
                .add("Prenom", user.user_firstname)
                .add("Tweets", user.jsonArrayTweets())
                .add("Retweets",user.jsonArrayRetweets()).build();
        return json;
    }
    
    
    private boolean userDontExistYet(User u){
        for(User user : followers){
            if(u.equals(user)) return false;
        }
        return true;
    }

    
    @Override
    public boolean equals(Object u){
        if(u instanceof User ){
            User user = (User) u;
            return user.getUserId() == this.user_id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.user_name);
        hash = 41 * hash + Objects.hashCode(this.user_firstname);
        hash = (int) (41 * hash + this.user_id);
        return hash;
    }
    // Getter and setters //
    public String getNom(){
        return this.user_name;
    }
    
    public String getPrenom(){
        return this.user_firstname;
    }
    
    public ArrayList<Tweet> getListTweet(){
        return this.userTweets;
    }
     
    public double getUserId(){
        return this.user_id;
    }
    
     public ArrayList<Retweet> getListRetweet(){
        return this.userRetweets;
    }
    
    public ArrayList<User> getListAbonnes(){
        return subscribers;
    }
    
    public ArrayList<User>  getListSuiveurs(){
        return followers;
    }
}
