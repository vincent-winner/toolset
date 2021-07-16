package io.vincentwinner.toolset.ai.seeta6jni.frame;

import io.vincentwinner.toolset.ai.seeta6jni.structs.SeetaAngle;
import io.vincentwinner.toolset.ai.seeta6jni.structs.SeetaPointF;
import io.vincentwinner.toolset.ai.seeta6jni.structs.SeetaRect;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import static java.lang.Math.sin;
import static java.lang.Math.cos;

/**
 * 此类不应被直接使用
 * 需要显示人脸检测结果需使用{@link ImageFrame}
 */
public class ImagePanel extends Panel {

    private Graphics2D g;
    private BufferedImage imgBuf;
    private SeetaRect[] rects;
    private SeetaPointF[] points;
    private SeetaAngle angle;
    private Class<?> type;

    ImagePanel(InputStream picStream, SeetaRect[] rects){
        type = SeetaRect[].class;
        this.rects = rects == null ? new SeetaRect[]{new SeetaRect()} : rects;
        init(picStream);
    }

    ImagePanel(InputStream picStream, SeetaPointF[] points){
        type = SeetaPointF.class;
        this.points = points == null ? new SeetaPointF[]{new SeetaPointF()} : points;
        init(picStream);
    }

    ImagePanel(InputStream picStream, SeetaAngle angle){
        type = SeetaAngle.class;
        this.angle = angle == null ? new SeetaAngle() : angle;
        init(picStream);
    }

    private void init(InputStream picStream){
        try {
            imgBuf = ImageIO.read(picStream);
            this.angle = angle == null ? new SeetaAngle() : angle;
            int backGroundWidth = Math.min(imgBuf.getWidth(), 1800);
            int backGroundHeight = Math.min(imgBuf.getHeight(), 1000);
            setPreferredSize(new Dimension(backGroundWidth, backGroundHeight));
            Image image = new BufferedImage(backGroundWidth, backGroundHeight, Image.SCALE_SMOOTH);
            g = (Graphics2D) image.getGraphics();
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
                g.drawRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
            }
        }else if(type == SeetaPointF.class){
            g.setColor(Color.RED);
            for(SeetaPointF point : points){
                g.fillOval((int)point.getX(),(int)point.getY(),10,10);
            }
        }else if(type == SeetaAngle.class){
            drawAxis(g,this.angle);
        }
    }

    private void drawAxis(Graphics g,SeetaAngle angle){
        final float PI = 3.14159265f;
        final int tdx = 70;
        final int tdy = 70;
        final int size = 55;
        int x1, y1,
            x2, y2,
            x3, y3;

        angle.setPitch(angle.getPitch() * PI / 180);
        angle.setYaw(angle.getYaw() * PI / 180);
        angle.setRoll(angle.getRoll() * PI / 180);

        x1 = Double.valueOf(
             size * (
                 cos(angle.getYaw()) *
                 cos(angle.getRoll())
             ) + tdy
        ).intValue();
        y1 = Double.valueOf(
            size * (
                 cos(angle.getPitch()) *
                 sin(angle.getRoll()) *
                 sin(angle.getPitch()) *
                 sin(angle.getYaw())
            ) + tdy
        ).intValue();

        x2 = Double.valueOf(
            size * (
                 -cos(angle.getYaw()) *
                 sin(angle.getRoll())
            ) + tdy
        ).intValue();
        y2 = Double.valueOf(
            size * -(
                 cos(angle.getPitch()) *
                 cos(angle.getRoll()) -
                 sin(angle.getPitch()) *
                 sin(angle.getYaw()) *
                 sin(angle.getRoll())
            ) + tdy
        ).intValue();

        x3 = Double.valueOf(
             size * (sin(angle.getYaw())) + tdy
        ).intValue();
        y3 = Double.valueOf(
             size * (
                 -cos(angle.getYaw()) *
                 sin(angle.getPitch())
             ) + tdy
        ).intValue();

        g.setColor(new Color(0,0,255));
        g.drawLine(tdx, tdy, x1, y1);
        g.setColor(new Color(0,255,0));
        g.drawLine(tdx, tdy, x2, y2);
        g.setColor(new Color(255,0,0));
        g.drawLine(tdx, tdy, x3, y3);
    }

}
