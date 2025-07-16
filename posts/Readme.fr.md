# Application de gestion de publication

## Vue d'ensemble

Vous devez créer une application mobile permettant aux utilisateurs de visualiser, créer et gérer des publications. Une publication est constituée d'un identifiant, d'un titre, d'un corps (body) et d'un utilisateur (username). L'application doit implémenter un mécanisme sécurisé d'authentification de l'utilisateur via Keycloak et stocker toutes les données localement sur l'appareil.

Ce projet servira à la fois d'évaluation technique et de tâche de développement de fonctionnalités dans le monde réel, évaluant votre capacité à intégrer l'authentification, à construire une UI/UX responsive et à gérer la persistance des données locales.

## Exigences

### Exigences fonctionnelles

L'application doit avoir :

* Une liste principale pour visualiser toutes les publications
* Une vue détaillée pour chaque publication
* Un formulaire pour créer/modifier une publication

### Exigences non fonctionnelles

* Exigences de sécurité : utiliser Keycloak comme fournisseur d'identité OAuth2 pour l'authentification des utilisateurs.
* L'application doit être responsive
* Les messages doivent être stockés localement en utilisant une base de données comme SQLite, Hive, ou toute autre solution de persistance locale de votre choix.

Note : Paramètres Keycloak à utiliser :

* issuer : "https://sso.bitkap.africa"
* domaine : "https://sso.bitkap.africa"
* Realm : “bitkap_dev”
* client_id : "angolar_test"

Avec l'utilisateur : email = "test@bitkap.net", password = "password"

## Modèle de message

Un message doit contenir les éléments suivants :

* id : Identifiant unique
* title : Texte court
* date : heure à laquelle le message a été créé
* body : Contenu principal
* username : nom d'utilisateur du créateur du message (doit être celui de l'utilisateur connecté)

## Critères d'évaluation

* Structure et clarté du code
* Respect des exigences
* Utilisation correcte des meilleures pratiques de Flutter
* Configuration de l'authentification
* Qualité et réactivité de l'UI/UX

## Livrables

* Lien GitHub/Gitlab vers le code source
* Une vidéo démontrant le fonctionnement de votre application
