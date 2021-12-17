# COMMENT UTILISER l'API
Avant de commencer, il est necessaire d'installer mongoDB et de le demarrer.  
Pour tester l'API, Nous allons utiliser postman.
## CONFIGURATION DE POSTMAN
Il est nécessaire de configurer le corps de la requête au format JSON lorsqu'un corps est nécessaire.
### 1- Creer un événement
requête: POST  
url: http://localhost:8080/api/events/create_event  
<strong style="color: green">body</strong>:  
{  
    "title": "Titre de l'évènement",  
    "description": "Description de l'évènement",  
    "person": {"name": "Prénom de la personne", "surname": "Nom de la personne"}  
}  
chacun des attributs est facultatif

### 2- Liste de tous les évènements
requête: GET  
url: http://localhost:8080/api/events/load_all_events  

### 3- Liste des événements avec pagination
requête: GET  
url: http://localhost:8080/api/events/load_events  
<strong style="color: orange">params</strong>:  
{  
"title": "Tout ou partie du titre de l'événement",  
"page": "2" //exemple de numéro de page,  
"size": "6" //exemple de taille d'une page  
}  
Chaque paramètre est facultatif. La valeur par défaut de la page est 0 (la première page) et la valeur par défaut de la taille d'une page est de 3.


### 4- Visualisation individuel de l'événement 
requête: GET  
url: http://localhost:8080/api/events/load_event  
<strong style="color: orange">params</strong>:  
{  
"id": "id de l'événement souhaitée"  
} 
### 5- Mise à jour d'un événement
requête: POST  
url: http://localhost:8080/api/events/update_event  
<strong style="color: green">body</strong>:  
{  
"id" : "id de l'événement à mettre à jour", // paramètre requis  
"title": "Nouveau titre de l'événement",  
"description": "Nouvelle description de l'événement",  
"person": {"name": "Prénom de la personne", "surname": "Nom de la personne"} //Nouvel objet Person  
}  
Les paramètres de l'événement (sauf l'id) sont facultatifs. Lorsqu'un paramètre n'est pas précisé, l'ancienne valeur est conservée.

### 6- Suppression d'un événement
requête: DELETE  
url: http://localhost:8080/api/events/delete_event  
<strong style="color: orange">params</strong>:  
{  
"id": "id de l'événement à supprimer"  
} 

### 7- Commenter un événement
requête: POST  
url: http://localhost:8080/api/events/add_comment  
<strong style="color: green">body</strong>:  
{  
"eventId": "id de l'évenement à commenter" //paramètre requis  
"description": "Description du commentaire",  
"date": "date du commentaire" ///La date au format "yyyy-MM-dd'T'HH:mm:ss.SSS"
}  

### 8- Modifier un commentaire
requête: POST  
url: http://localhost:8080/api/events/update_comment  
<strong style="color: green">body</strong>:  
{  
"id": "id du commentaire à modifier", // paramètre requis  
"description": "Description du commentaire",  
"date": "date du commentaire" // la date au format "yyyy-MM-dd'T'HH:mm:ss.SSS"  
}  

### 9- Visualiser un commentaire
requête: GET  
url: http://localhost:8080/api/events/load_event  
<strong style="color: orange">params</strong>:  
{  
"id": "id du commentaire que l'on souhaite visualiser"  
} 
### 10- Supprimer un commentaire
requête: DELETE  
url: http://localhost:8080/api/events/delete_comment  
<strong style="color: orange">params</strong>:  
{  
"id": "id du commentaire à supprimer"  
} 


N'hésitez pas à revenir vers moi pour toute préoccupation :-)
<a href="mailto:bakengfack@gmail.com">bakengfack@gmail.com</a>
