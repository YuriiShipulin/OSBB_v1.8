
OSBB
Short description
This application is designed to simplify payment transactions for the condominiums owners. Here is simple client-server program, which helps owners (users) to keep accounts, and helps the administrator to analyze and control accounts.
User actions:
-	register personal information;
-	register flat information;
-	see his neighbours personal info;
-	make counter readings;
-	calculate summary bill;
-	save the invoice;
-	invite neighbours by e-mail.

Admin actions:
-	register personal information;
-	create and register buildings;
-	see the list of dwellers in building;
-	see the list of dwellers last counter readings;
-	change rates for building services;
-	accept the summary bill for dwellers;
-	invite dwellers by e-mail.

We’ve decided to configure it without XML documents, using annotations.  Spring Data JPA was chosen to store and retrieve data in a relational database, because this framework is flexible enough for our tasks, it makes code easy to read and elegant.  Authentication was implemented by configuring Spring Security. We used Apache Tomcat 8 as a web server, iText framework for dynamic creating of PDF reports.
We used basic tools of Twitter Bootstrap with JavaScript for the front-end development.

Don't judge me too harshly
Best regards, Yurii.

 
