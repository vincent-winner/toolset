package io.vincentwinner.toolset.image.testui;

import io.vincentwinner.toolset.image.ImageExtension;
import org.bytedeco.opencv.opencv_core.Mat;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 图像面板
 */
public class ImageViewPanel extends JPanel {

    private Mat initImageMat;
    private Mat imageMat;

    public ImageViewPanel() {
    }

    public ImageViewPanel(final Mat imageMat) {
        this.initImageMat = imageMat.clone();
        this.imageMat = initImageMat.clone();
    }

    /**
     * 将图片以原始比例绘制在面板上
     * 并设置图片大小随容器大小变化
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (imageMat != null) {
            BufferedImage image = Util.mat2BufferedImage(imageMat, ImageExtension.PNG);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            int w = getWidth();
            int h = getHeight();
            int iw = image.getWidth();
            int ih = image.getHeight();
            double xScale = (double) w / iw;
            double yScale = (double) h / ih;
            double scale = Math.min(xScale, yScale);
            int width = (int) (scale * iw);
            int height = (int) (scale * ih);
            int x = (w - width) / 2;
            int y = (h - height) / 2;
            g.drawImage(image, x, y, width, height, this);
        }
    }

    public BufferedImage getImage() {
        return Util.matToBufferedImage(imageMat.clone());
    }

    public BufferedImage getInitImage(){
        return Util.matToBufferedImage(imageMat.clone());
    }

    public Mat getImageMat(){
        return imageMat.clone();
    }

    public Mat getInitImageMat(){
        return initImageMat.clone();
    }

    public void setImageMat(Mat imageMat) {
        if (initImageMat == null){
            this.initImageMat = imageMat.clone();
        }
        this.imageMat = imageMat.clone();
    }

}
