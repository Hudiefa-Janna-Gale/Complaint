package team.Complaint.domains.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team.Complaint.domains.Model.DepartMent;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartMent, Long>{

    Optional<DepartMent> findByDepName(String depName);

}
