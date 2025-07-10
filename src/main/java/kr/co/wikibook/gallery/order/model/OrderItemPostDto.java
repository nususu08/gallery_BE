package kr.co.wikibook.gallery.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor
public class OrderItemPostDto {
    private int orderId;
    private List<Integer> itemIds;
}
