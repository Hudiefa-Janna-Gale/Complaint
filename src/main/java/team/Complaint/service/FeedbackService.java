package team.Complaint.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import team.Complaint.domains.DTO.RequestDto.FeedbackRequestDTO;
import team.Complaint.domains.DTO.ResponseDto.FeedbackResponseDTO;
import team.Complaint.domains.Model.DepartMent;
import team.Complaint.domains.Model.Feedback;
import team.Complaint.domains.Model.Notification;
import team.Complaint.domains.Model.User;
import team.Complaint.domains.repository.DepartmentRepository;
import team.Complaint.domains.repository.FeedbackRepository;
import team.Complaint.domains.repository.NotificationRepository;
import team.Complaint.domains.repository.UserRepository;

@AllArgsConstructor
@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final NotificationRepository notificationRepository;

    public FeedbackResponseDTO createFeedback(FeedbackRequestDTO request) {

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        DepartMent department = departmentRepository.findById(request.departmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        Feedback feedback = new Feedback();
        feedback.setTitle(request.title());
        feedback.setMessage(request.message());
        feedback.setUser(user);
        feedback.setDepartment(department);

        Feedback savedFeedback = feedbackRepository.save(feedback);

        Notification notification = new Notification();
        notification.setTitle("Feedback Received");
        notification.setMessage("Your feedback '" + savedFeedback.getTitle() + "' was submitted successfully");
        notification.setUser(user);
        notificationRepository.save(notification);

        return FeedbackResponseDTO.from(savedFeedback);
    }

    public List<FeedbackResponseDTO> getAllFeedbacks() {
        return feedbackRepository.findAll()
                .stream()
                .map(FeedbackResponseDTO::from)
                .toList();
    }

    public FeedbackResponseDTO getFeedbackById(Long id) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));
        return FeedbackResponseDTO.from(feedback);
    }

    public List<FeedbackResponseDTO> getFeedbacksByUser(String userId) {
        return feedbackRepository.findByUserId(userId)
                .stream()
                .map(FeedbackResponseDTO::from)
                .toList();
    }

    public String deleteFeedback(Long id) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));
        feedbackRepository.delete(feedback);
        return "Feedback deleted successfully";
    }

}
