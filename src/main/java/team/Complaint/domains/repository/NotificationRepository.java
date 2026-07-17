package team.Complaint.domains.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team.Complaint.domains.Model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {

    List<Notification> findByUserId(String userId);

}



