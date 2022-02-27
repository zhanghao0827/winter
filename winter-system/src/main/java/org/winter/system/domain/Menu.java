package org.winter.system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.winter.common.base.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "sys_menu")
@Getter
@Setter
@ToString
public class Menu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long parentId;

    private Integer sort;

    private String type;

    private String name;

    private String title;

    private String path;

    private String component;

    private String icon;

    private String permission;

    @JsonIgnore
    @ManyToMany(mappedBy = "menus")
    private List<Role> roles;
}
