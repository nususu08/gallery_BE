package kr.co.wikibook.gallery.order;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.wikibook.gallery.cart.CartMapper;
import kr.co.wikibook.gallery.item.ItemMapper;
import kr.co.wikibook.gallery.item.model.ItemGetRes;
import kr.co.wikibook.gallery.order.model.*;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

@Builder
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderMapper orderMapper;
    private final ItemMapper itemMapper;
    private final OrderItemMapper orderItemMapper;
    private final CartMapper cartMapper;

    @Transactional
    public int saveOrder(OrderPostReq req, int memberId) {
        // 상품 정보 DB 로부터 가져온다.
        List<ItemGetRes> itemList = itemMapper.findAllByIdIn(req.getItemIds());
        log.info("itemList={}", itemList);
        int amount = 0;
        for (ItemGetRes list : itemList) {
            amount += list.getPrice() - (list.getPrice() * list.getDiscountPer()) / 100;
        }
        // 총합 값 찍어보기
        log.info("result: {}", amount);

        // 만드세요 OrderPostDto 객체화 하시고 데이터 넣어주세요.
        OrderPostDto orderPostDto = OrderPostDto.builder()
                .memberId(memberId)
                .name(req.getName())
                .address(req.getAddress())
                .payment(req.getPayment())
                .cardNumber(req.getCardNumber())
                .amount(amount)
                .build();

        log.info("before-orderPostDto={}", orderPostDto);
        int result = orderMapper.save(orderPostDto);
        log.info("after-orderPostDto={}", orderPostDto);

        // OrderItemPostDto 객체화 하시면서 데이터 넣어주세요.
        OrderItemPostDto orderItemPostDto = new OrderItemPostDto(orderPostDto.getOrderId(), req.getItemIds());
        int resultDetail = orderItemMapper.save(orderItemPostDto);

        cartMapper.deleteByMemberId(memberId);
        return 1;
    }

    public List<OrderGetRes> findAll(int memberId) {
        return orderMapper.findAllByMemberIdOrderByIdDesc(memberId);
    }

    public OrderDetailGetRes detail(OrderDetailGetReq req) {
        OrderDetailGetRes result = orderMapper.findByOrderIdAndMemberId(req);
        List<OrderDetailDto> items = orderItemMapper.findAllByOrderId(req.getOrderId());
        result.setItems(items);
        log.info("result={}", result);
        return result;
    }
}
