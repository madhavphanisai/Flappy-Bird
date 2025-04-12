import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class FlappyBird extends JPanel implements ActionListener, KeyListener{
    int boardHeight=640,boardWidth=360;

    Image background;
    Image birdImg;
    Image TopPipe;
    Image BottomPipe;

    int birdX = boardWidth/8;
    int birdY = boardHeight/2;
    int birdWidth = 34;
    int birdHeight = 24;
    class Bird{
        int x = birdX;
        int y = birdY;
        int width = birdWidth;
        int height = birdHeight;
        Image img;
        Bird(Image img){
            this.img=img;
        }
    }

    int pipeX = boardWidth;
    int pipeY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;
    class Pipe{
        int x = pipeX;
        int y = pipeY;
        int width = pipeWidth;
        int height = pipeHeight;
        Image img;
        boolean passed = false;
        Pipe(Image img){
            this.img=img;
        }
    }

    Bird bird;
    int velocityX=-4;
    int velocityY=0;
    int gravity =1;

    Timer gameLoop;
    Timer placePipesTimer;

    ArrayList<Pipe> pipes;
    Random random = new Random();

    boolean gameOver = false;
    double score = 0;
    public FlappyBird(){
        setPreferredSize(new Dimension(boardWidth,boardHeight));
        setFocusable(true);

        addKeyListener(this);
        
        // setBackground(Color.blue);
        background=new ImageIcon(getClass().getResource("flappybirdbg.png")).getImage();
        birdImg=new ImageIcon(getClass().getResource("flappybird.png")).getImage();
        TopPipe=new ImageIcon(getClass().getResource("toppipe.png")).getImage();
        BottomPipe=new ImageIcon(getClass().getResource("bottompipe.png")).getImage();
        bird = new Bird(birdImg);
        pipes = new ArrayList<Pipe>();
        placePipesTimer=new Timer(1500,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });
        placePipesTimer.start();  
        gameLoop=new Timer(1000/60,this);
        gameLoop.start();


    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }   
    public void draw(Graphics g){  
        g.drawImage(background,0,0,boardWidth,boardHeight,null);
        g.drawImage(bird.img,bird.x,bird.y,bird.width,bird.height,null);

        for(int i=0;i<pipes.size();i++){
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.img,pipe.x,pipe.y,pipe.width,pipe.height,null);
        }

        g.setColor(Color.red);
        g.setFont(new Font("Arial",Font.BOLD,32));
        if(gameOver){
            g.drawString("Game Over: "+String.valueOf((int)score),10,35);
        }
        else g.drawString(String.valueOf((int)score),10,35 );
    }
    public void move(){
        velocityY+=gravity;
        bird.y+=velocityY;
        bird.y=Math.max(bird.y,0);

        for(int i=0;i<pipes.size();i++){
            Pipe pipe = pipes.get(i);
            pipe.x+=velocityX;
            if(!pipe.passed && bird.x>pipe.x+pipe.width){
                pipe.passed=true;
                score+=0.5;
            }
            if(Collosion(bird,pipe)){
                gameOver=true;
            }
        }
        if(bird.y>boardHeight){
            gameOver=true;  
        }
    }
    
    public boolean Collosion(Bird a,Pipe b){
        return  a.x < b.x + b.width &&   
        a.x + a.width > b.x &&   
        a.y < b.y + b.height &&  
        a.y + a.height > b.y; 
    }

    public void placePipes(){
        int RandomPipeY = (int)(pipeY-pipeHeight/4 - Math.random()*(pipeHeight/2));
        int openingSpace = boardHeight/4;

        Pipe toppipe = new Pipe(TopPipe); 
        toppipe.y=RandomPipeY;  
        pipes.add(toppipe);
                       
        Pipe bottompipe = new Pipe(BottomPipe);
        bottompipe.y=toppipe.y+pipeHeight+openingSpace;
        pipes.add(bottompipe);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if(gameOver){
            placePipesTimer.stop();
            gameLoop.stop(); 

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            velocityY=-9;
        }
        if(gameOver){
            bird.y=birdY;
            velocityY=0;
            pipes.clear();
            score=0;
            gameOver=false;
            gameLoop.start();
            placePipesTimer.start();
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
         
    }


    @Override
    public void keyReleased(KeyEvent e) {
 
    }
}
