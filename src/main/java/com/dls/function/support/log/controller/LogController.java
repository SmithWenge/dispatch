package com.dls.function.support.log.controller;

import com.google.common.base.Optional;
import com.dls.arc.utils.ConstantFields;
import com.dls.function.support.log.LogContent;
import com.dls.function.support.log.service.LogServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/admin/log")
public class LogController {
    @Autowired
    private LogServiceI logService;

    @RequestMapping(value = "/page")
    public ModelAndView showLog(@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE)
                                        Pageable pageable, LogContent logContent, HttpSession session) {
        LogContent searchLog = (LogContent) session.getAttribute(ConstantFields.SESSION_LOG_SEARCH_KEY);

        Optional<LogContent> optional = Optional.fromNullable(searchLog);
        if (optional.isPresent()) {
            logContent = searchLog;
        }

        ModelAndView mav = new ModelAndView("admin/log/logPage");
        Page<LogContent> page = logService.query4Page(logContent, pageable);

        mav.addObject(ConstantFields.PAGE_KEY, page);

        return mav;
    }

    @RequestMapping(value = "/pageSearch")
    public ModelAndView searchLog(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startTime,
                                  @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endTime,
                                  @PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE) Pageable pageable,
                                  LogContent logContent, HttpSession session) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (startTime !=null) {
            String tmpStartTime = sdf.format(startTime);
            logContent.setQueryStartTime(Timestamp.valueOf(tmpStartTime));
        }

        if (endTime !=null) {
            String tmpEndTime = sdf.format(endTime);
            logContent.setQueryEndTime(Timestamp.valueOf(tmpEndTime));
        }

        session.setAttribute(ConstantFields.SESSION_LOG_SEARCH_KEY, logContent);

        ModelAndView mav = new ModelAndView("admin/log/logPage");
        Page<LogContent> page = logService.query4Page(logContent, pageable);

        mav.addObject(ConstantFields.PAGE_KEY, page);

        return mav;
    }

    @RequestMapping(value = "/routePage", method = RequestMethod.GET)
    public ModelAndView showFirstPage(HttpSession session) {
        session.removeAttribute(ConstantFields.SESSION_LOG_SEARCH_KEY);

        ModelAndView mav = new ModelAndView("admin/log/logPage");

        Page<LogContent> contents = logService.query4Page(new LogContent(-1), new PageRequest(0, ConstantFields.DEFAULT_PAGE_SIZE));
        mav.addObject(ConstantFields.PAGE_KEY, contents);

        return mav;
    }
}
