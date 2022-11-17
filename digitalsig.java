import javax.crypto.Cipher;
import java.io.InputStream;
import java.security.*;
import java.io.File;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.security.spec.X509EncodedKeySpec;
import static java.nio.charset.StandardCharsets.UTF_8;
import  java.nio.file.Files;



public class digitalsig{


	public byte[] sign(byte[] data, String keyFile) throws InvalidKeyException, Exception{
		Signature rsa = Signature.getInstance("SHA1withRSA"); 
		rsa.initSign(getPrivate(keyFile));
		rsa.update(data);
		return rsa.sign();
	}
	public PrivateKey getPrivate(String filename) throws Exception {
		byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePrivate(spec);
	}

    public boolean verifySignature(byte[] data, byte[] signature, String keyFile) throws Exception {
		Signature sig = Signature.getInstance("SHA1withRSA");
		sig.initVerify(getPublic(keyFile));
		sig.update(data);
		
		return sig.verify(signature);
	}
	
	//Method to retrieve the Public Key from a file
	public PublicKey getPublic(String filename) throws Exception {
		byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePublic(spec);
	}
	}
