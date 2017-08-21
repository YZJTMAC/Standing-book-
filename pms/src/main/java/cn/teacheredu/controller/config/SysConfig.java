package cn.teacheredu.controller.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration 
public class SysConfig {
	
	@Value("${uploadFileURL}")
	public String uploadFileURL;
	
	@Value("${importFileURL}")
	public String importFileURL;
	
	@Value("${needPhoneValid}")
	public Boolean needPhoneValid;

}
