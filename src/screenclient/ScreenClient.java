/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package screenclient;

import javax.swing.JLabel;
import java.net.Socket;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.net.UnknownHostException;
import java.io.InputStream;

/**
 *
 * @author Administrator
 */
public class ScreenClient implements Runnable{

    JLabel imgLabel;
    /**
     * @param args the command line arguments
     */
    
    public ScreenClient(JLabel _imgLabel) {
        imgLabel = _imgLabel;
    }
    
    public void run() {
        Boolean capturing = true;
        Socket server = null;
        InputStream input = null;
        try {
            server = new Socket("160.36.58.131", 5506);
            input = server.getInputStream();
        } catch (IOException e) {
            e.getMessage();
        }
        BufferedImage image = null;
        ImageIcon icon;
        while (capturing) {
            try {
                System.out.println("Reading...");
                image = ImageIO.read(input);
                if (image != null) {
                    System.out.println("It's not null!");
                    icon = new ImageIcon(image);
                    imgLabel.setIcon(icon);
                }
                else {
                    System.out.println("It's null!");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
