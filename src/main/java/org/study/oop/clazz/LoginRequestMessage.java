package org.study.oop.clazz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@AllArgsConstructor
public class LoginRequestMessage extends Message {

    private String token;


    @Override
    public Integer getMessageType() {
        return null;
    }
}
