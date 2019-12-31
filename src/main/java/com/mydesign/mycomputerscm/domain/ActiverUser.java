package com.mydesign.mycomputerscm.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiverUser {

	private SysUser user;
	
	private List<String> roles;
	
	private List<String> permissions;
}
