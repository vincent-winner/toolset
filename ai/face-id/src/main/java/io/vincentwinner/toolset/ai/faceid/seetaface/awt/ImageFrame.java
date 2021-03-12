package io.vincentwinner.toolset.ai.faceid.seetaface.awt;

import com.seetaface2.model.SeetaPointF;
import com.seetaface2.model.SeetaRect;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 将被处理的文件和处理结果传入可直接查看绘制好的人脸识别位置
 */
public class ImageFrame extends JFrame {

    public <T> ImageFrame(InputStream picStream, Future future,Class<T> resultType){
        setTitle("Result");
        if(resultType == SeetaRect[].class){
            SeetaRect[] rects$;
            try {
                rects$ = (SeetaRect[]) future.get();
            } catch (InterruptedException | ExecutionException e) {
                SeetaRect rect = new SeetaRect();
                rect.x = 0;rect.y = 0;rect.width = 0;rect.height = 0;
                rects$ = new SeetaRect[]{rect};
            }
            ImagePanel panel = new ImagePanel(picStream,rects$);
            add(panel);
        }else if(resultType == SeetaPointF[].class){
            SeetaPointF[] points$;
            try{
                points$ = (SeetaPointF[]) future.get();
            }catch (Exception e){
                SeetaPointF defaultPoint = new SeetaPointF();
                defaultPoint.x = 0d;defaultPoint.y = 0d;
                points$ = new SeetaPointF[]{defaultPoint};
            }
            ImagePanel panel = new ImagePanel(picStream,points$);
            add(panel);
        }
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
