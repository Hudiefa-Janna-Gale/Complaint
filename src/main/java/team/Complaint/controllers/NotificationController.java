package team.Complaint.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import team.Complaint.domains.DTO.ResponseDto.NotificationResponseDTO;
import team.Complaint.service.NotificationService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public List<NotificationResponseDTO> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    @GetMapping("/user/{userId}")
    public List<NotificationResponseDTO> getNotificationsByUser(@PathVariable String userId) {
        return notificationService.getNotificationsByUser(userId);
    }

    @PutMapping("/{id}/read")
    public NotificationResponseDTO markAsRead(@PathVariable Long id) {
        return notificationService.markAsRead(id);
    }

}
