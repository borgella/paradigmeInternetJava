
package serverRoutes;

import server.Request;
import server.Router;

/**
 *
 * @author Borgella
 */
public interface Route {
    
    boolean acceptUrl(Request request);
    
    void execute(Router router, Request request);
    
}
