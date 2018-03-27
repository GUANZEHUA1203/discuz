package com.util;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;




public class UploadDBhelper {
	public String addImage(HttpServletRequest req, HttpServletResponse resp,String path){
		DiskFileItemFactory fif = new DiskFileItemFactory();
		fif.setRepository(new File(path));
		fif.setSizeThreshold(10000000);
		ServletFileUpload upload=new ServletFileUpload(fif);
		String filename=null;
		try {
			List<FileItem> list = upload.parseRequest(req);
			for (FileItem fi : list) {
				if(!fi.isFormField()){
					Date d=new Date();
					SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmSSss");
					String time=sdf.format(d);
					String name=fi.getName();
					name="";
					if(name!=null){
						filename=time+name;
					}
					File f = new File(new File(path), filename);
					fi.write(f);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filename;
	}
	
}
