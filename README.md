## Messaging

A simple SpringBoot application for messaging

<hr>

#### Dependencies
* [Auth](https://github.com/r-c-s/Auth) & [AuthApi](https://github.com/r-c-s/AuthApi)
* [MongoDB](https://docs.mongodb.com/manual/installation/)

<hr>

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

<hr>

##### Register on Auth service

<pre>
curl -X POST authhost:authport/auth/api/users -H "Content-type:application/json" -d "{\"username\":\"USERNAME\",\"password\":\"PASSWORD\"}"
</pre>

##### Login on Auth service

<pre>
curl -X POST authhost:authport/auth/login -d "username=USERNAME&password=PASSWORD" -c cookies
</pre>

##### Send a message using cookies from above

<pre>
curl -b cookies -X POST host:port/messaging/api/messages -d "{\"to\":\"someUser\",\"subject\":\"someSubject\",\"body\":\"someBody\"}" -H "Content-type:application/json"
</pre>

##### Get inbox

<pre>
curl -X GET host:port/messaging/api/messages/inbox
</pre>

##### Get outbox

<pre>
curl -b cookies -X GET host:port/messaging/api/messages/outbox
</pre>