package com.sopnobazz.demo.comon.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;


/**
 * @version 1.0.0
 * @Author Md. Nayeemul Islam
 * @Project hospital-management
 * @Since May 28, 2021
 */
@Component
public class ImageUtils {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(ImageUtils.class);


    @Autowired
    private Environment env;

    public Map<String, String> uploadImageToServer(MultipartFile file, String code) throws IOException {

        Map<String, String> response = new HashMap<>();

        //dir
        String dir = env.getProperty("image.upload.dir");
        dir = dir.replace("\\", "/");

        //String dir = getImageDir();

        String fileName = file.getOriginalFilename();
        if (!ObjectUtils.isEmpty(code)) {
            fileName = code + ".png";
        }

        //check file dir
        Path root = Paths.get(dir);
        if (!Files.exists(root)) {
            Files.createDirectories(Paths.get(dir));
        }

        //chenge file name
        byte[] bytes = file.getBytes();
        String insPath = dir + fileName;

        //save to dir
        Files.write(Paths.get(insPath), bytes);

        response.put("fileName", fileName);
        response.put("url", dir);

        return response;
    }


    public Map<String, String> uploadRationEmployeeImageToServer(MultipartFile file, String code) throws IOException {

        Map<String, String> response = new HashMap<>();

        //dir
        //String dir = env.getProperty("ration.emp.image.upload.dir");
        String dir = env.getProperty("emp.image.upload.dir");
        dir = dir.replace("\\", "/");

        //String dir = getImageDir();

        String fileName = file.getOriginalFilename();
        if (!ObjectUtils.isEmpty(code)) {
            fileName = code + ".png";
        }

        //check file dir
        Path root = Paths.get(dir);
        if (!Files.exists(root)) {
            Files.createDirectories(Paths.get(dir));
        }

        //chenge file name
        byte[] bytes = file.getBytes();
        String insPath = dir + fileName;

        //save to dir
        Files.write(Paths.get(insPath), bytes);

        response.put("fileName", fileName);
        response.put("url", dir);

        return response;
    }


    public Map<String, String> uploadRationFamilyImageToServer(MultipartFile file, String code) throws IOException {

        Map<String, String> response = new HashMap<>();

        //dir
        String dir = env.getProperty("emp.image.upload.dir");
        dir = dir.replace("\\", "/");

        //String dir = getImageDir();
        String fileName = file.getOriginalFilename();
        if (!ObjectUtils.isEmpty(code)) {
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
            fileName = code + "_" + timestamp + "_" + fileName;
        }

        //check file dir
        Path root = Paths.get(dir);
        if (!Files.exists(root)) {
            Files.createDirectories(Paths.get(dir));
        }

        //chenge file name
        byte[] bytes = file.getBytes();
        String insPath = dir + fileName;

        //save to dir
        Files.write(Paths.get(insPath), bytes);

        response.put("fileName", fileName);
        response.put("fileLocation", dir);

        return response;
    }


    public Map<String, String> uploadEmployeeImageToServer(MultipartFile file, String code) throws IOException {

        Map<String, String> response = new HashMap<>();

        //dir
        String dir = env.getProperty("emp.image.upload.dir");
        dir = dir.replace("\\", "/");

        //String dir = getImageDir();

        String fileName = file.getOriginalFilename();
        if (!ObjectUtils.isEmpty(code)) {
            fileName = code + ".png";
        }

        //check file dir
        Path root = Paths.get(dir);
        if (!Files.exists(root)) {
            Files.createDirectories(Paths.get(dir));
        }

        //chenge file name
        byte[] bytes = file.getBytes();
        String insPath = dir + fileName;

        //save to dir
        Files.write(Paths.get(insPath), bytes);

        response.put("fileName", fileName);
        response.put("url", dir);

        return response;
    }


    public Map<String, String> uploadEmployeeSignatureToServer(MultipartFile file, String code) throws IOException {

        Map<String, String> response = new HashMap<>();

        //dir
        String dir = env.getProperty("emp.signature.upload.dir");
        dir = dir.replace("\\", "/");

        //String dir = getImageDir();

        String fileName = file.getOriginalFilename();
        if (!ObjectUtils.isEmpty(code)) {
            fileName = code + ".png";
        }

        //check file dir
        Path root = Paths.get(dir);
        if (!Files.exists(root)) {
            Files.createDirectories(Paths.get(dir));
        }

        //chenge file name
        byte[] bytes = file.getBytes();
        String insPath = dir + fileName;

        //save to dir
        Files.write(Paths.get(insPath), bytes);

        response.put("fileName", fileName);
        response.put("url", dir);

        return response;
    }


    public Map<String, String> uploadApplicantImageToServer(MultipartFile file, String code) throws IOException {

        Map<String, String> response = new HashMap<>();

        //dir
        String dir = env.getProperty("image.upload.dir");
        dir = dir.replace("\\", "/");

        //String dir = getImageDir();

        String fileName = file.getOriginalFilename();
        if (!ObjectUtils.isEmpty(code)) {
            fileName = code + ".png";
        }

        //check file dir
        Path root = Paths.get(dir);
        if (!Files.exists(root)) {
            Files.createDirectories(Paths.get(dir));
        }

        //chenge file name
        byte[] bytes = file.getBytes();
        String insPath = dir + fileName;

        //save to dir
        Files.write(Paths.get(insPath), bytes);

        response.put("fileName", fileName);
        response.put("url", dir);

        return response;
    }


    public Map<String, String> uploadCommonNoteSheetToServer(MultipartFile file, String code) throws IOException {

        Map<String, String> response = new HashMap<>();

        //dir
        String dir = env.getProperty("noteshet.upload.dir");
        dir = dir.replace("\\", "/");

        //String dir = getImageDir();

        String fileName = file.getOriginalFilename();
        if (!ObjectUtils.isEmpty(code)) {
            fileName = code + fileName;
        }

        //check file dir
        Path root = Paths.get(dir);
        if (!Files.exists(root)) {
            Files.createDirectories(Paths.get(dir));
        }

        //chenge file name
        byte[] bytes = file.getBytes();
        String insPath = dir + fileName;

        //save to dir
        Files.write(Paths.get(insPath), bytes);

        response.put("fileName", fileName);
        response.put("url", dir);

        return response;
    }

    public Boolean deleteImageFromServer(String path) throws IOException {
        Boolean response = false;
        Path filePath = Paths.get(path);
        Files.delete(filePath);
        response = true;
        return response;
    }

    public Boolean deleteEmployeeImageFromServer(String path) throws IOException {
        Boolean response = false;
        Path filePath = Paths.get(path);
        Files.delete(filePath);
        response = true;
        return response;
    }


    @SuppressWarnings("unused")
    private String getExtensionFromFile(MultipartFile file) {
        String orgnalFile = file.getOriginalFilename();
        String[] fileArray = orgnalFile.split(".");
        return String.format(".%s", fileArray[1].toLowerCase());
    }

    @SuppressWarnings("unused")
    private String getImageDir() {
        String dir = env.getProperty("image.upload.dir");
        dir = dir.replace("\\", "/");
        return dir;
    }

    @SuppressWarnings("unused")
    private Boolean checkDir(String dir) throws IOException {
        Path root = Paths.get(dir);
        if (!Files.exists(root)) {
            Files.createDirectories(Paths.get(dir));
        }
        return true;
    }

}
