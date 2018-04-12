package cn.xiaoyu.framework.ioc.test;

import cn.xiaoyu.framework.ioc.core.JsonApplicationContext;
import cn.xiaoyu.framework.ioc.domain.Robot;

/**
 * @author Roin_zhang
 * @date 2018/4/12 9:51
 */
public class TestMain {
    public static void main(String[] args) throws Exception {
        JsonApplicationContext applicationContext = new JsonApplicationContext("application.json");
        applicationContext.init();
        Robot aiRobot = (Robot) applicationContext.getBean("robot");

        aiRobot.show();
    }
}
