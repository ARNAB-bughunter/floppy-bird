import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;
import java.util.*;
class test{
	static JFrame frame;
	static Container c1;
	public static void main(String[] args) {
		frame=new JFrame();
		frame.setVisible(true);
        JLabel label=new JLabel("");
        JLabel label1=new JLabel("");
        label.setBounds(15,800,1355,20);
        label1.setBounds(15,820,1355,40);
        label.setBackground(Color.green);
        label.setOpaque(true);
        label1.setBackground(Color.orange);
        label1.setOpaque(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(10,10,1405,910);
		bird objx=new bird();
		c1=frame.getContentPane();
		frame.addKeyListener(objx);
		c1.setLayout(null);
		c1.setBackground(Color.black);
        c1.add(objx);
        c1.add(label);
        c1.add(label1);
	}
}

class bird extends JPanel implements KeyListener{
	File file1,file2,file3;
	Image bird_pic,buttom_pipe,top_pipe;
	int Score=0;
	int x=45,y=250;
	int gap=150;
	int pipe_speed=70;
	boolean connection=true;
	boolean status=true;
	Rectangle bird_rect;
	Rectangle top_rect1,down_rect1;
	Rectangle top_rect2,down_rect2;
	int downx1=1200,downy1=486,topx1=1200,topy1=(downy1-486)-gap;//for 1
	int downx2=700,downy2=400,topx2=700,topy2=(downy2-486)-gap;//for 2
	int downx3=200,downy3=314;
 public void paint(Graphics g){
 super.paint(g);
 try{
file1=new File("C:/Users/Arnab Naha/Desktop/gAmE/floppy bird/pic.png"); 
file2=new File("C:/Users/Arnab Naha/Desktop/gAmE/floppy bird/buttom.png");	
file3=new File("C:/Users/Arnab Naha/Desktop/gAmE/floppy bird/top.png");
bird_pic=ImageIO.read(file1);
buttom_pipe=ImageIO.read(file2);
top_pipe=ImageIO.read(file3);
}catch(Exception e){}
 g.drawImage(bird_pic,x,y,null);	
 g.drawImage(buttom_pipe,downx1,downy1,null);
 g.drawImage(top_pipe,topx1,topy1,null);
 g.drawImage(buttom_pipe,downx2,downy2,null);
 g.drawImage(top_pipe,topx2,topy2,null);
 g.setColor(Color.black);
 g.fillRect(15,15,170,50);
 g.setColor(Color.cyan);
 g.setFont(new Font("Comic Sans MS",Font.BOLD,30));
 g.drawString("Score="+String.valueOf(Score),15,50);
 }
 bird(){
 this.setBounds(15,15,1355,790);
this.setBackground(Color.blue);
this.setLayout(null);
 }  
class move extends Thread{
public void run(){
while(status){
y=y+35;
pipe_move();
if(downx1<=0){
	Score++;
	downx1=1215;
	Random rand=new Random();
    downy1=rand.nextInt(300)+315;
    topy1=(downy1-486)-gap;
  }
 if(downx2<=0){
 	Score++;
    downx2=1215;
	Random rand=new Random();
    downy2=rand.nextInt(300)+315;
    topy2=(downy2-486)-gap;    	
  }
if(topx1<=0){
topx1=1215;	
 }
if(topx2<=0){
topx2=1215;
 }
bird_rect=new Rectangle(x,y,71,75);
top_rect1=new Rectangle(topx1,topy1,140,495);
down_rect1=new Rectangle(downx1,downy1,140,495);
top_rect2=new Rectangle(topx2,topy2,140,495);
down_rect2=new Rectangle(downx2,downy2,140,495);
if(y>=710){y=710;}
if(x>=710){y=710;}
repaint();
if(bird_rect.intersects(top_rect1) || bird_rect.intersects(down_rect1)){
status=false;
connection=false;
}
if(bird_rect.intersects(top_rect2) || bird_rect.intersects(down_rect2)){
status=false;
connection=false;
}
try{Thread.sleep(350);}catch(Exception e){}
    }
  } 
}
public void keyReleased(KeyEvent key){}
public void keyTyped(KeyEvent key){}
public void keyPressed(KeyEvent key){
if(key.getKeyCode()==KeyEvent.VK_SPACE){
	y=y-45;
	if(y<=0){y=0;}
	if(connection)
    repaint();
   }
if(key.getKeyCode()==KeyEvent.VK_ENTER){
	status=true;
    move obj1=new move();
    obj1.start();
   }  
  }
public void pipe_move(){
downx1=downx1-pipe_speed;
topx1=topx1-pipe_speed;
downx2=downx2-pipe_speed;
topx2=topx2-pipe_speed;
repaint();
 }  
}