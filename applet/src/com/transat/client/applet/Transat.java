///////////////////////////////////////////////
//
//
//
//
///////////////////////////////////////////////

package com.transat.client.applet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;




public class Transat extends JApplet 
{
	private JLabel label = new JLabel();
	private JButton bouton = new JButton("Cliquez");
	private int count = 0;
	
	
	public void init()
	{
		this.setSize(800, 600);
		
		/*
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.blue);
		*/
		/*
		this.bouton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				count++;
				label.setText("Vous avez clique " + count + " fois sur le bouton");
			}			
		});
		*/
		
		
		this.setContentPane( new LoginPanel() );		
		
		
		//this.getContentPane().add(bouton, BorderLayout.SOUTH);
		//this.getContentPane().add(label, BorderLayout.NORTH);
	}
	
	
	
	
}



