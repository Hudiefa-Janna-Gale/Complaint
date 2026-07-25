package team.Complaint.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import team.Complaint.domains.DTO.RequestDto.FeedbackRequestDTO;
import team.Complaint.domains.DTO.ResponseDto.FeedbackResponseDTO;
import team.Complaint.service.FeedbackService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping
    public FeedbackResponseDTO createFeedback(@Valid @RequestBody FeedbackRequestDTO request) {
        return feedbackService.createFeedback(request);
    }

    @GetMapping
    public List<FeedbackResponseDTO> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }

    @GetMapping("/{id}")
    public FeedbackResponseDTO getFeedbackById(@PathVariable Long id) {
        return feedbackService.getFeedbackById(id);
    }

    @GetMapping("/user/{userId}")
    public List<FeedbackResponseDTO> getFeedbacksByUser(@PathVariable String userId) {
        return feedbackService.getFeedbacksByUser(userId);
    }

    @PutMapping("/{id}/resolve")
    public FeedbackResponseDTO resolveFeedback(@PathVariable Long id) {
        return feedbackService.resolveFeedback(id);
    }

    @DeleteMapping("/{id}")
    public String deleteFeedback(@PathVariable Long id) {
        return feedbackService.deleteFeedback(id);
    }

}
