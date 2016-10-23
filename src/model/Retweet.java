
package model;

import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author Borgella
 */
public class Retweet {
    
    private Tweet retweet;
    
    public Retweet(Tweet tweet){
        retweet = tweet;
    }
    
    public int getRetweetId(){
        return retweet.getTweetId();
    }
    
    public String getReTweetTexte(){
        return retweet.getTexte();
    }
    public JsonObject toJsonRetweets(){
        JsonObject builded = Json.createObjectBuilder()
            .add("Id_Retweets",this.retweet.getTweetId())
            .add("Retweets", this.retweet.getTexte())
            .build();
        return builded;
    }
}
