package cn.az.boot.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 常见的加密解密算法工具类
 *
 * @author azusachino
 */
public class EncryptUtil {
    /**
     * The constant KEY_SHA.
     */
    public static final String KEY_SHA = "SHA";
    /**
     * The constant KEY_MD5.
     */
    public static final String KEY_MD5 = "MD5";

    /**
     * BASE64解密
     *
     * @param key the key
     * @return string
     * @throws Exception the exception
     */
    public static String decryptByBASE64(String key) throws Exception {
        return parseByte2HexStr((new BASE64Decoder()).decodeBuffer(key));
    }

    /**
     * BASE64加密
     *
     * @param hexStr the hex str
     * @return string
     */
    public static String encryptByBASE64(String hexStr) {
        return (new BASE64Encoder()).encodeBuffer(Objects.requireNonNull(parseHexStr2Byte(hexStr)));
    }

    /**
     * MD5加密
     *
     * @param data the data
     * @return string
     * @throws Exception the exception
     */
    public static String encryptByMD5(String data) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
        md5.update(Objects.requireNonNull(parseHexStr2Byte(data)));
        return parseByte2HexStr(md5.digest());
    }

    /**
     * SHA加密
     *
     * @param data the data
     * @return string
     * @throws Exception the exception
     */
    public static String encryptBySHA(String data) throws Exception {
        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
        sha.update(data.getBytes(StandardCharsets.UTF_8));
        return parseByte2HexStr(sha.digest());
    }

    /**
     * AES加密字符串
     *
     * @param content  需要被加密的字符串
     * @param password 加密需要的密码
     * @return 密文 string
     */
    public static String encryptByAES(String content, String password) {
        try {
            // 创建AES的Key生产者
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            // 利用用户密码作为随机数初始化出
            kgen.init(128, new SecureRandom(password.getBytes()));
            // 128位的key生产者
            //加密没关系，SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有password就行
            // 根据用户密码，生成一个密钥
            SecretKey secretKey = kgen.generateKey();
            // 返回基本编码格式的密钥，如果此密钥不支持编码，则返回 null
            byte[] enCodeFormat = secretKey.getEncoded();
            // 转换为AES专用密钥
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            // 创建密码器
            Cipher cipher = Cipher.getInstance("AES");

            byte[] byteContent = content.getBytes(StandardCharsets.UTF_8);
            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, key);
            // 加密
            byte[] result = cipher.doFinal(byteContent);

            return parseByte2HexStr(result);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException
            | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密AES加密过的字符串
     *
     * @param hexStr   the hex str
     * @param password 加密时的密码
     * @return 明文 string
     */
    public static String decryptByAES(String hexStr, String password) {

        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(password.getBytes());
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, random);
            // 根据用户密码，生成一个密钥
            SecretKey secretKey = kgen.generateKey();
            // 返回基本编码格式的密钥
            byte[] enCodeFormat = secretKey.getEncoded();
            // 转换为AES专用密钥
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            // 创建密码器
            Cipher cipher = Cipher.getInstance("AES");
            // 初始化为解密模式的密码器
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] result = cipher.doFinal(Objects.requireNonNull(parseHexStr2Byte(hexStr)));
            // 明文
            return new String(result);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf the buf
     * @return string
     */
    public static String parseByte2HexStr(byte[] buf) {
        StringBuilder sb = new StringBuilder();
        for (byte b : buf) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr the hex str
     * @return byte [ ]
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * The constant KEY_ALGORTHM.
     */
    public static final String KEY_ALGORTHM = "RSA";
    /**
     * The constant SIGNATURE_ALGORITHM.
     */
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /**
     * The constant PUBLIC_KEY.
     */
    public static final String PUBLIC_KEY = "RSAPublicKey";
    /**
     * The constant PRIVATE_KEY.
     */
    public static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * 初始化密钥
     *
     * @return map
     * @throws Exception the exception
     */
    public static Map<String, Object> initKey() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORTHM);
        keyPairGenerator.initialize(1024, new SecureRandom());
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // 公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        // 私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        Map<String, Object> keyMap = new HashMap<>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);

        return keyMap;
    }

    /**
     * 取得公钥，并转化为String类型
     *
     * @param keyMap the key map
     * @return public key
     */
    public static String getPublicKey(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return encryptByBASE64(parseByte2HexStr(key.getEncoded()));
    }

    /**
     * 取得私钥，并转化为String类型
     *
     * @param keyMap the key map
     * @return private key
     */
    public static String getPrivateKey(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return encryptByBASE64(parseByte2HexStr(key.getEncoded()));
    }

    /**
     * 用私钥加密
     *
     * @param hexStr the hex str
     * @param key    密钥
     * @return string
     * @throws Exception the exception
     */
    public static String encryptByPrivateKey(String hexStr, String key) throws Exception {
        // 解密密钥
        byte[] keyBytes = Objects.requireNonNull(parseHexStr2Byte(decryptByBASE64(key)));
        // 取私钥
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORTHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return parseByte2HexStr(cipher.doFinal(Objects.requireNonNull(parseHexStr2Byte(hexStr))));
    }

    /**
     * 用私钥解密 *
     *
     * @param hexStr the hex str
     * @param key    密钥
     * @return string
     * @throws Exception the exception
     */
    public static String decryptByPrivateKey(String hexStr, String key) throws Exception {
        // 对私钥解密
        byte[] keyBytes = Objects.requireNonNull(parseHexStr2Byte(decryptByBASE64(key)));
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORTHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return parseByte2HexStr(cipher.doFinal(Objects.requireNonNull(parseHexStr2Byte(hexStr))));
    }

    /**
     * 用公钥加密
     *
     * @param hexStr the hex str
     * @param key    密钥
     * @return string
     * @throws Exception the exception
     */
    public static String encryptByPublicKey(String hexStr, String key) throws Exception {
        // 对公钥解密
        byte[] keyBytes = Objects.requireNonNull(parseHexStr2Byte(decryptByBASE64(key)));
        // 取公钥
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORTHM);
        Key publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return parseByte2HexStr(cipher.doFinal(Objects.requireNonNull(parseHexStr2Byte(hexStr))));
    }

    /**
     * 用公钥解密
     *
     * @param hexStr the hex str
     * @param key    密钥
     * @return string
     * @throws Exception the exception
     */
    public static String decryptByPublicKey(String hexStr, String key) throws Exception {
        // 对私钥解密
        byte[] keyBytes = Objects.requireNonNull(parseHexStr2Byte(decryptByBASE64(key)));
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORTHM);
        Key publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return parseByte2HexStr(cipher.doFinal(Objects.requireNonNull(parseHexStr2Byte(hexStr))));
    }

    /**
     * 用私钥对信息生成数字签名
     *
     * @param hexData    the hex data
     * @param privateKey //私钥
     * @return string
     * @throws Exception the exception
     */
    public static String sign(String hexData, String privateKey) throws Exception {
        // 解密私钥
        byte[] keyBytes = Objects.requireNonNull(parseHexStr2Byte(decryptByBASE64(privateKey)));
        // 构造PKCS8EncodedKeySpec对象
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        // 指定加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORTHM);
        // 取私钥匙对象
        PrivateKey privateKey2 = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        // 用私钥对信息生成数字签名
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateKey2);
        signature.update(Objects.requireNonNull(parseHexStr2Byte(hexData)));
        return encryptByBASE64(parseByte2HexStr(signature.sign()));
    }

    /**
     * 校验数字签名
     *
     * @param hexData   the hex data
     * @param publicKey 公钥
     * @param sign      数字签名
     * @return boolean
     * @throws Exception the exception
     */
    public static boolean verify(String hexData, String publicKey, String sign) throws Exception {
        // 解密公钥
        byte[] keyBytes = Objects.requireNonNull(parseHexStr2Byte(decryptByBASE64(publicKey)));
        // 构造X509EncodedKeySpec对象
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        // 指定加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORTHM);
        // 取公钥匙对象
        PublicKey publicKey2 = keyFactory.generatePublic(x509EncodedKeySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(publicKey2);
        signature.update(Objects.requireNonNull(parseHexStr2Byte(hexData)));
        // 验证签名是否正常
        return signature.verify(parseHexStr2Byte(decryptByBASE64(sign)));
    }

    /**
     * demo
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        System.out.println("AES Encrypt:" + encryptByAES("123qwe", "123456"));
        System.out.println("AES Decrypt:" + decryptByAES(encryptByAES("123qwe", "123456"), "123456"));

        System.out.println("Key:" + parseByte2HexStr("test123".getBytes(StandardCharsets.UTF_8)));
        System.out.println("BASE64 Encrypt:" + encryptByBASE64(parseByte2HexStr("test123".getBytes(StandardCharsets.UTF_8))));
        System.out.println("BASE64 Decrypt:" + decryptByBASE64(encryptByBASE64(parseByte2HexStr("test123".getBytes()))));

        System.out.println("MD5 Encrypt:" + encryptByMD5(parseByte2HexStr("test123".getBytes())));
        System.out.println("SHA Encrypt:" + encryptBySHA(parseByte2HexStr("test123".getBytes())));

        Map<String, Object> keyMap = initKey();
        //甲方构建密钥对儿，将公钥公布给乙方，将私钥保留。
        String publicKey = getPublicKey(keyMap);
        System.out.println("公钥:" + publicKey);
        String privateKey = getPrivateKey(keyMap);
        System.out.println("私钥:" + publicKey);

        //甲方使用私钥加密数据，然后用私钥对加密后的数据签名，发送给乙方签名以及加密后的数据；乙方使用公钥、签名来验证待解密数据是否有效，如果有效使用公钥对数据解密。
        String originalHexData = parseByte2HexStr("ABCefg".getBytes(StandardCharsets.UTF_8));
        System.out.println("甲方原始数据:" + originalHexData);
        String encryptData = encryptByPrivateKey(originalHexData, privateKey);
        System.out.println("甲方加密的数据:" + encryptData);
        String signData = sign(encryptData, privateKey);
        System.out.println("甲方数字签名:" + signData);
        System.out.println("乙方数字签名检验:" + verify(encryptData, publicKey, signData));
        System.out.println("乙方解密数据:" + decryptByPublicKey(encryptData, publicKey));

        //乙方使用公钥加密数据，向甲方发送经过加密后的数据；甲方获得加密数据，通过私钥解密。
        String originalHexData2 = parseByte2HexStr("abcEFG".getBytes(StandardCharsets.UTF_8));
        System.out.println("乙方原始数据:" + originalHexData2);
        String encryptData2 = encryptByPublicKey(originalHexData2, publicKey);
        System.out.println("乙方加密数据:" + encryptData2);
        System.out.println("甲方解密数据:" + decryptByPrivateKey(encryptData2, privateKey));
    }
}
