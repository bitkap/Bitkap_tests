import 'package:flutter/material.dart';
import 'package:todoflutter/helpers/database_helper.dart';
import 'package:todoflutter/models/Task.dart';

import 'package:intl/intl.dart' as Intl;
import 'package:todoflutter/screens/addTaskScreen.dart';

class TaskItem extends StatelessWidget {
  final Task task;
  final Function updateTasks;

  TaskItem({required this.task, required this.updateTasks});

  @override
  Widget build(BuildContext context) {

    Color priorityColor = Colors.grey;
    if (task.priority=='High') priorityColor = Colors.red;
    if (task.priority=='Medium') priorityColor = Colors.orange;
    if (task.priority=='Low') priorityColor = Colors.green;

    return Column(
      children: [
        ListTile(
          title: Text('${task.title}', style: TextStyle(color: Colors.white, fontSize: 20, decoration: task.status == 0 ? TextDecoration.none : TextDecoration.lineThrough)),
          subtitle: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text('${Intl.DateFormat.yMEd().add_jm().format(task.dateTask)}', style: TextStyle(color: Colors.grey, fontSize: 15, decoration: task.status == 0 ? TextDecoration.none : TextDecoration.lineThrough)),
              Text('${task.priority}', style: TextStyle(color: priorityColor, fontSize: 12)),
            ],
          ),
          leading: Checkbox(
            onChanged: (value) {
              task.status = value != null && value ? 1 : 0;
              DataBaseHelper.instance.updateTask(task);
              updateTasks();
            },
            value: task.status == 1 ? true : false,
            fillColor: MaterialStateProperty.all(Colors.green[600]),
          ),
          onTap: () {
            print("TASKKKKKKKKKKKKKKK ======+> ${task.dateTask}");
            Navigator.push(context, MaterialPageRoute(builder: (_) => AddTaskScreen(
              updateTaskList: updateTasks,
              task: task,
            )));
          },
        ),
        Divider(
          color: Colors.black,
        )
      ],
    );
  }
}
