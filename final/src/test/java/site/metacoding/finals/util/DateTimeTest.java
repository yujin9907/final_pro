package site.metacoding.finals.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateTimeTest {

    @Test
    public void datetimeTest() {
        String todays = DateTimeFormatter.ofPattern("dd").format(LocalDate.now());
        int today = Integer.parseInt(todays);

        log.debug("디버그 : " + today);

    }

}
