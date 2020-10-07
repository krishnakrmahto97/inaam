package io.inaam.main.service;

import io.inaam.main.dto.AdminDto;
import io.inaam.main.repository.AdminRepository;
import io.inaam.main.transformer.AdminTransformer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final AdminTransformer adminTransformer;

    @Override
    public void addAdminUser(AdminDto adminDto) {
        adminRepository.save(adminTransformer.toAdmin(adminDto));
    }

    @Override
    public boolean authenticate(AdminDto adminDto) {
        return Optional.ofNullable(adminRepository.findAdminByNameAndSecret(adminDto.getName(), adminDto.getSecret())).isPresent();
    }
}
