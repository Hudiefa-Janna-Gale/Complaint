package team.Complaint.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import team.Complaint.domains.Model.DepartMent;
import team.Complaint.service.DepartmentService;

@RestController
@RequestMapping("/api/departments")
@AllArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;


    // Create Department
    @PostMapping
    public ResponseEntity<DepartMent> createDepartment(
            @RequestBody DepartMent department
    ){
        return new ResponseEntity<>(
                departmentService.createDepartment(department),
                HttpStatus.CREATED
        );
    }



    // Get All Departments
    @GetMapping
    public ResponseEntity<List<DepartMent>> getAllDepartments(){

        return ResponseEntity.ok(
                departmentService.getAllDepartments()
        );
    }



    // Get Department By Id
    @GetMapping("/{id}")
    public ResponseEntity<DepartMent> getDepartmentById(
            @PathVariable Long id
    ){

        return ResponseEntity.ok(
                departmentService.getDepartmentById(id)
        );
    }



    // Update Department
    @PutMapping("/{id}")
    public ResponseEntity<DepartMent> updateDepartment(
            @PathVariable Long id,
            @RequestBody DepartMent department
    ){

        return ResponseEntity.ok(
                departmentService.updateDepartment(id, department)
        );
    }



    // Delete Department
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(
            @PathVariable Long id
    ){

        return ResponseEntity.ok(
                departmentService.deleteDepartment(id)
        );
    }

}