# This application implements authentication using JWT token.

# Technologies Used:
+ Spring Boot
+ Spring Data JPA
+ Spring Security
+ PostgreSQL Database
+ Lombok
+ Spring Dev Tools
+ Model Mapper
  
# Configuration Steps:

### Clone the Application:

Clone the repository using the following command:

```
git clone https://github.com/honeymoneyes/spring_jwt_application
```

### Create a PostgreSQL Database:
```
Create a new PostgreSQL database named "jwt_security_database".
```

### Configure PostgreSQL Username and Password:
Open the file server/src/main/resources/application.yml and update the following properties with your PostgreSQL installation details:

```
    url: YOUR_DATABASE_URL
    username: YOUR_DATABASE_USERNAME
    password: YOUR_DATABASE_PASSWORD
```

### Run the Application Using Maven:
Open a terminal, navigate to the project root directory (jwt_security), and run the following command:

```
mvn spring-boot:run
```

The application will start running at http://localhost:8080.

### Perform the registration:
By doing the registration you get a JVT token with which you can fulfill requests for authenticated users.

![ImageAlt](https://github.com/honeymoneyes/spring_jwt_application/blob/master/src/main/resources/static/registration.png)

You can copy it and keep it for future reference

![ImageAlt](https://github.com/honeymoneyes/spring_jwt_application/blob/master/src/main/resources/static/copy_token.png)

### Perform the login:
Next, the token has the property that it expires, so you have the ability to renew its expiration date.

![ImageAlt](https://github.com/honeymoneyes/spring_jwt_application/blob/master/src/main/resources/static/update_token_1.png)

In this way the token is updated

### Perform authenticated redirection:
Then, having the copied token we add it to the headers and go to the endpoint for authenticated users.

![ImageAlt](https://github.com/honeymoneyes/spring_jwt_application/blob/master/src/main/resources/static/add_token_to_headers.png)
![ImageAlt](https://github.com/honeymoneyes/spring_jwt_application/blob/master/src/main/resources/static/access_allowed_if_jwt_token_is_valid.png)

### Additional actions and handling of erroneous events:

Register a user with the same data

![ImageAlt](https://github.com/honeymoneyes/spring_jwt_application/blob/master/src/main/resources/static/access_denied_if_user_exist.png)

Incorrect credentials

![ImageAlt](https://github.com/honeymoneyes/spring_jwt_application/blob/master/src/main/resources/static/incorrect_credentials.png)


Endpoint access for authenticated users without a token or with an incorrect one

![ImageAlt](https://github.com/honeymoneyes/spring_jwt_application/blob/master/src/main/resources/static/access_to_authenticated_endpoint_without_jwt_token.png)
