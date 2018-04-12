package cn.xiaoyu.framework.ioc.domain;

/**
 * @author Roin_zhang
 * @date 2018/4/10 7:18
 */
public class Robot {
    private Hand hand;

    private Mouth mouth;

    public void show() {
        hand.waveHand();
        mouth.speak();
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public Mouth getMouth() {
        return mouth;
    }

    public void setMouth(Mouth mouth) {
        this.mouth = mouth;
    }
}
