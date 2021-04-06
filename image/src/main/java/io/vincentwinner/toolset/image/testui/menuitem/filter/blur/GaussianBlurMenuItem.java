package io.vincentwinner.toolset.image.testui.menuitem.filter.blur;

import io.vincentwinner.toolset.image.filter.blur.GaussianBlur;
import io.vincentwinner.toolset.image.testui.ImageViewPanel;
import io.vincentwinner.toolset.image.testui.TestFrame;
import org.bytedeco.opencv.opencv_core.Mat;

import javax.swing.*;
import java.awt.*;

public class GaussianBlurMenuItem extends JMenuItem {

    private static class OptionDialog extends JDialog {

        private static int sigma = 1;

        private final JLabel kernelWidthLabel = new JLabel("σ值:" + sigma);
        private final JSlider kernelWidthSlider = new JSlider(JSlider.HORIZONTAL, 1, 31, sigma);

        private static final ImageViewPanel panel = TestFrame.contentPanel;

        @SuppressWarnings("all")
        public OptionDialog() {
            setTitle("参数");
            setLayout(new GridLayout(2,1));
            setStyle(kernelWidthSlider);
            kernelWidthSlider.addChangeListener(listener -> {
                sigma = kernelWidthSlider.getValue();
                kernelWidthLabel.setText("σ值:" + sigma);
                activeImage();
            });
            add(kernelWidthLabel);
            add(kernelWidthSlider);
            setBounds(500,120,600,150);
            setVisible(true);
            setAlwaysOnTop(true);
        }

        private static void setStyle(JSlider slider){
            slider.setMajorTickSpacing(5);
            slider.setMinorTickSpacing(1);
            slider.setSnapToTicks(true);
            slider.setPaintTicks(true);
            slider.setPaintLabels(true);
        }

        private static void activeImage(){
            if(panel.getInitImage() != null){
                Mat src = panel.getInitImageMat();
                Mat mat = GaussianBlur.gaussianConvolution(src, sigma);
                panel.setImageMat(mat);
                src.release();
                mat.release();
                panel.repaint();
            }
        }

    }

    public GaussianBlurMenuItem() {
        setText("高斯模糊");
        addActionListener(listener -> {
            new OptionDialog();
        });
    }
}
