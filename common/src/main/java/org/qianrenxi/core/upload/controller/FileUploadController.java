package org.qianrenxi.core.upload.controller;

import java.util.List;

import org.qianrenxi.core.upload.model.FileUpload;
import org.qianrenxi.core.upload.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
public class FileUploadController {

	@Autowired
	FileUploadService fileUploadService;

	@ResponseBody
	@RequestMapping(value = "upload/file")
	public String uploadFile(@RequestParam(name = "file") MultipartFile file,
			@RequestParam(name = "savePath", required = false) String savePath) {
		FileUpload fileUpload = fileUploadService.upload(file, savePath);
		String result = "";
		try {
			result = Jackson2ObjectMapperBuilder.json().build().writeValueAsString(fileUpload);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "upload/files")
	public String uploadFiles(@RequestParam(name = "files") MultipartFile[] files,
			@RequestParam(name = "savePath", required = false) String savePath) {
		List<FileUpload> fileUploads = fileUploadService.upload(files, savePath);
		String result = "";
		try {
			result = Jackson2ObjectMapperBuilder.json().build().writeValueAsString(fileUploads);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}
}
