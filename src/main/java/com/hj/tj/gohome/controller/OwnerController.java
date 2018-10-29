package com.hj.tj.gohome.controller;

import com.hj.tj.gohome.entity.Owner;
import com.hj.tj.gohome.json.ApiRequest;
import com.hj.tj.gohome.json.JsonResponse;
import com.hj.tj.gohome.service.OwnerService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;

/**
 * @author tangj
 * @description
 * @since 2018/10/9 14:45
 */
@RestController
public class OwnerController {

    @Resource
    private OwnerService ownerService;

}
