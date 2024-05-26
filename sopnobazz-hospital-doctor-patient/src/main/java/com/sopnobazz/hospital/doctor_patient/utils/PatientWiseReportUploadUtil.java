package com.ibcsprimax.bof.employee_health.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @Project   ibcs-bof-erp
 * @Author    Md. Mizanur Rahman - 598
 * @Since     May 28, 2021
 * @version   1.0.0
 */
@Component
public class PatientWiseReportUploadUtil {
	
    @SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(PatientWiseReportUploadUtil.class);
    
    @Autowired
	private Environment env;
	


	public Map<String, String> PatientWiseReportUploadToServer(MultipartFile file, String fileLocation, String title) throws IOException {

		Map<String, String> response = new HashMap<>();

		// dir
		String dir = fileLocation;
		dir = dir.replace("\\", "/");

		// String dir = getImageDir();
		String fileName = file.getOriginalFilename();
		if(!ObjectUtils.isEmpty(title)) {
			String timestamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
			fileName = timestamp + "_" + title + "_" + fileName;
		}

		// check file dir
		Path root = Paths.get(dir);
		if (!Files.exists(root)) {
			Files.createDirectories(Paths.get(dir));
		}

		// change file name
		byte[] bytes = file.getBytes();
		String insPath = dir + fileName;

		// save to dir
		Files.write(Paths.get(insPath), bytes);

		response.put("fileName", fileName);
		response.put("fileLocation", dir);

		return response;
	}
}
