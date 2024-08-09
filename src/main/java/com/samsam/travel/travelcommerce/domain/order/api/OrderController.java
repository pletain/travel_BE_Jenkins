package com.samsam.travel.travelcommerce.domain.order.api;

import com.samsam.travel.travelcommerce.domain.order.service.OrderService;
import com.samsam.travel.travelcommerce.dto.order.OrderListResponse;
import com.samsam.travel.travelcommerce.dto.order.OrderRequest;
import com.samsam.travel.travelcommerce.utils.ApiResponse;
import com.samsam.travel.travelcommerce.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.samsam.travel.travelcommerce.global.status.CommonCode.SUCCESS_ALL_ORDER_LIST;
import static com.samsam.travel.travelcommerce.global.status.CommonCode.SUCCESS_ORDER_CREATE;

/**
 * 주문 관련 작업을 처리하는 컨트롤러입니다.
 *
 * @author lavin
 * @since 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    /**
     * 새로운 주문을 생성합니다.
     *
     * @param userDetails 주문을 생성하는 사용자. 인증 주체에서 가져옵니다.
     * @param orderRequest 생성할 주문의 세부 정보.
     * @return 성공 상태와 메시지를 포함하는 API 응답을 포함하는 응답 엔티티.
     */
    @PostMapping("/")
    public ResponseEntity<ApiResponse> createOrder(@AuthenticationPrincipal UserDetails userDetails, @RequestBody @Validated OrderRequest orderRequest) {
        orderService.createOrder(userDetails.getUsername(), orderRequest);
        return ResponseUtil.createApiResponse(SUCCESS_ORDER_CREATE, SUCCESS_ORDER_CREATE.getMessage());
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<OrderListResponse>>> getAllOrders(@AuthenticationPrincipal UserDetails userDetails) {
        List<OrderListResponse> allOrders = orderService.getAllOrders(userDetails.getUsername());
        return ResponseUtil.createApiResponse(SUCCESS_ALL_ORDER_LIST, allOrders);
    }
}