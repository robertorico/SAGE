Loan a Pound

1. Project description
  This project has as an objective to create diferent calls to web services with SOAP and REST protocols base on some creterias.
  
  The data base used is MySQL. You can see the model open the file 'data model LAP.mwb'
  
  You will find a form to insert customer data and loan amount, when sending the data, the process will obtain a score from webServices.
  
  In this scenario the criterias that I have configuret are "minAmount" and "maxAmount", the logic behind of them are based on:
	- if the amount is beetween 1000 to 5000 will call to SOAP web services
	- if the amount is beetween 5000 to 10000 will call to REST web services
	- if the amount is more or less will call all web services and get the best score
  
	Finally a page with all loan request and the status about the loan is displayed.
	
2. Install instructions
	1. Create the data base with MySQL, for that execute the script "script create DB with data.sql"
	2. In eclipse import the projects 'loanapound', 'providerscorerest' 'providerscoresoap' like maven project.
	3. Run all projects
	4. Execute http://localhost:8080/lap
