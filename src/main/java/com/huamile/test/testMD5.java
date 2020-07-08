package com.huamile.test;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

/**
 * @author Afa
 */
public class testMD5 {

    @Test
    public void getMD5(){
        Md5Hash md5Hash = new Md5Hash("123","qwer");
        System.out.println(md5Hash);

    }
}
