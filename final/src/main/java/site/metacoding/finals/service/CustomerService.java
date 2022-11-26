package site.metacoding.finals.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.metacoding.finals.domain.customer.Customer;
import site.metacoding.finals.domain.customer.CustomerRepository;
import site.metacoding.finals.domain.reservation.Reservation;
import site.metacoding.finals.domain.reservation.ReservationRepository;
import site.metacoding.finals.domain.shop.Shop;
import site.metacoding.finals.domain.shop.ShopRespository;
import site.metacoding.finals.domain.user.User;
import site.metacoding.finals.domain.user.UserRepository;
import site.metacoding.finals.dto.customer.CustomerReqDto.CustomerJoinReqDto;
import site.metacoding.finals.dto.customer.CustomerRespDto.CustomerJoinRespDto;
import site.metacoding.finals.dto.customer.CustomerRespDto.CustomerMyPageReservationRespDto;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final ShopRespository shopRespository;
    private final ReservationRepository reservationRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public CustomerJoinRespDto joinCustomer(CustomerJoinReqDto customerJoinReqDto) {
        String password = bCryptPasswordEncoder.encode(customerJoinReqDto.getPassword());
        customerJoinReqDto.setPassword(password);

        Customer customer = customerRepository.save(customerJoinReqDto.toCustomerEntity());
        User user = userRepository.save(customerJoinReqDto.toUserEntity());

        return new CustomerJoinRespDto(customer, user);
    }

    @Transactional(readOnly = true)
    public CustomerMyPageReservationRespDto mypageCustomerReservation(Long CustomerId) {
        List<Shop> shopList = shopRespository.findByResevationCustomerId(CustomerId);
        List<Reservation> reservationList = reservationRepository.findByCustomerId(CustomerId);

        log.debug("디버그 : " + shopList.size());
        log.debug("디버그 : " + reservationList.size());

        return new CustomerMyPageReservationRespDto(shopList, reservationList);

    }

}
