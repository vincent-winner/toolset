package io.vincentwinner.toolset.image.testui.menuitem.filter;

import io.vincentwinner.toolset.image.filter.CustomConvolutionFilter;
import io.vincentwinner.toolset.image.testui.ImageViewPanel;
import io.vincentwinner.toolset.image.testui.TestFrame;
import io.vincentwinner.toolset.image.testui.Util;
import org.bytedeco.opencv.opencv_core.Mat;

import javax.swing.*;
import java.awt.*;

public class CustomConvolutionFilterMenuItem extends JMenuItem {

    private static class PreOptionDialog extends JDialog {

        private final JTextField widthField = new JTextField();
        private final JTextField heightField = new JTextField();

        private final JButton okButton = new JButton("确定");

        public PreOptionDialog(){
            setTitle("卷积核参数");
            JLabel widthLabel = new JLabel("核宽:");
            JLabel heightLabel = new JLabel("核高:");
            setLayout(new GridLayout(1,5));
            add(widthLabel);
            add(widthField);
            add(heightLabel);
            add(heightField);
            add(okButton);
            okButton.addActionListener(listener -> {
                String widthStr = widthField.getText();
                String heightStr = heightField.getText();
                try {
                    int width = Integer.parseInt(widthStr);
                    int height = Integer.parseInt(heightStr);
                    if( width > 1 && width <= 10 && (width & 1) == 1 &&
                            height > 1 && height <= 10 && (height & 1) == 1
                    ){
                        new OptionDialog(height,width);
                        this.dispose();
                    }else {
                        JOptionPane.showMessageDialog(null,"数字大小必须为10以内奇数，且大于1！","非法输入", JOptionPane.ERROR_MESSAGE);
                    }
                }catch (NumberFormatException e){
                    JOptionPane.showMessageDialog(null,"请输入正确的整数！","非法输入", JOptionPane.ERROR_MESSAGE);
                }
            });
            setBounds(500,120,600,80);
            setVisible(true);
        }

    }

    private static class OptionDialog extends JDialog {

        private static final ImageViewPanel panel = TestFrame.contentPanel;
        private static final JPanel fieldPanel = new JPanel();
        private static final JButton activeButton = new JButton("执行");
        private static int kernelSize;
        private static double[] kernel;

        @SuppressWarnings("all")
        public OptionDialog(int rows,int cols) {
            setTitle("自定义卷积核");
            fieldPanel.setLayout(new GridLayout(rows,cols));
            fieldPanel.setSize(new Dimension(600,400));
            setLayout(new BorderLayout());
            add(fieldPanel,BorderLayout.CENTER);
            kernelSize = rows * cols;
            kernel = new double[kernelSize];
            fieldPanel.removeAll();
            for (int i = 0; i < kernelSize; i++) {
                JTextField field = new JTextField();
                fieldPanel.add(field);
            }
            activeButton.addActionListener(listener -> {
                try{
                    for (int i = 0; i < kernelSize; i++) {
                        String numStr = ((JTextField)fieldPanel.getComponent(i)).getText();
                        double num = numStr == null || "".equals(numStr) ? 0 : Double.parseDouble(numStr);
                        kernel[i] = num;
                    }
                    activeImage();
                }catch (NumberFormatException e){
                    JOptionPane.showMessageDialog(null,"卷积核项须为浮点数或整数（无论正负）","非法输入",JOptionPane.ERROR_MESSAGE);
                }
            });
            add(activeButton,BorderLayout.SOUTH);
            setBounds(500,120,600,480);
            setAlwaysOnTop(true);
            setVisible(true);
        }

        private static void activeImage(){
            if(panel.getInitImage() != null && kernel != null){
                Mat mat = CustomConvolutionFilter.customConvolution(Util.bufferedImageToMat(panel.getInitImage()), kernel);
                panel.setImage(Util.matToBufferedImage(mat));
                panel.repaint();
            }
        }

    }

    public CustomConvolutionFilterMenuItem() {
        setText("自定义卷积滤镜");
        addActionListener(listener -> {
            new PreOptionDialog();
        });
    }

}
