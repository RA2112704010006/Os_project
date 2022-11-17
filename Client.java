import java.io.*;
import java.math.BigInteger;
import java.net.Socket;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

@SuppressWarnings("resource")
public class Client
{

  //Public & Private Key Generation (should be done in target point):

  public static void generateKeys() throws Exception {
                KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
                kpg.initialize(2048);
                KeyPair kp = kpg.genKeyPair();
                PublicKey publicKey = kp.getPublic();
                PrivateKey privateKey = kp.getPrivate();
                //System.out.println("keys created");
                KeyFactory fact = KeyFactory.getInstance("RSA");
                RSAPublicKeySpec pub = fact.getKeySpec(publicKey, RSAPublicKeySpec.class);
                RSAPrivateKeySpec priv = fact.getKeySpec(privateKey,RSAPrivateKeySpec.class);
                saveToFile("public.txt", pub.getModulus(), pub.getPublicExponent());
                saveToFile("private.txt", priv.getModulus(), priv.getPrivateExponent());
            //System.out.println("keys saved");
            }
            public static void saveToFile(String fileName, BigInteger mod,
                    BigInteger exp) throws IOException {
                try (ObjectOutputStream fileOut = new ObjectOutputStream(
                        new BufferedOutputStream(new FileOutputStream(fileName)))) {
                    fileOut.writeObject(mod);
                    fileOut.writeObject(exp);
                } catch (Exception e) {
                    throw new IOException("Unexpected error");
                }
                //System.out.println("Closed writing file.");
            }

