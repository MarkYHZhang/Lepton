package com.markyhzhang.project.lepton;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainFrame extends JFrame{

    private int width = 1000, height = 650;
    private int startX = 30, startY = 50;

    private ArrayList<StringBuffer> lines = new ArrayList<>();

    private BufferedImage currentFrame;

    private int[] pixels;

    private int cursorLine = 0, cursorCharIndex = -1;

    private Color bgColor = Color.DARK_GRAY;
    private Font font;

    private BufferStrategy bs;
    private Graphics g;
    private FontMetrics fm;





    public MainFrame(){

        Log.d("Initializing MenuFrame window");
        //sets the size of the window
        setSize(width,height);
        //spawn location to center
        setLocationRelativeTo(null);
        //sets the title
        setTitle("Lepton");
        //disable resize
//        setResizable(false);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addKeyListener(new InteractionListener(this));
        setFocusTraversalKeysEnabled(false);

        lines.add(new StringBuffer());
        //init the image of current frame
        currentFrame = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //the pixels for the current image/frame
        pixels = ((DataBufferInt)currentFrame.getRaster().getDataBuffer()).getData();


        getContentPane().setBackground(bgColor);

        font = new Font("monospaced", Font.PLAIN, 20);

        setVisible(true);

        createBufferStrategy(2);
        bs = getBufferStrategy();
        g = bs.getDrawGraphics();
        fm = g.getFontMetrics(font);

        update();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                updateCursor();
            }
        }, 0, 300);
    }

    /*
     ctrls + :
     c = 3  copy
     v = 22 paste
     x = 24 cut
     a = 1 select all
     z = 26 undo
     y = 25 redo
     f = 6 find
     r = 18 replace
     t = 20 terminal
     ------
     [DONE] left = 200
     [DONE] up = 201
     [DONE] right = 202
     [DONE] down = 203

     enter = 10
     [DONE] backspace = 8
     delete = 127
     tab = 9
     */

