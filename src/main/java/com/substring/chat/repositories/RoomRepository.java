package com.substring.chat.repositories;

import com.substring.chat.entities.Room;
import org.springframework.data.mongodb.core.MongoAdminOperations;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface  RoomRepository extends MongoRepository<Room, String> {
    // get room using roomid
    Room findByRoomId(String roomId);
}
