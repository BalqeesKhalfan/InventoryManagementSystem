package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.AOP.TrackExecutionTime;
import com.slack.api.Slack;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SlackService {

    @Value("${slack.token.integration}")
    private String slackToken;

    @TrackExecutionTime
    public void sendMessage( String channel, String message) {
        Slack slack = Slack.getInstance();

        channel = "#balqees";//here we can sepcife the channel name
        //message = "Testing";
        ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                .channel(channel)
                .text(message)
                .build();

        try {
            ChatPostMessageResponse response = slack.methods(slackToken).chatPostMessage(request);
            if (response.isOk()) {
                System.out.println("Message sent successfully to Slack!");
            } else {
                System.out.println("Failed to send message to Slack: " + response.getError());
            }
        } catch (Exception e) {
            System.out.println("Error sending message to Slack: " + e.getMessage());
        }
    }
    @TrackExecutionTime
    public void sendMessageDeffrentChannel( String channel, String message) {
        Slack slack = Slack.getInstance();


        ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                .channel(channel)
                .text(message)
                .build();

        try {
            ChatPostMessageResponse response = slack.methods(slackToken).chatPostMessage(request);
            if (response.isOk()) {
                System.out.println("Message sent successfully to Slack!");
            } else {
                System.out.println("Failed to send message to Slack: " + response.getError());
            }
        } catch (Exception e) {
            System.out.println("Error sending message to Slack: " + e.getMessage());
        }
    }
  // @Scheduled(cron = "* 0/1 * * * *")  Add scheduled cron  exprection to run every second , every one mint every day of month and every month and evry day of week
  @TrackExecutionTime
    public  void  sendSchduledMessge(){
       String channel = "#balqees";//here we can sepcife the channel name
       String message = "Testing CRON";
       sendMessageCRON(channel,message);
   }
    @TrackExecutionTime
    public void sendMessageCRON( String channel, String message) {
        Slack slack = Slack.getInstance();


        ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                .channel(channel)
                .text(message)
                .build();

        try {
            ChatPostMessageResponse response = slack.methods(slackToken).chatPostMessage(request);
            if (response.isOk()) {
                System.out.println("Message sent successfully to Slack!");
            } else {
                System.out.println("Failed to send message to Slack: " + response.getError());
            }
        } catch (Exception e) {
            System.out.println("Error sending message to Slack: " + e.getMessage());
        }
    }



}