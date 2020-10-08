package io.inaam.main.service;

import io.inaam.main.dto.AdminDto;
import io.inaam.main.entity.Admin;
import io.inaam.main.repository.AdminRepository;
import io.inaam.main.transformer.AdminTransformer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    private final AdminTransformer adminTransformer;

    @Override
    public void createAdmin(AdminDto adminDto) {
        Admin admin = adminTransformer.toAdmin(adminDto);
        adminRepository.save(admin);
    }

    @Override
    public List<AdminDto> getAdmins() {
        return adminRepository.findAll().stream().map(adminTransformer::toAdminDto)
                .collect(Collectors.toList());
    }

}
