package org.winter.system.service.convert.impl;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.stereotype.Service;
import org.winter.system.domain.Role;
import org.winter.system.service.convert.RoleConvert;
import org.winter.system.service.dto.RoleDto;
import org.winter.system.service.dto.RoleSimpleDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleConvertImpl implements RoleConvert {

    @Override
    public RoleSimpleDto toSimpleDto(Role role) {
        RoleSimpleDto roleSimpleDto = new RoleSimpleDto();
        BeanUtil.copyProperties(role, roleSimpleDto);
        return roleSimpleDto;
    }

    @Override
    public RoleDto toDto(Role role) {
        RoleDto roleDto = new RoleDto();
        BeanUtil.copyProperties(role,roleDto);
        return roleDto;
    }

    @Override
    public List<RoleSimpleDto> toSimpleDtoList(List<Role> roles) {
        return roles.stream().map(this::toSimpleDto).collect(Collectors.toList());
    }
}
