package com.lan.ichat.controller;

import com.lan.common.util.BaseResult;
import com.lan.common.util.IChatStatus;
import com.lan.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

/**
 * package com.lan.ichat.controller
 *
 * @author spoomlan
 * @date 23/12/2017
 */
@RestController
public class ImageController {
    private final static Logger logger = LoggerFactory.getLogger(ImageController.class);

    private static String UPLOAD_DIR = "/Users/spoomlan/Documents/learn/html/img/";

    @PostMapping("/api/upload")
    public BaseResult uploadSingle(@RequestParam("file") MultipartFile uploadfile) {
        logger.debug("Single file upload!");
        String localPath = "";
        if (uploadfile.isEmpty()) {
            return new BaseResult("empty file", IChatStatus.FAILURE.code());
        }
        try {
            localPath = saveFile(Collections.singletonList(uploadfile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new BaseResult(localPath, IChatStatus.SUCCESS.code());
    }

    private String saveFile(List<MultipartFile> files) throws IOException {
        String name = null;
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue; //next pls
            }
            byte[] bytes = file.getBytes();
            name = UPLOAD_DIR + StringUtils.getUUID() + ".png";
            logger.info("target path: " + name);
            Path path = Paths.get(name);
            Files.write(path, bytes);
        }
        return name;
    }

}
