package io.vincentwinner.toolset.image.testui.menuitem;

import io.vincentwinner.toolset.image.testui.ImageViewPanel;
import io.vincentwinner.toolset.image.testui.TestFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SaveFileMenuItem extends JMenuItem {

    private final JFileChooser fileChooser = new JFileChooser(new File("."));

    public SaveFileMenuItem(){
        setText("保存");
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
                int status = fileChooser.showSaveDialog(this);
                if( status == JFileChooser.APPROVE_OPTION ){
                    File imgFile = fileChooser.getSelectedFile();
                    BufferedImage image = contentPanel.getImage();
                    if(image != null && image.getWidth() != 0 && image.getHeight() != 0) {
                        ImageIO.write(image, "png", imgFile);
                    }else {
                        JOptionPane.showMessageDialog(null,"没有打开任何图片！");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }



}
