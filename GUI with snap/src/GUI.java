import java.awt.*;
import java.awt.image.BufferStrategy;

import javax.swing.*;

public class GUI extends JFrame{
	
	/**
	 *Java Interface Prototype
	 *Created 5/15/15 by Kyle Xiao
	 *Siemens Competition 2015-16
	 */
	
	private static final long serialVersionUID = 1L;
	
	// contains DraggableRect objects and handles mouse input
	private Controller controller;
	
	// sets up buffer strategy for graphics
	public BufferStrategy s;
	
	// Default constructor; Sets default attributes of window and sets up handlers
	public GUI(){
		// instantiates controller object
		controller = new Controller();
		// adds mouse listeners
		this.addMouseListener(controller);
		this.addMouseMotionListener(controller);
		// adds default DraggableRect objects with positions
		controller.addRect(new DraggableRect(525, 120, 100, 100, Color.GREEN));
		controller.addRect(new DraggableRect(525, 250, 100, 100));
		// sets up window attributes
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.createBufferStrategy(2);
		s = this.getBufferStrategy();
		((Graphics2D) this.getGraphics()).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, 
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	}

	// main function
	public static void main(String[] args){
		GUI window = new GUI();
		window.setDefaultImage();
	}
	
	// Sets default layout and preferences for window
	public void setDefaultImage(){
		// sets basic graphical attributes for window
		setTitle("Siemens Intuitive Interface");
		setSize(800, 600);
		setBackground(Color.GRAY);
		setForeground(Color.BLACK);
		setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		// adds JLabel object
		add(new JLabel("Example Message"));
		
		// adds all DraggableRect object in controller onto the window JFrame
		for(DraggableRect r : controller.getRects()){
			add(r);
		}
     /* JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(10, 10));
        JScrollPane textScroller = new JScrollPane();
        JTextArea tarea = new JTextArea(300, 300);
        textScroller.setViewportView(tarea);
        contentPane.add(textScroller);
        setContentPane(contentPane);
        pack();*/
	}
	
	// Function to run handlers
	public void run(){
	}
	
	// Draw function which is called by default
	public void draw(Graphics2D g){
		g.drawString("Code Display", 20, 80);
		g.drawString("Interface", 520, 80);
		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(5));
		g.drawLine(0, 100, 2000, 100);
		g.drawLine(500, 100, 500, 1000);
	}
	
	// Overrides JFrame default paint function
	@Override
	public void paint(Graphics graphics){
		try{
			// calls default paint functions in parent object
			super.paint(s.getDrawGraphics());
			super.paintComponents(s.getDrawGraphics());
			// calls function to draw onto g
			Graphics2D g = (Graphics2D) s.getDrawGraphics();
			controller.showRects(g);
			draw(g);
			s.show();
			Toolkit.getDefaultToolkit().sync();
			repaint();
		}catch(Exception ex){}
	}
}