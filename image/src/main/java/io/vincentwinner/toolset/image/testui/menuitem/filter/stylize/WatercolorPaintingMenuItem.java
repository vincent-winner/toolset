package io.vincentwinner.toolset.image.testui.menuitem.filter.stylize;

import io.vincentwinner.toolset.image.filter.stylize.WatercolorPainting;
import io.vincentwinner.toolset.image.testui.ImageViewPanel;
import io.vincentwinner.toolset.image.testui.TestFrame;
import org.bytedeco.opencv.opencv_core.Mat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WatercolorPaintingMenuItem extends JMenuItem {

    private static class OptionDialog extends JDialog{

        private static float sigmaS = 1f;
        private static float sigmaR = 0f;
        private final JLabel sigmaSLabel = new JLabel("σS: ");
        private final JTextField sigmaSField = new JTextField();
        private final JLabel sigmaRLabel = new JLabel("σR: ");
        private final JTextField sigmaRField = new JTextField();
        private final JButton okButton = new JButton("执行");

        private static final ImageViewPanel panel = TestFrame.contentPanel;

        @SuppressWarnings("all")
        public OptionDialog(){
            setTitle("水彩效果参数");
            setLayout(new GridLayout(1,5));
            okButton.addActionListener(listener -> {
                try{
                    sigmaS = Float.parseFloat(sigmaSField.getText());
                    sigmaR = Float.parseFloat(sigmaRField.getText());
                    if(sigmaS < 0 || sigmaS > 200 || sigmaR < 0 || sigmaR > 1)
                        showMessage();
                    else
                        activeImage();
                }catch (NumberFormatException e){
                    showMessage();
                }
            });
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowOpened(WindowEvent e) {
                    panel.setBufferedMat(panel.getImageMat());
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
            add(sigmaSLabel);
            add(sigmaSField);
            add(sigmaRLabel);
            add(sigmaRField);
            add(okButton);
            setBounds(500,120,330,70);
            setVisible(true);
            setAlwaysOnTop(true);
        }

        private static void activeImage(){
            if(panel.getBufferedMat() != null){
                Mat src = panel.getBufferedMat();
                Mat mat = WatercolorPainting.waterColor(src, sigmaS, sigmaR);
                panel.setImageMat(mat);
                src.release();
                mat.release();
                panel.repaint();
            }
        }

        private static void showMessage(){
            JOptionPane.showMessageDialog(
                    null,
                    "参数范围（浮点数）：σS[0~200],σR[0~1]",
                    "非法参数",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    public WatercolorPaintingMenuItem(){
        setText("水彩");
        addActionListener(listener -> {
            new OptionDialog();
        });
    }

}
