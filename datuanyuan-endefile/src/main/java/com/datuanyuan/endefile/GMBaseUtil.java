package com.datuanyuan.endefile;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;

public class GMBaseUtil {
    public static final String ENCODING = "UTF-8";
    static {
        Security.addProvider(new BouncyCastleProvider());
    }
}
