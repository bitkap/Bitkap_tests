# todoflutter

A new Flutter project.

## Getting Started

This project is a starting point for a Flutter application.

A few resources to get you started if this is your first Flutter project:

- [Lab: Write your first Flutter app](https://flutter.dev/docs/get-started/codelab)
- [Cookbook: Useful Flutter samples](https://flutter.dev/docs/cookbook)

For help getting started with Flutter, view our
[online documentation](https://flutter.dev/docs), which offers tutorials,
samples, guidance on mobile development, and a full API reference.

## NB

- Le projet utilise l'annotation **@dart=2.9** au debut de certains fichier afin d'eviter le **null-safety**
- L'apk release se trouve dans le dossier **todo** et se nomme **todo-fire-app.apk**
- Le projet se build avec la commande
```
flutter build apk --release --no-sound-null-safety
```

## Somes Issues

- Quelques difficulté avec le workmanager pour le lancement des background tasks
    afin de checker le temps restant sur les taches de la TODO et notifier l'utilisateur


## Amelioration à faire

- Maitre en place le workmanager de facon à ce qu'il fonctionne correctement
- Ajouter un nouveau champ **time** le model Task du todo afin de pouvoir sauvegarder le temps en heure de la task