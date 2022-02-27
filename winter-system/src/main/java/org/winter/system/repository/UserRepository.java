package org.winter.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.winter.system.domain.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User findByUsername(String username);

    User getById(Long id);

    @Modifying
    @Transactional
    @Query("update sys_user set deleted=1 where id in (:ids) ")
    int deleteBatch(@Param("ids") List<Long> ids);

    @Modifying
    @Transactional
    @Query(value = "delete from sys_user_role where user_id in (:userIds) ", nativeQuery = true)
    void deleteUserRoleByUserIds(@Param("userIds") List<Long> userIds);

    @Modifying
    @Transactional
    @Query(value = "update sys_user set is_enabled=:#{#user.enabled} where id=:#{#user.id}", nativeQuery = true)
    int updateEnabled(@Param("user") User user);


}
