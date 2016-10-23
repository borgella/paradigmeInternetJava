
package serverRoutes;

import server.Request;

/**
 *
 * @author Borgella
 *
 * Implementation of the Strategy pattern,the super super class RouteControler
 * defines an object name URIType that will give its subclasses the capability 
 * to decide what type of URYType they will be at runtime. In this case they will
 * decide if yes or no the client's request for a resource is available. 
 * Each URIType object will implements the itMatches method is own way. 
 * 
 */
public abstract class RouteControler implements Route{
  
    protected URIType uriType;
    
    @Override
    public boolean acceptUrl(Request request) {
        
        return uriType.itMatches(request);
        
    }
    
}
