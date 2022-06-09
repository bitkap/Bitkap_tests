import 'package:flutter/material.dart';
import 'package:flutter_email_sender/flutter_email_sender.dart';
import 'package:path_provider/path_provider.dart';
import '../models/task.dart';

Future<void> sendTaskViaEmail(Task task) async {
  List<String> attachments = task.files == null ? []: task.files!.split(",");
  attachments.remove("");
  debugPrint("PATH >>>>>>>>>>>>>>>>>>>> $attachments");
  Email email = Email(
    subject: task.title,
    body: '${task.description!} \n\n Deadline: ${task.deadline} \n Links: ${task.links}',
    //attachmentPaths: ["/storage/emulated/sdcard/1/21c17f83e2b94f66bf2bd4297ec61a5f.jpg"]//attachments
  );
  await FlutterEmailSender.send(email);
}
