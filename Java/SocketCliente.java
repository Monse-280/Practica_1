import java.net.*;
import java.io.*;

/**
 * Clase que crea un socket cliente, establece la conexión y lee los datos
 * del servidor, escribiéndolos en pantalla.
 */
public class SocketCliente
 {
     /** Programa principal, crea el socket cliente */
     public static void main (String [] args)
     {
         new SocketCliente();
     }
     
     /**
      * Crea el socket cliente y lee los datos
      */
     public SocketCliente()
     {
         try
         {
             /* Se crea el socket cliente */
             Socket socket = new Socket ("localhost", 15557);
             System.out.println ("conectado");

             /* Se hace que el cierre espere a la recogida de todos los datos desde
             * el otro lado */
             socket.setSoLinger (true, 10);
             
             /* Se obtiene un stream de lectura para leer objetos */
             DataInputStream bufferEntrada =
                new DataInputStream (socket.getInputStream());
             
             /* Se lee un Datosocket que nos envía el servidor y se muestra 
              * en pantalla */
             DatoSocket dato = new DatoSocket("");
             dato.readObject(bufferEntrada);
             System.out.println ("Servidor Java: Recibido " + dato.toString());

             /* Se obtiene un flujo de envio de datos para enviar un dato al servidor */
             DataOutputStream bufferSalida =
               new DataOutputStream (socket.getOutputStream());

             /* Se crea el dato y se escribe en el flujo de salida */
             DatoSocket aux = new DatoSocket ("Hola como estas");
             aux.writeObject (bufferSalida);

             System.out.println ("Servidor Java: Enviado " + aux.toString());
           
             /* La llamada a setSoLinger() hará que el cierre espere a que el otro
             lado retire los datos que hemos enviado */
             socket.close();
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }
     }
}
