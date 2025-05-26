import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GaltonDriver {
    final static JFrame mainframe = new JFrame();
    final static int delay = 20;
    public static int wait = 0;
    public static int width, height;
    public static void main(String[] args) throws Exception {
        System.out.println("Hi, Kate!!");        
        System.out.println("begin");
        mainframe.setSize(1920, 800);
        mainframe.setResizable(false);
        
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GaltonDriver.width = 960; //requestInt("What width is your screen (in pixels)?", 800, 3840);
        GaltonDriver.height = 1000; //requestInt("What height is your screen? (in pixels)", 800, 1280);

        //We run a thread for the Graphics.
        new Thread() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mainframe.repaint();
                }
            }
        }.start();

        //Code used for sorting
        do {
            mainCode();
            System.out.println("BRO");
        } while (wait == 1);
        System.out.println("DONE");
        System.exit(0);
    }

    public static void mainCode() throws InterruptedException {
        mainframe.setVisible(false);

        mainframe.setSize(width, height);


        // Lilypad lily = Lilypad.drawMap(new Pointy(0, 0));
        Galton gobj = new Galton();
        mainframe.add(gobj);

        mainframe.setVisible(true);

        //0 represents keep going
        wait = 0;

        //We run one thread for the sort
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                gobj.begin();
                //1 represents repeat, 2 represents stop
                // wait = (JOptionPane.showConfirmDialog(mainframe, "Go again?", "Replay", JOptionPane.YES_NO_OPTION) == 0) ? 1 : 2;
                
            }
        }.start();

        while (wait == 0) {
            Thread.sleep(10);
        }

        //Remove the finished graphics
        mainframe.remove(gobj);
    }

    // Start of recursive file hunter
    // Made to find files no matter where they are in project directory
    public static File fileHunt(String filename) {
        // System.out.println();
        // System.out.println("UP");
        // System.out.println(dir.getAbsolutePath());

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
                JOptionPane.showMessageDialog(mainframe, "You must input an integer.","ERROR!",JOptionPane.ERROR_MESSAGE);
                continue;
            }
            if (output >= min && output <= max) {
                break;
            } else {
                if (output < min)
                    JOptionPane.showMessageDialog(mainframe, "Your number is way too small! Make sure it's >= to " + min,"ERROR!",JOptionPane.ERROR_MESSAGE);
                else
                    JOptionPane.showMessageDialog(mainframe, "Your number is way too big! Make sure it's <= to " + max,"ERROR!",JOptionPane.ERROR_MESSAGE);
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