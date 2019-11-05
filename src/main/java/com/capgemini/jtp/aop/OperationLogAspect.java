package com.capgemini.jtp.aop;


import com.capgemini.jtp.entity.OperateLog;
import com.capgemini.jtp.service.OperateLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * create by: MmmLll_Shen
 * description:操作日志
 * create time: 10:43 2019/10/11
 */

@Aspect
@Component
public class OperationLogAspect {

    private OperateLog operateLog = new OperateLog();

    @Autowired
    OperateLogService operateLogService;

    /**
     * 存储当前登录用户的 id 和 cname
     */
    private static Map<Integer, String> user = new HashMap<>();

    /**
     * 用户的信息由 config 中在用户正确登录之后设置
     * @param u
     * @return
     */
    public static Map<Integer, String> setUserInfo(Map<Integer, String> u) {
        user = u;
        return user;
    }


    /**
     * 查询业务操作的切面
     */
    @Pointcut("execution(public * com.capgemini.jtp.controller.*.select*(..))")
    public void selectCell() {

    }

    /**
     * 添加业务操作的切面
     */
    @Pointcut("execution(public * com.capgemini.jtp.controller.*.add*(..))")
    public void addCell() {

    }

    /**
     * 更新业务操作的切面
     */
    @Pointcut("execution(public * com.capgemini.jtp.controller.*.update*(..))")
    public void updateCell() {

    }

    /**
     * 删除业务操作的切面
     */
    @Pointcut("execution(public * com.capgemini.jtp.controller.*.delete*(..))")
    public void deleteCell() {

    }

    /**
     * 查询业务操作的切面
     */
    @Pointcut("execution(public * com.capgemini.jtp.controller.*.load*(..))")
    public void loadCell() {

    }

    /**
     * 统计业务操作的切面
     */
    @Pointcut("execution(public * com.capgemini.jtp.controller.*.count*(..))")
    public void countCell(){

    }

    /**
     * 发布信息业务的切面
     */
    @Pointcut("execution(public * com.capgemini.jtp.controller.*.publish*(..))")
    public void publishCell() {

    }

    /**
     * @Date: 2019/09/09
     * @Desc: 上传文件
     */
    @Pointcut("execution(public * com.capgemini.jtp.controller.*.upload*(..))")
    public void uploadCell() {

    }

    /**
     * @Date: 2019/09/09
     * @Desc: 将文件移动到垃圾站
     */
    @Pointcut("execution(public * com.capgemini.jtp.controller.*.move*(..))")
    public void moveCell() {

    }

    /**
     * @Date: 2019/09/09
     * @Desc: 创建文件
     */
    @Pointcut("execution(public * com.capgemini.jtp.controller.*.create*(..))")
    public void createCell() {

    }

    /**
     * @Date: 2019/09/09
     * @Desc: 恢复删除的内容
     */
    @Pointcut("execution(public * com.capgemini.jtp.controller.*.recovery*(..))")
    public void recoveryCell() {

    }

    /**
     * @Date: 2019/09/09
     * @Desc: 恢复已经删除内容
     * @param joinPoint
     * @param result
     */
    @AfterReturning(value = "recoveryCell()", returning = "result")
    public void recoveryLog(JoinPoint joinPoint, Object result) {
        /**
         * 获取目标类的方法签名
         */
        String methodName = joinPoint.getSignature().getName();
        insertToDatabase("恢复", methodName);
    }

    /**
     * @Date: 2019/09/09
     * @Desc: 创建文件或目录
     * @param joinPoint
     * @param result
     */
    @AfterReturning(value = "createCell()", returning = "result")
    public void createLog(JoinPoint joinPoint, Object result) {
        /**
         * 获取目标类的方法签名
         */
        String methodName = joinPoint.getSignature().getName();
        insertToDatabase("创建", methodName);
    }


