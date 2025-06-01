package com.jfcc.jianfei.utils;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EDcryptUtils {

//    @Autowired
//    private BCryptPasswordEncoder encoder;

    public String JBcryptEndode(String param) {
        return BCrypt.hashpw(param, BCrypt.gensalt());
    }

    public boolean JBcryptMatched (String source,String target) {
        return BCrypt.checkpw(source, target);
    }
}
