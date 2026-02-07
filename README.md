# AWS LAMBDA with JAVA

#### What is AWS lambda?

It's a serverless compute service, which means we don't need to manage any server, as AWS will automatically run the code once it is triggred.

Lambda can be triggered in any way, S3 file upload, API Http request, cloudwatch etc.

#### What will this service do?

This is a very basic lambda service that will help you execute a lambda in local desktop.

Here I'm sending an event from POSTMAN and then making a GET call through mongoClient to mongo collection.

More integration will be added to this project

#### What do you need for local testing

1. Docker
2. postman
3. IDE(intelliJ)
4. MongoDb compass

##### POSTMAN operation

`curl --location 'http://localhost:8080/2015-03-31/functions/function/invocations' \ --header 'Content-Type: application/json' \ --data '{ "_id": "68b2da662e725e87d6a8afce", "name": "John", "lastName": "Doe", "city": "Los Angles", "state": "California", "phone": "15551234567", "identificationId": "A123456789" }' `

##### MONGODB operation

`db.persons.insertOne({ "name": "John", "lastName": "Doe", "city": "Los Angeles", "state": "California", "phone": "15551234567", "identificationId": "A123456789" })`


#### How to start a Lambda application

To start the application, just go to the project path and run
`docker-compose up --build -d`

This will start all the services in your docker.

Once all the services are started, go to your postman and run the CURL command provided.

Once you send the event, go check the logs, and you can see the GET call getting executed!


**NOTE:** This is a very basic setup, this is just a beginner-friendly code, checkout different branches for different kinds of implementation.
