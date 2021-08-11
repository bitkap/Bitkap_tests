
# EventsManager Api

Application developpé dans le cadre du test de recrutement chez 


## Outils utlisés

 - [Spring boot](https://spring.io/projects/spring-boo) pour le developpement de l'API backend
 - [Postman](https://www.postman.com/) un outils permettant de tester les requetes HTTP

  une base de données MySql est utilisé. la configuration est dans le fichier 
  ```
  src/main/resources/application.properties
  ````
  Il faut remplacer les valeurs des parametres ci dessous par leurs valeurs appropriées:
```
spring.datasource.url=jdbc:mysql://localhost:3306/tp_spring_eventManagerApi
spring.datasource.username=root
spring.datasource.password=
```

## Features

- CRUD des utilisateurs
- CRUD des evenements
- GESTION des commentaires sur les évènements

  
## Usage/Examples

**NB**: un fichier exporté de postman nommé **Event Manager Api.postman_collection** suitué
à la racine du projet
peut etre importé toujours dans postman. ce fichier contient toutes les routes
de l'API directement tesstables. 

### CRUD des users

* GET ALL USERS
```javascript
HTTP GET http://localhost:9090/api/users
```


* CREATE USER 
```javascript
HTTP POST http://localhost:9090/api/users

//post data
{
    "name": "Kader keita",
    "email": "kader@gmail.com",
    "phone": "678543434"
}
```

* UPDATE USER 
```javascript
HTTP PUT http://localhost:9090/api/users

//post data
{
    "id": 1,
    "name": "Kader keita updated",
    "email": "kader@gmail.com",
    "phone": "678543434"
}
```

* DELETE USER 
```javascript
HTTP DELETE http://localhost:9090/api/users/{userId}
```

### CRUD des events

* GET EVENTS 
On récupère par pagination, par exemple:
```javascript
HTTP GET http://localhost:9090/api/events?pageNo=0&pageSize=2
```

* GET ONE EVENT 
```javascript
HTTP GET http://localhost:9090/api/events/{eventId}
```

* CREATE EVENT 
```javascript
HTTP POST http://localhost:9090/api/events

//post data
{
    "title": "Mon super event",
    "description": "une description de mon super evenement",
    "userId": 3
}
```

* UPDATE EVENT 
```javascript
HTTP PUT http://localhost:9090/api/events

//post data
{
    "id": 1,
    "title": "Mon super event modifié",
    "description": "une description de mon super evenement",
    "userId": 3
}
```

* DELETE EVENT 
```javascript
HTTP DELETE http://localhost:9090/api/events/{eventId}
```
## Appendix

Cette petite application pourrait bien sur etre amelioré un par exemple:

- Un module d'authentification pour la creation des events par un admin 
- Une interface frontend React ou Angular pour consommer l'Api 

## Authors

- Georges FouEjio [@joker7blue](https://github.com/joker7blue)

  
## License

[MIT](https://choosealicense.com/licenses/mit/)

  
## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://georges-fouejio.netlify.app/)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/georges-fouejio-83a714174/)
[![twitter](https://img.shields.io/badge/twitter-1DA1F2?style=for-the-badge&logo=twitter&logoColor=white)](https://twitter.com/GeorginhoPocho)

  