# E-Commerce App (XindusBazaar)
XindusBazaar: Your go-to shopping app for effortless and enjoyable online shopping experiences!

                   
<h1 align="center">
Wishlist Management Backend Application Documentation </h1>
<h2 align="center">Overview </h2>

This documentation provides a comprehensive guide for setting up, running, testing, and understanding the backend functionality of the Wishlist Management feature in the e-commerce platform. 
### Project Details

- Project Name: XindusBazaar
- Backend URL: https://xindusbazaar-production.up.railway.app
- Frontend URL:https://xindusbazaar-sunil.netlify.app/
- GitHub Repository: https://github.com/SunilSinghTarkar/XindusBazaar
  
 <h2 align="center">Features </h2>
<h3>Users Module</h3>
<!--  <br /> -->
<b>  Users Registration: </b>  Users can register with the application by providing their personal information and contact details.

Login: Registered Users can logIn to their accounts securely using Spring Security.

<b>Preview WishList:</b> Users can browse a list of available Items in their WishList.

<b>Add To WishList:</b> Users can Add Item to their WishList.

<b>Remove From WishList:</b> Users can Remove Item from WishList.

<h3>Technologies Used</h3>

Backend: Java, Spring Boot, Spring Security, JPA
<br>
Frontend: Html, Css ,JavaScript ( Frontend URL:https://xindusbazaar-sunil.netlify.app/)


<b>Database:</b> MySQL

<b>Authentication:</b> Spring Security with JWT (JSON Web Tokens)

<h3>How to Run the Application</h3>
Clone the repository from GitHub.

Set up the database by executing the provided SQL scripts in MySQL.

Configure the database connection properties in the application file.

Build and run the Spring Boot application.

Access the application through a PostMan and register/logIn.

Start using the application and add Items to WishList.

## Installation & Run

Before running the API server, please follow these steps:

1. Update the database configuration inside the `application-dev.yml` file located in your project:
 ```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    password: your_password
    url: jdbc:mysql://localhost/your_database_name
    username: your_username
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

## API Endpoints
<b>Localhost:</b>  http://localhost:8080
<br>
<b>Deployed:</b>   https://xindusbazaar-production.up.railway.app

```
Endpoints for WishList management.
.GET /api/wishlists: Retrieve a user's WishList.  (Authentication required)

.POST /api/wishlists: Adding a new item to WishList. (Authentication required) Note: item obj required to pass.

.DELETE /api/wishlists/{Id}: Remove a wishlist item by ID. (Authentication required)

Endpoints for Users management.
.GET /api/users/{userId}: Retrieve a user.

.GET /api/signIn: Registered Users can logIn to their accounts. (Without Authentication allowed)

.POST /api/registers: Registering a new User to the Database.   (Without Authentication allowed)
ex:
{ 
  "userName":"Sunil",
  "email":"sunil@gmail.com",
  "password":"1234"
}


Endpoints for Items management.
.GET /api/items/{itemId}: Retrieve the item. (Authentication required)

.GET /api/items: Retrieve the itemList. (Authentication required)

.POST /api/items: Create a new Item.  (Authentication required)
 ex:
{ 
  "itemName":"Item01",
  "category":"Fashion",
  "price":120.20
}

```

##Note 
Upon successful registration or login, users will receive a user object containing essential details such as userId and wishList.

<H3>Database architecture</H3>





![Screenshot_20240217_192951](https://github.com/SunilSinghTarkar/XindusBazaar/assets/121342167/c4acb1d8-f25b-4ee2-9636-b280830ddfbf)
