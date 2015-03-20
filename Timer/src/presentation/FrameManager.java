package presentation;

import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import objects.ETAupdater;

public class FrameManager implements ActionListener {
	private JFrame frame;
	private Container frameContent;
	private JPanel buttonContainer;
	private JLabel eta;
	private JButton start;
	private JButton stop;
	private ETAupdater timeStamper;
	
	public FrameManager() {		
		frame = new JFrame( "Timer" );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );		
		frameContent = frame.getContentPane();
		SpringLayout layout = new SpringLayout();
		frameContent.setLayout( layout );
		
		buttonContainer = new JPanel();
		JLabel infoLabel = new JLabel( "This timer has run for: " );
		eta = new JLabel( "00:00" );
		start = new JButton( "Start" );
		stop = new JButton( "Stop" );
		timeStamper = new ETAupdater( eta );
		
		start.addActionListener( this );
		stop.addActionListener( this );
		frameContent.add( infoLabel );
		frameContent.add( eta );
		frameContent.add( buttonContainer );
		buttonContainer.add( start );
		buttonContainer.add( stop );
		
		layout.putConstraint( SpringLayout.WEST, infoLabel, 20,
                SpringLayout.WEST, frameContent );
		
		layout.putConstraint( SpringLayout.NORTH, infoLabel, 5,
                SpringLayout.NORTH, frameContent );
		
		layout.putConstraint( SpringLayout.WEST, eta, 5,
				SpringLayout.EAST, infoLabel );
		
		layout.putConstraint( SpringLayout.NORTH, eta, 5,
				SpringLayout.NORTH, frameContent );
		
		layout.putConstraint( SpringLayout.NORTH, buttonContainer, 5,
				SpringLayout.SOUTH, infoLabel );
		
		layout.putConstraint( SpringLayout.WEST, buttonContainer, 30,
				SpringLayout.WEST, frameContent );
		
		layout.putConstraint(SpringLayout.EAST, frameContent, 20,
				SpringLayout.EAST, eta);
		
		layout.putConstraint(SpringLayout.SOUTH, frameContent, 5,
				SpringLayout.SOUTH, buttonContainer);
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.createImage( "./resources/clock-icon.png" );
		frame.setIconImage(img);
		frame.pack();
		frame.setVisible( true );
	}

	@Override
	public void actionPerformed( ActionEvent arg0 ) {
		if( arg0.getSource() == start ) {
			if( !timeStamper.getState().toString().equals("STARTED") ) {
				timeStamper = new ETAupdater( eta );
				timeStamper.execute();
			}
		} else if( arg0.getSource() == stop ) {
			if( timeStamper.getState().toString().equals("STARTED") ){
				timeStamper.interrupt();
			}
		}
	}
}