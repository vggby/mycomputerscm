package com.mydesign.mycomputerscm.Service;

import com.github.wenhao.jpa.Sorts;
import com.github.wenhao.jpa.Specifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import com.mydesign.mycomputerscm.Querydomain.queryRole;
import com.mydesign.mycomputerscm.domain.Role;
import com.mydesign.mycomputerscm.mapper.RoleManaRoleMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleManaRoleMapper RoleManaRoleMapper;
    @Override
    public Page<Role> findAll(queryRole queryrole) {
        List<Integer> status = queryrole.getStatus();
        String role_name = queryrole.getRole_name();

        Integer[] statusstrs=null;
        boolean staflaged= false;
        boolean rname=true;
        if(status!=null){
            final int size = status.size();
            statusstrs = status.toArray(new Integer[size]);
            staflaged=true;
        }
        if(role_name==null||"".equals(role_name)){
            rname=false;
        }
        Specification<Role> specification = Specifications.<Role>and()
                .eq(staflaged,"status", statusstrs)
                .eq(rname,"roleName",role_name)
                .build();

        Sort sort = Sorts.builder()

                .build();
        int  page = 0;
        int limit = 5 ;
        if (queryrole.getOffset()!=null &&queryrole.getOffset()!=0){
            page=queryrole.getOffset()-1;
        }
        if (queryrole.getLimit()!=null&& queryrole.getLimit()!=0){
            limit=queryrole.getLimit();
        }
        return  RoleManaRoleMapper.findAll(specification, PageRequest.of(page, limit, sort));
    }

    @Override
    public Role addRole(Role role){
        Role save = RoleManaRoleMapper.save(role);
        return save;
    }
    @Override
    @Transactional
    public void deleteRole (String roleid ){
        RoleManaRoleMapper.deleteByroleid(roleid);
    }

    @Override
    public Role findAllByroleid(String roleid) {
        return RoleManaRoleMapper.findAllByroleid(roleid);
    }

}
