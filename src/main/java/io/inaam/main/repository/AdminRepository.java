package io.inaam.main.repository;

import io.inaam.main.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, String> {
    Admin findAdminByNameAndSecret(String name, String secret);
}
