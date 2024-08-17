package com.sopnobazz.demo.comon.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;


/**
 * @version 1.0.0
 * @Author Md. Nayeemul Islam
 * @Project demo-management
 * @Since May 28, 2021
 */
@Component
public class CommonFileUploadUtils {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(CommonFileUploadUtils.class);


    public Map<String, String> uploadCommonFileToServer(MultipartFile file, String fileLocation, String title) throws IOException {

        Map<String, String> response = new HashMap<>();

        //dir
        String dir = fileLocation;
        dir = dir.replace("\\", "/");

        //String dir = getImageDir();
        String fileName = file.getOriginalFilename();
        if (!ObjectUtils.isEmpty(title)) {
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
            fileName = timestamp + "_" + title + "_" + fileName;
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


    public String uploadImageToServerFromBase64String(String base64StringData, String fileName, String fileLocation) throws IOException {

        String[] base64Array = base64StringData.split(",");
        String type = base64Array[0].split("/")[1].split(";")[0];
        String base64Image = base64Array[1];

        byte[] decoded = Base64.getDecoder().decode(base64Image);

        String timestamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        String newFileName = timestamp + "_" + fileName + "." + type;
        String newFilePath = fileLocation + newFileName;

        Files.write(Paths.get(newFilePath), decoded);

        return newFileName;
    }

}
