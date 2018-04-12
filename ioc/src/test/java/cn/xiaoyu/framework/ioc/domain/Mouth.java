package cn.xiaoyu.framework.ioc.domain;

/**
 * @author Roin_zhang
 * @date 2018/4/10 7:17
 */
public class Mouth {
    private Hand hand;
    private String code;

    public void speak() {
        System.out.println("mouth 编号：" + code + ",依赖于hand 编号" + hand.getCode());
        System.out.println("say hello world");
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
