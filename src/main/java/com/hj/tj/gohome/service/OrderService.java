package com.hj.tj.gohome.service;

import com.hj.tj.gohome.json.Page;
import com.hj.tj.gohome.vo.requestVo.OrderInsertReqObj;
import com.hj.tj.gohome.vo.requestVo.OrderReqObj;
import com.hj.tj.gohome.vo.responseVO.OrderResObj;
import com.hj.tj.gohome.vo.responseVO.OrderStatisticDataResObj;

import java.util.List;

public interface OrderService {

    /**
     * 订单列表
     *
     * @param orderReqObj
     * @param page
     * @return
     */
    List<OrderResObj> listOrder(OrderReqObj orderReqObj, Page page);

    /**
     * 订单条数
     *
     * @param orderReqObj
     * @return
     */
    Integer countByReqObj(OrderReqObj orderReqObj);

    /**
     * 新增或更新订单
     *
     * @param orderInsertReqObj
     * @return
     */
    Integer saveOrder(OrderInsertReqObj orderInsertReqObj) throws Exception;

    /**
     * 订单详情页面
     *
     * @param id
     * @return
     */
    OrderResObj getOrderDetail(Integer id);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    void deleteOrder(List<Integer> id);

    /**
     * 统计数据
     */
    OrderStatisticDataResObj statisticData();


    /**
     * 刷新用户出发日期日期
     */
    void refreshQueryDate();
}
