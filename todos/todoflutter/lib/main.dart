import 'package:flutter/material.dart';
// ignore: import_of_legacy_library_into_null_safe
import 'package:todoflutter/screens/todoListScreen.dart';

/* import 'package:workmanager/workmanager.dart';
import 'helpers/local_notification.dart'; */


/* void callbackDispatcher() {
  Workmanager().executeTask((taskName, inputData) async {
    //show the notification
    
    LocalNotification.Initializer();
    LocalNotification.ShowOneTimeNotification(DateTime.now());
    return Future.value(true);
  });
} */

void main() async {
  /* WidgetsFlutterBinding.ensureInitialized();
  await Workmanager().initialize(callbackDispatcher);
  await Workmanager().registerPeriodicTask("test_workertask", "test_workertask",
      inputData: {"data1": "value1", "data2": "value2"},
      frequency: Duration(minutes: 1),
      initialDelay: Duration(seconds: 10)); */

   runApp(MaterialApp(
      //debugShowCheckedModeBanner: false,
      home: TodoListScreen(),
    ));}
