import 'dart:io';

import 'package:path_provider/path_provider.dart';
import 'package:sqflite/sqflite.dart';
import 'package:sqflite/sqlite_api.dart';
import 'package:todoflutter/models/Task.dart';

class DataBaseHelper {
  static DataBaseHelper instance = DataBaseHelper._instance();
  // ignore: non_constant_identifier_names
  static dynamic db_sqflite;

  DataBaseHelper._instance();

  String taskTable = 'task_table';

  String colId = 'id';
  String colTitle = 'title';
  String colPriority = 'priority';
  String colDateTask = 'dateTask';
  String colStatus = 'status';

  Future<Database> get db async {
    if (db_sqflite == null) {
      db_sqflite = await _initDb();
    }

print('db_sqflite = $db_sqflite');
    return db_sqflite;
  }

  Future<Database> _initDb() async {
    Directory dir = await getApplicationDocumentsDirectory();
    String path = dir.path + 'todo_list.db';
    print('todoListDB = $path');
    final Database todoListDB =
        await openDatabase(path, version: 1, onCreate: _createDB);

        

    return todoListDB;
  }

  void _createDB(Database db, version) async {
    await db.execute(
        'CREATE TABLE $taskTable ($colId INTEGER PRIMARY KEY AUTOINCREMENT,  $colTitle TEXT, $colDateTask TEXT, $colPriority TEXT, $colStatus INTEGER)');
  print('creation de la table');
  }

  Future<List<Map<String, dynamic>>> getMapList() async {
    Database db = await this.db;
    final List<Map<String, dynamic>> result = await db.query(taskTable);
    print('map list ===========================================> $result');
    return result;
  }

  Future<List<Task>> getTaskList() async {
    List<Map<String, dynamic>> taskMapList = await getMapList();
    List<Task> taskList = [];
    taskMapList.forEach((taskMap) {
      taskList.add(Task.fromMap(taskMap));
    });
    taskList.sort((taskA, taskB) => taskA.dateTask.compareTo(taskB.dateTask));
    return taskList;
  }

  Future<int> insertTask(Task task) async {
    print('task to insert $task');
    Database db = await this.db;
    final int result = await db.insert(taskTable, task.toMap());
    print('result of insert $result');
    return result;
  }
 
  Future<int> updateTask(Task task) async {
    Database db = await this.db;
    final int result = await db.update(taskTable, task.toMap(),
        where: '$colId = ?', whereArgs: [task.id]);
    return result;
  }

  Future<int> deleteTask(int id) async {
    Database db = await this.db;
    final int result =
        await db.delete(taskTable, where: '$colId = ?', whereArgs: [id]);
    return result;
  }
}
