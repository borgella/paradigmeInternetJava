
document.addEventListener('DOMContentLoaded',function(){
    var model = new Model();
    subcribeViews(model);
    bindInputController(model);
    bindPostTweetController(model);
});

function subcribeViews(model){
    model.subscribers.push(renderUserFilViewTweet);
    model.subscribers.push(renderUserAbonnements);
    model.subscribers.push(renderUsersApplication);
}

/*
 * Fonction du model
 */

function Model(){
    this.subscribers = [];
    this.users = [];
    this.selectedUser = null;
    this.selectedTweet = null;
    this.selectedSuiveur = null;
    this.selectedAbonne = null;
    
}

Model.prototype.notify = function() {
    this.subscribers.forEach(function(abonnedView){
        abonnedView(this);
    }.bind(this));
};

Model.prototype.addUser = function(user){
    this.users.push(user);
    this.selectedUser = user;
    this.notify();
};

Model.prototype.setSelectedTweet = function(tweets,message){
    var array = tweets.List_Tweets;
    for(var i = 0; i < array.length;++i){
        var objet = array[i];
        if(objet.Tweets === message){
            this.selectedTweet = array[i];
        }
    }
};

Model.prototype.setSelectedSuiveur = function(suiveurs,nom){
    var array = suiveurs.List_Abonnements;
    for(var i = 0; i < array.length;++i){
        var objet = array[i];
        if(objet.Nom === nom)
            this.selectedSuiveur = array[i];
    }
    
};

Model.prototype.setselectedAbonne = function(users,nom){
    var array = users.Users;
    for(var i = 0; i < array.length;++i){
        var objet = array[i];
        if(objet.Nom === nom){
            this.selectedAbonne = array[i];
        }
    }
};

Model.prototype.setSelectedUserByLogin = function (login) {
  var found = this.users.filter(function (each) { return each.login === login; });
  if (found.length) {
        this.selectedUser = found[0];
  }
};

/*
 * Fonction de la vue
 */
function renderUserFilViewTweet(tweets,model) {
    var el = document.getElementById("fil_utilisateur");
    var objet = tweets.List_Tweets;
    var html = "";
    for(var index in objet){
        html += "<div class = 'input-group input-group-md'>\n\
                    <input type = 'text' id = 'tweet' class = 'form-control' placeholder = "+JSON.stringify(objet[index].Tweets)+">\n\
                    <span class = 'input-group-btn'>\n\
                        <button  class ='btn-supprimer btn btn-default'>Supprimer</button>\n\
                    </span>\n\
                </div>";
    }
    el.innerHTML = html;
    bindUserControllerSupprimerTweet(tweets,model);
}

function renderUserAbonnements(suiveurs,model) {
    var el = document.getElementById("fil_abonnes");
    var objet = suiveurs.List_Abonnements;
    var html = "";
    for(var index in objet){
        html += "<div class = 'input-group input-group-md'>\n\
                    <input type = 'text' id = 'nomSuiveur'  class = 'form-control' placeholder = "+JSON.stringify(objet[index].Nom)+">\n\
                    <span class = 'input-group-btn'>\n\
                        <button  class ='btn-desabonner btn btn-default'>Desabonner</button>\n\
                    </span>\n\
                </div>";
    }
    el.innerHTML = html;
    bindUserControllerDesabonner(suiveurs,model);
}


function renderUsersApplication(users,model) {
    var el = document.getElementById("app_utlisateur");
    var objet = users.Users;
    var html = "";
    for(var index in objet){
        html += "<div class = 'input-group input-group-md'>\n\
                    <input type = 'text' id ='nomAabonner' class = 'form-control' placeholder = "+JSON.stringify(objet[index].Nom)+">\n\
                    <span class = 'input-group-btn'>\n\
                        <button  class ='btn-abonner btn btn-default'>Abonner</button>\n\
                    </span>\n\
                </div>";
    }
    el.innerHTML = html;
    bindUserControllerAbonner(users,model);
    
}

/*
 * Fonction controleur qui sont abonnes a certaine partie du model
 */

function bindInputController(model){
    var input = document.getElementById("user-input");
    var login = document.getElementById("login");
    login.addEventListener("click",function(e){
        e.preventDefault();
        fetchUser(input.value,model);
    });
}
function bindPostTweetController(model){
    var message = document.getElementById("message");
    var post = document.getElementById("post");
    post.addEventListener("click",function(e){
        e.preventDefault();
        beforePost(message.value,model);
    });
    
}

function bindUserControllerSupprimerTweet(tweets,model) {
   var btns = document.getElementsByClassName('btn-supprimer');
   Array.prototype.forEach.call(btns, function(btn) {
       btn.addEventListener("click", function(e) {
           e.preventDefault();
           var aSuppr = this.parentNode.parentNode;
           var message = document.getElementById('tweet').placeholder;
           supprimerTweets(tweets,model,message);
           aSuppr.parentNode.removeChild(aSuppr);
       }.bind(btn));
   });
}

