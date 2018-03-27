package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bean.Label;
import com.google.gson.Gson;
import com.services.LabelServices;
@Controller
public class LabelController {
	@Autowired
	private LabelServices labelService;
	
	@RequestMapping(value="findAlllabels",produces="application/json;charset=utf-8")
	@ResponseBody
	public String findAll(){
		String json=null;
		try{
		List<Label> labels = this.labelService.findAll();
		Map<String, Object> map=new HashMap<String, Object>();
		Gson gson=new Gson();
		map.put("labels", labels);
		json=gson.toJson(map);
		}catch(Exception e){
			return "error.jsp?msg="+"暂无分类";
		}
		return json;
	}

}
