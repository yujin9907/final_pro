package site.metacoding.finals.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.finals.domain.customer.CustomerRepository;
import site.metacoding.finals.dto.user.UserReqDto.JoinReqDto;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public void join(JoinReqDto joinReqDto) {

    }

}
