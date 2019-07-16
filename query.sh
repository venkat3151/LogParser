#!/bin/bash

#This is the query script. Used to Query CPU usage for particular time ranges.
#Any invalid data or commands are handled and Proper usage are displayed on screen.

#Validation for empty directory.If yes, prompt user to provide correct DATA PATH
if [ -z "$1" ] || [ "$1" == " " ];
then
	echo "Data Path is Required after the ./query.sh"
	exit
fi

cd $1 || exit
cd - 

#Valid commands
command1="QUERY"
command2="EXIT"


#Compiling Java Code
javac Query.java
javac QueryHandler.java

echo "Compilation Successful"

#while loop flag to continuously take user commands
flag=1
while [ $flag -eq 1 ]
do 
	echo -n ">"
	read query
	
	#Split and store the fields given in query to respective variables
	IFS=' ' read command ip cpu_id s_date s_time d_date d_time <<< "$query"


        if [ "$command" == "$command2" ];
        then
            flag=0
        elif [ "$command" == "$command1" ];
        then
	 java Query $1 $command $ip $cpu_id $s_date $s_time $d_date $d_time
        
        else
          echo "You have entered a wrong command.Try with a Correct Format. "
        fi
done
echo ""

