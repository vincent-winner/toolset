package io.vincentwinner.toolset.image.testui.menuitem.filter.blur;

import io.vincentwinner.toolset.image.DDepth;
import io.vincentwinner.toolset.image.filter.blur.BoxBlur;
import io.vincentwinner.toolset.image.testui.ImageViewPanel;
import io.vincentwinner.toolset.image.testui.TestFrame;
import io.vincentwinner.toolset.image.testui.Util;
import org.bytedeco.opencv.opencv_core.Mat;

import javax.swing.*;
import java.awt.*;

public class BoxBlurMenuItem extends JMenuItem {

    private static class OptionDialog extends JDialog {

        private static int kernelWidth = 1;
        private static int kernelHeight = 1;

        private final JLabel kernelWidthLabel = new JLabel("卷积核宽:" + kernelWidth);
        private final JSlider kernelWidthSlider = new JSlider(JSlider.HORIZONTAL, 1, 79, kernelWidth);
        private final JLabel kernelHeightLabel = new JLabel("卷积核高:" + kernelHeight);
        private final JSlider kernelHeightSlider = new JSlider(JSlider.HORIZONTAL, 1, 79, kernelHeight);

        private static final ImageViewPanel panel = TestFrame.contentPanel;

        @SuppressWarnings("all")
        public OptionDialog() {
            setTitle("参数");
            setLayout(new GridLayout(4,1));
            setStyle(kernelWidthSlider);
            setStyle(kernelHeightSlider);
            kernelWidthSlider.addChangeListener(listener -> {
                kernelWidth = kernelWidthSlider.getValue();
                kernelWidthLabel.setText("卷积核宽:" + kernelWidth);
                activeImage();
            });
            kernelHeightSlider.addChangeListener(listener -> {
                kernelHeight = kernelHeightSlider.getValue();
                kernelHeightLabel.setText("卷积核高:" + kernelHeight);
                activeImage();
            });
            add(kernelWidthLabel);
            add(kernelWidthSlider);
            add(kernelHeightLabel);
            add(kernelHeightSlider);
            setBounds(500,120,600,400);
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
                Mat mat = BoxBlur.boxConvolution(Util.bufferedImageToMat(panel.getInitImage()), DDepth.ORIGINAL, kernelWidth, kernelHeight);
                panel.setImage(Util.matToBufferedImage(mat));
                panel.repaint();
            }
        }

    }

    public BoxBlurMenuItem() {
        setText("方框模糊");
        addActionListener(listener -> {
            new OptionDialog();
        });
    }
}
