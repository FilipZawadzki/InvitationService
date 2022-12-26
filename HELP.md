# Invitation App: backend

### Basic informations
* Started ~12.00 26.12
* Ended ~16:30 26.12
* Used technologies: Java/Spring, JPA, Maven, H2, Mockito, AssertJ

App allows you to create, list and change statuses of invitations.
Every action: create, decline, accept trigger listeners to send an email to the invitee.


### Configuration can be found in application.properties and MailConfig.class

* [Mail Server] - change host, port, name, password and properties to your own server. I recommend using mailtrap website to test.
* [DB] - db is set to H2.
* [Tests] - for easier testing I exported collection from postman

FZ
