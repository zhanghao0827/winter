package org.winter.system.domain.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordVo {

    private String oldPassword;

    private String newPassword;
}
