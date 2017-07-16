package org.qianrenxi.core.upload.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.qianrenxi.core.common.config.SystemProperties;
import org.qianrenxi.core.upload.config.FileUploadProperties;
import org.qianrenxi.core.upload.exception.FileUploadException;
import org.qianrenxi.core.upload.model.FileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadService {

	private static final Logger logger = LoggerFactory.getLogger(FileUploadService.class);
	@Autowired
	FileUploadProperties fileUploadProperties;
	@Autowired
	SystemProperties systemProperties;
	
	public FileUpload upload(MultipartFile file, String savePath) {
		FileUpload fileUpload = null;
		if (!file.isEmpty()) {
			String originFileName = file.getOriginalFilename();
			String ext = getExt(originFileName);
			Long fileSize = file.getSize();
			if (fileUploadProperties.getInvalidFileCheck()) {
				check(ext);
			}
			savePath = StringUtils.isEmpty(savePath) ? fileUploadProperties.getDefaultSavePath() : savePath;
			
			String targetFileName = UUID.randomUUID().toString() + ext;
			String basePath = systemProperties.getContentPath();
			String datePath = DateTime.now().toString("/yyyy/MM/dd/");
			String parentPath = basePath + savePath + datePath;
			String relativeUrl = savePath + datePath + targetFileName;
			File parentDir = new File(parentPath);
			if (!parentDir.exists()) {
				parentDir.mkdirs();
			}
			File target = new File(parentDir.getAbsolutePath(), targetFileName);
			String fullPath = target.getAbsolutePath();
			try {
				file.transferTo(target);
			} catch (Exception e) {
				e.printStackTrace();
				//logger.error(String.format("保存文件<%s>异常",originFileName));
				throw new FileUploadException("common.file.upload.exception.save-file-failed");
			}
			fileUpload = new FileUpload();
			fileUpload.setExtention(ext);
			fileUpload.setOriginFileName(originFileName);
			fileUpload.setFileSize(fileSize);
			fileUpload.setFileName(targetFileName);
			fileUpload.setFullPath(fullPath);
			fileUpload.setParentDirectoryPath(parentPath);
			fileUpload.setRelativePath(relativeUrl);
		} else {
			throw new FileUploadException("common.file.upload.exception.file-is-empty");
		}
		return fileUpload;
	}

	public FileUpload upload(MultipartFile file) {
		return upload(file, null);
	}
	public List<FileUpload> upload(MultipartFile[] files) {
		List<FileUpload> fileUploads = new ArrayList<>();
		for(MultipartFile file : files) {
			fileUploads.add(upload(file));
		}
		return fileUploads;
	}
	public List<FileUpload> upload(MultipartFile[] files, String savePath) {
		List<FileUpload> fileUploads = new ArrayList<>();
		for(MultipartFile file : files) {
			fileUploads.add(upload(file, savePath));
		}
		return fileUploads;
	}
	
	private void check(String ext) {
		String invalidFileExts = fileUploadProperties.getInvalidFileExts();
		if (StringUtils.isNoneBlank(invalidFileExts) && invalidFileExts.contains(ext)) {
			throw new FileUploadException("common.file.upload.exception.file-is-illegal");
		}
	}

	private String getExt(String filePath) {
		return filePath.substring(filePath.lastIndexOf("."));
	}
}
