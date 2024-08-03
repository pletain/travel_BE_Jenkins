package com.samsam.travel.travelcommerce.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReturnMessage {

    /**
     * @title	- API 결과코드
     * @author	- Colton
     * @date	- 2024.08.03
     * @param	- 0000	: 성공
     * @param	- 9xxx	: 실패
     * @param	- 9999	: 실패
     */

    private String result;
    private String message;
    private Object value;


    public ReturnMessage() {
        this.result = "0000";
        this.message = "성공";
        this.value = null;
    }

    public ReturnMessage(Object value) {
        this.result = "0000";
        this.message = "성공";
        this.value = value;
    }

    public ReturnMessage(String result, String message, Object value) {
        this.result = result;
        this.message = message;
        this.value = value;
    }

    public ReturnMessage(String result, String message, Exception e) {
        this.result = result;
        this.message = message;
        this.value = e.toString();
    }
}

