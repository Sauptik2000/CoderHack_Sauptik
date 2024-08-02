Developed a RESTful API service using Spring Boot to manage the Leaderboard for a Coding Platform while using MongoDB to persist the data.

## Features

- Register a user
- Retrieve all users sorted by score
- Retrieve user by ID
- Update user score
- Delete user

## Running the Project

1. Clone the repository:
    ```bash
    git clone https://github.com/yourusername/leaderboard-api.git
    cd leaderboard-api
    ```

2. Build and run the project using Maven:
    ```bash
    mvn spring-boot:run
    ```

3. Access the API at `http://localhost:8082`.

## API Endpoints

- `GET /users` - Retrieve a list of all registered users
- `GET /users/{id}` - Retrieve the details of a specific user
- `POST /users` - Register a new user
- `PUT /users/{id}` - Update the score of a specific user
- `DELETE /users/{id}` - Deregister a specific user

