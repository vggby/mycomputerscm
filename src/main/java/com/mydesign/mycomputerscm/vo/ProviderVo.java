package com.mydesign.mycomputerscm.vo;


import com.mydesign.mycomputerscm.domain.Provider;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProviderVo extends Provider {

	private Integer page = 1;
	private Integer limit = 10;

	private Integer[] ids;

}