//    ArrayList<Node> stuff = new ArrayList<>();

    public void test(){
        String input = "0 112\n" +
                "118 117\n" +
                "44 98\n" +
                "106 108\n" +
                "65 105\n" +
                "84 99\n" +
                "86 32\n" +
                "163 99\n" +
                "101 108\n" +
                "90 97\n" +
                "53 115\n" +
                "160 115\n" +
                "70 32\n" +
                "275 84\n" +
                "80 69\n" +
                "214 115\n" +
                "90 116\n" +
                "213 123\n" +
                "962 8\n" +
                "127 8\n" +
                "133 8\n" +
                "147 8\n" +
                "140 101\n" +
                "208 115\n" +
                "54 116\n" +
                "200 123\n" +
                "580 125\n" +
                "389 200\n" +
                "393 10\n" +
                "372 10\n" +
                "447 201\n" +
                "366 9\n" +
                "1640 112\n" +
                "109 117\n" +
                "46 98\n" +
                "120 108\n" +
                "60 105\n" +
                "96 99\n" +
                "100 32\n" +
                "417 115\n" +
                "80 116\n" +
                "99 97\n" +
                "75 116\n" +
                "85 105\n" +
                "131 99\n" +
                "139 32\n" +
                "207 118\n" +
                "91 111\n" +
                "134 100\n" +
                "480 8\n" +
                "158 105\n" +
                "60 100\n" +
                "139 109\n" +
                "300 8\n" +
                "115 32\n" +
                "81 109\n" +
                "119 97\n" +
                "66 105\n" +
                "54 110\n" +
                "283 40\n" +
                "230 83\n" +
                "84 116\n" +
                "135 114\n" +
                "61 105\n" +
                "50 110\n" +
                "69 103\n" +
                "77 32\n" +
                "780 97\n" +
                "80 114\n" +
                "160 103\n" +
                "78 115\n" +
                "56 91\n" +
                "71 93\n" +
                "533 95\n" +
                "573 8\n" +
                "379 41\n" +
                "429 123\n" +
                "170 125\n" +
                "414 200\n" +
                "283 10\n" +
                "188 10\n" +
                "620 9\n" +
                "1008 201\n" +
                "435 9\n" +
                "571 9\n" +
                "790 112\n" +
                "104 117\n" +
                "59 98\n" +
                "81 108\n" +
                "49 105\n" +
                "139 99\n" +
                "97 32\n" +
                "438 8\n" +
                "125 8\n" +
                "133 8\n" +
                "145 8\n" +
                "149 8\n" +
                "156 8\n" +
                "114 8\n" +
                "169 83\n" +
                "100 121\n" +
                "95 115\n" +
                "114 116\n" +
                "140 101\n" +
                "79 109\n" +
                "146 46\n" +
                "199 111\n" +
                "80 117\n" +
                "110 116\n" +
                "100 46\n" +
                "199 112\n" +
                "120 114\n" +
                "115 105\n" +
                "134 110\n" +
                "359 116\n" +
                "159 108\n" +
                "90 110\n" +
                "340 40\n" +
                "253 34\n" +
                "429 83\n" +
                "510 8\n" +
                "183 72\n" +
                "192 101\n" +
                "89 108\n" +
                "138 108\n" +
                "150 111\n" +
                "90 32\n" +
                "153 87\n" +
                "107 111\n" +
                "93 114\n" +
                "80 108\n" +
                "120 100\n" +
                "428 33\n" +
                "334 34\n" +
                "1146 95\n" +
                "535 8\n" +
                "534 41\n" +
                "300 59";

        Scanner sc = new Scanner(input);
        while (sc.hasNext()){
            try {
                Thread.sleep((long)sc.nextInt());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            input(sc.nextInt());
        }
    }

    long pre = -1;
    public void input(int val){
        if (pre==-1){
            pre=System.currentTimeMillis();
            System.out.println("0 " + val);
        }
        else{
            long cur = System.currentTimeMillis();
            System.out.println(cur-pre + " " + val);
            pre = cur;
        }
//        if (stuff.size()==0){
//            stuff.add(new Node(val, System.currentTimeMillis()));
//        }
//        else {
//            stuff.get(stuff.size()-1).time = System.currentTimeMillis() - stuff.get(stuff.size()-1).time;
//        }



        if (val>=32&&val<=126){
            //is typeable
            if (lines.get(cursorLine).length()==0)lines.get(cursorLine).append((char)val);
            else lines.get(cursorLine).insert(cursorCharIndex+1,(char)val);
            update();
            if (cursorCharIndex<lines.get(cursorLine).length()-1)cursorCharIndex++;
            updateCursor();
        }else{
            if (val==InputVal.BACKSPACE){

                if(lines.get(cursorLine).length()==0 && cursorLine!=0){
                    cursorCharIndex=lines.get(cursorLine-1).length()-1;
                    lines.remove(cursorLine--);
                    update();
                    updateCursor();
                    return;
                }

                if (cursorCharIndex!=-1) lines.get(cursorLine).deleteCharAt(cursorCharIndex);
                else if(cursorLine!=0){
                    cursorCharIndex=lines.get(cursorLine-1).length()-1;
                    lines.get(cursorLine-1).append(lines.get(cursorLine));
                    lines.remove(cursorLine--);
                    update();
                    updateCursor();
                    return;
                }

                update();
                if (cursorCharIndex>=0)cursorCharIndex--;
                updateCursor();

            }else if (val>=200&&val<=203){
                update();
                if (val==InputVal.LEFT&&cursorCharIndex>=0)cursorCharIndex--;
                if (val==InputVal.RIGHT&&cursorCharIndex<lines.get(cursorLine).length()-1)cursorCharIndex++;
                if (val==InputVal.UP&&cursorLine>0){
                    cursorLine--;
                    if (lines.get(cursorLine).length()<=cursorCharIndex){
                        cursorCharIndex = lines.get(cursorLine).length()-1;
                    }
                }
                if (val==InputVal.DOWN&&cursorLine<lines.size()-1){
                    cursorLine++;
                    if (lines.get(cursorLine).length()<=cursorCharIndex){
                        cursorCharIndex = lines.get(cursorLine).length()-1;
                    }
                }
                updateCursor();
            }else if (val==InputVal.ENTER){
                String concatted = "";

                if (lines.get(cursorLine).length()!=0) {
                    concatted = lines.get(cursorLine).substring(cursorCharIndex + 1, lines.get(cursorLine).length());
                    lines.get(cursorLine).delete(cursorCharIndex + 1, lines.get(cursorLine).length());
                }
                lines.add(++cursorLine,new StringBuffer(concatted));
                cursorCharIndex=-1;
                update();
            }else if (val==InputVal.TAB){
                if (lines.get(cursorLine).length()==0)lines.get(cursorLine).append(InputVal.TAB_STRING);
                else lines.get(cursorLine).insert(cursorCharIndex+1,InputVal.TAB_STRING);
                update();
                if (cursorCharIndex<lines.get(cursorLine).length()-1)cursorCharIndex+=InputVal.TAB_STRING.length();
                updateCursor();
            }
        }
    }

    private void update(){
        //Gets the current bufferstrategy for drawing purpose

//        g.drawImage(currentFrame, 0, 0, width, height, null);
        g.setColor(bgColor);
        g.fillRect(0,0,width,height);

        g.setFont(font);

        g.setColor(Color.YELLOW);

        for (int i = 0; i < lines.size(); i++) {
            g.drawString(lines.get(i).toString(),startX,startY+(fm.getHeight()+2)*i);
        }
        bs.show();
//        bs.show();
    }

    private boolean flipFlag = false;
    private void updateCursor(){
        flipFlag=!flipFlag;
        Color c = flipFlag ? Color.WHITE : bgColor;
        g.setColor(c);

        if (cursorCharIndex>=lines.get(cursorLine).length()&&lines.get(cursorLine).length()!=0)return;

//        if (lines.get(cursorLine).length()==0 || cursorCharIndex==-1) g.fillRect(startX,startY+(fm.getHeight()+2)*cursorLine,fm.charWidth('o'),3);
//        else g.fillRect(startX+fm.stringWidth(lines.get(cursorLine).substring(0,cursorCharIndex))+fm.charWidth('o'),startY+(fm.getHeight()+2)*cursorLine,fm.charWidth(lines.get(cursorLine).charAt(cursorCharIndex)),3);
        if (lines.get(cursorLine).length()==0 || cursorCharIndex==-1) g.fillRect(startX,startY+(fm.getHeight()+2)*(cursorLine)-fm.getHeight()+6,1,fm.getHeight()-2);
        else g.fillRect(startX+fm.stringWidth(lines.get(cursorLine).substring(0,cursorCharIndex))+fm.charWidth('o'),startY+(fm.getHeight()+2)*(cursorLine)-fm.getHeight()+6,1,fm.getHeight()-2);

        bs.show();
    }

}

class Node{
    int val;
    long time;
    public Node(int val, long time){
        this.val = val;
        this.time = time;
    }
}
