# OfferManager
# Implementation details:

GET request to server:port/offers/all will return all VALID the saved offers in the Database; 
GET request to server:port/offer/{client} will return all (VALID or EXPIRED) the saved offers for a single {client}; 
GET request to server:port/offer/id/{id} will return the offer (VALID or EXPIRED) with the following {id} ;
DEL request to server:port/cancel/{id} will cancel the saved offer for a single {id} no matter if they are VALID or EXPIRED
- If a improperly formed URL is sent or non-existing customer name or offer ID a 404 NOT FOUND will be returned. 

POST request to server:port/offer with the following body:
{
	  "customer": "CustomerName,
    "description": "Description of the offer goods or services",
    "price": "125.50",
    "validityDays": "10" 
}
 ... will save the offer to the Database and will return the saved offer with ID, default validity time if a negative number or a zero is selected. 

- If an erroneus request is sent an error code 412 PRECONDITION_FAILED will be returned. 

# *** #

# Requirements and Background:

- Background
Per Wikipedia, "an offer is a proposal to sell a specific product or
service under specific conditions". As a merchant I offer goods for
sale. I want to create an offer so that I can share it with my
customers.
All my offers have shopper friendly descriptions. I price all my offers
up front in a defined currency.
An offer is time-bounded, with the length of time an offer is valid for
defined as part of the offer, and should expire automatically. Offers may
also be explicitly cancelled before they expire.

- Assignment
You are required to design a simple RESTful software service that will
allow a merchant to create a new simple offer. Offers, once created, may be
queried. After the period of time defined on the offer it should expire and
further requests to query the offer should reflect that somehow. Before an offer
has expired users may cancel it.

- Guidelines
The solution should be designed in Java, Spring Boot Web Flux or MVC
The merchant should be able to interact with the service over HTTP
Please use TDD
No restrictions on external libraries
We are looking for a simple solution representative of an enterprise deliverable
Please pay attention to OO design; clean code, adherence to SOLID principles
As a simplification offers may be persisted to a database 
You can ignore authentication and authorization concerns
Feel free to make any assumptions 

