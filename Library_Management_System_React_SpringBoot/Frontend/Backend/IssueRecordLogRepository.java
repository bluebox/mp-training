package com.library.Library.repository;

import com.library.Library.model.IssueRecordLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRecordLogRepository extends JpaRepository<IssueRecordLog, Integer> {
}
