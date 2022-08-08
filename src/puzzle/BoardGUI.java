package puzzle;

import javax.swing.*;
import javax.swing.plaf.IconUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class BoardGUI implements ActionListener {
    int row;
    int col;
    JFrame f ;
    JPanel mainboard;
    JButton[][] buttons;
    JLabel[][] label;
    int[][] board;
    BoardGUI(){

         row =4;
         col=4;
        board =new  int[row][col];
        initGUI();


    }
    public void initGUI(){
         f=new JFrame("Number_Puzzle");
         mainboard =new JPanel();
         mainboard.setBackground(Color.white);
         mainboard.setLayout(new GridLayout(4,4));
         mainboard.setSize(500,500);
        buttons =new JButton[row][col];

        label =new JLabel[row][col];
        this.shuffleboard();
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
               buttons[i][j]=new JButton();
               String text=i+","+j;
               buttons[i][j].setText(text);
               buttons[i][j].setFont((new Font("TimesRoman",Font.PLAIN,0)));
         buttons[i][j].addActionListener(this);

               int val=board[i][j];
               String filename;
               if(val !=-1){
                   filename ="pics/"+val+ ".png";
                   label[i][j]= new JLabel( new ImageIcon(filename),JLabel.CENTER);
               }
               else{
                   label[i][j]=new JLabel("");
               }

//                this.buttons[i][j].addActionListener(this);
              buttons[i][j].add(label[i][j]);
               buttons[i][j].setBorder(BorderFactory.createLineBorder(Color.black,2));
//               buttons[i][j].setPreferredSize(new Dimension(30 ,20));
               buttons[i][j].setBackground(Color.LIGHT_GRAY);
               mainboard.add(buttons[i][j]);

           }
       }
        f.add(mainboard);
         f.setSize(400,400);
         f.setVisible(true);
         f.setLocationRelativeTo(null);
         f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
public  void shuffleboard(){

    Random rand =new Random();
    int array[] =new int[16];
    for(int i=0;i<16;i++){
        array[i] =i+1;
    }
    array[15]=-1; //last value is -1;
    //now shuffle array

    for(int i=0;i<16;i++)
    {
        int index =rand.nextInt(16);

        // replace element at randoom with i index elemnet

        int temp =array[i];
        array[i]=array[index];
        array[index]=temp;

    }
    int count =0;
    for(int i=0;i<row;i++){
        for(int j=0;j<col;j++){
            board[i][j]= array[count];
            count =count +1;
//            System.out.print(board[i][j] +" ");
        }
//        System.out.println("");
    }

}
public boolean isWin(){
        int count =1;
        for(int i=0;i<row ;i++){
            for(int j=0;j<col;j++){
                 if(board[i][j] !=count && board[i][j]!=-1){
                     return  false;
                 }
                 count =count+1;

            }
        }
return true;
}
 public void  displayWinMsg(){
 JFrame frame = new JFrame("Game Win");
 JLabel labe = new JLabel("You  solve the PuZZle",JLabel.CENTER);
  labe.setFont(new Font("TimeRoman",Font.BOLD,20));
  frame.add(labe);
  frame.setLayout(new GridLayout(1,1));
  frame.setSize(300,300);
 frame.setBackground(Color.PINK);
 frame.setVisible(true);
     frame.setLocationRelativeTo(null);
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

 }
    @Override
    public void actionPerformed(ActionEvent ae) {
     Boolean flag = isWin();


         String s=ae.getActionCommand().toString();
        int r= Integer.parseInt(s.split(",")[0]);
        int c= Integer.parseInt(s.split(",")[1]);
         if(board[r][c]!=-1){
             if(r+1<row && board[r+1][c]==-1){ //for up
                  label[r][c].setIcon(new ImageIcon(""));
                  String filename="pics/"+board[r][c]+".png";
                  label[r+1][c].setIcon((new ImageIcon(filename)));
                  int temp=board[r][c];
                  board[r][c] =board[r+1][c];
                  board[r+1][c]=temp;
             }
          else if(r-1>=0 && board[r-1][c]==-1){ //for down
                 label[r][c].setIcon(new ImageIcon(""));
                 String filename="pics/"+board[r][c]+".png";
                 label[r-1][c].setIcon((new ImageIcon(filename)));
                 int temp=board[r][c];
                 board[r][c]=board[r-1][c];
                 board[r-1][c]=temp;
             }
             else if(c+1<col && board[r][c+1]==-1){ // for right
                 label[r][c].setIcon(new ImageIcon(""));
                 String filename="pics/"+board[r][c]+".png";
                 label[r][c+1].setIcon((new ImageIcon(filename)));
                 int temp=board[r][c];
                 board[r][c]=board[r][c+1];
                 board[r][c+1]=temp;
             }
             else if(c-1>=0 && board[r][c-1]==-1){ //for left
                 label[r][c].setIcon(new ImageIcon(""));
                 String filename="pics/"+board[r][c]+".png";
                 label[r][c-1].setIcon((new ImageIcon(filename)));
                 int temp=board[r][c];
                 board[r][c]=board[r][c-1];
                 board[r][c-1]=temp;
             }


         }
         flag =isWin();
         if(flag==true){
             displayWinMsg();
         }





    }
}
