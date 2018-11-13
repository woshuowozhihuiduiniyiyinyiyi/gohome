package com.hj.tj.gohome.controller;

import com.hj.tj.gohome.annotation.SidSkip;
import com.hj.tj.gohome.enums.ErrorMsgEnum;
import com.hj.tj.gohome.exception.CustomException;
import com.hj.tj.gohome.json.ApiRequest;
import com.hj.tj.gohome.json.JsonResponse;
import com.hj.tj.gohome.service.PortalUserService;
import com.hj.tj.gohome.utils.StringUtil;
import com.hj.tj.gohome.vo.responseVO.LoginResObj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author tangj
 * @description
 * @since 2018/10/10 15:59
 */
@RestController
public class PortalUserController {

    private static Logger logger = LoggerFactory.getLogger(PortalUserController.class);

    @Resource
    private PortalUserService portalUserService;

    @SidSkip
    @RequestMapping("/portalUser/login")
    public JsonResponse login(@RequestBody ApiRequest jsonRequest) {
        String account = jsonRequest.getParamAsString("account");
        String password = jsonRequest.getParamAsString("password");

        logger.info("[action = `login`, account:{}, password:{}]", account, password);

        if (StringUtil.isBlank(account) || StringUtil.isBlank(password)) {
            throw new CustomException(ErrorMsgEnum.SYS_ERR);
        }

        LoginResObj loginResObj = portalUserService.login(account, password);
        if (StringUtils.isEmpty(loginResObj)) {
            throw new CustomException(ErrorMsgEnum.USER_INFO_ERR);
        }

        return JsonResponse.newOk(loginResObj);
    }

    @RequestMapping("/portalUser/list")
    public JsonResponse listPortalUserResObj(@RequestBody ApiRequest jsonRequest) {
        return JsonResponse.newOk(portalUserService.listPortalUserResObj());
    }
}
