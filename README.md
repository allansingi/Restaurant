# Restaurant Rest Delivery API project with JWT Authentication and Authorization

Java Back-End REST API for creating requests for menu delivery and assign the respective clients, couriers and menus,
change delivery status for delivery monitoring, respecting authorization to perform actions (CRUD for the 3 modules).

AUTHENTICATION:
In order to get the CLIENT or ADMIN JWT, pass a JSON object in the body of the request as follows:

{
    "email": "gusteau@mail.com",
    "password": "123"
}


END_POINTS:

MENU MODULE

/api/v1/menuList - list all menus inclusive inactive or expired

/api/v1/activeMenuList - list only active menus in order to request

/api/v1/menuById - search a menu by its id

/api/v1/addMenu - creates a new menu

/api/v1/updateMenu - updates an existing menu

/api/v1/deleteMenuById - deletes an existing menu (need ADMIN JWT authorization)


CLIENT MODULE

/api/v1/clientList - list all clients in database

/api/v1/clientById - search a client by its id

/api/v1/addClient - creates a new client

/api/v1/updateClient - updates an existing client

/api/v1/deleteClientById - deletes an existing client (need ADMIN JWT authorization)


COURIER MODULE

/api/v1/courierList - list all couriers in database

/api/v1/courierById - search a courier by its id

/api/v1/addCourier - creates a new courier

/api/v1/updateCourier - updates an existing courier

/api/v1/deleteCourierById - deletes an existing courier (need ADMIN JWT authorization)


REQUEST MODULE

/api/v1/requestList - list all requests in database

/api/v1/requestReadyList - list requests with READY status

/api/v1/requestById - search a request by its id

/api/v1/createRequest - creates a new request

/api/v1/updateRequest - updates an existing request (courier assignment, change number of requested menus and updates

menu stack, update request status - PREPARING, READY, IN_TRANSIT, DELIVERED and CANCELLED) - (need ADMIN JWT
authorization)


Technologies used:

Java 11 based

Maven

SpringBoot

SpringDevTools

SpringSecurity

JsonWebToken

MySql Database

H2 Database for initial data load for testing

by Allan Borges