    /**
     * @Date: 2019/09/09
     * @Desc: 将文件移动到垃圾站
     * @param joinPoint
     * @param result
     */
    @AfterReturning(value = "moveCell()", returning = "result")
    public void moveLog(JoinPoint joinPoint, Object result) {
        /**
         * 获取目标类的方法签名
         */
        String methodName = joinPoint.getSignature().getName();
        insertToDatabase("移动", methodName);
    }


    /**
     * @Date: 2019/09/09
     * @Desc: 上传文件的日志
     * @param joinPoint
     * @param result
     */
    @AfterReturning(value = "uploadCell()", returning = "result")
    public void uploadLog(JoinPoint joinPoint, Object result) {
        /**
         * 获取目标类的方法签名
         */
        String methodName = joinPoint.getSignature().getName();
        insertToDatabase("上传", methodName);
    }

    /**
     * 存储添加操作的操作日志
     * @param joinPoint
     * @param result
     */
    @AfterReturning(value = "addCell()", returning = "result")
    public void addLog(JoinPoint joinPoint, Object result) {
        /**
         * 获取目标类的方法签名
         */
        String methodName = joinPoint.getSignature().getName();
        insertToDatabase("添加", methodName);
    }

    /**
     * 存储更新操作的操作日志
     * @param joinPoint
     * @param result
     */
    @AfterReturning(value = "updateCell()", returning = "result")
    public void updateLog(JoinPoint joinPoint, Object result) {
        /**
         * 获取目标类的方法签名
         */
        String methodName = joinPoint.getSignature().getName();
        insertToDatabase("更新", methodName);
    }

    /**
     * 存储删除操作的日志
     * @param joinPoint
     * @param result
     */
    @AfterReturning(value = "deleteCell()", returning = "result")
    public void deleteLog(JoinPoint joinPoint, Object result) {
        /**
         * 获取目标类的方法签名
         */
        String methodName = joinPoint.getSignature().getName();
        insertToDatabase("删除", methodName);
    }

    /**
     * 存储查询操作的日志
     * @param joinPoint
     * @param result
     */
    @AfterReturning(value = "loadCell()", returning = "result")
    public void loadLog(JoinPoint joinPoint, Object result) {
        /**
         * 获取目标类的方法签名
         */
        String methodName = joinPoint.getSignature().getName();
        insertToDatabase("查询", methodName);
    }

    /**
     * 存储查询操作的日志
     * @param joinPoint
     * @param result
     */
    @AfterReturning(value = "selectCell()", returning = "result")
    public void selectLog(JoinPoint joinPoint, Object result) {
        /**
         * 获取目标类的方法签名
         */
        String methodName = joinPoint.getSignature().getName();
        insertToDatabase("查询", methodName);
    }

    /**
     * 存储统计信息的日志
     * @param joinPoint
     * @param result
     */
    @AfterReturning(value = "countCell()", returning = "result")
    public void countLog(JoinPoint joinPoint, Object result) {
        /**
         * 获取目标类的方法签名
         */
        String methodName = joinPoint.getSignature().getName();
        insertToDatabase("统计", methodName);
    }

    @AfterReturning(value = "publishCell()", returning = "result")
    public void publicLog(JoinPoint joinPoint, Object result) {
        /**
         * 获取目标类的方法签名
         */
        String methodName = joinPoint.getSignature().getName();
        insertToDatabase("发布信息", methodName);
    }

    /**
     * 向数据库中插入操作日志，将重复的内容提取出来
     * @param operateName
     * @param methodName
     */
    public void insertToDatabase(String operateName, String methodName) {
        Integer userId = (Integer)OperationLogAspect.user.keySet().toArray()[0];
        String cname = OperationLogAspect.user.get(userId);

        operateLog.setUserId(userId);
        operateLog.setObjectId(cname);
        operateLog.setOperateName(operateName);
        operateLog.setOperateDesc("执行的操作是：" + methodName);
        operateLog.setOperateTime(new Date());
//        System.out.println(operateLog);
        operateLogService.insert(operateLog);
    }

}
