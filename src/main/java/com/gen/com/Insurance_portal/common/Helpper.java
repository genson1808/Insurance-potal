package com.gen.com.Insurance_portal.common;

import com.gen.com.Insurance_portal.entites.User;
import com.gen.com.Insurance_portal.exceptions.MessageException;
import com.gen.com.Insurance_portal.services.IUserService;
import com.gen.com.Insurance_portal.utils.JwtUtil;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Helpper {

    public static int genCode() {
        Random r = new Random( System.currentTimeMillis() );
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }

    public static String genContractCode(String prefix) {
        Random r = new Random();
        long numbers = r.nextInt(1_000_000_000)
                + (r.nextInt(90) + 10) * 1_000_000_000L;
        return prefix + numbers;
    }

    public static String getCustomerCode(String token, JwtUtil jwtTokenUtil, IUserService userService) {
        String username = null;

        if (token != null && token.startsWith("Bearer ")){
            token= token.substring(7);
            username = jwtTokenUtil.extractUsername(token);
        }

        User user = userService.findByUsername(username);
        if (user.getCustomer() == null) {
            throw new MessageException("you isn't customer!");
        }
        return user.getCustomer().getCustomerCode();
    }

    public static Double percentage(Double percent, Double of) {
        return of * (percent / 100.0);
    }
}
