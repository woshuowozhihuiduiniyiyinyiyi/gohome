package com.hj.tj.gohome.controller;

import com.hj.tj.gohome.json.ApiRequest;
import com.hj.tj.gohome.json.JsonResponse;
import com.hj.tj.gohome.service.RobbingTicketUserService;
import com.hj.tj.gohome.utils.JSONUtils;
import com.hj.tj.gohome.vo.requestVo.RobbingTicketUserReqObj;
import com.hj.tj.gohome.vo.responseVO.RobbingTicketUserResObj;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tangj
 * @description
 * @since 2018/10/17 15:05
 */
@RestController
public class RobbingTicketUserController {

    @Resource
    private RobbingTicketUserService robbingTicketUserService;

    @RequestMapping("/robbing/ticket/user/list")
    public JsonResponse listRobbingTicketUser(@RequestBody ApiRequest apiRequest) {
        RobbingTicketUserReqObj robbingTicketUserReqObj = JSONUtils.toBean(apiRequest.getData(), RobbingTicketUserReqObj.class);
        List<RobbingTicketUserResObj> robbingTicketUserResObjs = robbingTicketUserService.listRobbingTicketUserResObj(robbingTicketUserReqObj);

        return JsonResponse.newOk(robbingTicketUserResObjs);
    }

}
