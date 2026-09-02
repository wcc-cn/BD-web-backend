package com.cc.behaviordetectionbackend.ws;

import com.alibaba.fastjson.JSONObject;
import com.cc.behaviordetectionbackend.domain.DetectResult;
import com.cc.behaviordetectionbackend.repository.DetectResultMapper;
import com.cc.behaviordetectionbackend.utils.SpringContextUtil;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName WsModelDetectionServer
 * @Description 模型检测模块 ws服务端
 * @Author cc
 * @Date 2025/11/30 17:34
 * @Version 1.0.0
 */

@ServerEndpoint("/ws/bd_model_detection")
@Slf4j
@Component
public class WsModelDetectionServer {

    private DetectResultMapper detectResultMapper;

    /**
     * 记录当前在线的连接数
     */
    private static AtomicInteger onlineCount = new AtomicInteger(0);
    /**
     * concurrentHashMap，用来存放每个客户端对应的WebSocketServer对象
     */
    private static WsModelDetectionServer instance;

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 连接websocket
     * @param session 会话
     */
    @OnOpen
    public void onOpen(Session session) {
        try{
            this.session = session;
            instance = this;
            onlineCount.incrementAndGet();
            log.info("【WEB-websocket】有新的连接，当前在线人数为{}", onlineCount.get());
        }catch (Exception e){
            log.error("【WEB-websocket】连接建立失败",e);
        }
    }

    /**
     * 关闭websocket
     */
    @OnClose
    public void onClose() {
        try {
            instance = null;  // 清空唯一实例
            onlineCount.decrementAndGet();
        } catch (Exception e) {
            log.error("【WEB-websocket】关闭连接失败",e);
        }
    }

    /**
     * 发生错误时
     * @param session 会话
     * @param error 错误信息
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("【WEB-websocket】发生错误",error);
    }

    /**
     * 收到客户端消息时触发
     * @param message
     */
    @OnMessage
    public void onMessage(String message) {
        log.info("【WEB-websocket】收到模型检测模块消息：{}", message);

        // 你可以在这里解析消息
        try {
            // 如果你收到的是 JSON，可以这样解析
            JSONObject json = JSONObject.parseObject(message);
            String cmd = json.getString("cmd");
            if ("detect_video_ack".equals(cmd)) {
                this.detectResultMapper = SpringContextUtil.getBean(DetectResultMapper.class);
                log.info("收到识别结果指令");
                Map<String, String> target_mapping = new HashMap<>();
                target_mapping.put("run", "奔跑");
                target_mapping.put("stand", "站立");
                target_mapping.put("lie", "平躺");
                target_mapping.put("sit", "端坐");

                JSONObject data = json.getJSONObject("data");
                DetectResult detectResult = new DetectResult();
                detectResult.setVideoName(data.getString("origin_video_name"));
                detectResult.setPredictVideoName(data.getString("predict_video_name"));
                List<String> behaviorList = data.getObject("detect_target", List.class);
                String description = behaviorList.size() == 0 ? "此视频中未存在待检测行为" : "此视频中存在待检测行为： ";
                for (String behavior : behaviorList) {
                    description = description + target_mapping.get(behavior) + ",";
                }
                detectResult.setDetectDescription(description);
                detectResult.init();
                detectResultMapper.insert(detectResult);
                // 开始检测
            } else {
                log.warn("收到未知指令: {}", message);
            }
        } catch (Exception e) {
            log.error("消息解析失败: {}", e.getMessage());
        }
    }

    /**
     * 服务端主动推送消息
     * @param message
     */
    public void sendMessage(String message){
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (Exception e) {
            log.error("【WEB-websocket】发送消息失败",e);
        }
    }

    /**
     * 对外暴露使用websocket推送消息的方法
     * @param message
     */
    public static void sendInfo(String message) {
        try{
            instance.sendMessage(message);
        }catch (Exception e){
            log.error("【WEB-websocket】发送信息失败",e);
        }
    }

}
