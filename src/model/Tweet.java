    
package model;
import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author Borgella
 */
public class Tweet {
    
    private int tweet_id;
    private String text;
    private static int cpt = 0;

    public Tweet(String texte) {
        this.text = texte;
        tweet_id = cpt++;    
    }
    
    public int getTweetId(){
        return this.tweet_id;
    }
    
    public String getTexte(){
        return this.text;
    }
    
    
    
    public JsonObject toJsonTweets(){
        JsonObject builded = Json.createObjectBuilder()
            .add("Id_Tweets",this.tweet_id)
            .add("Tweets", this.text)
            .build();
        return builded;
    }
    
}


