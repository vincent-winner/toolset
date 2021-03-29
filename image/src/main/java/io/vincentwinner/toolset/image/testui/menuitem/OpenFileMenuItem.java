package io.vincentwinner.toolset.image.testui.menuitem;

import io.vincentwinner.toolset.image.testui.ImageViewPanel;
import io.vincentwinner.toolset.image.testui.TestFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 打开文件菜单
 */
public class OpenFileMenuItem extends JMenuItem {

    private final JFileChooser fileChooser = new JFileChooser(new File("."));

    public OpenFileMenuItem() {
        setText("打开");
        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                String fileName = f.getName().toLowerCase();
                return f.isDirectory() || fileName.endsWith(".jpg") || fileName.endsWith(".png");
            }

            @Override
            public String getDescription() {
                return "*.png *.jpg";
            }
        });
        addActionListener(listener -> {
            ImageViewPanel contentPanel = TestFrame.contentPanel;
            try {
                int status = fileChooser.showOpenDialog(this);
                if( status == JFileChooser.APPROVE_OPTION ){
                    File imgFile = fileChooser.getSelectedFile();
                    BufferedImage image = ImageIO.read(imgFile);
                    contentPanel.setImage(image);
                    contentPanel.repaint();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
