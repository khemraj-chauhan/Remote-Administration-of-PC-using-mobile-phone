
package server;

import java.awt.AWTException;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.IOUtils;


public class HttpServer extends Thread {
    private Server message_to; 
    private int port; 
    byte[] b=new byte[502400];
    public HttpServer(int listen_port, Server to_send_message_to) {
    message_to = to_send_message_to;
    port = listen_port;
    this.start();
  }
     private void s(String s2) { 
    message_to.send_message_to_window(s2);
  }
    public void run() {
    ServerSocket serversocket = null;
    s("The httpserver\n");
    try {
       s("Trying to bind to localhost on port " + Integer.toString(port) + "...");
      
      serversocket = new ServerSocket();
      serversocket.setReuseAddress(true);
      serversocket.bind(new InetSocketAddress(port));
    }
    catch (Exception e) { 
      s("\nFatal Error:" + e.getMessage());
      return;
    }
    s("OK!\n");
    while (true) {
      s("\nReady, Waiting for requests.\n");
      try {
        ScreenCapture s1=new ScreenCapture();
        Socket connectionsocket = serversocket.accept();
        InetAddress client = connectionsocket.getInetAddress();
        s(client.getHostName() + " connected to server.\n");
        BufferedReader input =
            new BufferedReader(new InputStreamReader(connectionsocket.
            getInputStream()));
        DataOutputStream output =
            new DataOutputStream(connectionsocket.getOutputStream());
        
        http_handler(input, output);
      }
      catch (Exception e) { 
        s("\nError:" + e.getMessage());
      }

    } 
  }
    
    private String construct_http_header(int return_code, int file_type) {
    String s = "HTTP/1.0 ";
    switch (return_code) {
      case 200:
        s = s + "200 OK";
        break;
      case 400:
        s = s + "400 Bad Request";
        break;
      case 403:
        s = s + "403 Forbidden";
        break;
      case 404:
        s = s + "404 Not Found";
        break;
      case 500:
        s = s + "500 Internal Server Error";
        break;
      case 501:
        s = s + "501 Not Implemented";
        break;
    }

    s = s + "\r\n";
    s = s + "Connection: close\r\n"; 
    s = s + "Server: SimpleHTTPtutorial v0\r\n";
    switch (file_type) {
      case 0:
        break;
      case 1:
        s = s + "Content-Type: image/jpeg\r\n";
        break;
      case 2:
        s = s + "Content-Type: image/gif\r\n";
      case 3:
        s = s + "Content-Type: application/x-zip-compressed\r\n";
      default:
        s = s + "Content-Type: text/html\r\n";
        break;
    }
    s = s + "\r\n"; 
    return s;
  }
    
    private void http_handler(BufferedReader input, DataOutputStream output) {
    int method = 0; 
    String http = new String(); 
    String path = new String(); 
    String file = new String();
    String user_agent = new String();
    try {
      String tmp = input.readLine(); 
      String tmp2 = new String(tmp);
      System.out.println(tmp2);
      tmp.toUpperCase();
      if (tmp.startsWith("GET")) { 
        method = 1;
      }
      if (tmp.startsWith("HEAD")) {
        method = 2;
      } 

      if (method == 0) { 
        try {
          output.writeBytes(construct_http_header(501, 0));
          output.close();
          return;
        }
        catch (Exception e3) { 
          s("error:" + e3.getMessage());
        }
      }
      int start = 0;
      int end = 0;
      int posx=0,posy=0;
      int c=0,flag=0;
      for (int a = 0; a < tmp2.length(); a++) {
        if (tmp2.charAt(a) == ' ' && start != 0) {
          end = a;
          break;
        }
        if (tmp2.charAt(a) == '?' && start != 0) {
          end = a;
          flag=1;
          
          break;
        }
        if(tmp2.charAt(a)=='$' && start!=0){
            end=a;
            flag=2;
            System.out.println("vedavyas");
            break;
        }
        if (tmp2.charAt(a) == ' ' && start == 0) {
          start = a;
        }
      }
      path = tmp2.substring(start + 2, end); 
      if(flag==1){
      for(int b=end+1;b<tmp2.length();b++){
              if(tmp.charAt(b)=='&') {
                  posx=Integer.parseInt(tmp2.substring(end+1,b));
                  c=b;
                  break;
              }
          }
          for(int b=c+1;b<tmp2.length();b++){
              if(tmp.charAt(b)==' '){
                  posy=Integer.parseInt(tmp2.substring(c+1,b));
                  break;
              }
          }
          System.out.println(tmp2);
         // posx=posx*4;
         // posy=posy*4;
          System.out.println(posx);
          System.out.println(posy);
      MouseClick mc=new MouseClick(posx,posy);
      
      
      }
      if(flag==2){
         KeyPress kp=new KeyPress(tmp2.substring(end+1,tmp2.length())); 
      }
      
    }
    catch (Exception e) {
      s("errorr" + e.getMessage());
    } //catch any exception

    s("\nClient requested:" + new File(path).getAbsolutePath() + "\n");
    FileInputStream requestedfile = null;

    try {
      requestedfile = new FileInputStream(path);
    }
    catch (Exception e) {
      try {
        output.writeBytes(construct_http_header(404, 0));
        output.close();
      }
      catch (Exception e2) {}
      ;
      s("error" + e.getMessage());
    } 

    try {
      int type_is = 0;
      if (path.endsWith(".zip") )
              {
        type_is = 3;
      }
      if (path.endsWith(".jpg") || path.endsWith(".jpeg")) {
        type_is = 1;
      }
      if (path.endsWith(".gif")) {
        type_is = 2;
      }
      output.writeBytes(construct_http_header(200, 5));
      int read;
      byte[] buffer = new byte[65536];
      if (method == 1) { 
        while ((read=requestedfile.read(buffer))!=-1) {
                output.write(buffer,0,read);
        }
      }
      output.close();
      requestedfile.close();
    }

    catch (Exception e) {}

  }
    
}
