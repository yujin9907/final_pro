package site.metacoding.finals.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.finals.domain.image_file.ImageFile;
import site.metacoding.finals.domain.image_file.ImageFileRepository;
import site.metacoding.finals.domain.option.Option;
import site.metacoding.finals.domain.option.OptionRepository;
import site.metacoding.finals.dto.option.OptionReqDto.OptionSaveReqDto;
import site.metacoding.finals.dto.option.OptionRespDto.OptionSaveRespDto;
import site.metacoding.finals.handler.ImageFileHandler;

@Service
@RequiredArgsConstructor
public class OptionService {

    private final OptionRepository optionRepository;
    private final ImageFileRepository imageFileRepository;
    private final ImageFileHandler imageFileHandler;

    @Transactional
    public OptionSaveRespDto save(OptionSaveReqDto optionSaveReqDto) {

        // admin만 CRUD가 가능하도록 차후 코드 수정 해야함
        Option option = optionRepository.save(optionSaveReqDto.toSaveEntity());

        // 관리자 코드 추가 기능 차후 추가
        // List<String> img = Arrays.asList(optionSaveReqDto.getImg());
        // List<ImageFile> resultImg = imageFileHandler.storeFile(img);
        // imageFileRepository.save(resultImg);

        return new OptionSaveRespDto(option);

    }

}
