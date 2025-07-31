package com.medplus.marketing.helper;

import java.util.Arrays;
import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.medplus.activemq.helper.AMQHelper;
import com.medplus.pos.constant.CommonConstants;
import com.medplus.pos.constant.PosMasterDataKey;
import com.medplus.pos.domain.masterdata.PosMasterDataInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PosMasterDataHelper {

	@Autowired
	private AMQHelper amqHelper;
	
	public void pushToMasterDataUpdateInfoQueue(PosMasterDataKey posMasterDataKey, Object id, Date saveDate) {
		log.info("MasterDataKey: {}, id:{}", posMasterDataKey.getKey(), id);
		JmsTemplate jmsTemplate = amqHelper.getJmsTemplateByQueue(CommonConstants.POS_MASTER_UPDATE_INFO_QUEUE);
		jmsTemplate.send(CommonConstants.POS_MASTER_UPDATE_INFO_QUEUE, 	new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				PosMasterDataInfo posMasterDataInfo = new PosMasterDataInfo();
				posMasterDataInfo.setPosMasterDataKey(posMasterDataKey);
				posMasterDataInfo.setModifiedDateTime(saveDate);
				posMasterDataInfo.setPosMasterData(CommonConstants.GSON_BUILDER.toJson(Arrays.asList(id)));
				Message jmsMessage = session.createTextMessage(CommonConstants.GSON_BUILDER.toJson(posMasterDataInfo));
				jmsMessage.setStringProperty("JMSXGroupID", posMasterDataKey.getKey());
				return jmsMessage;
			}
		});
		log.info("Message sent successfully: {}", id);
	}
}
