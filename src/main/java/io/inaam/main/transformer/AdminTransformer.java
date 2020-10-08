package io.inaam.main.transformer;

import io.inaam.main.dto.AdminDto;
import io.inaam.main.entity.Admin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminTransformer {

    Admin toAdmin(AdminDto adminDto);
    AdminDto toAdminDto(Admin admin);
}
