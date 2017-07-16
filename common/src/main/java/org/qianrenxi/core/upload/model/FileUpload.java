package org.qianrenxi.core.upload.model;

public class FileUpload {

	/** 原始文件名称 */
	private String originFileName;
	/** 存储文件名称 */
	private String fileName;
	/** 文件大小 */
	private long fileSize;
	/** 文件上传后的全路径 */
	private String fullPath;
	/** 文件上传后的父目录全路径 */
	private String parentDirectoryPath;
	/** 通过浏览器访问文件的相对路径 */
	private String relativePath;
	/** 文件扩展名 */
	private String extention;


	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public String getExtention() {
		return extention;
	}

	public void setExtention(String extention) {
		this.extention = extention;
	}

	public String getOriginFileName() {
		return originFileName;
	}

	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getParentDirectoryPath() {
		return parentDirectoryPath;
	}

	public void setParentDirectoryPath(String parentDirectoryPath) {
		this.parentDirectoryPath = parentDirectoryPath;
	}

	public String getRelativePath() {
		return relativePath;
	}

	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

}
