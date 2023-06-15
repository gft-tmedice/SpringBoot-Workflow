package com.gft.app.elw;

public interface IAction<MessageRequest, MessageResponse> {

    MessageRequest requestModel();

    MessageResponse run(MessageRequest processParams);
}
