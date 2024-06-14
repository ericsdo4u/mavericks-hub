package com.maverickshube.maverickshube.utils;


import com.maverickshube.maverickshube.dtos.UploadMediaRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public class TestUtils {
        public static UploadMediaRequest buildUploadMediaRequest(InputStream inputStream) throws IOException {
                UploadMediaRequest uploadRequest = new UploadMediaRequest();
                MultipartFile file = new MockMultipartFile("media", inputStream);
                uploadRequest.setMediaFile(file);
                uploadRequest.setUserId(200L);
                return uploadRequest;
        }

        public static final String TEST_IMAGE_LOCATION = "C:\\Users\\DELL\\Desktop\\java-project\\maverickshube\\src\\main\\resources\\static\\dd.jpg";
        public static final String TEST_VIDEO_LOCATION = "C:\\Users\\DELL\\Desktop\\java-project\\maverickshube\\src\\main\\resources\\static\\srtikevid.mp4";
}


