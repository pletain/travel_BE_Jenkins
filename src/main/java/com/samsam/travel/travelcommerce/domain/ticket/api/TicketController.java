package com.samsam.travel.travelcommerce.domain.ticket.api;

import com.samsam.travel.travelcommerce.domain.ticket.service.TicketService;
import com.samsam.travel.travelcommerce.dto.ticket.TicketDto;
import com.samsam.travel.travelcommerce.dto.ticket.TicketResponseDto;
import com.samsam.travel.travelcommerce.entity.User;
import com.samsam.travel.travelcommerce.global.status.CommonCode;
import com.samsam.travel.travelcommerce.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import static com.samsam.travel.travelcommerce.global.status.CommonCode.SUCCESS_ADD_TICKET;
import static com.samsam.travel.travelcommerce.global.status.CommonCode.SUCCESS_MODIFY_TICKET;

/**
 * 상품(티켓)에 관련된 API를 수행하는 컨트롤러입니다.
 *
 * @author colton
 * @since 1.0.0
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    /**
     * 상품 등록 API
     *
     * @param userDetails 인증된 관리자의 세부 정보. Spring Security 컨텍스트에서 가져옵니다.
     * @param ticketDto 상품에 대한 정보
     * @return 상품 등록 성공 여부, 문구와 상품 데이터
     */
    @PostMapping("/regist")
    public ResponseEntity<ApiResponse<TicketResponseDto>> addTicket(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute TicketDto ticketDto) {
        setTicketDto(userDetails, ticketDto);
        TicketResponseDto responseDto = ticketService.addTicket(ticketDto);
        return buildResponseEntity(SUCCESS_ADD_TICKET, responseDto);
    }

    /**
     * 상품 수정 API
     *
     * @param userDetails 인증된 관리자의 세부 정보. Spring Security 컨텍스트에서 가져옵니다.
     * @param ticketDto 상품에 대한 정보
     * @return 상품 수정 여부, 문구와 상품 데이터
     */
    @PutMapping("/modify")
    public ResponseEntity<ApiResponse<Boolean>> updateTicket(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute TicketDto ticketDto) {
        setTicketDto(userDetails, ticketDto);
        boolean updateYn = ticketService.updateTicket(ticketDto) > 0;
        return buildResponseEntity(SUCCESS_MODIFY_TICKET, updateYn);
    }

    /**
     * ResposeEntity 생성 메서드
     *
     * @param code 상태 코드와 해당 메시지 담고 있는 부분
     * @param data 데이터를 담고 있는 부분
     * @return 상태 코드와 메시지, 데이트를 담고 있는 ResponseEntity
     */
    private <T> ResponseEntity<ApiResponse<T>> buildResponseEntity(CommonCode code, T data) {
        ApiResponse<T> response = ApiResponse.createResponse(code, data);
        return ResponseEntity.status(code.getHttpStatus()).body(response);
    }

    /**
     * Respose
     *
     * @param userDetails 인증된 관리자의 세부 정보. Spring Security 컨텍스트에서 가져옵니다.
     * @param ticketDto 상품에 대한 정보
     * @return 상품 수정 여부, 문구와 상품 데이터
     */
    public TicketDto setTicketDto(UserDetails userDetails, TicketDto ticketDto) {
        ticketDto.setUser(getUser(userDetails));
        return ticketDto;
    }

    /**
     * 유저 정보를 User Entity에 담는 메서드
     *
     * @param userDetails 인증된 관리자의 세부 정보. Spring Security 컨텍스트에서 가져옵니다.
     * @return User 정보를 담은 user Entity 반환
     */
    public User getUser(UserDetails userDetails) {
        return  User
                .builder()
                .userId(userDetails.getUsername())
                .password(userDetails.getPassword())
                .build();
    }
}