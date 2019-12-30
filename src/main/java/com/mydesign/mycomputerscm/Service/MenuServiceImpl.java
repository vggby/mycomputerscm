package com.mydesign.mycomputerscm.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mydesign.mycomputerscm.domain.Menu;
import com.mydesign.mycomputerscm.domain.SysUser;
import com.mydesign.mycomputerscm.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service
public class MenuServiceImpl  extends ServiceImpl<MenuMapper, Menu> implements MenuService{
    @Autowired
    private MenuMapper menuMapper;


    @Override
    public List<Menu> selectMenusByUser(SysUser user)
    {
        List<Menu> menus = new LinkedList<Menu>();

        menus = menuMapper.selectMenusByUserId(user.getUserid());
        List<Menu> root = getChildPerms(menus, "root");
        return root;
    }

    @Override
    public List<Menu> selectpermissionByUserId(SysUser user)
    {
        List<Menu> menus = new LinkedList<Menu>();

        menus = menuMapper.selectpermissionByUserId(user.getUserid());

        return menus;
    }


    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list 分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public List<Menu> getChildPerms(List<Menu> list, String parentId)
    {
        List<Menu> returnList = new ArrayList<Menu>();
        for (Iterator<Menu> iterator = list.iterator(); iterator.hasNext();)
        {
            Menu t = (Menu) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if ( parentId.equals(t.getParentId()))
            {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }
    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFn(List<Menu> list, Menu t)
    {
        // 得到子节点列表
        List<Menu> childList = getChildList(list, t);
        t.setChildren(childList);
        for (Menu tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                // 判断是否有子节点
                Iterator<Menu> it = childList.iterator();
                while (it.hasNext())
                {
                    Menu n = (Menu) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }
    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<Menu> list, Menu t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }
    /**
     * 得到子节点列表
     */
    private List<Menu> getChildList(List<Menu> list, Menu t)
    {
        List<Menu> tlist = new ArrayList<Menu>();
        Iterator<Menu> it = list.iterator();
        while (it.hasNext())
        {
            Menu n = (Menu) it.next();
            if (n.getParentId().equals(t.getMenuId()) )
            {
                tlist.add(n);
            }
        }
        return tlist;
    }



}
