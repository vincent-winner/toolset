package io.vincentwinner.toolset.image.testui.menuitem.filter;

import io.vincentwinner.toolset.image.filter.TranslateColorFilter;
import io.vincentwinner.toolset.image.testui.ImageViewPanel;
import io.vincentwinner.toolset.image.testui.TestFrame;
import org.bytedeco.opencv.opencv_core.Mat;

import javax.swing.*;

public class TranslateColorFilterMenuItems {

    private static final ImageViewPanel panel = TestFrame.contentPanel;

    public static class InverseColorMenuItem extends JMenuItem{
        public InverseColorMenuItem(){
            setText("反色");
            addActionListener(listener -> {
                Mat src = panel.getImageMat();
                Mat dst = TranslateColorFilter.inverseColor(src);
                panel.setImageMat(dst);
                src.release();
                dst.release();
                panel.repaint();
            });
        }
    }

}
