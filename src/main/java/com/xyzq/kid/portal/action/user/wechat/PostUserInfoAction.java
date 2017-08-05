package com.xyzq.kid.portal.action.user.wechat;

import com.xyzq.kid.CommonTool;
import com.xyzq.kid.portal.action.user.portal.PortalUserAjaxAction;
import com.xyzq.kid.logic.user.entity.UserEntity;
import com.xyzq.kid.logic.user.service.UserService;
import com.xyzq.simpson.base.json.JSONObject;
import com.xyzq.simpson.maggie.access.spring.MaggieAction;
import com.xyzq.simpson.maggie.framework.Context;
import com.xyzq.simpson.maggie.framework.Visitor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * 范例动作
 */
@MaggieAction(path = "kid/wechat/postUserInfo")
public class PostUserInfoAction extends PortalUserAjaxAction {
    /**
     * Action中只支持Autowired注解引入SpringBean
     */
    @Autowired
    private UserService userService;


    /**
     * 动作执行
     *
     * @param visitor 访问者
     * @param context 请求上下文
     * @return 下一步动作，包括后缀名，null表示结束
     */
    @Override
    public String doExecute(Visitor visitor, Context context) throws Exception {

        UserEntity userEntity = new UserEntity();
        userEntity.mobileno = (String) context.get(CONTEXT_KEY_MOBILENO);
        userEntity.openid = (String) context.get(CONTEXT_KEY_OPENID);
        userEntity.realname = (String)context.parameter("userName");
        userEntity.gender = (Integer)context.parameter("sex", -1);
        userEntity.address = (String)context.parameter("address", "未填");
        userEntity.subscribetime = CommonTool.dataToStringYMDHMS(new Date());

        userService.insertSelective(userEntity);
        return "success.json";
    }

}
