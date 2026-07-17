package team.Complaint.domains.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team.Complaint.domains.Model.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository <Feedback,Long> {

    List<Feedback> findByUserId(String userId);

}
