package com.example.bus_tracking_system.Repository;

import com.example.bus_tracking_system.Model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Integer> {
    @Query("select n from Notification n where n.receiverId=:id")
    List<Notification> getNotificationByReceiverId(@Param("id") String receiverId);
}
