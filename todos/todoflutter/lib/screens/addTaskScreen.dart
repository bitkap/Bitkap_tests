// @dart=2.9

import 'package:flutter/material.dart';
import 'package:intl/intl.dart' as Intl;
import 'package:todoflutter/helpers/database_helper.dart';
import 'package:todoflutter/models/Task.dart';

class AddTaskScreen extends StatefulWidget {
  final Function updateTaskList;
  final Task task;

  const AddTaskScreen({this.updateTaskList, this.task});

  @override
  _AddTaskScreenState createState() => _AddTaskScreenState();
}

class _AddTaskScreenState extends State<AddTaskScreen> {
  final _formKey = GlobalKey<FormState>();
  final List<String> priorities = ['Low', 'Medium', 'High'];

  String title = '';
  String priority;
  DateTime dateTask = DateTime.now();

  TextEditingController _dateController = TextEditingController();

  @override
  void initState() {
    super.initState();

    if (widget.task != null) {
      title = widget.task.title;
      priority = widget.task.priority;
      dateTask = widget.task.dateTask;
      _dateController.text = Intl.DateFormat.yMEd().add_jm().format(dateTask);
    }
  }

  _handleDatePicker() async {
    final DateTime date = await showDatePicker(
      context: context,
      initialDate: dateTask,
      firstDate: DateTime(2000),
      lastDate: DateTime(2100),
    );

    if (date != null && date != dateTask) {
      setState(() {
        dateTask = date;
      });
      _dateController.text = Intl.DateFormat.yMEd().add_jm().format(dateTask);
    }
  }

  _submit() {
    print('title: $title, date: $dateTask: priority: $priority');

    if (_formKey.currentState.validate()) {
      _formKey.currentState.save();

      final Task task =
          Task(title: title, dateTask: dateTask, priority: priority);

      if (widget.task != null) {
        task.id = widget.task.id;
        task.status = widget.task.status;
        DataBaseHelper.instance.updateTask(task);
      } else {
        task.status = 0;
        DataBaseHelper.instance.insertTask(task);
      }

      widget.updateTaskList();
      Navigator.pop(context);
    }
  }

  _deleteTask() {

    DataBaseHelper.instance.deleteTask(widget.task.id);

    widget.updateTaskList();
    Navigator.pop(context);
  }

  @override
  void dispose() {
    _dateController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.grey[850],
      appBar: AppBar(
        title: Text('Add Task'),
        backgroundColor: Colors.grey[900],
        foregroundColor: Colors.white,
        elevation: 0,
      ),
      body: Padding(
        padding: EdgeInsets.fromLTRB(20, 20, 20, 0),
        child: Column(
          children: [
            Form(
                key: _formKey,
                child: Column(
                  children: [
                    TextFormField(
                      style: TextStyle(fontSize: 20, color: Colors.white),
                      decoration: InputDecoration(
                          labelText: 'Title',
                          labelStyle:
                              TextStyle(fontSize: 18, color: Colors.white),
                          border: OutlineInputBorder(
                            borderRadius: BorderRadius.circular(10),
                          )),
                      validator: (input) =>
                          input.trim().isEmpty ? 'Please enter a title' : null,
                      onSaved: (input) => title = input.toString(),
                      initialValue: title,
                    ),
                    SizedBox(height: 30),
                    TextFormField(
                      style: TextStyle(fontSize: 20, color: Colors.white),
                      controller: _dateController,
                      onTap: _handleDatePicker,
                      decoration: InputDecoration(
                          labelText: 'Date',
                          labelStyle:
                              TextStyle(fontSize: 18, color: Colors.white),
                          border: OutlineInputBorder(
                            borderRadius: BorderRadius.circular(10),
                          )),
                      validator: (input) => input.trim().isEmpty
                          ? 'Please enter a due date'
                          : null,
                          //initialValue: Intl.DateFormat.yMEd().add_jm().format(dateTask),
                    ),
                    SizedBox(height: 30),
                    DropdownButtonFormField(
                      isDense: true,
                      icon: Icon(Icons.arrow_drop_down_circle),
                      iconSize: 22,
                      iconEnabledColor: Colors.green,
                      items: priorities.map((String val) {
                        return DropdownMenuItem(
                          value: val,
                          child: Text(
                            val,
                            style: TextStyle(color: Colors.white),
                          ),
                        );
                      }).toList(),
                      style: TextStyle(fontSize: 20, color: Colors.white),
                      dropdownColor: Colors.grey[800],
                      decoration: InputDecoration(
                          labelText: 'Priority',
                          labelStyle:
                              TextStyle(fontSize: 18, color: Colors.white),
                          border: OutlineInputBorder(
                            borderRadius: BorderRadius.circular(10),
                          )),
                      validator: (input) => priority == null
                          ? 'Please enter a priority level'
                          : null,
                      onChanged: (value) {
                        setState(() {
                          priority = value.toString();
                        });
                      },
                      value: priority,
                    ),
                    SizedBox(height: 30),
                    Container(
                      decoration: BoxDecoration(
                          color: Colors.green[600],
                          borderRadius: BorderRadius.circular(30)),
                      width: double.infinity,
                      height: 60,
                      child: TextButton(
                        child: Text(
                          widget.task != null ? 'Update Task' : 'Add Task',
                          style: TextStyle(
                              fontWeight: FontWeight.bold,
                              fontSize: 20,
                              color: Colors.white),
                        ),
                        onPressed: _submit,
                      ),
                    ),
                    widget.task != null ? Container(
                      margin: EdgeInsets.fromLTRB(0, 20, 0, 0),
                      decoration: BoxDecoration(
                          color: Colors.red[800],
                          borderRadius: BorderRadius.circular(30)),
                      width: double.infinity,
                      height: 60,
                      child: TextButton(
                        child: Text(
                          'Delete Task',
                          style: TextStyle(
                              fontWeight: FontWeight.bold,
                              fontSize: 20,
                              color: Colors.white),
                        ),
                        onPressed: _deleteTask,
                      ),
                    ) : Container()
                  ],
                ))
          ],
        ),
      ),
    );
  }
}
