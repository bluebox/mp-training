package com.library.Library.repository;

import com.library.Library.model.BookLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookLogRepository extends JpaRepository<BookLog, Integer> {}
