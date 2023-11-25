package Config;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ThreadLocalRandom;

public class Crypto {

    public static String hashPassword(String password) {
        try {
            // MessageDigest를 이용하여 SHA-256 해시 생성
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            // 바이트를 16진수 문자열로 변환
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            // 알고리즘이 지원되지 않을 경우 처리
            e.printStackTrace();
            return null;
        }
    }
    
    //인증키 6자리 숫자 랜덤 생성
    public static String makeAuthKey() {
    	String authKey = Integer.toString(ThreadLocalRandom.current().nextInt(100000, 1000000));
    	return authKey;
    }
    
    
    
}

