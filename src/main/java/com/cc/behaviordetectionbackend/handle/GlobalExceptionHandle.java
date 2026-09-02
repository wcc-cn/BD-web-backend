package com.cc.behaviordetectionbackend.handle;

import cn.dev33.satoken.exception.NotLoginException;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName GlobalExceptionHandle
 * @Description 全局异常处理类
 * @Author cc
 * @Date 2025/11/29 12:42
 * @Version 1.0.0
 */
@Hidden
@RestControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(NotLoginException.class)
    public ResponseEntity<Map<String, Object>> handleNotLoginException(NotLoginException e) {
        Map<String, Object> res = new HashMap<>();
        res.put("code", 401);
        res.put("msg", "未登录或登录已过期");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
    }

}
