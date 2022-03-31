package br.com.idelivery.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotificationService {

  private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

  public void notify(NotificationRequest notificationRequest) {
    logger.info(" == enviado email para {} ==", notificationRequest.getTo());
    logger.info(" == {} ==", notificationRequest.getTitle());
    logger.info(" == {} ==", notificationRequest.getMessage());


  }
}
