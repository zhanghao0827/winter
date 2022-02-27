package org.winter.common.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import org.springframework.web.multipart.MultipartFile;
import org.winter.common.constant.AliyunConstants;
import org.winter.common.constant.Constants;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

public class OSSUtils {

    private static OSS getOSS() {
        return new OSSClientBuilder().build(AliyunConstants.OSS.ENDPOINT, AliyunConstants.ACCESS_KEY_ID, AliyunConstants.SECRET_ACCESS_KEY);
    }

    public static String uploadImage(MultipartFile file) {
        OSS oss = getOSS();
        String bucket = AliyunConstants.OSS.BUCKET;
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType("image/jpg");
        String originalFilename = file.getOriginalFilename();
        String newName = bucket + Constants.AVATAR_URI + UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        try {
            oss.putObject(bucket, newName, file.getInputStream(), objectMetadata);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
        String url = oss.generatePresignedUrl(bucket, newName, expiration).toString();
        return url.substring(0, url.indexOf("?"));
    }

}
