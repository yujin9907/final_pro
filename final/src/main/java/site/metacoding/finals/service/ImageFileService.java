package site.metacoding.finals.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import site.metacoding.finals.domain.image_file.ImageFile;

@Service
public class ImageFileService {

    @Value("${filedir}")
    private String fileDir;

    // 파일 확장자
    protected String getExtension(String originalFilename) {
        int idx = originalFilename.lastIndexOf(".");
        return originalFilename.substring(idx);
    }

    // 파일 이름
    protected String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

    // 실제 로직
    public ImageFile storeFile(List<MultipartFile> multipartFiles) throws IOException {
        if (multipartFiles.isEmpty()) {
            return null;
        }

        String originalFilename = "";
        String storeFilename = "";
        for (MultipartFile multipartFile : multipartFiles) {
            originalFilename = multipartFile.getOriginalFilename();
            storeFilename = getUUID() + getExtension(originalFilename);

            multipartFile.transferTo(new File(fileDir, storeFilename));
        }

        // File image = new File(fileDir, storeFilename);
        // Files.copy(multipartFile.getInputStream(), image.toPath());

        return ImageFile.builder()
                .originFilename(originalFilename)
                .storeFilename(storeFilename)
                .build();
    }

}
