package io.vincentwinner.toolset.image.testui.menuitem;

import io.vincentwinner.toolset.image.testui.ImageViewPanel;
import io.vincentwinner.toolset.image.testui.TestFrame;
import io.vincentwinner.toolset.image.util.MatUtil;
import org.bytedeco.opencv.opencv_core.Mat;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

public class SaveFileMenuItem extends JMenuItem {

    private final JFileChooser fileChooser = new JFileChooser(new File("."));

    public SaveFileMenuItem(){
        setText("保存");
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
            int status = fileChooser.showSaveDialog(this);
            if( status == JFileChooser.APPROVE_OPTION ){
                File imgFile = fileChooser.getSelectedFile();
                Mat dst = contentPanel.getImageMat();
                if(!dst.empty()) {
                    MatUtil.writeMatToFile(dst,imgFile.getAbsolutePath());
                }else {
                    JOptionPane.showMessageDialog(null,"没有打开任何图片！");
                }
            }
        });
    }



}
