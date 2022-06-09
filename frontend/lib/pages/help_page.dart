import 'package:flutter/material.dart';

class HelpPage extends StatelessWidget {

  List<String> _priorities = ['urgent', 'high', 'medium', 'low', 'tooLow'];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text('Help'),
        ),
      body: SingleChildScrollView(
        child: Container(
          padding: const EdgeInsets.all(16),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text('Statuses', style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),),
              SizedBox(height: 15,),

              Wrap(
                children: [
                  Icon(Icons.arrow_right, color: Colors.black,),
                  SizedBox(width: 15,),
                  Text('Ongoing: ', style: TextStyle(fontWeight: FontWeight.bold),),
                  Text('This status means that the task is not completed', maxLines: null,)
                ],
              ),
              SizedBox(height: 10,),
              Wrap(
                children: [
                  Icon(Icons.arrow_right, color: Colors.black,),
                  SizedBox(width: 15,),
                  Text('Done: ', style: TextStyle(fontWeight: FontWeight.bold, color: Colors.teal),),
                  Text('This status means that the task is completed', maxLines: null,)
                ],
              ),

              SizedBox(height: 30,),
              Text('Priorities', style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),),
              SizedBox(height: 15,),

              Row(
                children: [
                  const Icon(Icons.arrow_upward, color: Colors.green, size: 14,),
                  Text('  :   Urgent', style: TextStyle(fontSize: 14),)
                ],
              ),
              SizedBox(height: 10,),
              Row(
                children: [
                  const Icon(Icons.arrow_upward, color: Colors.lightGreenAccent, size: 14,),
                  Text(' :  High', style: TextStyle(fontSize: 14),)
                ],
              ),
              SizedBox(height: 10,),
              Row(
                children: [
                  const Icon(Icons.commit, color: Colors.black, size: 14,),
                  Text(' :  Medium', style: TextStyle(fontSize: 14),)
                ],
              ),
              SizedBox(height: 10,),
              Row(
                children: [
                  const Icon(Icons.arrow_downward, color: Colors.orangeAccent,size: 14,),
                  Text(' :  Low', style: TextStyle(fontSize: 14),)
                ],
              ),
              SizedBox(height: 10,),
              Row(
                children: [
                  const Icon(Icons.arrow_downward, color: Colors.red,size: 14),
                  Text(' :  Too low', style: TextStyle(fontSize: 14),)
                ],
              ),

            ],
          ),
        )
      ),
    );
  }


}