package io.vincentwinner.toolset.image.testui.menuitem.filter.stylize;

import io.vincentwinner.toolset.image.filter.stylize.EdgeExtract;
import io.vincentwinner.toolset.image.testui.ImageViewPanel;
import io.vincentwinner.toolset.image.testui.TestFrame;
import io.vincentwinner.toolset.image.testui.Util;
import org.bytedeco.opencv.opencv_core.Mat;

import javax.swing.*;

public class EdgeExtractMenuItems {

    private static final ImageViewPanel panel = TestFrame.contentPanel;

    public static class LaplacianEdgeExtractMenuItem extends JMenuItem {
        public LaplacianEdgeExtractMenuItem(){
            setText("拉普拉斯");
            addActionListener(listener -> {
                Mat laplace = EdgeExtract.laplacian(Util.bufferedImageToMat(panel.getImage()),3);
                panel.setImage(Util.matToBufferedImage(laplace));
                panel.repaint();
            });
        }
    }

    public static class SobelEdgeExtractMenuItem extends JMenuItem {
        public SobelEdgeExtractMenuItem(){
            setText("索贝尔");
            addActionListener(listener -> {
                Mat sobel = EdgeExtract.sobel(Util.bufferedImageToMat(panel.getImage()),3);
                panel.setImage(Util.matToBufferedImage(sobel));
                panel.repaint();
            });
        }
    }

}
