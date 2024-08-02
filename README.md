Developed a RESTful API service using Spring Boot to manage the Leaderboard for a Coding Platform while using MongoDB to persist the data.

## Features

- Register a user
- Retrieve all users sorted by score
- Retrieve user by ID
- Update user score
- Delete user

Access the API at `http://localhost:8082`.

## API Endpoints

- `GET /users` - Retrieve a list of all registered users
- `GET /users/{id}` - Retrieve the details of a specific user
- `POST /users` - Register a new user
- `PUT /users/{id}` - Update the score of a specific user
- `DELETE /users/{id}` - Deregister a specific user

