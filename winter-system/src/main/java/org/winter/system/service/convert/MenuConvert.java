package org.winter.system.service.convert;

import org.winter.system.domain.Menu;
import org.winter.system.service.dto.MenuDto;

public interface MenuConvert {

    MenuDto toDto(Menu menu);


}
