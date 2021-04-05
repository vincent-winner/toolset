package io.vincentwinner.toolset.image.testui.menuitem;

import io.vincentwinner.toolset.image.testui.ImageViewPanel;
import io.vincentwinner.toolset.image.testui.TestFrame;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class ReOpenMenuItem extends JMenuItem {

    private static final ImageViewPanel panel = TestFrame.contentPanel;

    public ReOpenMenuItem(){
        setText("重新打开(图像复位)");
        addActionListener(listener -> {
            BufferedImage initImage = panel.getInitImage();
            if(initImage != null) {
                panel.setImage(panel.getInitImage());
                panel.repaint();
            } else{
                JOptionPane.showMessageDialog(
                        null,
                        "未有任何图像为打开状态",
                        "非法操作",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }

}
