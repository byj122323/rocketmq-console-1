/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.alibaba.rocketmq.console.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.common.MixAll;

/**
 * 
 * @author lvhonglei
 * @version $Id: ConfigController.java, v 0.1 2016年9月8日 下午7:19:10 lvhonglei Exp $
 */
@RequestMapping("/config")
public class ConfigController {
    /**
     * 配置namesrvaddr
     * @param nameSrvAddr
     * @return
     * @date: 2016年9月8日 下午7:21:03
     * @user lvhonglei
     */
    @RequestMapping("/namesrvaddr")
    @ResponseBody
    public String configNameSrvAddr(String nameSrvAddr){
        Map<String,String> resultMap = new HashMap<String,String>();
        if(StringUtils.isBlank(nameSrvAddr)){
            resultMap.put("Message", "监听地址必填");
            return JSON.toJSONString(resultMap);
        }
        System.setProperty(MixAll.NAMESRV_ADDR_PROPERTY, nameSrvAddr);
        resultMap.put("Message", "设置成功");
        return JSON.toJSONString(resultMap);
    }
}
