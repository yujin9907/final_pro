package site.metacoding.finals.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.finals.dto.ResponseDto;
import site.metacoding.finals.dto.customer.CustomerReqDto.CustomerJoinReqDto;
import site.metacoding.finals.dto.customer.CustomerRespDto.CustomerJoinRespDto;
import site.metacoding.finals.dto.customer.CustomerRespDto.CustomerMyPageReservationRespDto;
import site.metacoding.finals.service.CustomerService;

@RequiredArgsConstructor
@RestController
public class CustomerApiController {

    private final CustomerService customerService;

    @PostMapping("/customer/join")
    public ResponseEntity<?> joinCustomerApi(@RequestBody CustomerJoinReqDto customerJoinReqDto) {
        CustomerJoinRespDto customerJoinRespDto = customerService.joinCustomer(customerJoinReqDto);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.CREATED, "커스터머 회원가입 완료", customerJoinRespDto),
                HttpStatus.CREATED);
    }

    @GetMapping("/customer/mypage/reservation/{id}")
    public ResponseEntity<?> myPageCustomer(@PathVariable Long id) {
        CustomerMyPageReservationRespDto dto = customerService.mypageCustomerReservation(id);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "마이페이지 예약 목록", dto), HttpStatus.OK);
    }

}
