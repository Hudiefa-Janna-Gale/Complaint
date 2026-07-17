package team.Complaint.domains.Model;

import java.security.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import team.Complaint.domains.Enum.Roles;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;


    private String fullName;


    @Column(unique = true, nullable = false)
    private String email;


    private String phone;


    @Enumerated(EnumType.STRING)
    private Roles role = Roles.USER;


    @Column(nullable = false)
    private String password;


    @Column(nullable = false)
    private Boolean status = true;


    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private DepartMent department;


    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;


    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}