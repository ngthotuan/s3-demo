package com.tuannt7.s3;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by tuannt7 on 08/10/2024
 */

@Slf4j
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class S3Controller {
    private final S3Service s3Service;

    @GetMapping
    public String index() {
        return "index";
    }

    @PostMapping
    @ResponseBody
    public UploadS3ResultDto createBucket(MultipartFile file) {
        log.info("request upload file: {} to s3", file.getOriginalFilename());
        return s3Service.uploadFile(file);
    }
}
