import 'package:frontend/models/task.dart';
import 'package:path/path.dart';
import 'package:sqflite/sqflite.dart';
import 'dart:async';
import 'package:flutter/widgets.dart';

 Future<Database> getDb() async {

  return  openDatabase(
    join(await getDatabasesPath(), 'tasks.db'),
    onCreate: (db, version) {
      // Run the CREATE TABLE statement on the database.
      return db.execute(
      'CREATE TABLE IF NOT EXISTS task(id INTEGER PRIMARY KEY,'
      'title TEXT, '
      'description TEXT, '
      'state VARCHAR(20), '
      'deadline VARCHAR(30), '
      'priority VARCHAR(20), '
      'links TEXT, '
      'files TEXT'
      ')',
      );
    },
    version: 1,
);
}

Future<void> insertTask(Task task) async {
  final db = await getDb();
  await db.insert(
    'task',
    task.toMap(),
    conflictAlgorithm: ConflictAlgorithm.replace,
  );

}

Future<List<Task>> getTasks() async {
  // Get a reference to the database.
  final db = await getDb();

  final List<Map<String, dynamic>> maps = await db.query('task');

  return List<Task>.generate(maps.length, (i) {
    return Task.fromJson(maps[i]);
  });
}

Future<void> updateTask(Task task) async {

  final db = await getDb();
  await db.update(
    'task',
    task.toMap(),
    where: 'id = ?',
    whereArgs: [task.id],
  );
}

Future<void> deleteTask(int? id) async {
  // Get a reference to the database.
  final db = await getDb();

  await db.delete(
    'task',
    where: 'id = ?',
    whereArgs: [id],
  );
}

