package site.metacoding.finals.handler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
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
            folder.mkdir();

        System.err.println("디버그 : 이미지 핸들러 진입");

        List<ImageFile> images = new ArrayList<>();

        multipartFiles.forEach(multipartFile -> {
            System.out.println("반복문 실행");

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
        System.out.println("이미지 저장 완료 ");
        // File image = new File(fileDir, storeFilename);
        // Files.copy(multipartFile.getInputStream(), image.toPath());

        return images;
    }

}