package shop.mtcoding.blog._core.errors;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.mtcoding.blog._core.errors.exception.*;

// RuntimeException이 터지면 해당 파일로 오류가 모인다
@Slf4j // test 코드에 사용하면 무조건 실패함
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(Exception400.class)
    public String ex400(Exception400 e, HttpServletRequest request){
        request.setAttribute("msg", e.getMessage());
        log.warn("400 : " + e.getMessage());
        return "err/400";
    }
    @ExceptionHandler(Exception401.class)
    public String ex401(Exception401 e, HttpServletRequest request){
        request.setAttribute("msg", e.getMessage());
        log.warn("401 : " + e.getMessage());
        log.warn("IP : " + request.getRemoteAddr());
        log.warn("WAY : " + request.getHeader("User-Agent"));
        return "err/401";
    }
    @ExceptionHandler(Exception403.class)
    public String ex403(RuntimeException e, HttpServletRequest request){
        request.setAttribute("msg", e.getMessage());
        log.warn("403 : " + e.getMessage());
        // 비정상적인 접근이 여러번 반복될 경우 SMS 전송하는 로직 추가도 필요함
        // 변수 하나로는 절대 안됨, 변수가 아닌 map을 사용해서 설정해줘야 함
        return "err/403";
    }
    @ExceptionHandler(Exception404.class)
    public String ex404(RuntimeException e, HttpServletRequest request){
        request.setAttribute("msg", e.getMessage());
        log.info("404 : " + e.getMessage());
        return "err/404";
    }
    @ExceptionHandler(Exception500.class)
    public String ex500(RuntimeException e, HttpServletRequest request){
        request.setAttribute("msg", e.getMessage());
        log.error("500 : " + e.getMessage());
        return "err/500";
    }
}
