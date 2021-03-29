package io.vincentwinner.toolset.image.testui;

import io.vincentwinner.toolset.image.testui.menuitem.OpenFileMenuItem;
import io.vincentwinner.toolset.image.testui.menuitem.SaveFileMenuItem;
import io.vincentwinner.toolset.image.testui.menuitem.filter.CustomConvolutionFilterMenuItem;
import io.vincentwinner.toolset.image.testui.menuitem.filter.blur.*;

import javax.swing.*;

/**
 * 效果菜单栏
 *
 * 文件    滤镜
 *  ├打开  └自定义卷积滤镜
 *  └保存    └模糊
 *            ├均值模糊
 *            ├方框模糊
 *            ├高斯模糊
 *            └中值模糊
 */
public class EffectMenuBar extends JMenuBar {

    private final JMenu fileMenu = new JMenu("文件");
    private final JMenuItem openFile = new OpenFileMenuItem();
    private final JMenuItem saveFile = new SaveFileMenuItem();

    private final JMenu filterMenu = new JMenu("滤镜");
    private final JMenuItem customConvolutionFilter = new CustomConvolutionFilterMenuItem();

    private final JMenu blurMenu = new JMenu("模糊");
    private final JMenuItem averageBlur = new AverageBlurMenuItem();
    private final JMenuItem boxBlur = new BoxBlurMenuItem();
    private final JMenuItem gaussianBlur = new GaussianBlurMenuItem();
    private final JMenuItem medianBlur = new MedianBlurMenuItem();

    public EffectMenuBar(){
        initMenuBarUI();
    }

    private void initMenuBarUI(){
        fileMenu.add(openFile);
        fileMenu.add(saveFile);

        blurMenu.add(averageBlur);
        blurMenu.add(boxBlur);
        blurMenu.add(gaussianBlur);
        blurMenu.add(medianBlur);

        filterMenu.add(customConvolutionFilter);
        filterMenu.add(blurMenu);

        add(fileMenu);
        add(filterMenu);
    }

}
