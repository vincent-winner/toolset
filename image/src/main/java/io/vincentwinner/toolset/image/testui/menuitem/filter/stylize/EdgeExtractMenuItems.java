package io.vincentwinner.toolset.image.testui.menuitem.filter.stylize;

import io.vincentwinner.toolset.image.filter.stylize.EdgeExtract;
import io.vincentwinner.toolset.image.testui.ImageViewPanel;
import io.vincentwinner.toolset.image.testui.TestFrame;
import org.bytedeco.opencv.opencv_core.Mat;

import javax.swing.*;

public class EdgeExtractMenuItems {

    private static final ImageViewPanel panel = TestFrame.contentPanel;

    public static class LaplacianEdgeExtractMenuItem extends JMenuItem {
        public LaplacianEdgeExtractMenuItem(){
            setText("拉普拉斯");
            addActionListener(listener -> {
                Mat src = panel.getImageMat();
                Mat laplace = EdgeExtract.laplacian(src,3);
                panel.setImageMat(laplace);
                src.release();
                laplace.release();
                panel.repaint();
            });
        }
    }

    public static class SobelEdgeExtractMenuItem extends JMenuItem {
        public SobelEdgeExtractMenuItem(){
            setText("索贝尔");
            addActionListener(listener -> {
                Mat src = panel.getImageMat();
                Mat sobel = EdgeExtract.sobel(src,3);
                panel.setImageMat(sobel);
                src.release();
                sobel.release();
                panel.repaint();
            });
        }
    }

}
