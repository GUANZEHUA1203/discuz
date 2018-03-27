/**
 * @autho zehua
 *  上午11:48:33
 */
package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.sshtools.j2ssh.SshClient;
import com.sshtools.j2ssh.authentication.AuthenticationProtocolState;
import com.sshtools.j2ssh.authentication.PasswordAuthenticationClient;
import com.sshtools.j2ssh.sftp.SftpFile;
import com.util.DESUtils;
import com.util.ElementsUtil;

/**上午11:48:33
 * @author 2017*****上午11:48:33
 *
 */
public class test {
	public static void main(String[] args) {
		SshClient client=new SshClient();
	    try{
	        //设置用户名和密码
	       PasswordAuthenticationClient pwd = new PasswordAuthenticationClient();
	       pwd.setUsername("root");
	        pwd.setPassword(DESUtils.getDecryptString("6gXcGn4iw0ezg7ebryKmmA=="));
	        int result=client.authenticate(pwd);
	        if(result==AuthenticationProtocolState.COMPLETE){//如果连接完成
	            System.out.println("==============="+result);
	            List<SftpFile> list = client.openSftpClient().ls("/file");
	            File file=new File("c:\\a.txt");
	            FileWriter fw=new FileWriter(file);
	            for (int i=0;i<list.size();i++) {
	            	SftpFile f=list.get(i);
	                System.out.println(f.getFilename());
	                if(f.getFilename().length()>7){
	                	SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmSSsss");
	                	f.rename(sdf.format(new Date())+new Random().nextInt());
	                }
//	                System.out.println(f.getAbsolutePath());
	                fw.write("INSERT INTO `Boke`.`picture` (`pic_name`) VALUES ('"+f.getFilename()+"');");
	                fw.write("\r\n");
	              /*  if(f.getFilename().equals("aliases")){
	                    OutputStream os = new FileOutputStream("d:/mail/"+f.getFilename());
	                    client.openSftpClient().get("/etc/mail/aliases", os);
	                    //以行为单位读取文件start
	                    File file = new File("d:/mail/aliases");
	                    BufferedReader reader = null;
	                    try {
	                        System.out.println("以行为单位读取文件内容，一次读一整行：");
	                        reader = new BufferedReader(new FileReader(file));
	                        String tempString = null;
	                        int line = 1;//行号
	                        //一次读入一行，直到读入null为文件结束
	                        while ((tempString = reader.readLine()) != null) {
	                            //显示行号
	                            System.out.println("line " + line + ": " + tempString);
	                            line++;
	                        }
	                        reader.close();
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                    } finally {
	                        if (reader != null) {
	                            try {
	                                reader.close();
	                            } catch (IOException e1) {
	                            }
	                        }
	                    }
	                    //以行为单位读取文件end
	                }*/
	            }
	        }
	    }catch(IOException e){
	        e.printStackTrace();
	    }
	}
	
}
