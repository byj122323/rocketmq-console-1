/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.alibaba.rocketmq.console.common;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.tools.admin.DefaultMQAdminExt;

/**
 * 
 * @author lvhonglei
 * @version $Id: DefaultMQAdminUtils.java, v 0.1 2016年9月8日 下午5:48:23 lvhonglei Exp $
 */
public class DefaultMQAdminUtils {
    /**
     * 私有构造方法，禁止实例化
     */
    private DefaultMQAdminUtils(){}
    /**
     * 获取对象并启动
     * @return
     * @date: 2016年9月8日 下午5:52:17
     * @user lvhonglei
     */
    public static DefaultMQAdminExt getDefaultMQAdminExt(){
        DefaultMQAdminExt defaultMQAdminExt = new DefaultMQAdminExt();
        defaultMQAdminExt.setInstanceName(String.valueOf(System.currentTimeMillis()));
        try {
            defaultMQAdminExt.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        return defaultMQAdminExt;
    }
    /**
     * 停止
     * @param defaultMQAdminExt
     * @date: 2016年9月8日 下午5:52:39
     * @user lvhonglei
     */
    public static void shotdown(DefaultMQAdminExt defaultMQAdminExt){
        defaultMQAdminExt.shutdown();
    }
}
