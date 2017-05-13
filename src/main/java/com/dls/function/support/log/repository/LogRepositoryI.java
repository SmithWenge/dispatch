package com.dls.function.support.log.repository;

import com.dls.function.support.log.LogContent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LogRepositoryI {
    void insertLog(LogContent logContent);
    Page<LogContent> select4Page(LogContent logContent, Pageable pageable);
}
