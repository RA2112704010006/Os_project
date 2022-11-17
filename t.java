import java.io.FileInputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;


public class t {
    
    


    byte[] sign(String datafile, PrivateKey prvKey) throws Exception {
    Signature sig = Signature.getInstance("SHA256withRSA");
    sig.initSign(prvKey);
    FileInputStream fis = new FileInputStream(datafile);
    byte[] dataBytes = new byte[1024];
    int nread = fis.read(dataBytes);
    while (nread > 0) {
      sig.update(dataBytes, 0, nread);
      nread = fis.read(dataBytes);
    }
    return sig.sign();
  }
    boolean verify(String datafile, PublicKey pubKey,
      String sigAlg, byte[] sigbytes) throws Exception {
    Signature sig = Signature.getInstance(sigAlg);
    sig.initVerify(pubKey);
    FileInputStream fis = new FileInputStream(datafile);
    byte[] dataBytes = new byte[1024];
    int nread = fis.read(dataBytes);
    while (nread > 0) {
      sig.update(dataBytes, 0, nread);
      nread = fis.read(dataBytes);
    }
    return sig.verify(sigbytes);
  }

   


  }
  
