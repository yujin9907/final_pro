package site.metacoding.finals.handler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import site.metacoding.finals.domain.image_file.ImageFile;

@Slf4j
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
        log.debug("이미지 핸들러 진입");

        File folder = new File(fileDir);
        if (!folder.exists())
            folder.mkdir();

        List<ImageFile> images = new ArrayList<>();

        multipartFiles.forEach(multipartFile -> {

            try {
                multipartFile
                        .transferTo(new File(fileDir, getUUID() + getExtension(multipartFile.getOriginalFilename())));
            } catch (IOException e) {
                new RuntimeException("파일 저장 에러");
            }

            images.add(ImageFile.builder()
                    .originFilename(multipartFile.getOriginalFilename())
                    .storeFilename(getUUID() + getExtension(multipartFile.getOriginalFilename()))
                    .build());
        });

        log.debug("이미지 저장 완료");
        return images;
    }

    public void toEncoding() {

    }

}