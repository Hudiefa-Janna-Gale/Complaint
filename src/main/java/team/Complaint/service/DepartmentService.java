package team.Complaint.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import team.Complaint.domains.Model.DepartMent;
import team.Complaint.domains.repository.DepartmentRepository;


@Service
@AllArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;


    // Create Department
    public DepartMent createDepartment(DepartMent department){

        return departmentRepository.save(department);
    }



    // Get All Departments
    public List<DepartMent> getAllDepartments(){

        return departmentRepository.findAll();
    }



    // Get Department By Id
    public DepartMent getDepartmentById(Long id){

        return departmentRepository.findById(id)
                .orElseThrow(() -> 
                    new RuntimeException("Department not found with id: " + id)
                );
    }



    // Update Department
    public DepartMent updateDepartment(Long id, DepartMent department){

        DepartMent existingDepartment = getDepartmentById(id);

        existingDepartment.setDepName(department.getDepName());

        return departmentRepository.save(existingDepartment);
    }



    // Delete Department
    public String deleteDepartment(Long id){

        DepartMent department = getDepartmentById(id);

        departmentRepository.delete(department);

        return "Department deleted successfully";
    }

}