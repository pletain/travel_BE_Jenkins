package com.samsam.travel.travelcommerce.domain.cart.api;

import com.samsam.travel.travelcommerce.domain.cart.service.CartService;
import com.samsam.travel.travelcommerce.dto.cart.CartAddDto;
import com.samsam.travel.travelcommerce.dto.cart.CartDto;
import com.samsam.travel.travelcommerce.dto.ticket.TicketDto;
import com.samsam.travel.travelcommerce.entity.Ticket;
import com.samsam.travel.travelcommerce.entity.User;
import com.samsam.travel.travelcommerce.global.error.exception.CartInvalidInputException;
import com.samsam.travel.travelcommerce.utils.ApiResponse;
import com.samsam.travel.travelcommerce.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.samsam.travel.travelcommerce.global.status.CommonCode.SUCCESS_ADD_CART;
import static com.samsam.travel.travelcommerce.global.status.CommonCode.SUCCESS_VIEW_CART;
import static com.samsam.travel.travelcommerce.global.status.ErrorCode.BAD_REQUEST_INVALID_CART_VALUES;

/**
 * 상품(티켓)에 관련된 API를 수행하는 컨트롤러입니다.
 *
 * @author colton
 * @since 1.0.0
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    /**
     * 내 장바구니 조회
     *
     * @param userDetails 인증된 관리자의 세부 정보. Spring Security 컨텍스트에서 가져옵니다.
     * @return 조회 성공 여부 및 문구, 장바구느에 담긴 티켓 데이터
     */
    @GetMapping("/view")
    public ResponseEntity<ApiResponse<List<TicketDto>>> viewMyCartTicket(@AuthenticationPrincipal UserDetails userDetails) {
        CartDto cartDto = new CartDto();
        setUser(userDetails, cartDto);
        return ResponseUtil.createApiResponse(SUCCESS_VIEW_CART, cartService.getMyCartTicket(cartDto));
    }

    /**
     * 내 장바구니 등록
     *
     * @param userDetails 인증된 관리자의 세부 정보. Spring Security 컨텍스트에서 가져옵니다.
     * @return 조회 성공 여부 및 문구, 장바구느에 담긴 티켓 데이터
     */
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<CartDto>> addMyCart(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute CartAddDto cartAddDto) {
        if(cartAddDto.isValidate()) {
            throw new CartInvalidInputException(BAD_REQUEST_INVALID_CART_VALUES);
        }

        CartDto cartDto = new CartDto();
        setUser(userDetails, cartDto);
        setTicket(cartDto, cartAddDto.getTicketId());
        cartDto.setQuantity(cartAddDto.getQuantity());

        return ResponseUtil.createApiResponse(SUCCESS_ADD_CART, cartService.addMyCart(cartDto));
    }

    /**
     * Respose
     *
     * @param cartDto 장바구니에 대한 정보
     * @param ticketId 티켓 아이디
     * @return 상품 수정 여부, 문구와 상품 데이터
     */
    public CartDto setTicket(CartDto cartDto, String ticketId) {
        Ticket ticket = Ticket.builder()
                            .ticketId(ticketId)
                            .build();
        cartDto.setTicket(ticket);
        return cartDto;
    }

    /**
     * Respose
     *
     * @param userDetails 인증된 관리자의 세부 정보. Spring Security 컨텍스트에서 가져옵니다.
     * @param cartDto 장바구니에 대한 정보
     * @return 상품 수정 여부, 문구와 상품 데이터
     */
    public CartDto setUser(UserDetails userDetails, CartDto cartDto) {
        cartDto.setUser(getUser(userDetails));
        return cartDto;
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