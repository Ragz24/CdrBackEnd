package com.cdr.main.entity;



import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MmsCdr 
{      
private String messageId;
private String senderPhoneNumber;
private String receiverPhoneNumber;
private String messageDirection;
private String messageType;
private String networkType;
private String contentType;
private int contentSize;
private String contentFormat;
private String senderTimestampStr;
private String receiverTimestampStr;
private String messageStatus;
	 
}
 