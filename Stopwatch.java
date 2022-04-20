import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Stopwatch implements ActionListener{
    JFrame frame = new JFrame();
    JButton startButton = new JButton("START");
    JButton resetButton = new JButton("RESET");
    JButton lap = new JButton("LAP");
    JLabel timeLabel = new JLabel();
    JLabel laps = new JLabel();
    int elapsedTime=0;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    int i=0;
    String s="";
    boolean started = false;
    String seconds_string = String.format("%02d",seconds);
    String minutes_string = String.format("%02d", minutes);
    String hours_string = String.format("%02d", hours);

    Timer timer = new Timer(1000, new ActionListener(){
        public void actionPerformed(ActionEvent e){
            elapsedTime+=1000;
            hours=(elapsedTime/3600000);
            minutes=(elapsedTime/60000)%60;
            seconds=(elapsedTime/1000)%60;
            seconds_string = String.format("%02d",seconds);
            minutes_string = String.format("%02d", minutes);
            hours_string = String.format("%02d", hours);
            timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
        }
    });

    Stopwatch(){
        timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
        timeLabel.setBounds(100,100,200,100);
        timeLabel.setFont(new Font("Verdana",Font.PLAIN,35));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);

        laps.setBounds(100,300,200,100);
        laps.setFont(new Font("Verdana",Font.PLAIN,10));
        laps.setBorder(BorderFactory.createBevelBorder(1));
        laps.setOpaque(true);
        laps.setHorizontalAlignment(JTextField.CENTER);

        startButton.setBounds(100,200,100,50);
        startButton.setFont(new Font("Verdana",Font.PLAIN,20));
        startButton.setFocusable(false);
        startButton.addActionListener(this);
        
        resetButton.setBounds(200,200,100,50);
        resetButton.setFont(new Font("Verdana",Font.PLAIN,20));
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        lap.setBounds(100,250,200,50);
        lap.setFont(new Font("Verdana",Font.PLAIN,20));
        lap.setFocusable(false);
        lap.addActionListener(this);

        frame.add(timeLabel);
        frame.add(startButton);
        frame.add(resetButton);
        frame.add(lap);
        frame.add(laps);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650,650);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==startButton){
            start();
        }
        if(e.getSource()==resetButton)
            reset();
        if(e.getSource()==lap){
            s = (++i) + ": "+hours_string+":"+minutes_string+":"+seconds_string+" ";
            // s+="<html></br></html>";
            laps.setText(s);
        }
    }

    void start(){
        if(started==false){
            started=true;
            startButton.setText("STOP");
            timer.start();
        }else{
            started=false;
            startButton.setText("START");
            stop();
        }

    }

    void stop(){
        timer.stop();
    }

    void reset(){
        timer.stop();
        startButton.setText("START");
        elapsedTime=0;
        seconds = 0;
        minutes = 0;
        hours = 0;
        started = false;
        seconds_string = String.format("%02d",seconds);
        minutes_string = String.format("%02d", minutes);
        hours_string = String.format("%02d", hours);
        timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
        laps.setText("");
    }
    
}