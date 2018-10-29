package com.hj.tj.gohome.service;

import com.hj.tj.gohome.GohomeApplicationTests;
import com.hj.tj.gohome.service.OwnerService;
import com.hj.tj.gohome.vo.requestVo.OwnerInsertReqObj;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author tangj
 * @description
 * @since 2018/10/16 14:51
 */
public class OwnerServiceTest extends GohomeApplicationTests {

    @Resource
    private OwnerService ownerService;

    @Test
    public void testInsertSelective(){

        OwnerInsertReqObj ownerInsertReqObj = new OwnerInsertReqObj();

        ownerInsertReqObj.setPhone("153666666666");
        ownerInsertReqObj.setWxAccount("tj");
        ownerInsertReqObj.setWxNickname("tjName");

        Integer ownerId = ownerService.saveOwner(ownerInsertReqObj);

        System.out.println(ownerId);
    }


}
