package com.example.springsecurity.config;

import com.example.springsecurity.entities.Log;
import com.example.springsecurity.reposiroty.LogRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class LogInterceptor extends HandlerInterceptorAdapter {

    private final LogRepository logRepository;

    public LogInterceptor(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String apiKey = request.getHeader("api-key");
        if ("khue123".equals(apiKey)){
            Log log=new Log();
            log.setTime(new Date());
            log.setEndpointName(request.getRequestURI());
            logRepository.save(log);
            return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
