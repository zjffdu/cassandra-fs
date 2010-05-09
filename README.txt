Cassandra-fs: S3 like file system implemented based on Cassandra

By zjffdu@gmail.com, hhhust@gmail.com, joyeer.xuqing@gmail.com

Project description
===================
Cassandra-fs is a S3 like distributed file system implemented based on Cassandra.
Cassandra is a distributed storage system with column-family data model. We build a file system data model upon Cassandra.

Requirements
============
  * Java >= 1.6 
  * Ant >=1.7
  
Getting started
===============

Install and Run Cassandra-fs in local
=====================================
* Install it on windows
1. open cmd, go to directory %CASSANDA_HOME%, execute command : "ant jar" to build the cassandra-fs.
2. similar to step 1 and execute the following command to run the cassandra in local: "bin\cassandra.bat" to start cassandra on windows
3. similar to step 1, execute the following command : "bin\cassandra-fs-cli.bat" to start cassandra-fs-cli, then you can play with a lot of file system-based commands, such as ls,mkdir and etc.

* Install it on linux
1. Execute the following command:
 	sudo mkdir -p /var/log/cassandr
	sudo chown -R `whoami` /var/log/cassandra
	sudo mkdir -p /var/lib/cassandra
	sudo chown -R `whoami` /var/lib/cassandra
2. open shell, go to directory $CASSANDA_HOME, execute command : "ant jar" to build the cassandra-fs.
3. similar to step 1 and execute the following command to run cassandra in local: "bin/cassandra" to start cassandra on linux
4. similar to step 1, execute the following command : "bin/cassandra-fs-cli" to start cassandra-fs-cli, then you can play with a lot of file system-based commands, such as ls,mkdir and etc. 

Commands Cassandra-fs supports:
==============================
* ls <folder> | <file>  // list detail information of the files under the <folder> or the specified <file>
* cd <folder> 			// change the current working directory to <folder>
* pwd 					// print the current working directory on cassandra-fs
* copyToLocal <source> <dest>	// copy file or folder from cassandra-fs to local
* copyFromLocal <source> <dest> // copy file or folder from local to cassandra-fs
* touch <file>	// create empty file 
* rm <file | folder>	// remove the file or folder on cassandra-fs, the folder must be an empty folder
* rmr <file | folder>	// force remove the folder or file no matter the folder is empty or not
* newfile <file> <content>	// make a new file on cassandra-fs with the contents of the second argument
* cat <file>...	// print the file content in console
* copyFromHDFS <source> <dest>	// copy file or folder from HDFS to cassandra-fs
* mkdir <folder> 	// make directory on cassandra-fs including the parent directory



