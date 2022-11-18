package site.metacoding.finals.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@SpringBootTest
public class EnumTest {

    dummy dummy = new dummy(1, testcode.TEST1);

    @Test
    public void getname() {
        System.out.println(dummy.getTestcode());
        System.out.println(dummy.getTestcode().getValue());
        log.debug("디버그 " + dummy.getTestcode().getValue());
    }

}

@Getter
@AllArgsConstructor
@NoArgsConstructor
enum testcode {
    TEST1("TEST1"),
    TEST2("TEST2");

    private String value;
}

@Data
@AllArgsConstructor
class dummy {
    private int id;
    private testcode testcode;
}