package com.tuannt7.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;


/**
 * Created by tuannt7 on 08/10/2024
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service {
    private static final String UPLOAD_DIR = "uploads/";
    private final S3Config s3Config;
    private final AmazonS3 s3Client;

    public UploadS3ResultDto uploadFile(MultipartFile multipartFile) {
        try {
            File file = convertMultiPartToFile(multipartFile);
            String fileName = UPLOAD_DIR + System.currentTimeMillis() + "_" +  multipartFile.getOriginalFilename();
            s3Client.putObject(new PutObjectRequest(s3Config.getBucketName(), fileName, file));
            String fileUrl = s3Config.getEndpoint() + "/" + s3Config.getBucketName() + "/" + fileName;
            return new UploadS3ResultDto("OK", "Upload file to S3 success", fileUrl);
        } catch (Exception ex) {
            log.error("upload file to S3 exception: {}", ex.getMessage(), ex);
            return new UploadS3ResultDto("FAILED", ex.getMessage(), null);
        }
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
