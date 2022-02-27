package org.winter.system.service.convert;

import org.winter.system.domain.Role;
import org.winter.system.service.dto.RoleDto;
import org.winter.system.service.dto.RoleSimpleDto;

import java.util.List;

public interface RoleConvert {

    RoleSimpleDto toSimpleDto(Role role);

    RoleDto toDto(Role role);

    List<RoleSimpleDto> toSimpleDtoList(List<Role> roles);

}
