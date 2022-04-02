package ru.job4j.shortcut.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private final ObjectMapper objectMapper;

    public GlobalExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @ExceptionHandler(value = {IllegalArgumentException.class, NullPointerException.class})
    public void handleIllegalArgument(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("-------------------Начало handleIllegalArgument метода в GlobalExceptionHandler---------------------------");
        logger.error(e.getMessage());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() { {
            put("message", "Параметры запроса не верны");
            put("details", e.getMessage());
        }}));
        logger.info("Клиенту отправлен response");
        logger.info("------------------------Конец handleIllegalArgument------------------------------");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public void conflict(DataIntegrityViolationException e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("-------------------Начало conflict метода в GlobalExceptionHandler---------------------------");
        logger.error(e.getMessage());
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String message = NestedExceptionUtils.getMostSpecificCause(e).getMessage();
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() { {
            put("message", "Ошибка SQL операции");
            put("details", message);
        }}));
        logger.info("Клиенту отправлен response");
        logger.info("------------------------Конец conflict------------------------------");
    }
}
