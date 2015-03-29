    
package server;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Server {

    
    static JFrame frame=new JFrame("Cougar-Server");
    static JPanel p1=new JPanel();
    static JPanel p=new JPanel();
    static JLabel iconj;
    static ImageIcon icon=null;
    static JPanel ps;
    static JLabel stat;
    static JLabel stat_i;
    static JPanel pc;
    static ImageIcon start_i,stop_i,log_i,about_i,help_i,setting_i,user_i,device_i;
    static JButton start,stop,log,about,help,setting,user,device;
    static JScrollPane jScrollPane1 = new JScrollPane();
    static JTextArea jTextArea2 = new JTextArea();
    static HttpServer hs;
    static Server ser;
    public static void main(String[] args) {
        // TODO code application logic here
        ser=new Server();
        ser.Init();
        
    }
    public static void Init(){
        frame.setSize(500,500);
    	frame.setLayout(null);
        frame.setBackground(Color.BLACK);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("icon.jpg"));
        p.setBounds(0,0,500,105);
        p.setBackground(Color.BLACK);
        icon=new ImageIcon("cougar.jpg");
    	iconj=new JLabel(icon);
    	iconj.setBounds(0,0,500,100);
        ps=new JPanel();
    	ps.setLayout(null);
    	ps.setBackground(Color.BLACK);
    	ps.setBounds(0,105,500,160);
    	ps.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        stat=new JLabel("Status :");
        stat.setBounds(10,10,100,30);
        stat.setFont(new Font("Verdana", Font.ITALIC, 24));
        stat.setForeground(Color.LIGHT_GRAY);
        pc=new JPanel();
        pc.setLayout(null);
    	pc.setBackground(Color.BLACK);
    	pc.setBounds(0,265,500,425);
    	pc.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        
        start_i=new ImageIcon("start.png");
    	start=new JButton(start_i);
    	start.setLayout(new BorderLayout());
    	start.setBounds(20,60,180,70);
    	start.setBackground(Color.BLACK);
        
       
        
        stop_i=new ImageIcon("stop.png");
    	stop=new JButton(stop_i);
    	stop.setLayout(new BorderLayout());
    	stop.setBounds(240,60,180,70);
    	stop.setBackground(Color.BLACK);
        
        /*
        log_i=new ImageIcon("log.png");
    	log=new JButton(log_i);
    	log.setLayout(new BorderLayout());
    	log.setBounds(20,150,180,70);
    	log.setBackground(Color.BLACK);
        
        setting_i=new ImageIcon("setting.png");
    	setting=new JButton(setting_i);
    	setting.setLayout(new BorderLayout());
    	setting.setBounds(240,150,180,70);
    	setting.setBackground(Color.BLACK);
        
        user_i=new ImageIcon("user.png");
    	user=new JButton(user_i);
    	user.setLayout(new BorderLayout());
    	user.setBounds(20,240,180,70);
    	user.setBackground(Color.BLACK);
        
        help_i=new ImageIcon("help.png");
    	help=new JButton(help_i);
    	help.setLayout(new BorderLayout());
    	help.setBounds(240,240,180,70);
    	help.setBackground(Color.BLACK);
        */
        //
       jTextArea2.setBackground(Color.BLACK);
       jTextArea2.setForeground(Color.GREEN);
       jTextArea2.setBorder(BorderFactory.createLoweredBevelBorder());
       jTextArea2.setToolTipText("");
       jTextArea2.setEditable(false);
       jTextArea2.setColumns(30);
       jTextArea2.setRows(15);
       jScrollPane1.setBounds(3,40,480,118);
       jScrollPane1.getViewport().add(jTextArea2);
       ps.add(jScrollPane1);
        //
        pc.add(start);
        pc.add(stop);
        
        //pc.add(log);
        
       // pc.add(setting);
        //pc.add(user);
        //pc.add(help);
         
         
        ps.add(stat);
         
        p.add(iconj);
        frame.add(ps);
        frame.add(p);
        frame.add(pc);
        frame.setVisible(true);
        
         start.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){	
    			hs = new HttpServer(80,ser);
    		}
    		
    	});
    }
    public void send_message_to_window(String s) {
    jTextArea2.append(s);
  }
    
    
}
