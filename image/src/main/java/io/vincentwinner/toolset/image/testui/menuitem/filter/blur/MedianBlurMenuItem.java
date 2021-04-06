package io.vincentwinner.toolset.image.testui.menuitem.filter.blur;

import io.vincentwinner.toolset.image.filter.blur.MedianBlur;
import io.vincentwinner.toolset.image.testui.ImageViewPanel;
import io.vincentwinner.toolset.image.testui.TestFrame;
import org.bytedeco.opencv.opencv_core.Mat;

import javax.swing.*;
import java.awt.*;

public class MedianBlurMenuItem extends JMenuItem {

    private static class OptionDialog extends JDialog {

        private static int kernelSize = 1;

        private final JLabel kernelWidthLabel = new JLabel("卷积核大小:" + kernelSize);
        private final JSlider kernelWidthSlider = new JSlider(JSlider.HORIZONTAL, 1, 59, kernelSize);

        private static final ImageViewPanel panel = TestFrame.contentPanel;

        @SuppressWarnings("all")
        public OptionDialog() {
            setTitle("参数");
            setLayout(new GridLayout(2,1));
            setStyle(kernelWidthSlider);
            kernelWidthSlider.addChangeListener(listener -> {
                kernelSize = kernelWidthSlider.getValue();
                if((kernelSize & 1) == 1) kernelWidthLabel.setText("卷积核宽:" + kernelSize);
                activeImage();
            });
            add(kernelWidthLabel);
            add(kernelWidthSlider);
            setBounds(500,120,860,150);
            setVisible(true);
            setAlwaysOnTop(true);
        }

        private static void setStyle(JSlider slider){
            slider.setMajorTickSpacing(2);
            slider.setSnapToTicks(true);
            slider.setPaintTicks(true);
            slider.setPaintLabels(true);
        }

        private static void activeImage(){
            if( (kernelSize & 1) == 1 && panel.getInitImage() != null){
                Mat src = panel.getInitImageMat();
                Mat mat = MedianBlur.medianConvolution(src, kernelSize);
                panel.setImageMat(mat);
                src.release();
                mat.release();
                panel.repaint();
            }
        }

    }

    public MedianBlurMenuItem() {
        setText("中值模糊");
        addActionListener(listener -> {
            new OptionDialog();
        });
    }
}
