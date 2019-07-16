# LogParser
This application generates random logs for a monitoring system that has 1000 servers with 2 CPU's each. Also, i have created a command line tool that will query the generated logs!

(1) Developed a simulator to generate the logs for one day, say 2014-10-31, using random numbers between 0% to 100% as CPU usage. The generator writes data to files in a directory. The timestamp is Unix time.

(2) Created a command line tool which takes a directory of data files as a parameter and lets you query CPU usage for a specific CPU in a given time period. It is an interactive command line tool which read a user’s commands from stdin. The tool should support two commands namely QUERY and EXIT.

Kinldy Refer the report for proper instructions on runnning the tool.(Attached with screenshots)