function bindUserControllerDesabonner(suiveurs,model) {
   var btns = document.getElementsByClassName('btn-desabonner');
   Array.prototype.forEach.call(btns, function(btn) {
       btn.addEventListener("click", function(e) {
           e.preventDefault();
           var aDesabonn = this.parentNode.parentNode;
           var nom = document.getElementById('nomSuiveur').placeholder;
           desabonner(suiveurs,model,nom);
           aDesabonn.parentNode.removeChild(aDesabonn);
       }.bind(btn));
   });

}

function bindUserControllerAbonner(users,model) {
   var btns = document.getElementsByClassName('btn-abonner');
   Array.prototype.forEach.call(btns, function(btn) {
       btn.addEventListener("click", function(e) {
           e.preventDefault();
           var aAbonn = this.parentNode.parentNode;
           var nom = document.getElementById('nomAabonner').placeholder;
           abonner(users,model,nom);
           aAbonn.parentNode.removeChild(aAbonn);
       }.bind(btn));
   });
}
/*
 * Functions qui executent et traitent les commandes envoyées par les controleurs
 */

function fetchUser(user,model) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET","/utilisateurs/"+user+"/tweets",true);
    xhr.onreadystatechange = (function(){
        if(xhr.readyState === 4 && xhr.status === 200){
            model.addUser(user);
            renderUserFilViewTweet(JSON.parse(xhr.responseText),model);
            fetchUsersAbonnements(model);
            fetchAllAppUsers(model);
        } else if (xhr.status === 404)model.addUser(user);   
    });
    xhr.send();
}

function beforePost(message,model){
    var xhr = new XMLHttpRequest();
    xhr.open("GET","/users/"+model.selectedUser,true);
    xhr.onreadystatechange = (function(){
        if(xhr.readyState === 4 && xhr.status === 200){
            postTweet(message,model);
        }else if (xhr.status === 404)alert("Cet utilisateur n'existe pas...!");      
    });
    xhr.send();
    
}
function postTweet(message,model){
    var xhr = new XMLHttpRequest();
    var tweet = {};
    tweet.Tweet = message;
    xhr.open("POST","/utilisateurs/"+model.selectedUser+"/tweets",true);
    xhr.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
    xhr.onreadystatechange = (function(){
        if(xhr.readyState === 4 && xhr.status === 200){
            model.users.pop();
            fetchUser(model.selectedUser,model);
        } else if (xhr.status === 404)alert("Le tweet ne dois pas depasser 140 caracteres.");   
    });
    xhr.send(JSON.stringify(tweet));
}

function fetchUsersAbonnements(model){
    var xhr = new XMLHttpRequest();
    xhr.open("GET","/utilisateurs/"+model.selectedUser+"/abonnements",true);
    xhr.onreadystatechange = (function(){
        if(xhr.readyState === 4 && xhr.status === 200){
            renderUserAbonnements(JSON.parse(xhr.responseText),model);
        }    
    });
    xhr.send();
}

function fetchAllAppUsers(model){
    var xhr = new XMLHttpRequest();
    xhr.open("GET","/users/"+model.selectedUser,true);
    xhr.onreadystatechange = (function(){
        if(xhr.readyState === 4 && xhr.status === 200){
            renderUsersApplication(JSON.parse(xhr.responseText),model);
        }    
    });
    xhr.send();
}

function supprimerTweets(tweets,model,message){
    model.setSelectedTweet(tweets,message);
    var tweet = model.selectedTweet;
    var xhr = new XMLHttpRequest();
    xhr.open('DELETE','/utilisateurs/'+model.selectedUser+'/tweets/'+tweet.Id_Tweets,true);
    xhr.onreadystatechange = (function(){
        if(xhr.readyState === 4 && xhr.status === 200)
               fetchUser(model.selectedUser,model);    
    });
    xhr.send();
}  

function desabonner(suiveurs,model,nom){
    model.setSelectedSuiveur(suiveurs,nom);
    var suiveur = model.selectedSuiveur;
    var xhr = new XMLHttpRequest();
    xhr.open('DELETE','/utilisateurs/'+model.selectedUser+'/abonnements/'+suiveur.Id_User,true);
    xhr.onreadystatechange = (function(){
        if(xhr.readyState === 4 && xhr.status === 200)
               fetchUser(model.selectedUser,model);    
    });
    xhr.send(); 
} 

function abonner(users,model,nom){
    model.setselectedAbonne(users,nom);
    var sabonner = model.selectedAbonne;
    var xhr = new XMLHttpRequest();
    xhr.open('PUT','/utilisateurs/'+model.selectedUser+'/abonnements/'+sabonner.Id_User,true);
    xhr.onreadystatechange = (function(){
        if(xhr.readyState === 4 && xhr.status === 200)
                fetchUsersAbonnements(model);   
    });
    xhr.send();  

} 
