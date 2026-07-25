package team.Complaint.domains.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team.Complaint.domains.Enum.Roles;
import team.Complaint.domains.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    List<User> findByRole(Roles role);

}
