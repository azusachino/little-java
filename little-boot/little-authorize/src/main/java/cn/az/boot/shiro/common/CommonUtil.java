package cn.az.boot.shiro.common;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * <h3>MySpringBoot</h3>
 *
 * @author : azchino
 **/
public class CommonUtil {

    public static String encrypt(String password) {
        return new SimpleHash(Constants.ALGORITH, password, ByteSource.Util.bytes(Constants.SALT), Constants.HASH_ITERATION).toHex();
    }

    public static String encrypt(String username, String password) {
        return new SimpleHash(Constants.ALGORITH, password, ByteSource.Util.bytes(username + Constants.SALT),
            Constants.HASH_ITERATION).toHex();
    }

    public static void main(String[] args) {
        System.out.println(encrypt("admin", "123"));
    }
}
