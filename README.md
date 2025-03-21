# Chat Web Application (Backend)

## Overview
This is the backend for a real-time chat web application built using **Spring Boot**, **MongoDB**, and **WebSockets**. The backend provides APIs and WebSocket endpoints for managing chat rooms and messages, ensuring seamless communication.

## Technologies Used
- **Spring Boot** - Backend framework
- **MongoDB** - NoSQL database for storing messages and rooms
- **Spring WebSockets** - Real-time communication
- **STOMP & SockJS** - WebSocket protocol & fallback support
- **Spring Messaging** - Message handling
- **Lombok** - Reducing boilerplate code
- **Maven** - Dependency management

## Features
- Create and join chat rooms
- Store and retrieve messages with pagination
- **WebSockets for real-time messaging**
- Efficient message broadcasting using **STOMP & SockJS**

## Project Structure
```
ChatWebApp/
├── src/
│   ├── main/
│   │   ├── java/com/substring/chat/
│   │   │   ├── config/        # WebSocket & database configurations
│   │   │   ├── controllers/   # Handles API & WebSocket requests
│   │   │   ├── entities/      # Entity definitions (Message, Room, User)
│   │   │   ├── payload/       # Request & response DTOs
│   │   │   ├── repositories/  # Database interactions
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

## WebSocket Workflow
1. **Client connects** to the WebSocket server at `/chat`.
2. **Users send messages** to `/app/sendMessage/{roomId}`.
3. **Backend processes the message**, stores it in MongoDB.
4. **Backend broadcasts** the message to `/topic/room/{roomId}`.
5. **Clients subscribed** to this topic instantly receive the message.

## WebSocket Endpoints
- **Connect to WebSocket:**
  - `ws://localhost:8080/chat`
- **Send Message:**
  - Endpoint: `/app/sendMessage/{roomId}`
  - Request Body:
    ```json
    {
      "content": "Hello, world!",
      "sender": "Vishal",
      "roomId": "12345"
    }
    ```
- **Receive Messages (Subscription):**
  - Topic: `/topic/room/{roomId}`

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
- User authentication and authorization
- Frontend integration with React
- Typing indicators and user presence detection

## Contributing
Contributions are welcome! Feel free to submit a pull request.

## License
This project is licensed under the MIT License.

