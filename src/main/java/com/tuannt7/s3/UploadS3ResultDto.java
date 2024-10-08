package com.tuannt7.s3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by tuannt7 on 08/10/2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadS3ResultDto {
    private String status;
    private String message;
    private String url;
}
