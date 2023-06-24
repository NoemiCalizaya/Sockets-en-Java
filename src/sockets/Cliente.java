package sockets;

import java.awt.event.*;
import java.io.*;
import java.io.IOException;
import java.net.*;

import javax.swing.*;

/* Los sockets en Java nos permite crear aplicaciones cliente-servidor, de manera que vamos a poder mover información desde un 
   ordenador cliente a un ordenador servidor. 
   Para esto crearemos un puente virtual que comunicará al cliente con el servidor a través del socket.
   Para crear el socket vamos a necesitar la dirección del servidor y el puerto de recepción. También 
   necesitaremos utilizar un flujo de datos: un OutputStream en la aplicacion del cliente y un InputStream para la aplicacion del servidor.
   Para tener un ejemplo de socket en Java vamos a utilizar swing para crear una aplicación cliente y servidor. 
   A partir de ello veremos como podemos trasladar información desde el cliente al servidor.
*/

public class Cliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MarcoCliente mimarco=new MarcoCliente();
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}


class MarcoCliente extends JFrame{
	
	public MarcoCliente(){
		
		setBounds(600,300,280,350);
				
		LaminaMarcoCliente milamina=new LaminaMarcoCliente();
		
		add(milamina);
		
		setVisible(true);
		}	
	
}

class LaminaMarcoCliente extends JPanel{
	
	private JTextField campo1;
	private JButton miboton;
	
	public LaminaMarcoCliente(){
	
		JLabel texto=new JLabel("CLIENTE");
		
		add(texto);
	
		campo1=new JTextField(20);
	
		add(campo1);		
	
		miboton=new JButton("Enviar");
		
		EventoMiboton evento_miboton = new EventoMiboton();
		
		miboton.addActionListener(evento_miboton);
		
		add(miboton);
		
	}

	class EventoMiboton implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				Socket clientesocket = new Socket("192.168.0.7", 9999);
				
				DataOutputStream flujo_datos = new DataOutputStream(clientesocket.getOutputStream());
				
				flujo_datos.writeUTF(campo1.getText());
				
				flujo_datos.close();
				
			} catch (UnknownHostException e1) {
				
				e1.printStackTrace();
				
			} catch (IOException e1) {
				
				System.out.println(e1.getMessage());
			}
		}
	}
}
