package io.vincentwinner.toolset.image.testui.menuitem.filter.stylize;

import io.vincentwinner.toolset.image.filter.stylize.OilPainting;
import io.vincentwinner.toolset.image.testui.ImageViewPanel;
import io.vincentwinner.toolset.image.testui.TestFrame;
import org.bytedeco.opencv.opencv_core.Mat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class OilPaintingMenuItem extends JMenuItem {

    private static class OptionDialog extends JDialog{

        private static int size = 1;
        private static int dynRatio = 1;
        private final JLabel sizeLabel = new JLabel("半径: " + size);
        private final JSlider sizeSlider = new JSlider(JSlider.HORIZONTAL, 1, 10, 1);
        private final JLabel dnyRatioLabel = new JLabel("dyn比率: " + dynRatio);
        private final JSlider dynRatioSlider = new JSlider(JSlider.HORIZONTAL,1,10,1);

        private static final ImageViewPanel panel = TestFrame.contentPanel;

        @SuppressWarnings("all")
        public OptionDialog(){
            setTitle("油画效果参数");
            setLayout(new GridLayout(4,1));
            setStyle(sizeSlider);
            setStyle(dynRatioSlider);
            sizeSlider.addChangeListener(listener -> {
                size = sizeSlider.getValue();
                sizeLabel.setText("半径: " + size);
                activeImage();
            });
            dynRatioSlider.addChangeListener(listener -> {
                dynRatio = dynRatioSlider.getValue();
                dnyRatioLabel.setText("dyn比率: " + dynRatio);
                activeImage();
            });
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowOpened(WindowEvent e) {
                    panel.setBufferedMat(panel.getImageMat());
                    sizeSlider.setValue(1);
                    dynRatioSlider.setValue(1);
                }
                @Override
                public void windowClosing(WindowEvent e) {
                    System.gc();
                }
                @Override
                public void windowClosed(WindowEvent e) {
                    Mat bufferedMat = panel.getBufferedMat();
                    if(bufferedMat != null) bufferedMat.release();
                }
            });
            add(sizeLabel);
            add(sizeSlider);
            add(dnyRatioLabel);
            add(dynRatioSlider);
            setBounds(500,120,600,400);
            setVisible(true);
            setAlwaysOnTop(true);
        }

        private static void setStyle(JSlider slider){
            slider.setMajorTickSpacing(1);
            slider.setSnapToTicks(true);
            slider.setPaintTicks(true);
            slider.setPaintLabels(true);
        }

        private static void activeImage(){
            if(panel.getBufferedMat() != null){
                Mat src = panel.getBufferedMat();
                Mat mat = OilPainting.oilPainting(src, size, dynRatio);
                panel.setImageMat(mat);
                src.release();
                mat.release();
                panel.repaint();
            }
        }
    }

    public OilPaintingMenuItem(){
        setText("油画");
        addActionListener(listener -> {
            new OptionDialog();
        });
    }

}
