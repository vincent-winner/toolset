package io.vincentwinner.toolset.image.testui.menuitem.filter.stylize;

import io.vincentwinner.toolset.image.filter.stylize.EdgeExtract;
import io.vincentwinner.toolset.image.testui.ImageViewPanel;
import io.vincentwinner.toolset.image.testui.TestFrame;
import org.bytedeco.opencv.opencv_core.Mat;

import javax.swing.*;
import java.awt.*;

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

    public static class CannyEdgeExtractMenuItem extends JMenuItem{
        private static class OptionDialog extends JDialog{
            private final JCheckBox l2Gradient = new JCheckBox("使用L2范数（更精确）");
            private final JTextField thresholdLow = new JTextField("低阈值（浮点数默认50）");
            private final JTextField thresholdHigh = new JTextField("高阈值(浮点数默认150)");
            private final JLabel kernelSizeLabel = new JLabel("卷积核大小（默认值 3）：");
            private final JComboBox<Integer> kernelSize = new JComboBox<Integer>(new Integer[]{1,3,5,7,9});
            private final JButton okButton = new JButton("执行");
            private final ImageViewPanel panel = TestFrame.contentPanel;
            private OptionDialog(){
                setTitle("精确边缘检测参数");
                setLayout(new GridLayout(3,3));
                okButton.addActionListener(listener -> {
                    double thresholdLowValue;
                    double thresholdHighValue;
                    int kernelSizeValue;
                    boolean l2GradientValue;
                    try{
                        thresholdLowValue = Double.parseDouble(thresholdLow.getText());
                        thresholdHighValue = Double.parseDouble(thresholdHigh.getText());
                        kernelSizeValue = kernelSize.getItemAt(kernelSize.getSelectedIndex());
                        l2GradientValue = l2Gradient.isSelected();
                    }catch (NumberFormatException e){
                        thresholdLowValue = 50d;
                        thresholdHighValue = 150d;
                        kernelSizeValue = 3;
                        l2GradientValue = true;
                        JOptionPane.showMessageDialog(null,
                                "传入了非法参数，将使用默认值 [低阈值:50,高阈值:150,核心大小:3,使用L2范数:true]",
                                "参数错误",JOptionPane.WARNING_MESSAGE);
                    }
                    Mat src = panel.getImageMat();
                    Mat dst = EdgeExtract.canny(
                            src,
                            thresholdLowValue,
                            thresholdHighValue,
                            kernelSizeValue,
                            l2GradientValue
                    );
                    panel.setImageMat(dst);
                    src.release();
                    dst.release();
                    panel.repaint();
                });
                l2Gradient.setSelected(true);
                kernelSize.setSelectedIndex(1);
                add(thresholdLow);
                add(thresholdHigh);
                add(kernelSizeLabel);
                add(kernelSize);
                add(l2Gradient);
                add(okButton);
                setBounds(500,120,380,120);
                setAlwaysOnTop(true);
                setVisible(true);
            }
        }
        public CannyEdgeExtractMenuItem(){
            setText("精确");
            addActionListener(listener -> {
                new OptionDialog();
            });
        }
    }

}
