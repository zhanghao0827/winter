package org.winter.monitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.winter.monitor.domain.Log;

public interface LogRepository extends JpaRepository<Log, Long>, JpaSpecificationExecutor<Log> {

}