   void clt()throws Exception{
     Socket sock = new Socket("127.0.0.1", 3000);

                               // reading from keyboard (keyRead object)
     BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
                              // sending to server (pwrite object)
     OutputStream ostream = sock.getOutputStream(); 
     PrintWriter pwrite = new PrintWriter(ostream, true);
 
                              // receiving from server ( receiveRead  object)
     InputStream istream = sock.getInputStream();
     BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));
           
      System.out.println("Start the chitchat, type and press Enter key");
 
      String receiveMessage, sendMessage;               
      while(true)
     {
        sendMessage = keyRead.readLine();
        try{
    // Create file 
         /* FileWriter fstream = new FileWriter("client_ser.txt");
    BufferedWriter out = new BufferedWriter(fstream);
    out.write(sendMessage);
     out.close();

          String encryptedDataFile = "aeskey.txt";
      

      File dfx = new File(encryptedDataFile);

      FileInputStream fsr = new FileInputStream(dfx);

      byte[] dbt = new byte[fsr.available()];

      fsr.read(dbt);

      fsr.close();
          


    //encrypting
    AES obj = new AES();
    String dataFilePath = "client_ser.txt";
    File dataFile = new File(dataFilePath);
    FileInputStream fis = new FileInputStream(dataFile);
    byte[] dataBytes = new byte[fis.available()];
    fis.read(dataBytes);
    fis.close();
    byte[] encryptedData = obj.encrypt(dataBytes,dbt);
    
    FileOutputStream en = new FileOutputStream("client_ser_en.txt");
    en.write(encryptedData);
    en.close();
    
      
         
        
      OutputStream y = sock.getOutputStream(); 

    File enfile = new File ("client_ser_en.txt");
    byte [] enarray  = new byte [(int)enfile.length()];
    FileInputStream fn = new FileInputStream(enfile);
    BufferedInputStream bn = new BufferedInputStream(fn);
    bn.read(enarray,0,enarray.length);
    System.out.println("Sending Files...");
    y.write(enarray,0,enarray.length);
    y.flush();
    */

       
    //Close the output stream
   
    }catch (Exception e){//Catch exception if any
      System.err.println("Error: " + e.getMessage());
    }  // keyboard reading
  
  
        pwrite.println(sendMessage);       // sending to server
        pwrite.flush();   
                         // flush the data

        if((receiveMessage = receiveRead.readLine()) != null) //receive from server
        {
            System.out.println(receiveMessage); // displaying at DOS prompt
        }


      }               
    }

    void file_aes() throws Exception
  {
     Socket sock = new Socket( "127.0.0.1", 3000);
 
                 // reading the file name from keyboard. Uses input st
     System.out.print("Enter the file name");
     BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
     String fname = keyRead.readLine();

      InputStream rstream = sock.getInputStream( );
                                         
          // sending the file name to server. Uses PrintWriter
     OutputStream  os = sock.getOutputStream( );
     PrintWriter pwrite = new PrintWriter(os, true);
     pwrite.println(fname);



     generateKeys();
     
    
      
    System.out.println("Receiving the encrypted data  and encrypted key from server...");

      new BufferedReader(new InputStreamReader(rstream));


     FileOutputStream stream = new FileOutputStream("client_aes.txt");
     BufferedOutputStream out1 = new BufferedOutputStream(stream);
     
       
      int size=988987626;
      int rd;
      int current = 0;
      byte [] enc  = new byte [size];
        rd = rstream.read(enc,0,enc.length);
        current= rd;

      do {
         rd =
            rstream.read(enc, current, (enc.length-current));
         if(rd >= 0) current += rd;
      } while(rd > -1);

      out1.write(enc,0,current);
      out1.flush();

     
     out1.close();

     System.out.println("Received encrypted data from server...");
     
     System.out.println("...................................");

     System.out.println("decrypting key....... ");
      new RSA();
     RSA.rsaDecrypt("aesen_key.txt","de_aeskey.txt");
     

     System.out.println("Decryption finished.......");


                      // receiving the contents from server.  Uses input stream

      System.out.println("...................................");

     System.out.println("decrypting data.....");

     AESEncryptor obj = new AESEncryptor();
     

      String encryptedDataFile = "client_aes.txt";
      

      File df = new File(encryptedDataFile);

      FileInputStream fs = new FileInputStream(df);

      byte[] db = new byte[fs.available()];

      fs.read(db);

      fs.close();

      String deaes = "de_aeskey.txt";
      

      File dfe = new File(deaes);

      FileInputStream fsf = new FileInputStream(dfe);

      byte[] dbg = new byte[fsf.available()];

      fsf.read(dbg);

      fsf.close();

      byte[] decryptedData = obj.decrypt(db,dbg);
      FileOutputStream d = new FileOutputStream("aes_de.txt");
      d.write(decryptedData);
      d.close();
      System.out.println("Decryption of data finished.......");

      String dsk="ds.txt";
      File dfeo = new File(dsk);

      FileInputStream fsfw = new FileInputStream(dfeo);

      byte[] vd = new byte[fsfw.available()];

      fsfw.read(vd);

      fsfw.close();
      
      PublicKey pubk=getPublic("public_s.txt");

      System.out.println("verfing the message");
      t dss=new t();
     
       
      System.out.println(dss.verify("aes_de.txt" ,pubk, "SHA256withRSA",vd));
      
     pwrite.close(); keyRead.close();
     
      sock.close();
     

     
                      
}
void file_des() throws Exception
  {
     Socket sock = new Socket( "127.0.0.1", 3000);
 
                 // reading the file name from keyboard. Uses input st
     System.out.print("Enter the file name");
     BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
     String fname = keyRead.readLine();

      InputStream rstream = sock.getInputStream( );
                                         
          // sending the file name to server. Uses PrintWriter
     OutputStream  os = sock.getOutputStream( );
     PrintWriter pwrite = new PrintWriter(os, true);
     pwrite.println(fname);



     generateKeys();
     
    
      
    System.out.println("Receiving the encrypted data  and encrypted key from server...");

      new BufferedReader(new InputStreamReader(rstream));
              
    
     FileOutputStream stream = new FileOutputStream("client_des.txt");
     BufferedOutputStream out1 = new BufferedOutputStream(stream);
     
       
      int size=988987626;
      int rd;
      int current = 0;
      byte [] enc  = new byte [size];
        rd = rstream.read(enc,0,enc.length);
        current= rd;

      do {
         rd =
            rstream.read(enc, current, (enc.length-current));
         if(rd >= 0) current += rd;
      } while(rd > -1);

      out1.write(enc,0,current);
      out1.flush();

     
     out1.close();

     System.out.println("Received encrypted data from server...");
     
     System.out.println("...................................");



     RSA rs=new RSA();
         rs.rsaDecrypt("desen_key.txt","de_deskey.txt");
     

     System.out.println("Decryption finished...");


                      

     

     System.out.println("decrypting data...");

      new DESEncryptor();
     

      String encryptedDataFile = "client_des.txt";
      

      File df = new File(encryptedDataFile);

      FileInputStream fs = new FileInputStream(df);

      byte[] db = new byte[fs.available()];

      fs.read(db);

      fs.close();

      String deaes = "de_deskey.txt";
      

      File dfe = new File(deaes);

      FileInputStream fsf = new FileInputStream(dfe);

      byte[] dbg = new byte[fsf.available()];

      fsf.read(dbg);

      fsf.close();

      byte[] decryptedData = DESEncryptor.decrypt(dbg,db);
      FileOutputStream d = new FileOutputStream("de_des.txt");
      d.write(decryptedData);
      d.close();

      System.out.println("Data decrypted.....");

      String dsk="ds.txt";
      File dfeo = new File(dsk);

      FileInputStream fsfw = new FileInputStream(dfeo);

      byte[] vd = new byte[fsfw.available()];

      fsfw.read(vd);

      fsfw.close();
      
      PublicKey pubk=getPublic("public_s.txt");

      System.out.println("verfing the message");
      t dss=new t();
     
       
      System.out.println(dss.verify("de_des.txt" ,pubk, "SHA256withRSA",vd));
     
     pwrite.close(); keyRead.close();
     
      sock.close();
     

     
                      
}
public PublicKey getPublic(String filename) throws Exception {
    byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
    X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
    KeyFactory kf = KeyFactory.getInstance("RSA");
    return kf.generatePublic(spec);
  }
 }
