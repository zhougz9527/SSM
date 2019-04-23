package com.ssm.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 * @Author: Think
 * @Date: 2019/4/23
 * @Time: 23:14
 */
@Component
public class WebSocketDisconnectHandler implements ApplicationListener<SessionDisconnectEvent> {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    private final static String SUBSCRIBE_LOGOUT_URI = "/topic/logout";



    @Override
    public void onApplicationEvent(SessionDisconnectEvent sessionDisconnectEvent) {
        // TODO
    }
}
