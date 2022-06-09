import 'package:flutter/material.dart';
import 'package:frontend/pages/task_add_page.dart';
import 'package:frontend/models/task_priorities.dart';
import 'package:frontend/models/task_statuses.dart';
import 'package:frontend/services/db_services.dart';
import 'package:frontend/services/email_send.dart';
import 'package:frontend/services/file_service.dart';
import 'package:path/path.dart';

import '../models/task.dart';
import 'help_page.dart';

class TaskHome extends StatefulWidget {
  const TaskHome({Key? key}) : super(key: key);

  @override
  State<StatefulWidget> createState() => _TaskHomeState();

}

class _TaskHomeState extends State<TaskHome> {

  @override
  void initState() {
  }

  @override
  Widget build(BuildContext context) {

    return Scaffold(
      appBar: AppBar(
        title: const Text('Task Manager'),
        actions: [
          IconButton(onPressed: () {
            Navigator.push(context, MaterialPageRoute(builder: (context) {
              return HelpPage();
            }));

          }, icon: const Icon(Icons.question_mark, color: Colors.white,))
        ],
      ),
      body: FutureBuilder<List<Task>>(
        future: getTasks(),
        builder: (BuildContext context, AsyncSnapshot<List<Task>> snapshot) {
          Widget view = Container();

          if(snapshot.hasData) {
            view = SingleChildScrollView(
              child: Container(
                padding: EdgeInsets.all(16),
                child: Column(
                  children:
                     _displayListElement(snapshot.data!, context)
                ),
              )
            );
          }
          else if(snapshot.hasError) {

            view = Container(
              child: const Center(child: Text("Something went wrong", style: TextStyle(color: Colors.red),),),
            );
          }
          else {
            view = Container(
              child: Center(
                child: Text("No task saved yet!"),
              ),
            );
          }

          return view;
        },
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          Navigator.push(context, MaterialPageRoute(builder: (context) {
            return TaskPage();
          }));
        },
        tooltip: 'Add a task',
        child: const Icon(Icons.add),
      ),
    );
  }

  List<ListTile> _displayListElement(List<Task> tasks, BuildContext context) {
    if(tasks == null) {
      return <ListTile>[];
    }

    return tasks.map((task) {
      bool isDeadlineCrossed = false;
      TextStyle deadlineStyle;

      if(task.deadline != null && task.deadline!.isNotEmpty) {
        isDeadlineCrossed = checkDeadline(task.deadline);
      }
      deadlineStyle = isDeadlineCrossed ? const TextStyle(fontSize: 10,color: Colors.red):
                                          const TextStyle(fontSize: 10,color: Colors.grey);

      return ListTile(
              title: Text(task.title,overflow: TextOverflow.ellipsis,),
              subtitle: Row(
                  children: [
                    Text("Deadline: ${task.deadline}   ",
                        style: deadlineStyle),
                    Text("Status: ${task.getStateName()}",
                        style: TextStyle(fontSize: 10, color: _getTaskColor(task.state), fontWeight: FontWeight.bold) )
                  ],
              ),
              leading: _getPriority(task.priority),
              onTap: () {
                Navigator.push(context, MaterialPageRoute(builder: (context) =>
                   TaskPage(task: task)
                ));
              },
              trailing: IconButton(
                icon: const Icon(Icons.email, color: Colors.cyan,),
                onPressed: () {
                  sendTaskViaEmail(task).then((value) {

                  });
                },),

      );
    }).toList();
  }

  Color _getTaskColor(TaskStatus status) {
    Color c = Colors.black;
    switch(status) {
      case TaskStatus.done: c = Colors.teal; break;
      case TaskStatus.ongoing: c = Colors.black; break;
      default: c; break;
    }

    return c;
  }

  Icon _getPriority(TaskPriorities priority) {
    Icon i;
    const double size = 14;
    switch(priority) {
      case TaskPriorities.urgent: i = const Icon(Icons.arrow_upward, color: Colors.green, size: size,);
        break;
      case TaskPriorities.high: i = const Icon(Icons.arrow_upward, color: Colors.lightGreenAccent, size: size,);
        break;
      case TaskPriorities.medium: i = const Icon(Icons.commit, color: Colors.black, size: size,);
        break;
      case TaskPriorities.low: i = const Icon(Icons.arrow_downward, color: Colors.orangeAccent,size: size,);
        break;
      case TaskPriorities.tooLow: i = const Icon(Icons.arrow_downward, color: Colors.red,size: size,);
        break;
      default: i = const Icon(Icons.commit, color: Colors.black, size: size,);
        break;
    }

    return i;
  }

  bool checkDeadline(String? deadline) {
    bool isDeadlineCrossed = false;
    if(deadline == null) {
      return isDeadlineCrossed;
    }
    List<String> split = deadline.split("/");
    /*String dateTimedDeadlineString = '${split[2]}-${split[1]}-${split[0]} 00:00:00.000';
    DateTime dateTimedDeadline = DateTime.parse(dateTimedDeadlineString);*/

    DateTime dateTimedDeadline = DateTime.utc(int.parse(split[2]), int.parse(split[1]), int.parse(split[0]));
    DateTime now = DateTime.now();

    if(now.isAfter(dateTimedDeadline)) {
      isDeadlineCrossed = true;
    }

    return isDeadlineCrossed;

  }

}