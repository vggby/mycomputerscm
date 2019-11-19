package com.mydesign.mycomputerscm.Service;
import com.mydesign.mycomputerscm.Querydomain.queryMenu;
import com.mydesign.mycomputerscm.domain.Menu;
import com.mydesign.mycomputerscm.domain.Role;
import com.mydesign.mycomputerscm.domain.RoleMenu;
import com.mydesign.mycomputerscm.mapper.MenuMapper;
import com.mydesign.mycomputerscm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Service
public class MenuServiceImpl implements MenuService{
    @Autowired
    private MenuMapper menuMapper;
    @Override
    public List<Menu> getMenuTree(queryMenu querymenu, List<Role> roleList) {
        List<Menu> list = menuMapper.findAll(new Specification<Menu>() {
            @Override
            public Predicate toPredicate(Root<Menu> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                // Subquery<Integer> 表示新建一个子查询语句,泛型是主键类型
                Subquery<String> roleMenuSubquery = query.subquery(String.class);
                // 根据子查询所要使用的表建立一个相应的root供它获取该表属性
                Root<RoleMenu> roleMenuRoot = roleMenuSubquery.from(RoleMenu.class);
                Predicate equal = criteriaBuilder.equal(roleMenuRoot.get("menuId"), root.get("menuId"));
                if(roleList!=null && roleList.size()>0){
                    for (Role role:
                         roleList) {
                        predicates.add(criteriaBuilder.equal(roleMenuRoot.get("roleId"), role.getRoleId()));
                    }
                }
                Predicate or = criteriaBuilder.or(predicates.toArray(new Predicate[]{}));
                Predicate and = criteriaBuilder.and(equal, or);
                roleMenuSubquery.select(roleMenuRoot.get("id")).where(and);

                Predicate exists = criteriaBuilder.exists(roleMenuSubquery);
                List<Predicate> menupredicates = new ArrayList<Predicate>();
                if(querymenu!=null&&querymenu.getParentMenuId()!=null){
                    menupredicates.add(criteriaBuilder.equal(root.get("parentId"), querymenu.getParentMenuId()))  ;
                }
                if(querymenu!=null&&querymenu.getMenuid()!=null){
                    menupredicates.add(criteriaBuilder.equal(root.get("menuId"), querymenu.getMenuid()))  ;
                }
                if(menupredicates!=null && menupredicates.size()>0){
                    Predicate and1 = criteriaBuilder.and(menupredicates.toArray(new Predicate[]{}));
                    return criteriaBuilder.and(and1,exists);
                }else{
                    return exists;
                }
            }
        });

        if (list != null && list.size() > 0) {
            Iterator<Menu> menuIterator = list.iterator();
            while (menuIterator.hasNext()) {
                Menu menu = menuIterator.next();
                queryMenu qmenu =new queryMenu();
                qmenu.setParentMenuId(menu.getMenuId());
                menu.setSubMenuList(getMenuTree(qmenu, roleList));
            }
        }

        return list;
    }

}
