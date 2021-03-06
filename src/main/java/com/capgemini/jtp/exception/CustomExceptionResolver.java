package com.capgemini.jtp.exception;

import com.capgemini.jtp.vo.base.RespBean;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: TODO
 * @Classname : CustomExceptionResolver
 * @author: Jason Jin
 * @date: 2019/5/19 11:46 PM
 */
@Component
@ControllerAdvice
public class CustomExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse response, Object o, Exception e) {
        e.printStackTrace();
        ModelAndView mv = new ModelAndView(new MappingJackson2JsonView());
        Map<String, Object> map = new HashMap<>();
        map.put("status", 500);
        map.put("msg", "操作失败!");
        mv.addAllObjects(map);
        return mv;
    }


    /**
     * @author: 陈宇雄
     * @date: 2019/07/28
     * @description: MethodArgumentNotValidException异常返回处理
     * @classname: CustomExceptionResolver
     */
    @ResponseBody
    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handleValidationExceptions(MethodArgumentNotValidException  exception){
        System.out.println("BindExceptionHandler!");
        String errorMessage = exception.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("\n", "[", "]"));
        System.out.println(errorMessage);
        return RespBean.error(errorMessage);
    }

}