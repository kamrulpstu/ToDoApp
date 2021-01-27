package com.task.kamrul.bean;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Md. Kamrul Hasan Shamim
 */
public class BaseResponse {

    private boolean status;
    private List<String> messages;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<String> getMessages() {
        if (messages == null) {
            messages = new ArrayList<>();
        }
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
