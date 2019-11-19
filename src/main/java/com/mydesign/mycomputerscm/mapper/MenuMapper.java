package com.mydesign.mycomputerscm.mapper;

import com.mydesign.mycomputerscm.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MenuMapper extends JpaRepository<Menu, String> ,JpaSpecificationExecutor<Menu>  {

}
