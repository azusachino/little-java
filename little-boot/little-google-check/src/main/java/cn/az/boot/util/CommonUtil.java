package cn.az.boot.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author azusachino
 * @version 12/16/2019
 */
public class CommonUtil {

    protected CommonUtil() {

    }

    /**
     * 文字列 SHA 暗号化
     *
     * @param strText str
     * @return sha
     */
    public static String sha256(final String strText) {
        // 返却値
        String strResult = null;

        // 文字列の有効性を判断する
        if (strText != null && strText.length() > 0) {
            try {
                // SHA 暗号化開始
                // 暗号化対象を作って、暗号化タイプを渡る
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                // 暗号したい文字列を渡る
                messageDigest.update(strText.getBytes());
                // byteタイプの結果を得る
                byte[] byteBuffer = messageDigest.digest();

                // byteをstringに変換する
                StringBuilder strHexString = new StringBuilder();
                // 遍歷 byte buffer
                for (byte b : byteBuffer) {
                    String hex = Integer.toHexString(0xff & b);
                    if (hex.length() == 1) {
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }
                // 返却値をリターンする
                strResult = strHexString.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return strResult;
    }
}
