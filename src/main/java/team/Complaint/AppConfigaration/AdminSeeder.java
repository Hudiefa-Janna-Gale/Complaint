package team.Complaint.AppConfigaration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import team.Complaint.domains.Enum.Roles;
import team.Complaint.domains.Model.DepartMent;
import team.Complaint.domains.Model.User;
import team.Complaint.domains.repository.DepartmentRepository;
import team.Complaint.domains.repository.UserRepository;

@Component
@AllArgsConstructor
public class AdminSeeder implements CommandLineRunner {

    private static final String ADMIN_EMAIL = "admin@gmail.com";
    private static final String ADMIN_PASSWORD = "root123";

    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        if (userRepository.existsByEmail(ADMIN_EMAIL)) {
            return;
        }

        DepartMent department = departmentRepository.findByDepName("Administration")
                .orElseGet(() -> {
                    DepartMent newDepartment = new DepartMent();
                    newDepartment.setDepName("Administration");
                    return departmentRepository.save(newDepartment);
                });

        User admin = new User();
        admin.setFullName("Admin");
        admin.setEmail(ADMIN_EMAIL);
        admin.setPhone("00000000");
        admin.setPassword(passwordEncoder.encode(ADMIN_PASSWORD));
        admin.setRole(Roles.ADMIN);
        admin.setStatus(true);
        admin.setDepartment(department);

        userRepository.save(admin);

        System.out.println("Default admin created: " + ADMIN_EMAIL);
    }
}
