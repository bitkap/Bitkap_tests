// @dart=2.9

class Task {
  int id;
  String title;
  String priority;
  DateTime dateTask;
  int status; // 0 = imcomplete, 1 = task càmplete

  Task({ this.title,  this.priority,  this.dateTask,  this.status});

  Task.withId({ this.id,  this.title,  this.priority,  this.dateTask,  this.status});

  Map<String, dynamic> toMap() {
    final map = Map<String, dynamic>();
    
    map['id'] = id;
    map['title'] = title;
    map['priority'] = priority;
    map['dateTask'] = dateTask.toIso8601String();
    map['status'] = status;

    return map;
  }

  factory Task.fromMap(Map<String, dynamic> map) {

    return Task.withId(
      id: map['id'],
      title: map['title'],
      priority: map['priority'],
      dateTask: DateTime.parse(map['dateTask']),
      status: map['status'],
    );
  }
}
