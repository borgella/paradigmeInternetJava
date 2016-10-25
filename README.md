### Projet d'étude pour le cours de paradigme des échanges internet INF4375

*Le but du projet est la mise en place d’un serveur web de bas niveau (utilisation des sockets) sur lequel est bâti un service web pour traiter les différentes requêtes d’un client (navigateur web) à l’aide du protocole Http et l’API  Rest de Tweeter. Pour ce projet on a définit un API pour :*

1. *Retourner le fil d'un utilisateur = "/utilisateurs/[0-9]+/fil"*
2. *Avoir la liste de tous les abonnés d'un utilisateur = "/utilisateurs/[0-9]+/abonnements"*
3. *Ajouter, retourner et supprimer un abonné dans la liste d'un utilisateur = "/utilisateurs/[0-9]+/abonnements/[0-9]+"*
4. *Avoir la liste de tous les tweets d'un utilisateur ou créer un tweet en particulier = "/utilisateurs/[0-9]+/tweets*
5. *Avoir un tweet en particulier ou de supprimer un tweet d'un utilisateur = "/utilisateurs/[0-9]+/tweets/[0-9]+"*
6. *Avoir la liste de tous les retweets d'un utilisateur ou d'en créer un = "/utilisateurs/[0-9]+/retweets"*
7. *Avoir un retweet en particulier d'un utilisateur ou d'en supprimer un = "/utilisateurs/[0-9]+/retweets/[0-9]+"*
8. *Finalement avoir la liste de tous les utilisateurs du système = "/users/[0-9]+"*


*Afin de se concentrer sur l'essentiel du projet, tous les ID des utilisateurs sont des entiers compris entre 0-9. L'utilisation d'une base de donées n'est pas requise et un arrayList d'objets est suffisant pour sauvegarder les utilisateurs du systéme.*


**Architecture du projet**

*Pour la réalisation du projet on a utilisé le style architectural MVC autant du côté serveur et du côté client, ce qui a permis un découpage du projet en 5 packages distints et un répertoire public pour le front-end du projet*

**Conception du projet et l'explication sur les différents packages**

1. *Le package Model comprend l'interface de tous actions possibles par un utilisateur, la logique métier du système et l'utilisation du   patron de conception DAO pour la réalisation de cette tâche.*
2. *Le package server comprend les classes nécessaire pour la conception du serveur web*
3. *Le package serverRoutes comprend l'implémentation des différentes requêtes auxquelles le serveur donnera suite. L'utilisation des patrons de conception strategie et commande sont utilisés pour la réalisation de ce package*
4. *Le package view comprend le format de réponse que le serveur envoit au client ce qui peut être soit du Json ou un fichier html brut*
5. *Le package mainService qui est le point d'entrée du programme*

#### N'Hésitez pas à jeter un coup d'oeil sur le code pour plus de commentaire et d'explication supplémentaire ####
