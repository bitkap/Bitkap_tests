# Specifications

The goal of the exercise is to make a REST API for event management. It must be possible to add, modify, delete and view events (in list form and individually). I can manage comments for an event (creation, modification, deletion, visualization). For the return of the list of events, each event must contain its list of comments. The list of events must allow pagination. The returns must be of JSON type.

An event consists of a title of 100 characters maximum, a description and a person involved in the event. A comment consists of a description and a date and must be linked to an event.
