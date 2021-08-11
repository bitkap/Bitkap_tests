// @dart=2.9

import 'package:flutter/material.dart';
import 'package:todoflutter/components/taskItem.dart';
import 'package:todoflutter/helpers/database_helper.dart';
import 'package:todoflutter/models/Task.dart';
import 'package:todoflutter/screens/addTaskScreen.dart';

class TodoListScreen extends StatefulWidget {

  @override
  _TodoListScreenState createState() => _TodoListScreenState();
}

class _TodoListScreenState extends State<TodoListScreen> {
  Future<List<Task>> _taskList;

  @override
  void initState() {
    super.initState();
    _updateTaskList();
  }

  _updateTaskList() {
    setState(() {
      _taskList = DataBaseHelper.instance.getTaskList();
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: Colors.grey[850],
        appBar: AppBar(
          title: Text('TODO FIRE 🔥'),
          backgroundColor: Colors.grey[900],
          foregroundColor: Colors.white,
          elevation: 0,
        ),
        floatingActionButton: FloatingActionButton(
          onPressed: () {
            Navigator.push(
                context, MaterialPageRoute(builder: (_) => AddTaskScreen(updateTaskList: _updateTaskList)));
          },
          child: Icon(Icons.add),
          backgroundColor: Colors.grey[900],
          elevation: 6,
        ),
        body: FutureBuilder(
            future: _taskList,
            builder: (context, snapShot) {
              if (!snapShot.hasData) {
                print('eeeexxxx ${snapShot.data}');
                return Center(child: CircularProgressIndicator());
              }

              print('rrrrr ${snapShot.data}');

              final countTaskCompleted = snapShot.data.where((task) => task.status == 1).length;

              return Padding(
                padding: EdgeInsets.fromLTRB(20, 20, 20, 0),
                child: ListView.builder(
                    itemCount:
                        snapShot.data == null ? 1 : 1 + snapShot.data.length,
                    itemBuilder: (BuildContext buildContex, index) {
                      if (index == 0) {
                        print('bassta');
                        return Column(
                          crossAxisAlignment: CrossAxisAlignment.center,
                          children: [
                            Text(
                              'My Tasks',
                              style: TextStyle(
                                  fontSize: 25,
                                  color: Colors.white,
                                  fontWeight: FontWeight.bold),
                            ),
                            SizedBox(height: 10),
                            Text(
                              '$countTaskCompleted of ${snapShot.data.length}',
                              style: TextStyle(
                                  fontSize: 18, color: Colors.grey[300]),
                            ),
                            SizedBox(height: 20),
                          ],
                        );
                      }
                      return TaskItem(
                          task: snapShot.data != null ? snapShot.data[index - 1] : null,
                          updateTasks: _updateTaskList);
                    }),
              );
            }));
  }
}
