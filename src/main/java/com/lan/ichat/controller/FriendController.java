package com.lan.ichat.controller;

import com.lan.common.annotation.LoginUser;
import com.lan.common.util.BaseResult;
import com.lan.ichat.model.User;
import com.lan.ichat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * package com.lan.ichat.controller
 *
 * @author spoomlan
 * @date 14/02/2018
 */
@RestController
@RequestMapping(value = "/api/friend")
public class FriendController {
    @Autowired
    private UserService userService;

    /**
     * add friend
     *
     * @return
     */
    @PostMapping(value = "/add")
    public BaseResult insertFriend() {
        BaseResult result = new BaseResult();
        return result;
    }

    /**
     * update friend's remark
     *
     * @param friendId
     * @param remark
     * @param user
     * @return
     */
    @PostMapping(value = "/remark")
    public BaseResult updateRemark(@RequestParam("friendId") Long friendId, @RequestParam("remark") String remark,
                                   @LoginUser User user) {
        BaseResult result = new BaseResult();
        userService.updateRemark(user.getId(), friendId, remark);
        return result.setMsg("update remark success").setData(friendId);
    }

    /**
     * update moment purview
     *
     * @param friendId
     * @param hideMyMM
     * @param hideHisMM
     * @param user
     * @return
     */
    @PostMapping(value = "/moment")
    public BaseResult updateMomentSetting(@RequestParam("friendId") Long friendId,
                                          @RequestParam("hideMyMM") int hideMyMM,
                                          @RequestParam("hideHisMM") int hideHisMM,
                                          @LoginUser User user) {
        BaseResult result = new BaseResult();
        userService.updateMomentSetting(user.getId(), friendId, hideMyMM, hideHisMM);
        return result.setMsg("update moment setting success").setData(friendId);
    }

    /**
     * update star friend
     *
     * @param friendId
     * @param star
     * @param user
     * @return
     */
    @PostMapping(value = "/star")
    public BaseResult updateStar(@RequestParam("friendId") Long friendId,
                                 @RequestParam("star") int star,
                                 @LoginUser User user) {
        BaseResult result = new BaseResult();
        userService.updateStar(user.getId(), friendId, star);
        return result.setMsg("update star success").setData(friendId);
    }
}
