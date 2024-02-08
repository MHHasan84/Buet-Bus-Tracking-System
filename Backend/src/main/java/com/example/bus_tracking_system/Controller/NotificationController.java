package com.example.bus_tracking_system.Controller;

import com.example.bus_tracking_system.Model.Notification;
import com.example.bus_tracking_system.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NotificationController {
    @Autowired
    private NotificationRepository notificationRepository;

    @GetMapping("notification/{id}")
    List<Notification> getNotification(@PathVariable("id") String receiverId){
        return notificationRepository.getNotificationByReceiverId(receiverId);
    }
    @PostMapping("notification")
    Notification addNotification(@RequestBody Notification notification){
        return notificationRepository.save(notification);
    }

    @PostMapping("notification/all")
    List<Notification> addAllNotification(@RequestBody List<Notification> notificationList){
        return notificationRepository.saveAll(notificationList);
    }

    @PutMapping("notification")
    Notification updateNotification(@RequestBody Notification notification){
        return notificationRepository.save(notification);
    }
}
