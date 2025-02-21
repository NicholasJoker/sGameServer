package com.fps.tasks;

import com.fps.dao.UsersDao;
import com.fps.entity.api.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class CommonTask {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    UsersDao usersDao;

    //每隔一秒钟统计一次：*/1 * * ? * *
    //每隔一分钟统计一次：0 */1 * ? * *
    //每隔一小时统计一次：0 0 */1 ? * *


    @Scheduled(cron = "*/5 * * ? * *")
    public void statisticalFinancialDaily() {
//        int i = usersDao.updateNotClaimed(Constant.Gold_Sync_Time);
    }


}
