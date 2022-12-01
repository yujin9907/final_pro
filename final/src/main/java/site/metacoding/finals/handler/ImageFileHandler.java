package site.metacoding.finals.handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    // 파일 디코딩
    protected byte[] decoder(String base64) {
        Base64.Decoder decoder = Base64.getDecoder();
        return decoder.decode(base64.getBytes());
    }

    // 파일 이름
    protected String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

    // 폴더 생성
    protected void getFolder(String dir) {
        File folder = new File(fileDir);
        if (!folder.exists())
            folder.mkdir();
    }

    // 실제 로직
    public List<ImageFile> storeFile(List<String> files) {

        log.debug("이미지 핸들러 진입");

        List<byte[]> fileBytes = files.stream().map((f) -> decoder(f)).collect(Collectors.toList());

        getFolder(fileDir);

        List<ImageFile> images = new ArrayList<>();
        String fileName = getUUID() + ".png";

        fileBytes.forEach(fileByte -> {

            try {
                FileOutputStream fileOutputStream = new FileOutputStream(fileDir + fileName);
                fileOutputStream.write(fileByte);
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            images.add(ImageFile.builder()
                    .storeFilename(fileName)
                    .build());
        });

        log.debug("이미지 저장 완료");
        return images;
    }

}