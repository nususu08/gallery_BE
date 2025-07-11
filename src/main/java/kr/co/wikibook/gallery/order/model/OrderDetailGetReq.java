package kr.co.wikibook.gallery.order.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDetailGetReq {
    private int orderId;
    private int memberId;
}
