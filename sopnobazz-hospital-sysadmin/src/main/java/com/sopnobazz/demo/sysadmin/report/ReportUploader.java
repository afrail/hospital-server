package com.sopnobazz.demo.sysadmin.report;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;


/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */

@Component
public class ReportUploader {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(ReportUploader.class);

    @Autowired
    ReportSources reportSources;

    public Map<String, String> uploadMasterReportToServer(MultipartFile file, String fileName) throws IOException {

        Map<String, String> response = new HashMap<>();

        //dir
        String dir = reportSources.REPORT_SOURCE_DIR;
        dir = dir.replace("\\", "/");

        //String dir = getImageDir();

        String englishFileName = file.getOriginalFilename();

        if (!ObjectUtils.isEmpty(fileName)) {
            englishFileName = fileName + ".jrxml";
        }

        //check file dir
        Path root = Paths.get(dir);
        if (!Files.exists(root)) {
            Files.createDirectories(Paths.get(dir));
        }

        //chenge file name
        byte[] bytes = file.getBytes();
        String insPathEnglish = dir + englishFileName;

        //save to dir
        Files.write(Paths.get(insPathEnglish), bytes);

        response.put("fileNameEng", englishFileName);
        response.put("fileNameJasperEng", fileName + ".jasper");
        response.put("fileLocation", dir);

        return response;
    }


    public Map<String, String> uploadSubReportToServer(MultipartFile file, String fileName) throws IOException {

        Map<String, String> response = new HashMap<>();

        //dir
        String dir = reportSources.REPORT_SOURCE_SUBREPORT_DIR;
        dir = dir.replace("\\", "/");

        //String dir = getImageDir();

        String englishFileName = file.getOriginalFilename();

        if (!ObjectUtils.isEmpty(fileName)) {
            englishFileName = fileName + ".jrxml";
        }

        //check file dir
        Path root = Paths.get(dir);
        if (!Files.exists(root)) {
            Files.createDirectories(Paths.get(dir));
        }

        //chenge file name
        byte[] bytes = file.getBytes();
        String insPathEnglish = dir + englishFileName;

        //save to dir
        Files.write(Paths.get(insPathEnglish), bytes);

        response.put("fileNameEng", englishFileName);
        response.put("fileNameJasperEng", fileName + ".jasper");
        response.put("fileLocation", dir);

        return response;
    }


    public Boolean deleteMasterReportFromServer(String path) throws IOException {
        Boolean response = false;
        Path filePath = Paths.get(path);
        Files.delete(filePath);
        response = true;
        return response;
    }

    public Boolean deleteSubReportFromServer(String path) throws IOException {
        Boolean response = false;
        Path filePath = Paths.get(path);
        Files.delete(filePath);
        response = true;
        return response;
    }


    //	public Map<String, String> uploadJrxmmlReportToServer(MultipartFile file, String fileName, String filenameBangla) throws IOException {
    //
    //	Map<String, String> response = new HashMap<>();
    //
    //	//dir
    //	String dir = env.getProperty("report.upload.classpath.dir");
    //	dir = dir.replace("\\", "/");
    //
    //	//String dir = getImageDir();
    //
    //	String englishFileName = file.getOriginalFilename();
    //	String banglaFileName = file.getOriginalFilename();
    //
    //	if(!ObjectUtils.isEmpty(fileName)) {
    //		englishFileName = fileName + ".jrxml";
    //	}
    //
    //	if(!ObjectUtils.isEmpty(filenameBangla)) {
    //		banglaFileName = filenameBangla + ".jrxml";
    //	}
    //
    //	//check file dir
    //	Path root = Paths.get(dir);
    //    if (!Files.exists(root)) {
    //    	Files.createDirectories(Paths.get(dir));
    //    }
    //
    //	//chenge file name
    //    byte[] bytes = file.getBytes();
    //	String insPathEnglish = dir + englishFileName;
    //	String insPathBangla = dir + banglaFileName;
    //
    //	//save to dir
    //    Files.write(Paths.get(insPathEnglish), bytes);
    //    Files.write(Paths.get(insPathBangla), bytes);
    //
    //	response.put("fileNameEng", englishFileName);
    //	response.put("fileNameBan", banglaFileName);
    //	response.put("fileNameJasperEng", fileName+".jasper");
    //	response.put("fileNameJasperBan", filenameBangla+".jasper");
    //	response.put("fileLocation", dir);
    //
    //	return response;
    //}
}
