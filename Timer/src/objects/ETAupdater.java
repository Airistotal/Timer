package objects;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

public class ETAupdater extends SwingWorker< Boolean, String > {
	JLabel pub;
	boolean isOn;
	
	public ETAupdater( JLabel eta){
		if( eta != null ) {
			pub = eta;
		} else {
			System.out.println("Warning: JLabel null.");
		}
		
		this.isOn = true;
	}
	
	@Override
	protected Boolean doInBackground() throws Exception {
		int length = 6000;
		int[] time = new int[2];
		int second = 0;
		
		for(int i = 0; i < length && this.isOn; i++){
			if( second == 10 ) {
				second = 0;
				
				time[1] += 1;
				if( time[1] == 60 ) {
					time[1] = 0;
					time[0] += 1;
				}
				
				if( time[0] / 10 < 1 && time[1] / 10 < 1 ) {
					publish( "0" + time[0] + ":" + "0" + time[1] );
				} else if( time[0] / 10 < 1) {
					publish( "0" + time[0] + ":" + time[1] );
				} else if ( time[1] / 10 < 1 ) {
					publish( time[0] + ":" + "0" + time[1] );
				} else {
					publish( time[0] + ":" + time[1] );
				}
			}
			
			try {
				Thread.sleep( 100 );
			} catch ( InterruptedException e ) {
				e.printStackTrace();
			}
			
			second += 1;
		}
		
		return true;
	}
	
	@Override
	public void done() {
		java.awt.Toolkit.getDefaultToolkit().beep();	    
		
		JOptionPane.showMessageDialog(null, "Timer complete!");
		this.pub.setText("00:00");
	}
	
	public void interrupt() {
		this.isOn = false;
	}
	
	@Override
	public void process(List<String> chunks) {
		if( chunks != null && chunks.size() > 0) {
			this.pub.setText( chunks.get( 0 ) );
		}
	}

}
