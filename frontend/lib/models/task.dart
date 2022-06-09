import 'package:flutter/material.dart';
import 'package:frontend/models/task_priorities.dart';
import 'package:frontend/models/task_statuses.dart';

class Task {
  int? id;
  String title;
  String? description;
  TaskStatus state;
  String? deadline;
  TaskPriorities priority;
  String? links;
  String? files;

  Task({this.id, required this.title,  this.description, required this.state, this.deadline,
  required this.priority, this.links, this.files});

  Task.uname(this.id, this.title, this.description, this.state, this.deadline,
      this.priority, [this.links, this.files]);

  factory Task.fromJson(Map<String, dynamic> json) {
    TaskStatus status = TaskStatus.ongoing;
    TaskPriorities prioritiy = TaskPriorities.medium;
    switch(json['state']) {
      case "done": status = TaskStatus.done ;break;
      case "ongoing": status = TaskStatus.ongoing; break;
      default: status ; break;
    }
    switch(json['priority']) {
      case 'urgent': prioritiy = TaskPriorities.urgent; break;
      case 'high': prioritiy = TaskPriorities.high; break;
      case 'medium': prioritiy = TaskPriorities.medium; break;
      case 'low': prioritiy = TaskPriorities.low; break;
      case 'tooLow': prioritiy = TaskPriorities.tooLow; break;
      default: {}
        break;
    }

    return Task(id: json['id'],
                title: json['title'],
                description: json['description'],
                state: status,
                deadline: json['deadline'],
                priority: prioritiy,
                links: json['links'],
                files: json['files']);
  }

  Map<String, dynamic> toMap() {
    return {
      'id': id,
      'title': title,
      'description': description,
      'state': getStateName(),
      'deadline': deadline,
      'priority': getPriorityName(),
      'links': links,
      'files': files
    };
  }


  String getStateName() {
    String name = "";
    switch(state) {
      case TaskStatus.done: name = "done"; break;
      case TaskStatus.ongoing: name = "ongoing"; break;
      default: name; break;
    }
    return name;
  }

  static TaskStatus getStateFromString(String s) {
    TaskStatus status = TaskStatus.ongoing;
    switch(s) {
      case "done":
        status = TaskStatus.done ;
        break;
      case "ongoing":
        status = TaskStatus.ongoing;
        break;
      default:
        status ;
        break;
    }
    return status;
  }

  String getPriorityName() {
    String name;

    switch(priority) {
      case TaskPriorities.urgent: name = 'urgent'; break;
      case TaskPriorities.high: name = 'high'; break;
      case TaskPriorities.medium: name = 'medium'; break;
      case TaskPriorities.low: name = 'low'; break;
      case TaskPriorities.tooLow: name = 'tooLow'; break;
      default: name = ''; break;
    }
    return name;
  }

  static TaskPriorities getPriorityFrom(String s) {
    TaskPriorities p = TaskPriorities.medium;

    switch(s) {
      case 'urgent': p = TaskPriorities.urgent; break;
      case 'high': p = TaskPriorities.high; break;
      case 'medium': p = TaskPriorities.medium; break;
      case 'low': p = TaskPriorities.low; break;
      case 'tooLow': p = TaskPriorities.tooLow; break;
      default: p; break;
    }
    return p;
  }

  @override
  String toString() {
    return 'Task(id: $id, title: $title, description: $description, state: $state, priority: $priority, '
        'deadline: $deadline, links: $links, files: $files)';
  }
}