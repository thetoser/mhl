package com.zhljava.mhl.service;

import com.zhljava.mhl.dao.MenuDAO;
import com.zhljava.mhl.domain.Menu;

import java.util.List;

public class MenuService {

    private MenuDAO menuDAO = new MenuDAO();

    public List<Menu> getMenu() {
        String sql = "select * from menu";
        return menuDAO.queryMulti(sql, Menu.class);
    }

    public Menu getMenuById(int id) {
        String sql = "select * from menu where id = ?";
        return menuDAO.querySingle(sql, Menu.class, id);
    }
}
