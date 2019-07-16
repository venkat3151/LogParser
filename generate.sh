#!/bin/sh
#This is the generator script
#Creates output directory

#Check if command line is empty
if [ ! -z "$1" ]&& [ "$1" != " " ];then

#Create directory specified by the user
mkdir $1
echo "Compiling Source code"

#Compile the source code
javac Generate.java
javac GenerateRandomLogs.java
echo "Compilation Successful"

#Execute the code and send directory as argument to Java File 
java Generate $1
else
echo "Enter Valid Command with a DATA_PATH"
fi
