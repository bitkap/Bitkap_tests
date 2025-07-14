# Event Management App

[French version here](ReadMe.fr.md)

## Overview

You are required to develop a web application for managing events using Spring Boot for the backend and Angular for the frontend. The objective here is to develop an event management app where users can register, edit, delete, display events and make comments.\
The application should use Keycloak as the identity provider to secure all API endpoints except for the public listing of events.

## Requirements

### Event Model

Each **Event** should contain :

* a title (with a maximum of 100 characters)
* a description
* a date of the event
* a person responsible for the event
* a list of comments (it can be empty)

Each **Comment** should contain :

* a text
* a date

### API Requirements

Endpoints :

* List events (no authentication needed). It should be paginated
* Create a new event
* Get details of an event alongside with its comments
* Update an event
* Delete an event
* Add a comment to an event
* Delete a comment

### Frontend Requirements

* Implement a UI using Angular that allows:
  
  * Public listing of paginated events

  * Authenticated users to create, update, and delete events
  
  * Authenticated users to add and delete comments on events

* Integrate with Keycloak for login/logout

* Display user info when logged in

* Provide error messages and loading states

### Security Requirements

* Use Keycloak as the OAuth2 Identity Provider.

* Secure all backend endpoints except the one to get the list of events.

* Angular frontend should:

  * Redirect users to Keycloak login

  * Perform authenticated requests

Keycloak parameters to use :

* issuer: 'https://sso.bitkap.africa'
* domain: 'https://sso.bitkap.africa'
* Realm: 'bitkap_dev'
* client id: 'angolar_test'
  
With user : email = test@bitkap.net , password = password  

### Tech stack

* Backend: Java 8+, Spring Boot, Spring Security, Spring Data JPA, Keycloak adapter, H2/PostgreSQL

* Frontend: Angular 12+

* Bonus (but not required): Docker to containerize each app

## Evaluation Criteria

* Code structure and clarity

* Fulfillment of requirements

* Proper use of Spring Boot and Angular best practices

* Authentication and authorization setup

* Pagination and RESTful design

* UI/UX quality and responsiveness

## Deliverables

* GitHub/Gitlab link to the source code
* A video demonstrating how your application works
