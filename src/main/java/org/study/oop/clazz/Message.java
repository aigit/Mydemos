package org.study.oop.clazz;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class Message implements Serializable {



    private Long sequenceId;
    private Integer messageType;
    public abstract Integer getMessageType();


    public Long getSeqId(){
        return sequenceId;
    }

    public void setSequenceId(Long sequenceId) {
        this.sequenceId = sequenceId;
    }
}
