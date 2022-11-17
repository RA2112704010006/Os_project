 
import java.io.*;
import java.net.*;
import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.io.FileOutputStream;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.lang.*;
import java.io.FileInputStream;
import java.security.KeyPair;
import java.util.Random;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Server

{
public static KeyPair generateKeys_s() throws Exception {
                KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
                kpg.initialize(2048);
                KeyPair kp = kpg.genKeyPair();
                return kp;}
               
  

byte[] skey = new byte[1000];

static byte[] raw;   
  public static byte[] generateKey() throws NoSuchAlgorithmException
{
    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
    keyGenerator.init(256); // 128 default; 192 and 256 also possible
    SecretKey skey = keyGenerator.generateKey();

  byte[] AesKey = skey.getEncoded();

  return AesKey;
    
}

 byte[] generateSymmetricKey() {
try {
Random r = new Random();
int num = r.nextInt(10000);
String knum = String.valueOf(num);
byte[] knumb = knum.getBytes();
skey=getRawKey(knumb);

}
catch(Exception e) {
System.out.println(e);
}
return skey;}


private static byte[] getRawKey(byte[] seed) throws Exception {
KeyGenerator kgen = KeyGenerator.getInstance("DES");
SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
sr.setSeed(seed);
kgen.init(56, sr);
SecretKey skey = kgen.generateKey();
raw = skey.getEncoded();
return raw;
}



    void ser()throws Exception{
  //open the server
      ServerSocket sersock = new ServerSocket(3000);
      System.out.println("Server  ready for chatting");
  //waiting for client
      Socket sock = sersock.accept( ); 
      byte[] k=generateKey();
     FileOutputStream dek = new FileOutputStream("aeskey.txt");
      dek.write(k);
      dek.close();                         
        // reading from keyboard (keyRead object)
      BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
        // sending to client (pwrite object)
      OutputStream ostream = sock.getOutputStream(); 
      PrintWriter pwrite = new PrintWriter(ostream, true);
 
        // receiving from client( receiveRead  object)
      InputStream istream = sock.getInputStream();
      BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));
 
      String receiveMessage, sendMessage;               
      while(true)
      {
        if((receiveMessage = receiveRead.readLine()) != null)  
        {
           System.out.println(receiveMessage); 
           
    
    /* FileOutputStream stream = new FileOutputStream("client_ser_re.txt");
     BufferedOutputStream out1 = new BufferedOutputStream(stream);
     
       
      int size=988987626;
      int rd;
      int current = 0;
      byte [] enc  = new byte [size];
        rd = istream.read(enc,0,enc.length);
        current= rd;

      do {
         rd =
            istream.read(enc, current, (enc.length-current));
         if(rd >= 0) current += rd;
      } while(rd > -1);

      out1.write(enc,0,current);
      out1.flush();

     
     out1.close();
            //decryption

            AES aes1=new AES();
            String encryptedDataFile = "client_ser_re.txt";
      

      File df = new File(encryptedDataFile);

      FileInputStream fs = new FileInputStream(df);

      byte[] db = new byte[fs.available()];

      fs.read(db);

      fs.close();
            String deaes = "aeskey.txt";
      

      File dfe = new File(deaes);

      FileInputStream fsf = new FileInputStream(dfe);

      byte[] dbg = new byte[fsf.available()];

      fsf.read(dbg);

      fsf.close();

      byte[] decryptedData = aes1.decrypt(db,dbg);
      FileOutputStream d = new FileOutputStream("client_ser_de.txt");
      d.write(decryptedData);
      d.close();*/      
        }


        sendMessage = keyRead.readLine();
        try{
    // Create file 
          
    FileWriter fstream = new FileWriter("client_ser_de.txt");
        BufferedWriter out = new BufferedWriter(fstream);
out.write(sendMessage);

//aes1.encrypt("ser_client_en.txt");



      
    //Close the output stream
    
    }catch (Exception e){//Catch exception if any
      System.err.println("Error: " + e.getMessage());
}
  
   
        pwrite.println(sendMessage);             
        pwrite.flush();
      }               
    }




    void file_aes() throws Exception
  {                           // establishing the connection with the server
     ServerSocket sersock = new ServerSocket(3000);
     System.out.println("Server ready for connection");
     Socket sock = sersock.accept(); // binding with port: 4000
     System.out.println("Connection is successful and wating for chatting");
      

      InputStream istream = sock.getInputStream( );
      OutputStream y = sock.getOutputStream( );


    // reading the file name from client
    BufferedReader fileRead =new BufferedReader(new InputStreamReader(istream));
    String fname = fileRead.readLine( );
     
     
    //generating the aeskey
     byte[] k=generateKey();
     FileOutputStream dek = new FileOutputStream("aeskey.txt");
      dek.write(k);
      dek.close();

      
      
    //encrypting file
    System.out.println("encrypting file data using aeskey");
    AESEncryptor obj = new AESEncryptor();
    String dataFilePath = "source.txt";
    File dataFile = new File(dataFilePath);
    FileInputStream fis = new FileInputStream(dataFile);
    byte[] dataBytes = new byte[fis.available()];
    fis.read(dataBytes);
    fis.close();

    KeyPair kep=generateKeys_s();
   
    PrivateKey prvk = kep.getPrivate();
    PublicKey publicKey = kep.getPublic();
    
   byte[] pu_key = publicKey.getEncoded();
    
    String dfile = "source.txt";
    t ds=new t();
    byte[] sigbytes = ds.sign( dfile, prvk);
    

    FileOutputStream sigfos = new FileOutputStream("ds.txt");
    sigfos.write(sigbytes);
    sigfos.close();

 
  FileOutputStream keyfos = new FileOutputStream("public_s.txt");
  keyfos.write(pu_key);
  keyfos.close();
      System.out.println("signing finished ");


    byte[] encryptedData = obj.encrypt(dataBytes,k);
    
    FileOutputStream en = new FileOutputStream("aes_en.txt");
    en.write(encryptedData);
    en.close();
    System.out.println("encryption of data finished");
      
      
     System.out.println("encrypting aes key");
    RSA rsa=new RSA();
    rsa.rsaEncrypt("aeskey.txt","aesen_key.txt");
    System.out.println("encryption of daeskey finished"); 
     
    System.out.println("sending encrypted data to client");         
      
    File enfile = new File ("aes_en.txt");
    byte [] enarray  = new byte [(int)enfile.length()];
    FileInputStream fn = new FileInputStream(enfile);
    BufferedInputStream bn = new BufferedInputStream(fn);
    bn.read(enarray,0,enarray.length);
    System.out.println("Sending Files...");
    y.write(enarray,0,enarray.length);
    y.flush();
    System.out.println("File transfer complete");

     
     

      sock.close(); 
       sersock.close();
       

  }
  void file_des() throws Exception
  {                           // establishing the connection with the server
     ServerSocket sersock = new ServerSocket(3000);
     System.out.println("Server ready for connection");
     Socket sock = sersock.accept(); // binding with port: 4000
     System.out.println("Connection is successful and wating for file name");
      

      InputStream istream = sock.getInputStream( );
      OutputStream y = sock.getOutputStream( );


    // reading the file name from client
    BufferedReader fileRead =new BufferedReader(new InputStreamReader(istream));
    String fname = fileRead.readLine( );
    

     
    //generating the aeskey
     byte[] k=generateSymmetricKey();
     FileOutputStream dek = new FileOutputStream("deskey.txt");
      dek.write(k);
      dek.close();

      
      
    //encrypting file
    System.out.println("encrypting file data using deskey");
    DESEncryptor obj = new DESEncryptor();
    String dataFilePath = "source.txt";
    File dataFile = new File(dataFilePath);
    FileInputStream fis = new FileInputStream(dataFile);
    byte[] dataBytes = new byte[fis.available()];
    fis.read(dataBytes);
    fis.close();


  System.out.println("signing the message");
    


    


    
   KeyPair kep=generateKeys_s();
   
    PrivateKey prvk = kep.getPrivate();
    PublicKey publicKey = kep.getPublic();
    
   byte[] pu_key = publicKey.getEncoded();
    
    String dfile = "source.txt";
    t ds=new t();
    byte[] sigbytes = ds.sign( dfile, prvk);


    FileOutputStream sigfos = new FileOutputStream("ds.txt");
    sigfos.write(sigbytes);
    sigfos.close();

 
  FileOutputStream keyfos = new FileOutputStream("public_s.txt");
  keyfos.write(pu_key);
  keyfos.close();
      System.out.println("signing finished ");



    byte[] encryptedData = obj.encrypt(k,dataBytes);
    
    FileOutputStream en = new FileOutputStream("des_en.txt");
    en.write(encryptedData);
    en.close();
    System.out.println("encryption of data finished");
    System.out.println("sending encrypted data to client");         
      
    File enfile = new File ("des_en.txt");
    byte [] enarray  = new byte [(int)enfile.length()];
    FileInputStream fn = new FileInputStream(enfile);
    BufferedInputStream bn = new BufferedInputStream(fn);
    bn.read(enarray,0,enarray.length);
    System.out.println("Sending Files...");
    y.write(enarray,0,enarray.length);
    y.flush();
    System.out.println("File transfer complete");
      
      
     System.out.println("encrypting aes key");
    RSA rsa=new RSA();
    rsa.rsaEncrypt("deskey.txt","desen_key.txt");
    System.out.println("encryption of daeskey finished"); 
     

     sock.close(); 
       sersock.close();
       

  }

}
    
      

    
                                        
        
       


     
    
          
    
      

      
    

       
     

     
     

     


                    

          



                             
     
      


          







    
     
                       
