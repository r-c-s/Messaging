## Messaging

A simple application for messaging

##### Build

<pre>
mvn clean package
</pre>

##### Run unit tests

<pre>
mvn test
</pre>

##### Run application

<pre>
java -jar target/Messaging-1.0-SNAPSHOT.jar
</pre>

##### Send message

<pre>
curl -X POST localhost:8080/messaging/api/messages -d "{\"to\":\"someUser\",\"subject\":\"someSubject\",\"body\":\"someBody\"}" -H "Content-type:application/json"</pre>