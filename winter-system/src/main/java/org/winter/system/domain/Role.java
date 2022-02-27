package org.winter.system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.winter.common.base.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "sys_role")
@Getter
@Setter
@ToString
public class Role extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String scope;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sys_role_menu",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "menu_id")})
    private List<Menu> menus;

}
