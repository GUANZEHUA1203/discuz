package com.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bean.Aticle;
import com.bean.Label;
import com.bean.Login;
import com.google.gson.Gson;
import com.services.AticleServices;
import com.services.LabelServices;
import com.util.StringUtil;
import com.util.redis.BaseRedisService;
import com.util.webUtil.paramUtil;
@Controller
public class AticleController {
	@Resource
	private AticleServices aticleServices;
	@Resource
	private LabelServices labelService;
	@Autowired
	private BaseRedisService baseRedisService;
	
	
	@RequestMapping("fabiao")
	public String fabiao(Aticle aticle,Model model,HttpServletRequest req){
		String msg="发帖成功";
			try{
			if(req.getSession().getAttribute("logined")==null){
				return "error.jsp";
			}
			if(aticle.getTitle()==null||aticle.getContext()==null||aticle.getTitle().length()==0||aticle.getContext().length()==0){
				msg="发帖失败[请输入标题或内容]";
				return "fatie.jsp?msg="+msg;
			}
			this.aticleServices.fabiao(aticle);
			return "fatie.jsp?msg="+msg;
			}catch(Exception e){
				return "error.jsp";
			}
	}
	@RequestMapping(value="showaticles",produces="application/json;charset=utf-8")
	@ResponseBody
	public String showaticles(HttpServletRequest request,HttpServletResponse  response,int pn,int px,String name,Model model,Integer sequence){
		Map<String, Object> map=new HashMap<String, Object>();
		Gson gson=new Gson();
		String json=null;
		try{
			
			map.clear();
			Map<String, Object> resultmap=new HashMap<String, Object>();
			map.clear();
			map.put("atman", name);
			map.put("pn", pn*px);
			map.put("px", px);
			map.put("sequence", sequence==null?0:sequence);
			List<Aticle> atices = this.aticleServices.selectAticle(map);
			int findCountAticle = this.aticleServices.findCountAticle(map);
			List<Label> labels = this.labelService.findAll();
			
			resultmap.put("count", findCountAticle);
			resultmap.put("aticles", atices);
			resultmap.put("labels", labels); 
			json = gson.toJson(resultmap);
			
	}catch(Exception e){
		System.err.println("错误信息*******************"+e);
		return "error.jsp";
	}
		return json;
	}
	
	@RequestMapping(value="deleteAticle",produces="application/json;charset=utf-8")
	@ResponseBody
	public String deleteaticles(HttpServletRequest req,Integer id){
	try{
		Login attribute = (Login)req.getSession().getAttribute("logined");
		if(attribute==null){
			return "error.jsp";
		}
		if(attribute.getpower()==1){
			this.aticleServices.deleteAticle(id);
		}else{
			return "error.jsp";
		}
	}catch(Exception e){
		return "error.jsp";
	}
		return "redirect:selectAticle";
	}
	@RequestMapping(value="selectAticle",produces="application/json;charset=utf-8")
	@ResponseBody
	public String selectAticle(HttpServletRequest request,HttpServletResponse  response, Aticle aticle,String num,Integer pn,Integer px,Integer sequence){
		String json2=null;
		try{
		Map<String, Object> requestParameter = paramUtil.getRequestParameter(request, response);
		requestParameter.put("path", request.getContextPath());
		int hashCode = requestParameter.hashCode();
		if(baseRedisService.get(StringUtil.getString(hashCode))!=null) {
			return baseRedisService.get(StringUtil.getString(hashCode));
		}
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.clear();
		
		Map<String, Object> mapjson=new HashMap<String, Object>();
		Gson gson=new Gson();
		map.put("atid", aticle.getId());
		map.put("atman", aticle.getMan());
		map.put("attitle", aticle.getTitle());
		map.put("atdate", aticle.getDate());
		map.put("atcontext", aticle.getContext());
		map.put("atlabel", aticle.getLabel());
		map.put("atdate",num);
		map.put("pn", pn*px);
		map.put("px", px);
		map.put("sequence", sequence==null?0:sequence);
		List<Aticle> atices = this.aticleServices.selectAticle(map);
		int findCountAticle = this.aticleServices.findCountAticle(map);
		for(int i=0;i<atices.size();i++){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:SS ss");
			String Stdate = sdf.format(atices.get(i).getDate());
			try {
				Date newDate = sdf.parse(Stdate);
				atices.get(i).setDate(newDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		mapjson.put("aticles", atices);
		mapjson.put("count", findCountAticle);
		//表示是否为多条件判断
		mapjson.put("isselect", "yes");
		json2 = gson.toJson(mapjson);
		baseRedisService.set(StringUtil.getString(hashCode), json2);
		baseRedisService.expire(StringUtil.getString(hashCode), baseRedisService.TIME_EXPRIRE);
	}catch(Exception e){
		return "error.jsp";
	}
		return json2;
	}
	@RequestMapping(value="updateAticle",produces="application/json;charset=utf-8")
	@ResponseBody
	public String updateAticle(Aticle aticle){
		try{
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("atid", aticle.getId());
		map.put("atman", aticle.getMan());
		map.put("attitle", aticle.getTitle());
		if(aticle.getDate()!=null){
			map.put("atdate", aticle.getDate());
		}
		map.put("atcontext", aticle.getContext());
		map.put("atlabel", aticle.getLabel());
		this.aticleServices.updateAticle(map);
	}catch(Exception e){
		return "error.jsp";
	}
		return "showaticles";
	}
	
}
