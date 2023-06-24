package sockets;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class Servidor  {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MarcoServidor mimarco=new MarcoServidor();
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
	}	
}

class MarcoServidor extends JFrame implements Runnable{
	
	public MarcoServidor(){
		
		setBounds(1200,300,280,350);				
			
		JPanel milamina= new JPanel();
		
		milamina.setLayout(new BorderLayout());
		
		areatexto=new JTextArea();
		
		JScrollPane scrollPane = new JScrollPane(areatexto);
		
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		milamina.add(scrollPane, BorderLayout.CENTER);
		
		add(milamina);
		
		setVisible(true);
		
		Thread mihilo = new Thread(this);
		
		mihilo.start();
		
	}
	
	@Override
	public void run(){
		try {
			ServerSocket servidor = new ServerSocket(9999);//está a la escucha con el puerto abierto
			
			while(true) {
				Socket servidorsocket = servidor.accept();//acepte las conexiones que vengan del exterior
				
				DataInputStream flujo_entrada = new DataInputStream(servidorsocket.getInputStream());
				
				String mensaje_texto_recibido = flujo_entrada.readUTF();
				
				areatexto.append("\n"+mensaje_texto_recibido);
				
				servidorsocket.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private	JTextArea areatexto;
}

