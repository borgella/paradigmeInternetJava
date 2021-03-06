
package mainService;

import java.io.IOException;
import java.util.ArrayList;
import model.UserDAOImplementation;
import model.Tweet;
import model.DaoUtilisation;
import model.User;
import server.Error;
import server.Router;
import serverRoutes.RouteAddGetDeleteAbonnes;
import serverRoutes.RouteGetDeleteATweet;
import serverRoutes.RouteGetAbonnements;
import serverRoutes.RouteGetFil;
import serverRoutes.RouteGetPostTweets;
import serverRoutes.RouteDeletePutRetweet;
import serverRoutes.RouteGetAppUsers;
import serverRoutes.RouteGetRetweets;
import serverRoutes.Route;

/**
 *
 * @author Borgella
 */
public class TweeterService {
    
    public static void main(String[] args) throws IOException, Error {
        final int port = 4001;
       // Represente notre base de données des utilisateurs.
        ArrayList<User> listUtilisateurs = new ArrayList(5);
       /*
        ************************** Début de la preparation des utilisateurs ******************************************************** 
        */
        User toto = new User("Toto","Uqam");
        User marie = new User("Marie","Uqam");
        User michel = new User("Michel","Uqam");
        User madeline = new User("Madeline","Uqam");
        User john = new User("John","Uqam");
        
        toto.addTweet(new Tweet("Hello je m'appelle Toto et c'est mon premier tweet."));
        toto.addTweet(new Tweet("C'est toujours moi Toto,je m'amuse a tweeter."));
        toto.addTweet(new Tweet("Toto et tweeter ce n'est pas une histoire d'un soir."));
        toto.addTweet(new Tweet("Un dernier Tweet avant de passer a autre chose."));
        marie.addTweet(new Tweet("Salut moi c'est Marie ,ca fait du bien de tweeter."));
        michel.addTweet(new Tweet("Michel c'est mon nom, je suis nouveau sur Tweeter."));
        madeline.addTweet(new Tweet("Je suis Madeline et je viens voir qu'est ce qui se passe ici."));
        john.addTweet(new Tweet("I'm John and i don't understand a damn thing of what you're saying.!"));
        
        //Ajout des suiveurs 
        toto.addSuiveurs(marie);
        toto.addSuiveurs(john);
        toto.addSuiveurs(madeline);
        toto.addSuiveurs(michel);
        
        marie.addSuiveurs(toto);
        madeline.addSuiveurs(michel);
        madeline.addSuiveurs(toto);
        michel.addSuiveurs(marie);
        
        toto.addRetweet(marie.getOneTweet(4).getTweetId());
        toto.addRetweet(john.getOneTweet(7).getTweetId());
        marie.addRetweet(toto.getOneTweet(0).getTweetId());
        /*
        ******************************  Fin de la preparation des Utilisateurs ******************************************************
        */
        /*
        ************* Ajout des utilisateurs dans notre " List ArrayList" qui sert de Base de données pour l'instant *****************
        */
        listUtilisateurs.add(toto);
        listUtilisateurs.add(marie);
        listUtilisateurs.add(michel);
        listUtilisateurs.add(madeline);
        listUtilisateurs.add(john);
        /*
        ****************************** Fin des ajoouts dans notre Base de donnée  ***************************************************
        */
        
        //Classe adapteur, cette classe peut être modifier pour faire le
        // traitement en utilisant une base de donnée SQL, MySql ou MongoDB
        UserDAOImplementation jsonUserDao = new UserDAOImplementation(listUtilisateurs); 
        
        DaoUtilisation jsonUser = new DaoUtilisation(jsonUserDao);// Classe cliente qui ne changera pas et utilisera toujours
                                                                         // un Objet UserDAOImplementation
        
        // On ajoute les différentes routes que notre serveur traitera
        ArrayList<Route> routes = new ArrayList(8);
        
        routes.add(new RouteGetFil(jsonUser));
        routes.add(new RouteGetAbonnements(jsonUser));
        routes.add(new RouteAddGetDeleteAbonnes(jsonUser));
        routes.add(new RouteGetPostTweets(jsonUser));
        routes.add(new RouteGetDeleteATweet(jsonUser));
        routes.add(new RouteDeletePutRetweet(jsonUser));
        routes.add(new RouteGetRetweets(jsonUser));
        routes.add(new RouteGetAppUsers(jsonUser,listUtilisateurs));
        
        Router router = new Router(routes);
        
        System.out.println("Le client se connecte sur le port " + port + "...\n");
        
        router.listen(port);
        
    }
    
}


