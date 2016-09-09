/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.alibaba.rocketmq.console.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.common.protocol.body.ClusterInfo;
import com.alibaba.rocketmq.console.service.ClusterService;

/**
 * 集群相关
 * @author lvhonglei
 * @version $Id: ClusterController.java, v 0.1 2016年9月8日 下午5:12:57 lvhonglei Exp $
 */
@Controller
@RequestMapping("/cluster")
public class ClusterController {
    @Resource
    private ClusterService clusterService;
    /**
     * 请求集群信息
     * @return
     * @date: 2016年9月8日 下午7:18:13
     * @user lvhonglei
     */
    @RequestMapping("/list")
    public String getClusterInfo(){
        return "/cluster/list";
    }
    @RequestMapping("/listdata")
    @ResponseBody
    public String getClusterInfoData(){
        ClusterInfo clusterInfo = clusterService.getClusterInfo();
        return JSON.toJSONString(clusterInfo);
    }
}
