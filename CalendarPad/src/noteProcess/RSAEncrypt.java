package noteProcess;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair; 
import java.security.KeyPairGenerator; 
import java.security.interfaces.RSAPrivateKey; 
import java.security.interfaces.RSAPublicKey; 
import javax.crypto.Cipher;/** *//** 
* RSAEncrypt 
* 
* @author maqujun 
* @see 
*/ 
public class RSAEncrypt
{ 
	/** *//** 
	 * Main method for RSAEncrypt. 
	 * @param args 
	 */ 
	public static void encryption(String content,String filename)
	{ 
		try
		{ 
			RSAEncrypt encrypt = new RSAEncrypt(); 
			//String encryptText = "encryptText"; 
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA"); 
			keyPairGen.initialize(1024); 
			KeyPair keyPair = keyPairGen.generateKeyPair(); 
			// Generate keys 
			//RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate(); 
			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic(); 
			byte[] e = encrypt.encrypt(publicKey, content.getBytes()); 
			//byte[] de = encrypt.decrypt(privateKey,e); 
			FileOutputStream fos = new FileOutputStream(filename);
			@SuppressWarnings("resource")
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(keyPair);
			oos.writeObject(e);
			oos.close() ;
			fos.close() ;
			//System.out.println(keyPair.getPublic().toString());
			//System.out.println(keyPair.getPrivate().toString());
			//System.out.println(encrypt.bytesToString(e));
			//System.out.println(encrypt.bytesToString(e)); 
			//System.out.println(encrypt.bytesToString(de)); 
		}
		catch (Exception e)
		{ 
			//e.printStackTrace(); 
		} 
	}
	public static String encryption(String content)
	{ 
		try
		{ 
			RSAEncrypt encrypt = new RSAEncrypt(); 
			//String encryptText = "encryptText"; 
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA"); 
			keyPairGen.initialize(1024); 
			KeyPair keyPair = keyPairGen.generateKeyPair(); 
			// Generate keys 
			//RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate(); 
			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic(); 
			byte[] e = encrypt.encrypt(publicKey, content.getBytes()); 
			//byte[] de = encrypt.decrypt(privateKey,e); 
			return encrypt.bytesToString(e);
			//System.out.println(keyPair.getPublic().toString());
			//System.out.println(keyPair.getPrivate().toString());
			//System.out.println(encrypt.bytesToString(e));
			//System.out.println(encrypt.bytesToString(e)); 
			//System.out.println(encrypt.bytesToString(de)); 
		}
		catch (Exception e)
		{ 
			//e.printStackTrace(); 
		} 
		return null;
	}
	public static String decryption(String filename)
	{ 
		try
		{ 
			FileInputStream fos = new FileInputStream(filename);
			@SuppressWarnings("resource")
			ObjectInputStream oos = new ObjectInputStream(fos);
			KeyPair keyPair = (KeyPair)oos.readObject();
			byte[] contentE = (byte[])oos.readObject();
			//System.out.println(keyPair.getPublic().toString());
			//System.out.println(keyPair.getPrivate().toString());
			//System.out.println(contentE);
			RSAEncrypt encrypt = new RSAEncrypt();
			RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate(); 
			byte[] de = encrypt.decrypt(privateKey, contentE);
			fos.close();
			oos.close();
			return encrypt.bytesToString(de);
			//System.out.println(content);
			//System.out.println(encrypt.bytesToString(e)); 
			//System.out.println(encrypt.bytesToString(de)); 
		}
		catch (Exception e)
		{ 
			//e.printStackTrace(); 
		} 
		return null;
	}
	public static void main(String[] args)
	{
		RSAEncrypt.encryption("hehe", "src\\diary\\miao.bak");
		RSAEncrypt.decryption("src\\diary\\miao.bak");
	}
	/** *//** 
	 * Change byte array to String. 
	 * @return byte[] 
	 */ 
	protected String bytesToString(byte[] encrytpByte)
	{ 
		String result = ""; 
		for (Byte bytes : encrytpByte)
		{ 
			result += (char) bytes.intValue(); 
		} 
		return result; 
	} 
	/** *//** 
	 * Encrypt String. 
	 * @return byte[] 
	 */ 
	protected byte[] encrypt(RSAPublicKey publicKey, byte[] obj)
	{ 
		if (publicKey != null)
		{ 
			try
			{ 
				Cipher cipher = Cipher.getInstance("RSA"); 
				cipher.init(Cipher.ENCRYPT_MODE, publicKey); 
				return cipher.doFinal(obj); 
			}
			catch (Exception e)
			{ 
				//e.printStackTrace(); 
			} 
		} 
		return null; 
	} 
	/** *//** 
	 * Basic decrypt method 
	 * @return byte[] 
	 */ 
	protected byte[] decrypt(RSAPrivateKey privateKey, byte[] obj)
	{ 
		if (privateKey != null)
		{ 
			try
			{ 
				Cipher cipher = Cipher.getInstance("RSA"); 
				cipher.init(Cipher.DECRYPT_MODE, privateKey); 
				return cipher.doFinal(obj); 
			}
			catch (Exception e)
			{ 
				//e.printStackTrace(); 
			} 
		} 
		return null; 
	} 
}