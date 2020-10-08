package io.inaam.main.service;

import io.inaam.main.dto.AdminDto;

import java.util.List;

public interface AdminService {
    void createAdmin(AdminDto adminDto);

    List<AdminDto> getAdmins();
}
