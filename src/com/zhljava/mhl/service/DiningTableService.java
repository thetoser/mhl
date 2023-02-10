package com.zhljava.mhl.service;

import com.zhljava.mhl.dao.DiningTableDAO;
import com.zhljava.mhl.domain.DiningTable;

import java.util.List;

public class DiningTableService {

    private DiningTableDAO diningTableDAO = new DiningTableDAO();

    //返回所有餐桌信息
    public List<DiningTable> list() {
        String sql = "select id, state from diningTable";
        return diningTableDAO.queryMulti(sql, DiningTable.class);
    }

    //根据id 查询DiningTable对象
    public DiningTable getDiningTable(int id) {
        String sql = "select * from diningTable where id = ?";
        return diningTableDAO.querySingle(sql, DiningTable.class, id);
    }

    //如果可以预订，对其状态更新
    public boolean orderDiningTable(int id, String orderName, String orderTel) {
        String sql = "update diningTable set state = '已经预订', orderName = ?, orderTel = ? where id = ?";
        int update = diningTableDAO.update(sql, orderName, orderTel, id);
        return update > 0;
    }

    //更新餐桌状态
    public boolean updateDiningTableState(int id, String state) {
        String sql = "update diningTable set state = ? where id = ?";
        int update = diningTableDAO.update(sql, state, id);
        return update > 0;
    }

    public boolean updateDiningTableStateToFree(int id, String state) {
        String sql = "update diningTable set state = ?, orderName = '', orderTel = '' where id = ?";
        int update = diningTableDAO.update(sql, state, id);
        return update > 0;
    }

}
