# Event Management App

## Overview

Vous devez développer une application web pour la gestion d'événements en utilisant Spring Boot pour le backend et Angular pour le frontend. L'objectif est de développer une application de gestion d'événements où les utilisateurs peuvent s'inscrire, modifier, supprimer, afficher des événements et y faire des commentaires.\
L'application doit utiliser Keycloak comme fournisseur d'identité pour sécuriser tous les endpoints de l'API, à l'exception de celui qui fournit la liste des événements.

## Requirements

### Event Model

Chaque **événement** doit contenir :

* un titre (avec un maximum de 100 caractères)
* une description
* une date de l'événement
* une personne responsable de l'événement
* une liste de commentaires (elle peut être vide)

Chaque **commentaire** doit contenir :

* un texte
* une date

### API Requirements

Routes :

* Liste d'événements (pas d'authentification nécessaire). Elle doit être paginée
* Créer un nouvel événement
* Obtenir les détails d'un événement ainsi que ses commentaires
* Mise à jour d'un événement
* Supprimer un événement
* Ajouter un commentaire à un événement
* Supprimer un commentaire

### Frontend Requirements

* Implémenter une interface utilisateur utilisant Angular qui permet :
  
  * de lister événements, tout en les paginant (cette route doit être publique)

  * aux utilisateurs authentifiés de créer, mettre à jour et supprimer des événements
  
  * Les utilisateurs authentifiés peuvent ajouter et supprimer des commentaires sur les événements.

* Intégrer Keycloak pour la connexion et la déconnexion.

* Affichage des informations sur l'utilisateur lorsqu'il est connecté

* Fournir des messages d'erreur et des états de chargement

### Security Requirements

* Utiliser Keycloak comme fournisseur d'identité OAuth2

* Sécuriser toutes les routes du backend, sauf celui qui permet d'obtenir la liste des événements

* Le frontend Angular devrait :

  * Rediriger les utilisateurs vers le login de Keycloak

  * Effectuer des requêtes authentifiées

### Tech stack

* Backend: Java 8+, Spring Boot, Spring Security, Spring Data JPA, Keycloak adapter, H2/PostgreSQL

* Frontend: Angular 12+

* Bonus (mais pas obligatoire) : Docker pour conteneuriser chaque application

## Evaluation Criteria

* Structure et clarté du code

* Respect des exigences

* Utilisation correcte des meilleures pratiques Spring Boot et Angular

* Configuration de l'authentification et de l'autorisation

* Pagination et conception RESTful

* Qualité et réactivité de l'interface utilisateur et de l'interface graphique

## Deliverables

* Lien GitHub/Gitlab du code source
* Une vidéo demontrant comment l'application fonctionne
