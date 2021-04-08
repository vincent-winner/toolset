package io.vincentwinner.toolset.image.testui.menuitem;

import io.vincentwinner.toolset.image.testui.ImageViewPanel;
import io.vincentwinner.toolset.image.testui.TestFrame;
import org.bytedeco.opencv.opencv_core.Mat;

import javax.swing.*;

public class ReOpenMenuItem extends JMenuItem {

    private static final ImageViewPanel panel = TestFrame.contentPanel;

    public ReOpenMenuItem(){
        setText("重新打开(图像复位)");
        addActionListener(listener -> {
            Mat initImageMat = panel.getInitImageMat();
            if(initImageMat != null && !initImageMat.empty()) {
                panel.setImageMat(initImageMat);
                panel.releaseBuffer();
                initImageMat.release();
                System.gc();
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
