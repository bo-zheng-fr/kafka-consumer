This is a kafka-consumer application for localhost test for developers. This application deserializes and logs the message sent from LANE. 
Please launch the kafka server before test. Here are the scripts for kafka server : 
# cd C:\kafka (goto kafka installed folder)
# run zookeeper cluster
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
# run broker server on another cmd
.\bin\windows\kafka-server-start.bat .\config\server.properties
