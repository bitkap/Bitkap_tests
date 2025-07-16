# Event Management App

## Vue d'ensemble

Vous devez développer une application web pour la gestion d'événements en utilisant Spring Boot pour le backend et Angular pour le frontend. L'objectif est de développer une application de gestion d'événements où les utilisateurs peuvent s'inscrire, modifier, supprimer, afficher des événements et y faire des commentaires.\
L'application doit utiliser Keycloak comme fournisseur d'identité pour sécuriser tous les endpoints de l'API, à l'exception de celui qui fournit la liste des événements.

## Exigences

### Evénement

Chaque **événement** doit contenir :

* un titre (avec un maximum de 100 caractères)
* une description
* une date de l'événement
* une personne responsable de l'événement
* une liste de commentaires (elle peut être vide)

Chaque **commentaire** doit contenir :

* un texte
* une date

### Exigences de l'API

Routes :

* Liste d'événements (pas d'authentification nécessaire). Elle doit être paginée
* Créer un nouvel événement
* Obtenir les détails d'un événement ainsi que ses commentaires
* Mise à jour d'un événement
* Supprimer un événement
* Ajouter un commentaire à un événement
* Supprimer un commentaire

### Exigences Frontend

* Implémenter une interface utilisateur utilisant Angular qui permet :
  
  * de lister événements, tout en les paginant (cette route doit être publique)

  * aux utilisateurs authentifiés de créer, mettre à jour et supprimer des événements
  
  * Les utilisateurs authentifiés peuvent ajouter et supprimer des commentaires sur les événements.

* Intégrer Keycloak pour la connexion et la déconnexion.

* Affichage des informations sur l'utilisateur lorsqu'il est connecté

* Fournir des messages d'erreur et des états de chargement

### Securité

* Utiliser Keycloak comme fournisseur d'identité OAuth2

* Sécuriser toutes les routes du backend, sauf celui qui permet d'obtenir la liste des événements

* Le frontend Angular devrait :

  * Rediriger les utilisateurs vers le login de Keycloak

  * Effectuer des requêtes authentifiées

Paramètres Keycloak à utiliser :

* issuer: "https://sso.bitkap.africa"
* domain: "https://sso.bitkap.africa"
* Realm: "bitkap_dev"
* client id: "angolar_test"
  
Avec un utilisateur : email = "test@bitkap.net" , password = "password"  

### Tech stack

* Backend: Java 8+, Spring Boot, Spring Security, Spring Data JPA, Keycloak adapter, H2/PostgreSQL

* Frontend: Angular 12+

* Bonus (mais pas obligatoire) : Docker pour conteneuriser chaque application

## Critères d'évaluation

* Structure et clarté du code

* Respect des exigences

* Utilisation correcte des meilleures pratiques Spring Boot et Angular

* Configuration de l'authentification et de l'autorisation

* Pagination et conception RESTful

* Qualité et réactivité de l'interface utilisateur et de l'interface graphique

## Livrables

* Lien GitHub/Gitlab du code source
* Une vidéo demontrant comment l'application fonctionne
