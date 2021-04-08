package io.vincentwinner.toolset.image.testui.menuitem;

import io.vincentwinner.toolset.image.testui.ImageViewPanel;
import io.vincentwinner.toolset.image.testui.TestFrame;
import io.vincentwinner.toolset.image.util.MatUtil;
import org.bytedeco.opencv.opencv_core.Mat;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * 打开文件菜单
 */
public class OpenFileMenuItem extends JMenuItem {

    private final JFileChooser fileChooser = new JFileChooser(new File("."));

    public OpenFileMenuItem() {
        setText("打开");
        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                String fileName = f.getName().toLowerCase();
                return f.isDirectory() || fileName.endsWith(".jpg") || fileName.endsWith(".png");
            }

            @Override
            public String getDescription() {
                return "*.png *.jpg";
            }
        });
        addActionListener(listener -> {
            ImageViewPanel contentPanel = TestFrame.contentPanel;
            int status = fileChooser.showOpenDialog(this);
            if( status == JFileChooser.APPROVE_OPTION ){
                File imgFile = fileChooser.getSelectedFile();
                Mat src = MatUtil.readFileToMat(imgFile.getAbsolutePath());
                contentPanel.setImageMat(src,true);
                contentPanel.releaseBuffer();
                src.release();
                System.gc();
                contentPanel.repaint();
            }
        });
    }

}
