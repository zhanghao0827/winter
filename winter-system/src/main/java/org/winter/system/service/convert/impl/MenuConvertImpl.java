package org.winter.system.service.convert.impl;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.stereotype.Service;
import org.winter.system.domain.Menu;
import org.winter.system.service.convert.MenuConvert;
import org.winter.system.service.dto.MenuDto;

@Service
public class MenuConvertImpl implements MenuConvert {

    @Override
    public MenuDto toDto(Menu menu) {
        MenuDto menuDto = new MenuDto();
        BeanUtil.copyProperties(menu, menuDto);
        return menuDto;
    }
}
