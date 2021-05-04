package io.vincentwinner.toolset.ai.seeta6jni.frame;

import io.vincentwinner.toolset.ai.seeta6jni.structs.SeetaFaceInfo;
import io.vincentwinner.toolset.ai.seeta6jni.structs.SeetaPointF;
import io.vincentwinner.toolset.ai.seeta6jni.structs.SeetaRect;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.InputStream;

/**
 * 将被处理的文件和处理结果传入可直接查看绘制好的人脸识别位置
 */
public class ImageFrame extends JFrame {

    public ImageFrame(InputStream picStream, SeetaFaceInfo[] faceInfos){
        SeetaRect[] rects = new SeetaRect[faceInfos.length];
        for (int i = 0; i < faceInfos.length; i++) {
            rects[i] = new SeetaRect(
                    faceInfos[i].getX(),
                    faceInfos[i].getY(),
                    faceInfos[i].getWidth(),
                    faceInfos[i].getHeight()
            );
        }
        setTitle("Result");
            ImagePanel panel = new ImagePanel(picStream,rects);
            add(panel);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                e.getWindow().dispose();
            }
        });
        this.pack();
        this.setVisible(true);
    }

    public ImageFrame(InputStream picStream, SeetaPointF[] points){
        setTitle("Result");
        ImagePanel panel = new ImagePanel(picStream,points);
        add(panel);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                e.getWindow().dispose();
            }
        });
        this.pack();
        this.setVisible(true);
    }

}
