package com.capgemini.jtp.config;

import com.capgemini.jtp.aop.OperationLogAspect;
import com.capgemini.jtp.common.UserUtils;
//import RespBean;
//import com.capgemini.jtp.entity.Hr;
import com.capgemini.jtp.entity.LoginLog;
import com.capgemini.jtp.entity.User;
import com.capgemini.jtp.service.LoginLogService;
import com.capgemini.jtp.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.capgemini.jtp.service.HrService;
import com.capgemini.jtp.vo.base.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: TODO
 * @Classname : WebSecurityConfig
 * @author: Jason Jin
 * @date: 2019/5/19 11:47 PM
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    LoginLogService loginLogService;

    @Autowired
    UserService userService;

    @Autowired
    CustomMetadataSource metadataSource;

    @Autowired
    UrlAccessDecisionManager urlAccessDecisionManager;

    @Autowired
    AuthenticationAccessDeniedHandler deniedHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/index.html", "/static/**", "/login_p", "/favicon.ico","/swagger-ui.html","/webjars/**","/swagger-resources/**","/v2/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(metadataSource);
                        o.setAccessDecisionManager(urlAccessDecisionManager);
                        return o;
                    }
                })
                .and().cors()
                .and()
                .formLogin().loginPage("/login_p").loginProcessingUrl("/login")
                .usernameParameter("username").passwordParameter("password")
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req,
                                                        HttpServletResponse resp,
                                                        AuthenticationException e) throws IOException {
                        resp.setContentType("application/json;charset=utf-8");
                        RespBean respBean = null;
                        if (e instanceof BadCredentialsException ||
                                e instanceof UsernameNotFoundException) {
                            respBean = RespBean.error("账户名或者密码输入错误!");
                        } else if (e instanceof LockedException) {
                            respBean = RespBean.error("账户被锁定，请联系管理员!");
                        } else if (e instanceof CredentialsExpiredException) {
                            respBean = RespBean.error("密码过期，请联系管理员!");
                        } else if (e instanceof AccountExpiredException) {
                            respBean = RespBean.error("账户过期，请联系管理员!");
                        } else if (e instanceof DisabledException) {
                            respBean = RespBean.error("账户被禁用，请联系管理员!");
                        } else {
                            respBean = RespBean.error("登录失败!");
                        }

                        // 写入登录失败信息
                        resp.setStatus(401);
                        ObjectMapper om = new ObjectMapper();
                        PrintWriter out = resp.getWriter();
                        out.write(om.writeValueAsString(respBean));
                        out.flush();
                        out.close();

                        // 登录日志的信息
                        LoginLog loginLog = new LoginLog();
                        loginLog.setUserId(0);
                        loginLog.setLoginDesc(respBean.getMsg());
                        loginLog.setLoginUserIp(TrueIP.getIpAddr(req));
                        loginLog.setLoginTime(new Date());
                        loginLog.setIfSuccess("0");
                        loginLogService.insertSelective(loginLog);
                    }
                })
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req,
                                                        HttpServletResponse resp,
                                                        Authentication auth) throws IOException {
                        resp.setContentType("application/json;charset=utf-8");
//                        Hr hr = UserUtils.getCurrentUser();
                        User user = UserUtils.getCurrentUser();


                        HttpSession session = req.getSession();
//                        session.setAttribute("operationUserId", hr.getId());
                        session.setAttribute("operationUserId", user.getUserId());

//                        RespBean respBean = RespBean.ok("登录成功!", hr);
                        RespBean respBean = RespBean.ok("登录成功!", user);

                        ObjectMapper om = new ObjectMapper();
                        PrintWriter out = resp.getWriter();
                        out.write(om.writeValueAsString(respBean));
                        out.flush();
                        out.close();

                        LoginLog loginLog = new LoginLog();
                        loginLog.setUserId(user.getUserId());
                        loginLog.setChineseName(user.getChineseName());

                        loginLog.setLoginDesc("登录成功");
                        loginLog.setLoginUserIp(TrueIP.getIpAddr(req));
                        loginLog.setLoginTime(new Date());
                        loginLog.setIfSuccess("1");
                        loginLogService.insertSelective(loginLog);

                        Map<Integer, String> usera = new HashMap<>();
                        usera.put(user.getUserId(), user.getChineseName());
                        OperationLogAspect.setUserInfo(usera);

                    }
                })
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        RespBean respBean = RespBean.ok("注销成功!");
                        ObjectMapper om = new ObjectMapper();
                        PrintWriter out = resp.getWriter();
                        out.write(om.writeValueAsString(respBean));
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and().csrf().disable()
                .exceptionHandling().accessDeniedHandler(deniedHandler);
    }
}