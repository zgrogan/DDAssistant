package ddassistant;
//import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class DDAssistant {
	public static void main(String[] args) {
		//Lets make a window!
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e){
			System.out.println("Look & Feel Exception!");
		}
		
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				new DDAssistantWindow().setVisible(true);
			}
			
		});
	}

}
