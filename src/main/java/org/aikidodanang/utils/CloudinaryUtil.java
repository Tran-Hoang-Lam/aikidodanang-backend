package org.aikidodanang.utils;

import com.cloudinary.Cloudinary;
import org.aikidodanang.properties.CloudinaryProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CloudinaryUtil {
    private final CloudinaryProperties cloudinaryProperties;

    private Cloudinary cloudinary = new Cloudinary();

    public CloudinaryUtil(CloudinaryProperties cloudinaryProperties) {
        this.cloudinaryProperties = cloudinaryProperties;
        cloudinary.setConfig("cloud_name", this.cloudinaryProperties.getCloudName());
        cloudinary.setConfig("api_key", this.cloudinaryProperties.getKey());
        cloudinary.setConfig("api_secret", this.cloudinaryProperties.getSecret());
    }

    public String uploadImage(Object file) throws IOException {
        if (file != null) {
            Map<String, Object> options = new HashMap<>();
            Map result = cloudinary.uploader().upload(file, options);
            return (String) result.get("url");
        }
        return null;
    }

}
