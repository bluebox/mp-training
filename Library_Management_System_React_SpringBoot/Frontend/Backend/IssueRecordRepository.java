package com.library.Library.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.library.Library.model.IssueRecord;

@Repository
public interface IssueRecordRepository extends JpaRepository<IssueRecord, Integer> {
	List<IssueRecord> findByStatus(char status);

    List<IssueRecord> findByStatusAndIssueDateBefore(char status, LocalDate cutoffDate);
    boolean existsByMemberId(Integer memberId);
}