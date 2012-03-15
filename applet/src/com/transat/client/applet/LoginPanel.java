///////////////////////////////////////////////
//
//
//
//
///////////////////////////////////////////////


package com.transat.client.applet;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

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
	
	private Box b_center = null;
	
	private Box b_pseudo = null;
	private JLabel l_pseudo = null;
	private JTextField t_pseudo = null;
	
	private Box b_password = null;
	private JLabel l_password = null;
	private JPasswordField t_password = null;

	private Button bt_ok = null;
	
	
	
	//-----------------------------------------
	//	CONSTRUCTOR
	//-----------------------------------------
	public LoginPanel()
	{
		super();
				
		this.setLayout(new BorderLayout());
		
		// TOP
		// Title
		this.l_title = new JLabel();
		this.l_title.setHorizontalAlignment( JLabel.CENTER );
		this.l_title.setText("еее SE CONNECTER еее");
		this.l_title.setFont( new Font("Dialog", Font.PLAIN, 24) );
		
		// CENTER
		// Pseudo
		this.b_pseudo = new Box( BoxLayout.X_AXIS );
		this.l_pseudo = new JLabel();
		this.l_pseudo.setText("Pseudo");
		this.l_pseudo.setFont( new Font("Dialog", Font.PLAIN, 18) );
		this.t_pseudo = new JTextField();
		this.b_pseudo.add(this.l_pseudo);
		this.b_pseudo.add(this.t_pseudo);
		this.b_pseudo.setMaximumSize(new Dimension(300, 24));
		// Password
		this.b_password = new Box( BoxLayout.X_AXIS );
		this.l_password = new JLabel();
		this.l_password.setText("Password");
		this.l_password.setFont( new Font("Dialog", Font.PLAIN, 18) );
		this.t_password = new JPasswordField();
		this.b_password.add(this.l_password);
		this.b_password.add(this.t_password);
		this.b_password.setMaximumSize(new Dimension(300, 24));
		// Button OK
		this.bt_ok = new Button("Me connecter");
		this.bt_ok.setMaximumSize(new Dimension(300, 25));
		this.bt_ok.addActionListener(this);
		// Box center
		this.b_center = new Box( BoxLayout.Y_AXIS );
		this.b_center.setOpaque(true);
		this.b_center.add(this.b_pseudo);
		this.b_center.add(this.b_password);
		this.b_center.add(this.bt_ok);
		
		
		
		this.add(this.l_title, BorderLayout.NORTH);
		this.add(this.b_center, BorderLayout.CENTER);
		
	}




	//-----------------------------------------
	//	ACTION PERFORMED
	//-----------------------------------------
	@Override
	public void actionPerformed(ActionEvent event)
	{
		String dataUrl = "http://localhost:9000/user/read?pseudo=axel&password=axel";
		//dataUrl = dataUrl.replace(" ", "%20");
		URL 		url 		= null;
		Object 		content 	= null;
		String		response	= null;
		try
		{
			url = new URL( dataUrl );
			content = url.getContent();
		}
		catch (MalformedURLException e) { e.printStackTrace();}
		catch (IOException e) { e.printStackTrace(); }
		
		if( content != null )
		{
			InputStream is	= (InputStream) content;
			response = is.toString();
			System.out.println(response);
		}
		
	}
	
	
	
	
	//-----------------------------------------
	//	METHODS
	//-----------------------------------------
	
	
	
	
	
	
	
	
	
	
	
}
