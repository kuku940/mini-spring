package cn.xiaoyu.framework.ioc.domain;

/**
 * @author Roin_zhang
 * @date 2018/4/10 7:17
 */
public class Hand {
    private Mouth mouth;
    private String code;

    public void waveHand() {
        System.out.println("hand 编号：" + code + ",依赖于mouth 编号" + mouth.getCode());
        System.out.println("挥一挥手");
    }

    public Mouth getMouth() {
        return mouth;
    }

    public void setMouth(Mouth mouth) {
        this.mouth = mouth;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
