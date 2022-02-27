package org.winter.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.winter.system.domain.Menu;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long>, JpaSpecificationExecutor<Menu> {

    /**
     * 根据关联表sys_role_menu的role_id查询Sys_menu
     * 由于多对多关系，注意要去重distinct
     */
    @Query(value = "SELECT distinct m.* FROM sys_menu m, sys_role_menu rm WHERE m.id= rm.menu_id AND rm.role_id IN ?1 and m.type=?2", nativeQuery = true)
    List<Menu> findByRoleIdsAndType(List<Long> roleIds, String type);

    @Query("select id from sys_menu")
    List<Long> findAllId();

    @Query(value = "select count(1) from sys_menu where parent_id=?1", nativeQuery = true)
    Long countChildren(Long id);

    Menu getById(Long id);

}
