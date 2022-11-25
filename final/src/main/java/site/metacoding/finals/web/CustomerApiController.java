package site.metacoding.finals.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.finals.dto.ResponseDto;
import site.metacoding.finals.dto.customer.CustomerReqDto.CustomerJoinReqDto;
import site.metacoding.finals.dto.customer.CustomerRespDto.CustomerJoinRespDto;
import site.metacoding.finals.service.CustomerService;

@RequiredArgsConstructor
@RestController
public class CustomerApiController {

    private final CustomerService customerService;

    @PostMapping("/customer/join")
    public ResponseDto<?> joinCustomerApi(@RequestBody CustomerJoinReqDto customerJoinReqDto) {
        CustomerJoinRespDto customerJoinRespDto = customerService.joinCustomer(customerJoinReqDto);
        return new ResponseDto<>(HttpStatus.CREATED, "커스터머 회원가입 완료", customerJoinRespDto);
    }
}
