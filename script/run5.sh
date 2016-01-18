
#!/bin/bash

docker pull tomcat:latest;

#for i in 'seq 1,5'; do
#  	echo $i;
#    	docker run -d -P --name tomcat$i tomcat:latest;
#done
for i in `seq 1 5`;
do
	# echo $i
	# echo tomcat$i
	docker run -d -P --name=tomcat$i tomcat:latest;
done 
