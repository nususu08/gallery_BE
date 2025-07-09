package kr.co.wikibook.gallery.cart.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CartGetRes {
    private int id;
    private String name;
    private int price;
    private String imgPath;
    private int discountPer;
}
