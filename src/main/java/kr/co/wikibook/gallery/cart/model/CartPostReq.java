package kr.co.wikibook.gallery.cart.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartPostReq {
    private int memberId;
    private int itemId;
}
