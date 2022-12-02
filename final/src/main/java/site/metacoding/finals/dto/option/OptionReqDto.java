package site.metacoding.finals.dto.option;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.finals.domain.option.Option;

public class OptionReqDto {
    @Setter
    @Getter
    public static class OptionSaveReqDto {
        private String optionName;
        private String img;

        public Option toSaveEntity() {
            return Option.builder()
                    .optionName(optionName)
                    .img(img)
                    .build();
        }
    }
}
