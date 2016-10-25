## Projet d'étude pour le cours de paradigme des échanges internet INF4375

*Le but du projet est la mise en place d’un serveur web de bas niveau (utilisation des sockets) sur lequel est bâti un service web pour traiter les différentes requêtes d’un client (navigateur web) à l’aide du protocole Http et l’API  Rest de Tweeter. Pour ce projet on a construit l'API pour :* 
*
1. Retourner le fil d'un utilisateur = "/utilisateurs/[0-9]+/fil"
2. Avoir la liste de tous les abonnés d'un utilisateur = "/utilisateurs/[0-9]+/abonnements"
3. Ajouter, retourner et supprimer un abonné dans la liste d'un utilisateur = "/utilisateurs/[0-9]+/abonnements/[0-9]+"
4. Avoir la liste de tous les tweets d'un utilisateur ou créer un tweet en particulier = "/utilisateurs/[0-9]+/tweets 
5. Avoir un tweet en particulier ou de supprimer un tweet d'un utilisateur = "/utilisateurs/[0-9]+/tweets/[0-9]+"
6. Avoir la liste de tous les retweets d'un utilisateur ou d'en créer un = "/utilisateurs/[0-9]+/retweets"
7. Avoir un retweet en particulier d'un utilisateur ou d'en supprimer un = "/utilisateurs/[0-9]+/retweets/[0-9]+"
8. Finalement avoir la liste de tous les utilisateurs du système = "/users/[0-9]+"
*

*Afin de simplifier l'API et de rester sur l'essentiel du projet, tous les ID utilisateurs sont compris entre 0-9. Le projet est
divisé en 5 packages distincts*
** Architecture du projet = MVC**

** Conception du projet et l'explication sur les différents packages **
*
1. - Le package Model comprend l'interface de tous actions possibles par un utilisateur, la logique métier du système et l'utilisation du patron de conception DAO pour la réalisation de cette tâche.
2. - Le package server comprend les classes nécessaire pour la conception du serveur web
3. - Le package serverRoutes comprend l'implémentation des différentes requêtes auxquelles le serveur donnera suite. L'utilisation des patrons de conception strategie et commande sont utilisés pour la réalisation de ce package
4. - Le package view comprend le format de réponse que le serveur envoit au client ce qui peut être soit du Json ou un fichier html brut
5. - Le package mainService qui est le point d'entrée du programme
