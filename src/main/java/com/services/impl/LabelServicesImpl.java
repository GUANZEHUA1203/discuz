package com.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Label;
import com.dao.LabelDao;
import com.services.LabelServices;
@Service("labelService")
public class LabelServicesImpl implements LabelServices {
	@Autowired
	private LabelDao labeldao;

	public List<Label> findAll() {
		List<Label> labels = this.labeldao.findAll();
		return labels;
	}
	


}
