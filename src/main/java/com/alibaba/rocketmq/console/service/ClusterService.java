/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.alibaba.rocketmq.console.service;

import com.alibaba.rocketmq.common.protocol.body.ClusterInfo;


/**
 * 
 * @author lvhonglei
 * @version $Id: ClusterService.java, v 0.1 2016年9月8日 下午5:27:15 lvhonglei Exp $
 */
public interface ClusterService {
    /**
     * 获取集群信息
     * @return
     * @date: 2016年9月8日 下午5:28:24
     * @user lvhonglei
     */
    public ClusterInfo getClusterInfo();
}
