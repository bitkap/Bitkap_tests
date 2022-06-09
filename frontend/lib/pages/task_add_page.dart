import 'dart:math';

import 'package:flutter/material.dart';
import 'package:frontend/models/task.dart';
import 'package:frontend/models/task_priorities.dart';
import 'package:frontend/models/task_statuses.dart';
import 'package:frontend/services/db_services.dart';
import 'package:frontend/services/file_service.dart';
import 'package:intl/intl.dart';

import 'task_home.dart';

Task? rTask;

class TaskPage extends StatefulWidget {

  Task? task;
  TaskPage({Key? key, task}) : super(key: key) {
    rTask = task;
  }

  @override
  State<StatefulWidget> createState() => TaskPageState();

}

class TaskPageState extends State<TaskPage> {

  final _formKey = GlobalKey<FormState>();
  TextStyle textStyle = const TextStyle();
  String title = '';
  String links = '';
  String description = '';
  String? priority = 'medium';
  TaskPriorities? taskPriority;
  DateTime _date = DateTime.now();
  String _deadline = "";
  String _files = '';
  final TextEditingController _titleController = TextEditingController();
  final TextEditingController _descController = TextEditingController();
  final TextEditingController _linksController = TextEditingController();


  @override
  void initState() {
    if(rTask != null) {
      if(rTask!.title != null) _titleController.text = rTask!.title;
      if(rTask!.description != null) _descController.text = rTask!.description!;
      if(rTask!.links != null) _linksController.text = rTask!.links!;
      if(rTask!.deadline != null) _deadline = rTask!.deadline!;
      if(rTask!.priority != null) priority = rTask?.getPriorityName();
      if(rTask!.files != null) _files = rTask!.files!;
    }

  }

  Future<Null> selectDate(BuildContext context) async {
    final dateFormatter = DateFormat('dd/MM/yyyy');
    final DateTime? picked = await showDatePicker(
      context: context,
      initialDate: this._date,
      firstDate: DateTime(1970),
      lastDate: DateTime(2200),
    );

    if (picked != null && picked != this._date) {
      setState(() {
        this._date = picked;
        this._deadline = dateFormatter.format(this._date);
      });
    }
  }


