# Chat Web Application (Backend)

## Overview
This is the backend for a real-time chat web application built using Spring Boot, MongoDB, and WebSockets. The backend provides APIs for managing chat rooms and messages, ensuring efficient and scalable communication.

## Technologies Used
- **Spring Boot** - Backend framework
- **MongoDB** - NoSQL database for storing messages and rooms
- **Spring WebSockets** - Real-time communication
- **Lombok** - Reducing boilerplate code
- **Maven** - Dependency management

## Features
- Create and join chat rooms
- Store and retrieve messages with pagination
- WebSockets for real-time messaging (planned)

## Project Structure
```
ChatWebApp/
├── src/
│   ├── main/
│   │   ├── java/com/chatapp/
│   │   │   ├── controller/    # Handles API requests
│   │   │   ├── service/       # Business logic layer
│   │   │   ├── model/         # Entity definitions
│   │   │   ├── repository/    # Database interactions
│   │   │   ├── config/        # WebSocket and database configurations
│   ├── resources/
│   │   ├── application.properties  # Application configurations
├── pom.xml   # Maven dependencies
└── README.md
```

## ER Diagram
```plaintext
+------------+       +------------+       +------------+
|   User     |       |   Room     |       |  Message   |
+------------+       +------------+       +------------+
| userId     | 1   * | roomId     | 1   * | messageId  |
| username   |-------| users      |-------| senderId   |
| email      |       | messages   |       | roomId     |
+------------+       +------------+       | content    |
                                          | timestamp  |
                                          +------------+
```
Users can join multiple rooms, and each room contains multiple messages from different users.

## High-Level Architecture
```
Client (React)  -->  WebSocket Server (Spring Boot)  -->  MongoDB (Database)
```
The frontend connects to the WebSocket server for real-time updates, while the backend stores chat history in MongoDB.

## Installation & Setup
1. Clone the repository:
   ```sh
   git clone https://github.com/Vishal-jain2003/ChatWebApp.git
   cd ChatWebApp
   ```
2. Configure MongoDB:
   - Ensure MongoDB is running locally on `mongodb://localhost:27017/chatapp`
   - Update `application.properties` if needed
3. Build and run the application:
   ```sh
   mvn spring-boot:run
   ```

## API Endpoints
### Room Management
- **Create Room**
  - `POST /api/v1/rooms`
  - Request Body: `{ "roomId": "room1" }`
  - Response: `201 Created`

- **Join Room**
  - `GET /api/v1/rooms/{roomId}`
  - Response: `200 OK` or `400 Bad Request`

### Message Handling
- **Get Messages (Paginated)**
  - `GET /api/v1/rooms/{roomId}/messages?page=0&size=20`
  - Response: `200 OK` with message list

## Future Enhancements
- Implement WebSockets for real-time chat
- User authentication and authorization
- Frontend integration with React

## Contributing
Contributions are welcome! Feel free to submit a pull request.

## License
This project is licensed under the MIT License.

