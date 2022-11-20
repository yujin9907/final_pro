package site.metacoding.finals.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponseDto<T> {
    private int code; // http 상태코드
    private String msg;
    private T data;
}
