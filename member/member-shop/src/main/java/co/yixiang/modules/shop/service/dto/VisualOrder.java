package co.yixiang.modules.shop.service.dto;

import co.yixiang.modules.shop.domain.YxUser;

import java.util.List;

public class VisualOrder {
    public List<YxUser> getUserlist() {
        return userlist;
    }

    public void setUserlist(List<YxUser> userlist) {
        this.userlist = userlist;
    }

    public Integer getGiftid() {
        return giftid;
    }

    public void setGiftid(Integer giftid) {
        this.giftid = giftid;
    }

    private List<YxUser> userlist;
    private Integer giftid;
}
