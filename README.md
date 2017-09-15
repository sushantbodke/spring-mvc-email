
## Spring MVC Email 

This project is a client app to send an email (via GMAIL SMTP) using Pivotal Cloud Foundry service borker. 

1. Just deploy the broker project 'cloudfoundry-service-broker-master' https://github.com/sushantbodke/cloudfoundry-service-broker-master.git as an app to PCF and convert it into a service broker. (Update the gmail credentials in the yml file before deploying the broker app)

2. Create a service instance of the broker 

3. Then deploy this 'spring-mvc-email' project as an app in the same space, and bind it to the broker service.

4.  Hit the endpioint "/mail" to send the email
