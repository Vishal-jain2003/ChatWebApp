package com.substring.chat.controllers;

import com.substring.chat.entities.Message;
import com.substring.chat.entities.Room;
import com.substring.chat.payload.MessageRequest;
import com.substring.chat.repositories.RoomRepository;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Controller
@CrossOrigin(origins = "${frontend.url}")  // Dynamic frontend URL
public class ChatController {


    private RoomRepository roomRepository;

    public ChatController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    // for sending and recieving messages
    @MessageMapping("/sendMessage/{roomId}")  // message will be sent to this endpoint // send msg
    @SendTo("/topic/room/{roomId}") // message will be published to this endpoint for subscription of client

    public Message sendMessage(@DestinationVariable String roomId, @RequestBody MessageRequest request){

        Room room = roomRepository.findByRoomId(request.getRoomId());

        Message message = new Message();
        message.setContent(request.getContent());
        message.setSender(request.getSender());
        message.setTimeStamp(LocalDateTime.now());
        if (room!=null){
               room.getMessages().add(message);
               roomRepository.save(room);
        }
        else
        {
            throw new RuntimeException("Room not found");
        }

        return message;

    }

}

//Client sends a message → stompClient.send("/app/sendMessage/roomId", {}, message).
//Backend processes the message, saves it to DB.
//Backend broadcasts the message to all users subscribed to /topic/room/roomId.
//Clients receive and display the message in real-time.
