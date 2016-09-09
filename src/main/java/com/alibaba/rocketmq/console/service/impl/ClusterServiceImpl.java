/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.alibaba.rocketmq.console.service.impl;

import org.springframework.stereotype.Service;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.common.MixAll;
import com.alibaba.rocketmq.common.protocol.body.ClusterInfo;
import com.alibaba.rocketmq.console.common.DefaultMQAdminUtils;
import com.alibaba.rocketmq.console.service.ClusterService;
import com.alibaba.rocketmq.remoting.exception.RemotingConnectException;
import com.alibaba.rocketmq.remoting.exception.RemotingSendRequestException;
import com.alibaba.rocketmq.remoting.exception.RemotingTimeoutException;
import com.alibaba.rocketmq.tools.admin.DefaultMQAdminExt;

/**
 * 
 * @author lvhonglei
 * @version $Id: ClusterServiceImpl.java, v 0.1 2016年9月8日 下午5:35:22 lvhonglei Exp $
 */
@Service("clusterService")
public class ClusterServiceImpl implements ClusterService{
    
    /** 
     * @see com.alibaba.rocketmq.console.service.ClusterService#getClusterInfo()
     */
    @Override
    public ClusterInfo getClusterInfo() {
        ClusterInfo clusterInfo = null;
        DefaultMQAdminExt defaultMQAdminExt = DefaultMQAdminUtils.getDefaultMQAdminExt();
        try {
            clusterInfo = defaultMQAdminExt.examineBrokerClusterInfo();
            DefaultMQAdminUtils.shotdown(defaultMQAdminExt);
        } catch (RemotingConnectException e) {
            e.printStackTrace();
        } catch (RemotingTimeoutException e) {
            e.printStackTrace();
        } catch (RemotingSendRequestException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        }
        return clusterInfo;
    }
    
    public static void main(String[] args) {
        ClusterInfo clusterInfo = null;
        System.setProperty(MixAll.NAMESRV_ADDR_PROPERTY, "192.168.5.90:9876");
        DefaultMQAdminExt defaultMQAdminExt = DefaultMQAdminUtils.getDefaultMQAdminExt();
        try {
            clusterInfo = defaultMQAdminExt.examineBrokerClusterInfo();
        } catch (RemotingConnectException e) {
            e.printStackTrace();
        } catch (RemotingTimeoutException e) {
            e.printStackTrace();
        } catch (RemotingSendRequestException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        }
        DefaultMQAdminUtils.shotdown(defaultMQAdminExt);
        System.out.println(clusterInfo.toJson());
    }

}
