import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame implements ActionListener {
    JFrame frame=new JFrame();
    JFrame frame1=new JFrame();
    JButton button1=new JButton("Play");
    JButton button2=new JButton("Exit");

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button1){
            frame.dispose();
            frame1.add(new App());
            frame1.setTitle("Snake");
            frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame1.setResizable(false);
            frame1.pack();
            frame1.setVisible(true);
            frame1.setLocationRelativeTo(null);
        }
        if(e.getSource()==button2){
            System.exit(0);
        }
    }
    Frame(){
        button1.setBounds(110,110,200,40);
        button1.setFocusable(false);
        button1.addActionListener(this);

        button2.setBounds(110,230,200,40);
        button2.setFocusable(false);
        button2.addActionListener(this);

        frame.add(button1);
        frame.add(button2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }
}
