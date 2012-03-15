///////////////////////////////////////////////
//
//
//
//
///////////////////////////////////////////////


package com.transat.client.applet;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;




public class LoginPanel extends JPanel implements ActionListener
{
	//-----------------------------------------
	//	STATIC
	//-----------------------------------------
	
	
	
	
	
	
	//-----------------------------------------
	//	ATTRIBUTES
	//-----------------------------------------
	
	private JLabel l_title = null;
	
	private Box b_pseudo = null;
	private JLabel l_pseudo = null;
	private JTextField t_pseudo = null;
	
	private Box b_password = null;
	private JLabel l_password = null;
	private JPasswordField t_password = null;

	
	
	
	
	//-----------------------------------------
	//	CONSTRUCTOR
	//-----------------------------------------
	public LoginPanel()
	{
		super();
		
		this.setBackground(Color.ORANGE);
		
		// Title
		this.l_title = new JLabel();
		this.l_title.setHorizontalAlignment(JLabel.CENTER);
		this.l_title.setText("еее SE CONNECTER еее");
		this.l_title.setFont( new Font("Dialog", Font.PLAIN, 24) );
		// Pseudo
		this.b_pseudo = new Box( BoxLayout.X_AXIS );
		this.l_pseudo = new JLabel();
		this.l_pseudo.setText("Pseudo");
		this.l_pseudo.setFont( new Font("Dialog", Font.PLAIN, 18) );
		this.t_pseudo = new JTextField();
		this.t_pseudo.setColumns(25);
		this.b_pseudo.add(this.l_pseudo);
		this.b_pseudo.add(this.t_pseudo);
		// Password
		this.b_password = new Box( BoxLayout.X_AXIS );
		this.l_password = new JLabel();
		this.l_password.setText("Password");
		this.l_password.setFont( new Font("Dialog", Font.PLAIN, 18) );
		this.t_password = new JPasswordField();
		this.t_password.setColumns(25);
		this.b_password.add(this.l_password);
		this.b_password.add(this.t_password);
		
		
		this.add(this.l_title);
		//this.add(this.b_pseudo);
		//this.add(this.b_password);
	}




	//-----------------------------------------
	//	ACTION PERFORMED
	//-----------------------------------------
	@Override
	public void actionPerformed(ActionEvent event)
	{
		
	}
	
	
	
	
	//-----------------------------------------
	//	METHODS
	//-----------------------------------------
	
	
	
	
	
	
	
	
	
	
	
}
