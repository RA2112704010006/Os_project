        import java.io.BufferedInputStream;
        import java.io.BufferedOutputStream;
        import java.io.FileInputStream;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.ObjectInputStream;
        import java.io.ObjectOutputStream;
        import java.math.BigInteger;
        import java.security.Key;
        import java.security.KeyFactory;
        import java.security.KeyPair;
        import java.security.KeyPairGenerator;
        import java.security.PrivateKey;
        import java.security.PublicKey;
        import java.security.spec.KeySpec;
        import java.security.spec.RSAPrivateKeySpec;
        import java.security.spec.RSAPublicKeySpec;
        import javax.crypto.Cipher;
        import javax.crypto.CipherInputStream;
        import javax.crypto.CipherOutputStream;
        import java.security.spec.EncodedKeySpec;
        import java.security.spec.PKCS8EncodedKeySpec;
        import java.security.spec.X509EncodedKeySpec;
        import java.io.*;
import java.net.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException; 




  public class RSA{

        

// Return the saved key
            static Key readKeyFromFile(String keyFileName) throws IOException {
                InputStream in = new FileInputStream(keyFileName);
                ObjectInputStream oin = new ObjectInputStream(new BufferedInputStream(
                        in));
                try {
                    BigInteger m = (BigInteger) oin.readObject();
                    BigInteger e = (BigInteger) oin.readObject();
                    KeyFactory fact = KeyFactory.getInstance("RSA");
                    if (keyFileName.startsWith("public"))
                        return fact.generatePublic(new RSAPublicKeySpec(m, e));
                    else
                        return fact.generatePrivate(new RSAPrivateKeySpec(m, e));
                } catch (Exception e) {
                    throw new RuntimeException("Spurious serialisation error", e);
                } finally {
                    oin.close();
                    //System.out.println("Closed reading file.");
                }
            }
            // Use this PublicKey object to initialize a Cipher and encrypt some data
            public static void rsaEncrypt(String file_loc, String file_des)
                    throws Exception {
                byte[] data = new byte[32];
                int i;
                //System.out.println("start encyption");
                Key pubKey = readKeyFromFile("public.key");
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.ENCRYPT_MODE, pubKey);
                FileInputStream fileIn = new FileInputStream(file_loc);
                FileOutputStream fileOut = new FileOutputStream(file_des);
                CipherOutputStream cipherOut = new CipherOutputStream(fileOut, cipher);
                // Read in the data from the file and encrypt it
                while ((i = fileIn.read(data)) != -1) {
                    cipherOut.write(data, 0, i);
                }
                // Close the encrypted file
                cipherOut.close();
                fileIn.close();
                //System.out.println("encrypted file created");
            }
            // Use this PublicKey object to initialize a Cipher and decrypt some data
            public static void rsaDecrypt(String file_loc, String file_des)
                    throws Exception {
                byte[] data = new byte[32];
                int i;
                //System.out.println("start decyption");
                Key priKey = readKeyFromFile("private.key");
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.DECRYPT_MODE, priKey);
                FileInputStream fileIn = new FileInputStream(file_loc);
                CipherInputStream cipherIn = new CipherInputStream(fileIn, cipher);
                FileOutputStream fileOut = new FileOutputStream(file_des);
                // Write data to new file
                while ((i = cipherIn.read()) != -1) {
                    fileOut.write(i);
                }
                // Close the file
                fileIn.close();
                cipherIn.close();
                fileOut.close();
                //System.out.println("decrypted file created");
            }
        }