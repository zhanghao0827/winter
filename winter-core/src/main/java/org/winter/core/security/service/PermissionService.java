package org.winter.core.security.service;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.winter.common.util.SecurityUtils;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PermissionService {

    public boolean hasPermission(String permission) {
        if (StrUtil.isEmpty(permission)) {
            return false;
        }
        UserDetails userDetails = SecurityUtils.getLoginUserDetails();
        if (ObjectUtil.isNull(userDetails)) {
            return false;
        }
        Set<String> permissions = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        if (ArrayUtil.isEmpty(permission)) {
            return false;
        }
        return permissions.contains(permission.trim());
    }

}
