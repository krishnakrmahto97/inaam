package io.inaam.main.service;

import io.inaam.main.dto.AdminDto;

public interface AdminService {
    void addAdminUser(AdminDto adminDto);

    boolean authenticate(AdminDto adminDto);
}
