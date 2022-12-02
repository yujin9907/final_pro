package site.metacoding.finals.dto.option;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.finals.domain.option.Option;

public class OptionRespDto {

    @Setter
    @Getter
    public static class OptionSaveRespDto {
        private Long id;
        private String optionName;
        private String img;

        public OptionSaveRespDto(Option option) {
            this.id = option.getId();
            this.optionName = option.getOptionName();
            this.img = option.getImg();
        }
    }
}
