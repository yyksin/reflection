package com.cos.reflect.filter;

import com.cos.reflect.ano.RequestMapping;
import com.cos.reflect.controller.UserController;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Enumeration;

public class Dispatcher implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        UserController userController = new UserController();

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Method[] methods = userController.getClass().getDeclaredMethods();
        for(Method method : methods){
            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);

            String endPoint = request.getRequestURI();

            if(requestMapping.value().equals(endPoint)){

                try {
                    Parameter[] params = method.getParameters();
                    String path;
                    if(params.length > 0){
                        Object dtoInstance = params[0].getType().newInstance();
                        setData(dtoInstance, request);
                    }
                    //메서드 실행 및 리턴값
                    path = (String) method.invoke(userController);
                    RequestDispatcher dis = request.getRequestDispatcher(path);
                    dis.forward(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String keyToMethodKey(String key){
        String firstkey = key.substring(0, 1);
        String remainKey = key.substring(1);
        return "set" + firstkey.toUpperCase() + remainKey;
    }

    private <T> void setData(T instance, HttpServletRequest request){
        System.out.println("instance type : " + instance.getClass());
        Enumeration<String> params = request.getParameterNames();

        while(params.hasMoreElements()){
            String key = params.nextElement();
            String methodKey = keyToMethodKey(key);
            Method[] methods = instance.getClass().getMethods();
            for(Method m : methods){
                if(m.getName().equals(methodKey)){
                    try{
                        m.invoke(instance, request.getParameter(key));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
