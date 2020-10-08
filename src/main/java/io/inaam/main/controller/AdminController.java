package io.inaam.main.controller;

import io.inaam.main.dto.AdminDto;
import io.inaam.main.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@AllArgsConstructor
@RequestMapping("/admin")
@RestController
public class AdminController {

    private final AdminService adminService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAdmin(AdminDto adminDto) {
        adminService.createAdmin(adminDto);
    }

    @GetMapping
    public List<AdminDto> getAdmins() {
        return adminService.getAdmins();
    }
}
