package io.vincentwinner.toolset.image.testui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 图像面板
 */
public class ImageViewPanel extends JPanel {

    private BufferedImage initImage;
    private BufferedImage image;

    public ImageViewPanel() {
    }

    public ImageViewPanel(BufferedImage bufferedImage) {
        this.image = bufferedImage;
        this.initImage = bufferedImage;
    }

    /**
     * 将图片以原始比例绘制在面板上
     * 并设置图片大小随容器大小变化
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (image != null) {
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
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        if (initImage == null){
            this.initImage = image;
        }
    }

    public BufferedImage getInitImage(){
        return this.initImage;
    }

}
