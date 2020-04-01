package com.mydesign.mycomputerscm.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mydesign.mycomputerscm.Service.CustomerService;
import com.mydesign.mycomputerscm.domain.Customer;
import com.mydesign.mycomputerscm.domain.DataGridView;
import com.mydesign.mycomputerscm.domain.ResultInfo;
import com.mydesign.mycomputerscm.vo.CustomerVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	/**
	 * 查询
	 */
	@PostMapping("loadAllCustomer")
	@ResponseBody
	public DataGridView loadAllCustomer(CustomerVo customerVo) {
		IPage<Customer> page = new Page<>(customerVo.getPage(), customerVo.getLimit());
		QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
		queryWrapper.like(StringUtils.isNotBlank(customerVo.getCustomername()), "customername",
				customerVo.getCustomername());
		queryWrapper.like(StringUtils.isNotBlank(customerVo.getTelephone()), "telephone", customerVo.getTelephone());
		queryWrapper.like(StringUtils.isNotBlank(customerVo.getConnectionperson()), "connectionperson",
				customerVo.getConnectionperson());
		this.customerService.page(page, queryWrapper);
		return new DataGridView(page.getTotal(), page.getRecords());
	}

	/**
	 * 添加
	 */
	@PostMapping("addCustomer")
	@ResponseBody
	public ResultInfo addCustomer(@RequestBody CustomerVo customerVo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			this.customerService.save(customerVo);
			resultInfo.setFlag(true);
			resultInfo.setErrorMsg("添加成功");
			return resultInfo;
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setFlag(false);
			resultInfo.setErrorMsg("添加失败");
			return resultInfo;
		}
	}

	/**
	 * 修改
	 */
	@PostMapping("updateCustomer")
	@ResponseBody
	public ResultInfo updateCustomer(CustomerVo customerVo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			this.customerService.updateById(customerVo);
			resultInfo.setFlag(true);
			resultInfo.setErrorMsg("修改成功");
			return resultInfo;
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setFlag(false);
			resultInfo.setErrorMsg("修改失败");
			return resultInfo;
		}
	}

	/**
	 * 删除
	 */
	@PostMapping("deleteCustomer")
	@ResponseBody
	public ResultInfo deleteCustomer(Integer id) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			this.customerService.removeById(id);
			resultInfo.setFlag(true);
			resultInfo.setErrorMsg("删除成功");
			return resultInfo;
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setFlag(false);
			resultInfo.setErrorMsg("删除失败");
			return resultInfo;
		}
	}

}
