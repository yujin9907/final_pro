package site.metacoding.finals.handler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import site.metacoding.finals.domain.image_file.ImageFile;

@Component
public class ImageFileHandler {

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
    public List<ImageFile> storeFile(List<MultipartFile> multipartFiles) {
        if (multipartFiles.isEmpty()) {
            return null;
        }
        File folder = new File(fileDir);
        if (!folder.exists())
            folder.mkdirs();

        System.err.println("디버그 : 서비스 진입");
        System.out.println("디버그 : " + fileDir);

        String originalFilename = "";
        String storeFilename = "";
        List<ImageFile> images = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            originalFilename = multipartFile.getOriginalFilename();
            storeFilename = getUUID() + getExtension(originalFilename);

            try {
                multipartFile.transferTo(new File(fileDir, storeFilename));
            } catch (IOException e) {
                new RuntimeException("파일 저장 에러");
            }

            images.add(ImageFile.builder()
                    .originFilename(originalFilename)
                    .storeFilename(storeFilename)
                    .build());
        }

        // File image = new File(fileDir, storeFilename);
        // Files.copy(multipartFile.getInputStream(), image.toPath());

        return images;
    }

}