  @override
  Widget build(BuildContext context) {

    return Scaffold(
        appBar: AppBar(
        title: const Text('Task Manager'),
        actions: [
          if(rTask != null && rTask?.state != TaskStatus.done)
            IconButton(onPressed: () {
              showDialog(
                  context: context,
                  builder: (BuildContext context) {
                    return AlertDialog(
                      title: const Text("Is this task finished?"),
                      actions: [
                        TextButton(
                          child: const Text('No', style: TextStyle(color: Colors.grey),),
                          onPressed: () {Navigator.of(context).pop();},
                        ),
                        TextButton(
                            onPressed: () {
                              rTask!.state = TaskStatus.done;
                              updateTask(rTask!);
                              ScaffoldMessenger.of(context).showSnackBar(
                                const SnackBar(content: Text('Congratulation! You realized this task!', style: TextStyle(color: Colors.blue),)),
                              );
                              Navigator.pop(context);
                              Navigator.push(context, MaterialPageRoute(builder: (context) {
                                return TaskHome();
                              }));
                            },
                            child: const Text("Yes", style: TextStyle(color: Colors.green),))
                      ],
                    );
                  }
              );
          }, icon: const Icon(Icons.check, color: Colors.white,)),
          IconButton(onPressed: () {
            showDialog(
                context: context,
                builder: (BuildContext context) {
                  return AlertDialog(
                    title: const Text("Do you want to delete this task?"),
                    actions: [
                      TextButton(
                        child: const Text('No'),
                        onPressed: () {Navigator.of(context).pop();},
                      ),
                      TextButton(
                          onPressed: () {
                          deleteTask(rTask!.id).then((value) {
                            ScaffoldMessenger.of(context).showSnackBar(
                              const SnackBar(content: Text('Task deleted!', style: TextStyle(color: Colors.red),)),
                            );
                            Navigator.pop(context);
                            Navigator.push(context, MaterialPageRoute(builder: (context) {
                              return TaskHome();
                            }));
                          });

                        },
                          child: Text("Yes", style: TextStyle(color: Colors.red),))
                    ],
                  );
                }
            );
          }, icon: const Icon(Icons.delete, color: Colors.red,))
        ],
      ),
      body: SingleChildScrollView(
        child: Container(
          padding: const EdgeInsets.all(16),
          child: Builder(
            builder: (context) => Form(
              key: _formKey,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  const SizedBox(height: 20,),
                  Row(mainAxisAlignment: MainAxisAlignment.start, children: [Text("Title", style: textStyle,)],),
                  TextFormField(
                    decoration: const InputDecoration(filled: true),
                    validator: (value) {
                      if (value == null || value.isEmpty) {
                        return 'Please enter the title';
                      }
                      return null;
                    },
                    onSaved: (val) => setState(() {
                      title = val!;
                    }),
                    controller: _titleController,
                    keyboardType: TextInputType.multiline,
                    maxLines: null,

                  ),
                  const SizedBox(height: 15,),

                  Row(mainAxisAlignment: MainAxisAlignment.start, children: [Text("Description", style: textStyle)],),
                  TextFormField(
                    decoration: const InputDecoration(filled: true),
                    onSaved: (val) => setState(() {
                      description = val!;
                    }),
                    controller: _descController,
                    keyboardType: TextInputType.multiline,
                    maxLines: null,
                  ),

                  const SizedBox(height: 15,),
                  Row(mainAxisAlignment: MainAxisAlignment.start, children: [Text("Priority", style: textStyle)],),
                  Row(
                      mainAxisAlignment: MainAxisAlignment.start,
                      children: [
                        const SizedBox(width: 20,),
                        DropdownButton<String>(
                          value: priority,
                          icon: const Icon(Icons.arrow_downward),
                          elevation: 16,
                          style: const TextStyle(color: Colors.black),
                          underline: Container(
                            height: 2,
                            color: Colors.deepPurpleAccent,
                          ),
                          onChanged: (String? newValue) {
                            setState(() {
                              priority = newValue!;
                              switch(priority) {
                                case 'urgent': taskPriority = TaskPriorities.urgent; break;
                                case 'high': taskPriority = TaskPriorities.high; break;
                                case 'medium': taskPriority = TaskPriorities.medium; break;
                                case 'low': taskPriority = TaskPriorities.low; break;
                                case 'tooLow': taskPriority = TaskPriorities.tooLow; break;
                              }
                            });
                          },
                          items: <String>['urgent', 'high', 'medium', 'low', 'tooLow']
                              .map<DropdownMenuItem<String>>((String value) {
                            return DropdownMenuItem<String>(
                              value: value,
                              child: Text(value),
                            );
                          }).toList(),
                        ),
                      ],
                  ),
                  const SizedBox(height: 15,),

                  Row(mainAxisAlignment: MainAxisAlignment.start, children: [Text("DeadLine", style: textStyle,),]),
                  Row(
                    children: <Widget>[
                      IconButton(
                        icon: const Icon(
                          Icons.calendar_today,
                          color: Colors.blue,
                        ),
                        onPressed: () {
                          selectDate(context);
                        },
                      ),
                      Text(_deadline != null ? _deadline : ''),
                    ],
                  ),
                  const SizedBox(height: 15,),

                  Text("Links", style: textStyle,),
                  TextFormField(
                    decoration: const InputDecoration(filled: true),
                    controller: _linksController,
                    keyboardType: TextInputType.multiline,
                    maxLines: null,
                  ),
                  const SizedBox(height: 15,),

                  TextButton(onPressed: () {
                    importFile(context).then((value) {
                      setState(() {
                        if(value != null && value.isNotEmpty)
                          _files += '$value,';
                      });
                    });
                  }, child: Row(
                    children: [
                      Icon(Icons.link_outlined,),
                      Text("  Files", style: TextStyle(fontSize: 16),)
                    ],
                  )),
                  const SizedBox(height: 10,),
                  Wrap(
                    children: [
                      Text(_files, style: TextStyle(fontSize: 9),)
                    ],
                  ),

                  const SizedBox(height: 18,),
                  ElevatedButton(onPressed: () {
                    if (_formKey.currentState!.validate()) {

                      if(rTask != null) {
                        if(title.isNotEmpty) rTask!.title = _titleController.text;
                        if(_descController.text.isNotEmpty) rTask!.description = _descController.text;
                        if(rTask!.priority != taskPriority && taskPriority != null) rTask?.priority = taskPriority!;
                        if(_deadline.isNotEmpty) rTask!.deadline = _deadline;
                        if(_linksController.text.isNotEmpty) rTask!.links = _linksController.text;
                        rTask!.files = _files;

                        ScaffoldMessenger.of(context).showSnackBar(
                          const SnackBar(content: Text('Processing Data')),
                        );

                        updateTask(rTask!);

                        ScaffoldMessenger.of(context).showSnackBar(
                          const SnackBar(content: Text('Task updated successfully!', style: TextStyle(color: Colors.green),)),
                        );

                        Navigator.push(context, MaterialPageRoute(builder: (context) {
                          return TaskHome();
                        }));

                      }
                      else {
                          taskPriority = Task.getPriorityFrom(priority!);
                          Task task = Task.uname(null, _titleController.text,
                              _descController.text, TaskStatus.ongoing,
                              _deadline, taskPriority!, _linksController.text, _files
                              );
                          debugPrint("TASK TO BE INSERTED >>>>>>>>> $task");
                          insertTask(task).then((value) {
                            ScaffoldMessenger.of(context).showSnackBar(
                              const SnackBar(content: Text('Processing Data')),
                            );

                            ScaffoldMessenger.of(context).showSnackBar(
                              const SnackBar(content: Text('Task saved successfully!', style: TextStyle(color: Colors.green),)),
                            );

                            Navigator.push(context, MaterialPageRoute(builder: (context) {
                              return TaskHome();
                            }));
                          });
                          /**/

                      }

                    }
                  },
                      child: const Text('Save'),)

                ],
              ),
            )
          )
        ),
      )
    );
  }

}