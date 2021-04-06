package io.vincentwinner.toolset.image.testui.menuitem.filter.blur;

import io.vincentwinner.toolset.image.filter.blur.AverageBlur;
import io.vincentwinner.toolset.image.testui.ImageViewPanel;
import io.vincentwinner.toolset.image.testui.TestFrame;
import org.bytedeco.opencv.opencv_core.Mat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AverageBlurMenuItem extends JMenuItem {

    private static class OptionDialog extends JDialog {

        private static int kernelWidth = 1;
        private static int kernelHeight = 1;

        private final JLabel kernelWidthLabel = new JLabel("卷积核宽:" + kernelWidth);
        private final JSlider kernelWidthSlider = new JSlider(JSlider.HORIZONTAL, 1, 79, kernelWidth);
        private final JLabel kernelHeightLabel = new JLabel("卷积核高:" + kernelHeight);
        private final JSlider kernelHeightSlider = new JSlider(JSlider.HORIZONTAL, 1, 79, kernelHeight);

        private static final ImageViewPanel panel = TestFrame.contentPanel;

        private static Mat bufferedMat;

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
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    super.windowClosed(e);
                    if(bufferedMat != null) bufferedMat.release();
                }
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
                Mat src = panel.getImageMat();
                Mat mat = AverageBlur.averageConvolution(src, kernelWidth, kernelHeight);
                panel.setImageMat(mat);
                src.release();
                mat.release();
                panel.repaint();
            }
        }

    }

    public AverageBlurMenuItem() {
        setText("均值模糊");
        addActionListener(listener -> {
            new OptionDialog();
        });
    }

}
