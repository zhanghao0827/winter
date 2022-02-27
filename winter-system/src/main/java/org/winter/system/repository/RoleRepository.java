package org.winter.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.winter.system.domain.Role;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {

    @Query(value = "select role_id from sys_user_role where user_id=?1", nativeQuery = true)
    List<Long> findRoleIdsByUserId(Long userId);

    Role getById(Long id);

}
