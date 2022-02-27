package org.winter.core.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.winter.system.domain.Role;
import org.winter.system.domain.User;
import org.winter.core.security.model.SysUserDetails;
import org.winter.system.service.MenuService;
import org.winter.system.service.UserService;
import org.winter.system.service.convert.UserConvert;

import java.util.stream.Collectors;

/**
 * 必须要有一个UserDetailsService的Bean,否则报错
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserConvert userConvert;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在!");
        }
        SysUserDetails userDetails = new SysUserDetails();
        userDetails.setUser(userConvert.toSimpleDto(user));
        userDetails.setRoles(user.getRoles().stream().map(Role::getScope).collect(Collectors.toList()));
        userDetails.setPermissions(menuService.findPermissionByUser(user));
        return userDetails;
    }
}
