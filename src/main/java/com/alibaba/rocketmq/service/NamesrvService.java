package com.alibaba.rocketmq.service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.cli.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.rocketmq.common.Table;
import com.alibaba.rocketmq.tools.admin.DefaultMQAdminExt;
import com.alibaba.rocketmq.tools.command.namesrv.DeleteKvConfigCommand;
import com.alibaba.rocketmq.tools.command.namesrv.UpdateKvConfigCommand;
import com.alibaba.rocketmq.tools.command.namesrv.WipeWritePermSubCommand;
import com.alibaba.rocketmq.validate.CmdTrace;


/**
 * 
 * @author yankai913@gmail.com
 * @date 2014-2-17
 */
@Service
public class NamesrvService extends AbstractService {

    static final Logger logger = LoggerFactory.getLogger(NamesrvService.class);

    static final DeleteKvConfigCommand deleteKvConfigCommand = new DeleteKvConfigCommand();


    public Collection<Option> getOptionsForDeleteKvConfig() {
        return getOptions(deleteKvConfigCommand);
    }


    @CmdTrace(cmdClazz = DeleteKvConfigCommand.class)
    public boolean deleteKvConfig(String namespace, String key) throws Throwable {
        Throwable t = null;
        DefaultMQAdminExt defaultMQAdminExt = getDefaultMQAdminExt();
        try {
            defaultMQAdminExt.start();
            defaultMQAdminExt.deleteKvConfig(namespace, key);
            return true;
        }
        catch (Throwable e) {
            logger.error(e.getMessage(), e);
            t = e;
        }
        finally {
            shutdownDefaultMQAdminExt(defaultMQAdminExt);
        }
        throw t;
    }

    static final UpdateKvConfigCommand updateKvConfigCommand = new UpdateKvConfigCommand();


    public Collection<Option> getOptionsForUpdateKvConfig() {
        return getOptions(updateKvConfigCommand);
    }


    @CmdTrace(cmdClazz = UpdateKvConfigCommand.class)
    public boolean updateKvConfig(String namespace, String key, String value) throws Throwable {
        Throwable t = null;
        DefaultMQAdminExt defaultMQAdminExt = new DefaultMQAdminExt();
        try {
            defaultMQAdminExt.start();
            defaultMQAdminExt.createAndUpdateKvConfig(namespace, key, value);
            return true;
        }
        catch (Throwable e) {
            logger.error(e.getMessage(), e);
            t = e;
        }
        finally {
            shutdownDefaultMQAdminExt(defaultMQAdminExt);
        }
        throw t;
    }

    static final WipeWritePermSubCommand wipeWritePermSubCommand = new WipeWritePermSubCommand();
    
    public Collection<Option> getOptionsForWipeWritePerm() {
        return getOptions(wipeWritePermSubCommand);
    }
    
    @CmdTrace(cmdClazz = WipeWritePermSubCommand.class)
    public Table wipeWritePerm(String brokerName) throws Throwable {
        Throwable t = null;
        DefaultMQAdminExt defaultMQAdminExt = getDefaultMQAdminExt();
        try {
            defaultMQAdminExt.start();
            List<String> namesrvList = defaultMQAdminExt.getNameServerAddressList();
            if (namesrvList != null) {
                List<Map<String, String>> result = new ArrayList<Map<String, String>>();
                for (String namesrvAddr : namesrvList) {
                    try {
                        int wipeTopicCount = defaultMQAdminExt.wipeWritePermOfBroker(namesrvAddr, brokerName);
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("brokerName", brokerName);
                        map.put("namesrvAddr", namesrvAddr);
                        map.put("wipeTopicCount", String.valueOf(wipeTopicCount));
                        result.add(map);
                        // System.out.printf("wipe write perm of broker[%s] in name server[%s] OK, %d\n",//
                        // brokerName,//
                        // namesrvAddr,//
                        // wipeTopicCount//
                        // );
                    }
                    catch (Exception e) {
                        System.out.printf("wipe write perm of broker[%s] in name server[%s] Failed\n",//
                            brokerName,//
                            namesrvAddr//
                            );

                        logger.error(e.getMessage(), e);
                    }
                }
                Table table = Table.Maps2HTable(result);
                return table;
            }
        }
        catch (Throwable e) {
            logger.error(e.getMessage(), e);
            t = e;
        }
        finally {
            shutdownDefaultMQAdminExt(defaultMQAdminExt);
        }
        throw t;
    }
}
