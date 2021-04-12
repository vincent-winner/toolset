package io.vincentwinner.toolset.image.testui;

import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.Mat;

import javax.swing.*;
import java.awt.*;

public class TestFrame extends JFrame {

    public static ImageViewPanel contentPanel = new ImageViewPanel();

    public TestFrame() {
        try {
            javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch	(Exception e) {
            e.printStackTrace();
        }
        setBounds(550,120,800,800);
        setLayout(new BorderLayout());
        add(new EffectMenuBar(),BorderLayout.NORTH);
        add(contentPanel);
        setTitle("图像处理可视化测试界面");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public TestFrame(String initFileName){
        this();
        try{
            Mat src = opencv_imgcodecs.imread(initFileName);
            Mat dst = new Mat();
            opencv_imgproc.Laplacian(src,dst,-1);
            src.release();
            dst.release();
        }catch (Exception e){}
    }

}
