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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.json.JSONException;
import org.json.JSONObject;

import com.transat.client.utils.Log;




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
		this.l_title.setText("••• SE CONNECTER •••");
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
		
		// CLIC BOUTON LOGIN
		String pseudo = this.t_pseudo.getText();
		String password = new String( this.t_password.getPassword() );
		if( pseudo.length()>0 && password.length()>0 )
		{
			String jsonString = sendRequestGet( "http://localhost:9000/user/read?pseudo="+pseudo+"&password="+password+"" );
			if( jsonString.length() > 0 )
			{
				try {
					JSONObject obj = new JSONObject( jsonString );
					Log.v( "response : "+obj.getString("pseudo") );
					Log.v( "response : "+obj.getString("password") );
					Log.v( "response : "+obj.getString("email") );
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			else
			{
				Log.v("Le serveur n'a rien renvoyé :)");
			}
		}
		else
		{
			
		}
	}
	
	
	
	
	//-----------------------------------------
	//	METHODS
	//-----------------------------------------
	String sendRequestGet( String requestURL )
	{
		String result = "";		
		//dataUrl = dataUrl.replace(" ", "%20");
		String dataUrl = requestURL;
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
			StringWriter writer = new StringWriter();
			InputStreamReader streamReader = new InputStreamReader(is);
			BufferedReader buffer=new BufferedReader(streamReader);
			String line="";
			try {
				while ( null!=(line=buffer.readLine())){
					writer.write(line); 
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			result = writer.toString();
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
}
