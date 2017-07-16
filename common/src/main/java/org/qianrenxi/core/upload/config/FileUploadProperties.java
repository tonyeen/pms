package org.qianrenxi.core.upload.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "file.upload")
public class FileUploadProperties {

	/** 是否开启非法文件检查,默认开启 */
	private Boolean invalidFileCheck = true;
	/** 非法文件后缀名 */
	private String invalidFileExts = "jsp,sh";
	/** 默认的文件上传存储相对路径 */
	private String defaultSavePath = "/upload";
	
	public Boolean getInvalidFileCheck() {
		return invalidFileCheck;
	}
	public void setInvalidFileCheck(Boolean invalidFileCheck) {
		this.invalidFileCheck = invalidFileCheck;
	}
	public String getInvalidFileExts() {
		return invalidFileExts;
	}
	public void setInvalidFileExts(String invalidFileExts) {
		this.invalidFileExts = invalidFileExts;
	}
	public String getDefaultSavePath() {
		return defaultSavePath;
	}
	public void setDefaultSavePath(String defaultSavePath) {
		this.defaultSavePath = defaultSavePath;
	}
	
}
