/*
* A simple chat server
* GitHub - https://github.com/anjanborah/Chat_Server_Java_Single_Threaded
* Author - Anjan Borah < anjanborah@aol.com >
* Copyright ( c ) 2013 Anjan Borah
*/

import java.util.Scanner;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

public class Main {
  public static void main( String args[] ) {
    Chat_Server object = new Chat_Server();
  }
}

class Chat_Server {

  public Chat_Server() {
    try {
      this.start_server();
      this.engine();
      this.stop_server();
    } catch( Exception exception ) {
      System.out.println( "Exception - " + exception.toString() );
      System.exit( 0 );
    }
  }

  private void start_server() {
    try {
    this.chat_server_parent = new ServerSocket( 8888 );
    this.chat_server_child = this.chat_server_parent.accept();
    this.input_stream = this.chat_server_child.getInputStream();
    this.output_stream = this.chat_server_child.getOutputStream();
    this.from_client = new Scanner( this.input_stream );
    this.to_client = new PrintWriter( this.output_stream, true );
    } catch( Exception exception ) {
      System.out.println( "Exception - " + exception.toString() );
    }
  }
  
  private void engine() {
    try {
      this.to_client.println( "+----------------------------------------------------------------------+" );
      this.to_client.println( "|                             CHAT SERVER                              |" );
      this.to_client.println( "|    Type bye to exit. Wait for the reply after sending a message      |" );
      this.to_client.println( "+----------------------------------------------------------------------+\n\n" );
      while( this.from_client.hasNextLine() ) {
        String message = this.from_client.nextLine();
        if( message.trim().equals( "bye" ) || message.trim().equals( "Bye" ) ) {
          this.stop_server();
        } else {
          System.out.println( "\n" + this.chat_server_child.getRemoteSocketAddress().toString() + " - " + message );
          System.out.print( "Reply - " );
          String reply = this.get.nextLine();
          this.to_client.println( "\n" + reply );
        }
      }
    } catch( Exception exception ) {
      System.out.println( "Exception - " + exception.toString() );
    }
  }
  
  private void stop_server() {
    try {
      this.chat_server_parent.close();
      this.chat_server_child.close();
    } catch( Exception exception ) {
      System.out.println( "Exception - " + exception );
    }
  }
  
  private Scanner get = new Scanner( System.in );
  private ServerSocket chat_server_parent;
  private Socket chat_server_child;
  private InputStream input_stream;
  private OutputStream output_stream;
  private Scanner from_client;
  private PrintWriter to_client;
}
