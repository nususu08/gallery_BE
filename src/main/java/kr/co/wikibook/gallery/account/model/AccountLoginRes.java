package kr.co.wikibook.gallery.account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AccountLoginRes {
    private int id;
    @JsonIgnore
    private String loginPw;
}
