package com.hj.tj.gohome.vo.responseVO;

/**
 * @author tangj
 * @description
 * @since 2018/11/7 11:57
 */
public class OrderStatisticDataResObj {

    private Integer totalOrderCount;

    private Integer robbingCount;

    private Integer successCount;

    private Double totalProfit;

    public Integer getTotalOrderCount() {
        return totalOrderCount;
    }

    public void setTotalOrderCount(Integer totalOrderCount) {
        this.totalOrderCount = totalOrderCount;
    }

    public Integer getRobbingCount() {
        return robbingCount;
    }

    public void setRobbingCount(Integer robbingCount) {
        this.robbingCount = robbingCount;
    }

    public Integer getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(Integer successCount) {
        this.successCount = successCount;
    }

    public Double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(Double totalProfit) {
        this.totalProfit = totalProfit;
    }
}
