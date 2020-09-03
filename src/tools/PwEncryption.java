package tools;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

import model.TbPerson;

import org.apache.commons.codec.binary.Base64;

import dao.PersonDao;

/**
 * @version V1.0
 * @desc AES 加密工具类
 */

public class PwEncryption {
   private static final String KEY_ALGORITHM = "AES";
   private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";//默认的加密算法
 
   /**
    * AES 加密操作
    *
    * @param content 待加密内容
    * @param password 加密密码
    * @return 返回Base64转码后的加密数据
    */
    public static String encrypt(String content, String password) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(password));// 初始化为加密模式的密码器
            byte[] result = cipher.doFinal(byteContent);// 加密
            return Base64.encodeBase64String(result);//通过Base64转码返回
        } catch (Exception ex) {
        	Logger.getLogger(PwEncryption.class.getName()).log(Level.SEVERE, null, ex);
        }
	    return null;

    }

    /**
     * AES 解密操作
     *
     * @param content
     * @param password
     * @return
     */
	public static String decrypt(String content, String password) {
		try {
			//实例化
			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
			//使用密钥初始化，设置为解密模式
			cipher.init(Cipher.DECRYPT_MODE, getSecretKey(password));
			//执行操作
			byte[] result = cipher.doFinal(Base64.decodeBase64(content));
            return new String(result, "utf-8");
		} catch (Exception ex) {
			Logger.getLogger(PwEncryption.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
	
	/**
	 * 生成加密秘钥
	 * @return
	 */
	private static SecretKeySpec getSecretKey(final String password) {
		//返回生成指定算法密钥生成器的 KeyGenerator 对象
		KeyGenerator kg = null;
		try {
			kg = KeyGenerator.getInstance(KEY_ALGORITHM);
			//AES 要求密钥长度为 128
            kg.init(128, new SecureRandom(password.getBytes()));
            //生成一个密钥
            SecretKey secretKey = kg.generateKey();
            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);// 转换为AES专用密钥
		} catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PwEncryption.class.getName()).log(Level.SEVERE, null, ex);
        }
		return null;
	}
    public static void main(String[] args) {
    	String s = "123456";
    	System.out.println("s:" + s);
    	String s1 = PwEncryption.encrypt(s, "key");
    	System.out.println(s+"加密：" + s1);
    	System.out.println("解密:"+PwEncryption.decrypt("Ebw+f2puaXnEvwTQ/QVY/Q==", "key"));

    	TbPerson p=PersonDao.findPersonByRecordNumber("T00001");
    	if (p.getPassword().equals(s1)) {
    		System.out.println("相等");
    	}else {
    		System.out.println("不相等");
    	}      
    }
}