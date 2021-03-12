package io.vincentwinner.toolset.ai.faceid.seetaface.awt;

import com.seetaface2.model.SeetaPointF;
import com.seetaface2.model.SeetaRect;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * 此类不应被直接使用
 * 需要显示人脸检测结果需使用{@link ImageFrame}
 */
public class ImagePanel extends Panel {

    private Graphics2D g;
    private BufferedImage imgBuf;
    private SeetaRect[] rects;
    private SeetaPointF[] points;
    private Class<?> type;

    ImagePanel(InputStream picStream, SeetaRect[] rects){
        try {
            type = SeetaRect[].class;
            imgBuf = ImageIO.read(picStream);
            this.rects = rects == null ? new SeetaRect[]{new SeetaRect()} : rects;
            int backGroundWidth = Math.min(imgBuf.getWidth(), 1800);
            int backGroundHeight = Math.min(imgBuf.getHeight() , 1000);
            setPreferredSize(new Dimension(backGroundWidth ,backGroundHeight));
            Image image = new BufferedImage(backGroundWidth,backGroundHeight,Image.SCALE_SMOOTH);
            g = (Graphics2D) image.getGraphics();
            setFocusable(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ImagePanel(InputStream picStream, SeetaPointF[] points){
        try{
            type = SeetaPointF.class;
            imgBuf = ImageIO.read(picStream);
            this.points = points == null ? new SeetaPointF[]{new SeetaPointF()} : points;
            int backGroundWidth = Math.min(imgBuf.getWidth(), 1800);
            int backGroundHeight = Math.min(imgBuf.getHeight() , 1000);
            setPreferredSize(new Dimension(backGroundWidth ,backGroundHeight));
            Image image = new BufferedImage(backGroundWidth,backGroundHeight,Image.SCALE_SMOOTH);
            g = (Graphics2D) image.getGraphics();
            setFocusable(true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(imgBuf,0,0,null);
        if(type == SeetaRect[].class){
            g.setColor(Color.GREEN);
            for (SeetaRect rect : rects) {
                g.drawRect(rect.x, rect.y, rect.width, rect.height);
            }
        }else if(type == SeetaPointF.class){
            g.setColor(Color.RED);
            for(SeetaPointF point : points){
                g.drawOval((int)point.x,(int)point.y,10,10);
            }
        }
    }

}
