package com.gdng.inner.api.task.dto.mq;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/25 15:53
 * @Description:
 * @Version: 1.0.0
 */
public class GoodsSendDTO implements Serializable {

    private Long taskId;
    private Long strategyId;
    private String uid;
    private List<GoodsSendItemDTO> goodsItemList;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<GoodsSendItemDTO> getGoodsItemList() {
        return goodsItemList;
    }

    public void setGoodsItemList(List<GoodsSendItemDTO> goodsItemList) {
        this.goodsItemList = goodsItemList;
    }
}
