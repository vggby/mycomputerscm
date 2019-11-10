package com.mydesign.mycomputerscm.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mydesign.mycomputerscm.domain.User;
import com.mydesign.mycomputerscm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserDetailsService,UserService {
    @Autowired
    private PasswordEncoder passwordencoder;
    @Autowired
    private UserMapper usermapper;
    @Override
    public User UserLogin(User user) {
       /* QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);*/
        Map<String,Object>colum = new HashMap<>();
        colum.put("username",user.getUsername());
        List<User> users = usermapper.selectByMap(colum);
        if(!users.isEmpty() && passwordencoder.matches(user.getPassword(),users.get(0).getPassword())){
            return users.get(0);
        }


        return null;
    }

    @Override
    public int SaveUser(User user) {
        user.setPassword(passwordencoder.encode(user.getPassword()));
        return usermapper.insert(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        Map<String,Object>colum = new HashMap<>();
        colum.put("username",username);
        List<User> users = usermapper.selectByMap(colum);
        if (users==null){
            return null;
        }

        return null;
    }
}
