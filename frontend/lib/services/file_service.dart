import 'dart:io';

import 'package:file_picker/file_picker.dart';
import 'package:filesystem_picker/filesystem_picker.dart';
import 'package:flutter/material.dart';
import 'package:permission_handler/permission_handler.dart';

Future<String> importFile(BuildContext context) async {
  String? path = await FilesystemPicker.open(
    title: 'Open file',
    context: context,
    rootDirectory: Directory("/storage"),
    fsType: FilesystemType.file,
    folderIconColor: Colors.teal,
    //allowedExtensions: [],
    fileTileSelectMode: FileTileSelectMode.wholeTile,
  );

  return path == null ? '' : path;
}

Future<String> pickFile() async {
  String path = '';
  /*FilePickerResult? result = await FilePicker.platform.pickFiles(
    type: FileType.custom,
    allowedExtensions: ['jpg', 'pdf', 'doc'],
  );*/

  FilePickerResult? result = await FilePicker.platform.pickFiles();
  debugPrint("RESULT PIC FILE >>>>>>>>>>>>>>>>>$result");
  if(result?.paths != null && result!.paths.isNotEmpty)
      path =  result.paths.join(',');

  return path;
}