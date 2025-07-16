# Posts Management App

## Overview

You are required to build a mobile application that allows users to visualize, create and manage posts. A post consist of an id, a title, a body and a user (username). The application should implement secure user authentication via Keycloak and store all data locally on the device.

This project will serve as both a technical assessment and a real-world feature development task, evaluating your ability to integrate authentication, build responsive UI/UX, handle local data persistence, and implement authentication logic.

## Requirements

### Functional requirements

The app should have:

* A main list to visualize all posts
* A detail view for each post
* A form to create/edit a post

### Non functional requirements

* Security Requirements: use Keycloak as the OAuth2 Identity Provider to perform user authentication
* The application should be responsive
* Posts must be stored locally using a database like SQLite, Hive, or any local persistence solution of your choice

Note: Keycloak parameters to use :

* issuer: "https://sso.bitkap.africa"
* domain: "https://sso.bitkap.africa"
* Realm: "bitkap_dev"
* client_id: "angolar_test"

With user : email = test@bitkap.net , password = "password"

## Post Model

A Post should include the following:

* id: unique identifier
* title: short text
* date: time at which a post has been created
* body: main content
* username: post creator's username (must be the one for the currently logged in user)

## Evaluation Criteria

* Code structure and clarity
* Fulfillment of requirements
* Proper use of Flutter best practices
* Authentication setup
* UI/UX quality and responsiveness

## Deliverables

* GitHub/Gitlab link to the source code
* A video demonstrating how your application works
