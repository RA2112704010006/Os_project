SERVER-CLIENT SECURE CHAT (AND FILE TRANSFER) CODE:
The code above includes secure file transfer as well as messaging options.The security provided by the code is based on encryption and decryption using cryptographic systems namely AES and DES.Also the keys provided in the above code are based on RSA cryptosystem and keys are generated such that integrity is maintained and authenticity is also maintained using RSA Digital Signature.

PREREQUISITES:
The code works in Java environment.Thus a Java code compiler is the basic requirement to make the  code function.

RUNNING THE TESTS:
The code gives the user an option to choose between client-server chat or file transfer.The user can choose accordingly.Also the user can choose between the type of encryption to be choosed.It can be either AES or DES.

Chat-
The chat option includes transfer of simple messages between the client and the server.These messages are encrypted/decrypted using the concepts of cryptography.

File transfer-
The code can transfer files upto size 100Mb(tested) between client and server.The file thus transfered is encrypted/decrypted using the concepts of cryptography.
DEPLOYMENT:
The project requires a Text file to be created to run and test the encryption/decryption.

If you want to run the code in Termanal.
First run the servertalk.java file
Then run the clienttalk.java file
both should be running simultaniously
then choose the option to either chat or transfer file
in chat the message you have type in client will be visible in server terminal

in file transfer type the filename in the terminal and press enter the file will be transfered.
