Multi-thread programming in Java: Marathon race runners
===========================

## Introduction
This repository was created to share my class project. 

##### User Story: 
A sports company has hired you to write an application that would simulate a
marathon race between interesting groups of participants. They could be slow as a tortoise, as
fast as a hare, and anything in between. The runners differ in their speed and how often they need
to rest. Some may be slow and never rest; others may run fast but lose steam quickly and rest a
lot of the time. Still others could be anything in between



## Try it out

Run Main.java.  
The program asks you what data source you would like to use for reading runners information as below.


```
1. Derby database
2. XML file
3. Text file
4. Default two runners
5. Exit
```

If you choose:  
1) Reads runners data from the embedded database.  
2) You need to specify xml file. Type runners.xml for using the test data.  
3) You need to specify text file. Type runners.txt for using the test data.  
4) Uses hard-coded test data.  
5) The program exits  


##### Example of outputs

```
Welcome to the Marathon Race Runner Program

Select your data source: 

1. Derby database
2. XML file
3. Text file
4. Default two runners
5. Exit

Enter your choice: 3
Enter text file name: runners.txt
Ready set...Go!

Tortoise: 10
Dog: 50
Hare: 100
Rabbit: 40
Dog: 100
Rabbit: 80
Tortoise: 20
Rabbit: 120
Tortoise: 30
Dog: 150

~ SNIP ~

Hare: 800
Tortoise: 260
Cat: 270
Rabbit: 880
Dog: 850
Rabbit: 920
Tortoise: 270
Tortoise: 280
Rabbit: 960
Tortoise: 290
Rabbit: 1000
Rabbit: I finished!

The race is over! Rabbit is the winner.

Dog: You beat me fair and square.
Tortoise: You beat me fair and square.
Hare: You beat me fair and square.
Cat: You beat me fair and square.

Press any key to continue...
```