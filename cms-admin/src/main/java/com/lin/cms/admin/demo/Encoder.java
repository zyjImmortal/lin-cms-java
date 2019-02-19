package com.lin.cms.admin.demo;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * Created by zhouyajun on 2019/2/1
 */
public class Encoder {
    public static void main(String[] args) {
        String password = "123456";
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println(hashed);
        String hashed2 = BCrypt.hashpw(password, BCrypt.gensalt(12));
        String candidate = "123456";
        System.out.println(BCrypt.hashpw(password, hashed2));
//String candidate = "wrongtestpassword";
        if (BCrypt.checkpw(candidate, hashed))
            System.out.println("It matches");
        else
            System.out.println("It does not match");
    }

}
