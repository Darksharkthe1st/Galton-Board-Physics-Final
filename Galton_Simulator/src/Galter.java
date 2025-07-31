package src;


import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Galter {
    final static JFrame mainframe = new JFrame();
    final static JFrame optionFrame = new JFrame();
    public static int delay = 20;
    public static int wait = 0;
    public static int width, height, realWidth;

    public static void main(String[] args) throws Exception {
        // mainframe.setSize(1920, 800);
        mainframe.setResizable(true);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Galter.width = 720; // requestInt("What width is your screen (in pixels)?", 800, 3840);
        Galter.height = 960; // requestInt("What height is your screen? (in pixels)", 800, 1280);
        Galter.realWidth = Galter.width - 16; // Actual width of the window

        // We run a thread for the Graphics.
        new Thread() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mainframe.repaint();
                    optionFrame.repaint();
                }
            }
        }.start();

        setupOptions();

        // Code used for the main action:
        do {
            mainCode();
            System.out.println("BRO");
        } while (wait == 1);
        System.out.println("DONE");
        System.exit(0);
    }

    public static void setupOptions() {
        optionFrame.setTitle("Control Options");
        optionFrame.setSize(500, 500);
        optionFrame.setLocationRelativeTo(null);
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        int compIndex = 0;
        JSlider speedSlider = new JSlider(SwingConstants.HORIZONTAL, 50, 400, 380);

        speedSlider.setMajorTickSpacing(50);
        speedSlider.setMinorTickSpacing(25);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        
        speedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Galter.delay = 400 - speedSlider.getValue();
            }
        });

        JLabel startingLabel = new JLabel("Starting X Position (50-" + (Galter.width - 50) + "):");
        JSlider startingSlider = new JSlider(SwingConstants.HORIZONTAL, 50, Galter.width - 50, 720/2-20);

        startingSlider.setMajorTickSpacing(50);
        startingSlider.setMinorTickSpacing(25);
        startingSlider.setPaintTicks(true);
        startingSlider.setPaintLabels(true);
        
        startingSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Galton.startingX = startingSlider.getValue();
            }
        });
        
        JLabel variationLabel = new JLabel("Variation (20-400):");
        JSlider variationSlider = new JSlider(SwingConstants.HORIZONTAL, 20, 400, 50);

        variationSlider.setMajorTickSpacing(50);
        variationSlider.setMinorTickSpacing(25);
        variationSlider.setPaintTicks(true);
        variationSlider.setPaintLabels(true);
        
        variationSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Galton.variation = variationSlider.getValue();
                startingLabel.setText("Starting X Position (" + variationSlider.getValue() + "-" + (Galter.width - variationSlider.getValue()) + "):");
                startingSlider.setMinimum(variationSlider.getValue());
                startingSlider.setMaximum(Galter.width - variationSlider.getValue());
            }
        });

        JLabel biasLabel = new JLabel("Horizontal Bias (-1 to 1):");
        JSlider biasSlider = new JSlider(SwingConstants.HORIZONTAL, -100, 100, 0);

        biasSlider.setMajorTickSpacing(50);
        biasSlider.setMinorTickSpacing(25);
        biasSlider.setPaintTicks(true);
        biasSlider.setPaintLabels(true);
        
        biasSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Ball.xBias = biasSlider.getValue() / 100.0;
            }
        });

        
        

        JButton stopButton = new JButton();
		stopButton.setText("Continue");
        stopButton.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // TODO Auto-generated method stub
				if (stopButton.getText().equals("Stop")) {
					stopButton.setText("Continue");
					Galton.paused = !Galton.paused;
				} else if (stopButton.getText().equals("Continue")) {
					stopButton.setText("Stop");
					Galton.paused = !Galton.paused;
				}
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {}

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {}

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {}

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {}
			
		});

        JButton replayButton = new JButton();
		replayButton.setText("Replay");
        replayButton.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                wait = 1;
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {}

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {}

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {}

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {}
			
		});

        p.add(new JLabel("Speed (50-400):"), compIndex++);
        p.add(speedSlider, compIndex++);
        p.add(variationLabel, compIndex++);
        p.add(variationSlider, compIndex++);
        p.add(startingLabel, compIndex++);
        p.add(startingSlider, compIndex++);
        p.add(biasLabel, compIndex++);
        p.add(biasSlider, compIndex++);
        p.add(stopButton, compIndex++);
        p.add(replayButton, compIndex++);
        

        optionFrame.add(p);
        optionFrame.setVisible(false);

        System.out.println("HI");

    }

    public static void mainCode() throws InterruptedException {
        mainframe.setVisible(false);

        mainframe.setSize(Galter.width, Galter.height);
        System.out.println(Galter.width + ", " + Galter.height);
        Galton.totalBalls = requestInt("How many balls would you like to simulate?", 1, 40000000);
        

        // Make the Galton Board
        Galton gobj = new Galton();
        mainframe.add(gobj); // Put it into the java graphics machine
        mainframe.setVisible(true);

        // 0 represents keep going
        wait = 0;

        optionFrame.setVisible(true);

        // We run one thread for the action
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                gobj.begin(); // Begin the simulation
                // 1 represents repeat, 2 represents stop
                // wait = (JOptionPane.showConfirmDialog(mainframe, "Go again?", "Replay",
                // JOptionPane.YES_NO_OPTION) == 0) ? 1 : 2;
            }
        }.start();

        while (wait == 0) {
            Thread.sleep(10);
        }
        
        //Reset the pillars
        Ball.reset();

        // Remove the finished graphics
        mainframe.remove(gobj);
    }

    // Start of recursive file hunter
    // Made to find files no matter where they are in project directory
    public static File fileHunt(String filename) {
        File dir = new File(new File("").getAbsolutePath() + "\\");
        return recurFileHunt(dir.getAbsolutePath(), filename);
    }

    // Asks user for an integer value with message, int range inclusive, inclusive
    public static int requestInt(String message, int min, int max) {
        int output;
        String answer = "";
        while (true) {
            answer = JOptionPane.showInputDialog(mainframe, message);
            try {
                output = Integer.valueOf(answer);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(mainframe, "You must input an integer.", "ERROR!",
                        JOptionPane.ERROR_MESSAGE);
                continue;
            }
            if (output >= min && output <= max) {
                break;
            } else {
                if (output < min)
                    JOptionPane.showMessageDialog(mainframe,
                            "Your number is way too small! Make sure it's >= to " + min, "ERROR!",
                            JOptionPane.ERROR_MESSAGE);
                else
                    JOptionPane.showMessageDialog(mainframe, "Your number is way too big! Make sure it's <= to " + max,
                            "ERROR!", JOptionPane.ERROR_MESSAGE);
            }
        }
        return output;
    }

    // Recursively goes through folders to find the file
    public static File recurFileHunt(String path, String filename) {
        // Get the list of files in the directory
        File[] files = new File(path).listFiles();

        // Check if it's there
        for (File f : files) {
            // Return if found
            if (!f.isDirectory() && f.getName().equals(filename))
                return f;
        }

        // Go through the directories, recursively search them
        for (File f : files) {
            // Only go through a directory if actually a directory
            if (f.isDirectory()) {
                // Hunt recursively
                File result = recurFileHunt(f.getAbsolutePath() + "\\", filename);
                if (result != null)
                    return result;// The only way it's not null is if we found it!
            }
        }

        return null;
    }
}