package org.winter.system.domain;

import lombok.Getter;
import lombok.Setter;
import org.winter.common.base.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "sys_user")
@Getter
@Setter
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String nickname;

    private String password;

    private String sex;

    private String avatar;

    private String phone;

    private String email;

    @Column(name = "is_enabled")
    private Boolean enabled;

    @Column(name = "is_deleted")
    private Boolean deleted;

    /**
     * joinColumns 用来指定中间表user_role中关联自己ID的字段
     * inverseJoinColumns 用来指定中间表user_role中关联对方(role)ID的字段
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sys_user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roles;

}
