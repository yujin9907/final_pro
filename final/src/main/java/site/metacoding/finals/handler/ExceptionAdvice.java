package site.metacoding.finals.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import site.metacoding.finals.dto.ResponseDto;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseDto<?> globalException(Exception e) {
        log.debug("디버그 : " + e);
        return new ResponseDto<>(-1, e.getMessage(), null);
    }
}
