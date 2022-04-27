# The_Other_Guys_P2
Ticket Tracking System For IT Support

# Technologies Used
- JavaScript
- HTML
- CSS
- Postgres SQL
- Java
- Javalin
- Postman
- Selenium
- AWS RDS
- REST
- Cucumber
- Git
- TestNG
- JUnit

# Description
Help Request Ticketing System

Our application allows a client to submit a request for help with an issue. A technician can then view the open requests 
and open a resolution ticket to track work done to resolve the issue.  While the ticket is open, the client is restricted 
from submitting another ticket, but can view the status of their open ticket or update the description if they think of 
something new that may help the technician or even cancel the request if they resolved the issue on their own.  
The technician can only work on one ticket at a time and is allowed to view/edit comments on their progress and 
eventually they can resolve the ticket, which will close the request opened by the client.
At any time, both client and technician are allowed to change some personal information.

# Features
- Employees can log into the app.
- Internal Clients can create and submit new help requests.
- Internal Clients can view their open requests.
- Internal Clients can update their open requests.
- Internal Clients can cancel their open requests.
- Internal Clients can update their personal info.
- Technicians can view all open help requests.
- Technicians can open tickets to resolve help requests.
- Technicians can view their open tickets.
- Technicians can update their open tickets.
- Technicians can resolve their open tickets and close open help request for that ticket.
- Technicians can update their personal info.
- Employees can log out of the app.

# To Do
- Allow for multiple tickets per help request
- Populate tciket and request ids for the update and cancel features
  to prevent invalid input from the user
- Allow for changing of username and password in update personal info feature

# Getting Started
- to clone:
  - git clone https://github.com/mcballard/The_Other_Guys_P2.git
- Database:
  - Create a local or cloud based Postgres RDS.
  - Use your preferred database management software to run scripts located in the database documentation folder.
- To deploy:
  - Install application required technologies to be found in the virtual environment.
  - Run HelpTicketApi.java to start the application. 

# Usage
- Log in on the login.html page as either an internal client or a technician
  - As a client you will have access to:
     - Updating personal information 
     - Creating a help request
     - Viewing your open help request
     - Updating your open help request
     - Cancelling your open help request
     - Logging out
  - As a Technician you will have access to: 
     - Updating personal information
     - Creating a work ticket for an open help request
     - Viewing your open work ticket
     - Updating your open work ticket
     - Resolving your open work ticket and closing both the ticket and the request
     - Logging out

# Contributors
  - Matt Ballard
  - Joseph Raghuraj
  - Andraey Pompey
  - Alemayehu Ashagre
  

