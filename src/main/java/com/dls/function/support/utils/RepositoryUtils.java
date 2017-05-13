package com.dls.function.support.utils;

import com.dls.arc.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositoryUtils<T extends Entry> {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public RepositoryUtils(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Page<T> select4Page(String sql, Pageable pageable, Object[] args, RowMapper<T> rowMapper) {
        String countSql = buildCountSql(sql);
        int maxCount = queryMaxCount(countSql, args);
        String pageSql = buildPageSql(sql, pageable);

        List<T> list = null;

        try {
            list = jdbcTemplate.query(pageSql, args, rowMapper);
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        Page<T> result = new PageImpl<T>(list, pageable, maxCount);

        return result;
    }

    private String buildPageSql(String sql, Pageable pageable) {
        StringBuffer buffer = new StringBuffer(sql)
                .append(mysql2PageSql(pageable.getPageNumber(), pageable.getPageSize()));

        return buffer.toString();
    }

    private String mysql2PageSql(int pageNumber, int pageSize) {
        StringBuffer buffer = new StringBuffer(" limit ")
                .append(searchStartPoint(pageNumber, pageSize)).append(",").append(pageSize);

        return buffer.toString();
    }

    private int searchStartPoint(int pageNumber, int pageSize) {
        int start = 0;

        pageNumber = pageNumber < 0 ? 1 : pageNumber;
        pageSize = pageSize < 1 ? 1 : pageSize;

        start = pageNumber * pageSize;

        return start;
    }

    private static final String COUNT_SQL_PREFIX = "SELECT COUNT(1) FROM (";
    private static final String COUNT_SQL_SUFFIX = ") AS countSql";

    private String buildCountSql(String sql) {
        StringBuffer buffer = new StringBuffer(COUNT_SQL_PREFIX)
                .append(sql).append(COUNT_SQL_SUFFIX);

        return buffer.toString();
    }

    private int queryMaxCount(String sql, Object[] args) {
        Number number =(Number) jdbcTemplate.queryForObject(sql, args, Integer.class);
        return number != null ? number.intValue() : 0;
    }

}
