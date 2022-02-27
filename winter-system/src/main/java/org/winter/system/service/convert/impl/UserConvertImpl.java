package org.winter.system.service.convert.impl;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.stereotype.Service;
import org.winter.system.domain.User;
import org.winter.system.service.convert.UserConvert;
import org.winter.system.service.dto.UserDto;
import org.winter.system.service.dto.UserSimpleDto;

@Service
public class UserConvertImpl implements UserConvert {

    @Override
    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        BeanUtil.copyProperties(user, userDto);
        //使用org.springframework.beans.BeanUtils 会把SysUser存在但SysUserDto不存在的字段拷贝，给SysUserDto添加新的属性，不符合需求
//        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    @Override
    public UserSimpleDto toSimpleDto(User user) {
        UserSimpleDto userSimpleDto = new UserSimpleDto();
        BeanUtil.copyProperties(user, userSimpleDto);
        return userSimpleDto;
    }

    @Override
    public User toUser(UserDto user) {
        User u = new User();
        BeanUtil.copyProperties(user, u);
        return u;
    }
}
