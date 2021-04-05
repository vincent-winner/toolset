package io.vincentwinner.toolset.image.testui.menuitem.filter;

import io.vincentwinner.toolset.image.filter.TranslateColorFilter;
import io.vincentwinner.toolset.image.testui.ImageViewPanel;
import io.vincentwinner.toolset.image.testui.TestFrame;
import io.vincentwinner.toolset.image.testui.Util;
import org.bytedeco.javacv.Java2DFrameUtils;
import org.bytedeco.opencv.opencv_core.Mat;

import javax.swing.*;

public class TranslateColorFilterMenuItems {

    private static final ImageViewPanel panel = TestFrame.contentPanel;

    public static class InverseColorMenuItem extends JMenuItem{
        public InverseColorMenuItem(){
            setText("反色");
            addActionListener(listener -> {
                Mat dst = TranslateColorFilter.inverseColor(Util.bufferedImageToMat(panel.getImage()));
                panel.setImage(Java2DFrameUtils.toBufferedImage(dst));
                panel.repaint();
            });
        }
    }

}
