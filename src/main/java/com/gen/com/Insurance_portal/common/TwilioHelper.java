package com.gen.com.Insurance_portal.common;

import com.gen.com.Insurance_portal.configs.TwilioConfiguration;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Component;

@Component
public class TwilioHelper {
    private final TwilioConfiguration twilio;

    public TwilioHelper(TwilioConfiguration twilio) {
        this.twilio = twilio;
    }

    public static void send(String phone, String content) {
        PhoneNumber from = new PhoneNumber("+12058396039");
        PhoneNumber to = new PhoneNumber(phone);
        MessageCreator creator = Message.creator(to, from, content);
        creator.create();
    }
}
