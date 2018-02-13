package com.lan.ichat.controller;

import com.lan.common.util.BaseResult;
import com.lan.common.util.IChatStatus;
import com.lan.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * package com.lan.ichat.controller
 *
 * @author spoomlan
 * @date 23/12/2017
 */
@RestController
@RequestMapping(value = "/api")
public class CloudFileController {
    private final static Logger logger = LoggerFactory.getLogger(CloudFileController.class);

    //private static String UPLOAD_DIR = "E:/elysium_server/web_server/htdocs/";
    private static String UPLOAD_DIR = "/Users/spoomlan/Documents/learn/html";

    @PostMapping("/upload")
    public BaseResult uploadSingle(@RequestParam("file") MultipartFile file) {
        BaseResult result = new BaseResult();
        String path;
        if (file.isEmpty()) {
            return result.setMsg("empty file").setCode(IChatStatus.FAILURE.code());
        }
        String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
        String type = file.getContentType().substring(0, file.getContentType().indexOf('/'));
        path = "/" + type + "/" + StringUtils.getUUID() + ext;
        try {
            File desFile = new File(UPLOAD_DIR + path);
            file.transferTo(desFile);
            return result.setMsg("upload success").setData(path);
        } catch (IOException e) {
            e.printStackTrace();
            return result.setMsg("io exception").setCode(IChatStatus.FAILURE.code());
        }
    }
}
