package site.metacoding.finals.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.finals.domain.customer.Customer;
import site.metacoding.finals.domain.customer.CustomerRepository;
import site.metacoding.finals.domain.user.User;
import site.metacoding.finals.domain.user.UserRepository;
import site.metacoding.finals.dto.customer.CustomerReqDto.CustomerJoinReqDto;
import site.metacoding.finals.dto.customer.CustomerRespDto.CustomerJoinRespDto;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public CustomerJoinRespDto joinCustomer(CustomerJoinReqDto customerJoinReqDto) {
        String password = bCryptPasswordEncoder.encode(customerJoinReqDto.getPassword());
        customerJoinReqDto.setPassword(password);

        Customer customer = customerRepository.save(customerJoinReqDto.toCustomerEntity());
        User user = userRepository.save(customerJoinReqDto.toUserEntity());

        return new CustomerJoinRespDto(customer, user);
    }

}
