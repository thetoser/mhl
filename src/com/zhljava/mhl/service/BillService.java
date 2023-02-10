package com.zhljava.mhl.service;

import com.zhljava.mhl.dao.BillDAO;
import com.zhljava.mhl.domain.Bill;

import java.util.List;
import java.util.UUID;

public class BillService {

    private BillDAO billDAO = new BillDAO();
    private MenuService menuService = new MenuService();
    private DiningTableService diningTableService = new DiningTableService();

    public boolean payBill(int diningTableId, String payMode) {
        String sql = "update bill set state = ? where diningTableId = ? and state = '未结账'";
        int update = billDAO.update(sql, payMode, diningTableId);
        if (update <= 0) {
            return false;
        }

        if(!(diningTableService.updateDiningTableStateToFree(diningTableId, "空"))) {
            return false;
        }
        return true;
    }

    public boolean hasPayBillByDiningTableId(int diningTableId) {
        String sql = "select * from bill where state = '未结账' and diningTableId = ? limit 0, 1";
        Bill bill = billDAO.querySingle(sql, Bill.class, diningTableId);
        return bill != null;
    }

    //查看账单
    public List<Bill> getBill() {
        String sql = "select * from bill";
        return billDAO.queryMulti(sql, Bill.class);
    }

    public boolean orderMenu(int menuId, int nums, int diningTableId) {
        String billID = UUID.randomUUID().toString();
        String sql = "insert into bill values(null, ?, ?, ?, ?, ?, now(), '未结账')";
        int update = billDAO.update(sql, billID, menuId, nums,
                menuService.getMenuById(menuId).getPrice() * nums, diningTableId);

        if (update <= 0) {
            return false;
        }
        return diningTableService.updateDiningTableState(diningTableId, "就餐中");
    }

}
