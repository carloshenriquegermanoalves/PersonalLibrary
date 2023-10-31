package org.br.ufpb.dcx.carlos.personalLibrary.view;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {

    private Image backgroundImage;

    public ImagePanel(String fileName) {
        backgroundImage = new ImageIcon(fileName).getImage();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
    }
}
