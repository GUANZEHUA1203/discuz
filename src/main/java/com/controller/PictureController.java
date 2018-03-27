package com.controller;


import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bean.Picture;
import com.google.gson.Gson;
import com.services.PictureServices;
@Controller
public class PictureController {
	@Resource
	private PictureServices pictureServices;
	
	@RequestMapping(value="getimg",produces="application/json;charset=utf-8")
	@ResponseBody
	public String checkName(String callback){
		List<Picture> picture = this.pictureServices.getRandomPic();
		for (Picture picture2 : picture) {
			Random r=new Random();
			picture2.setHeight(String.valueOf(r.nextInt(600)+300));
			picture2.setWidth(String.valueOf(r.nextInt(400)+300));
			picture2.setImage(picture2.getPreview());
			picture2.setTitle("");
			picture2.setUrl(picture2.getPreview());
		}
			Gson gson=new Gson();
			String json = gson.toJson(picture);
			return callback+"("+json+")";
	}
}
