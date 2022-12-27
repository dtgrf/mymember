package co.yixiang.modules.activity.domain;

import co.yixiang.modules.shop.domain.YxUser;

import java.util.List;

public class VisualCoupon {
    public List<YxUser> getUserlist() {
        return userlist;
    }

    public void setUserlist(List<YxUser> userlist) {
        this.userlist = userlist;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private List<YxUser> userlist;
    private Integer id;
}
