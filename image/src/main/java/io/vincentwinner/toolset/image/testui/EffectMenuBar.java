package io.vincentwinner.toolset.image.testui;

import io.vincentwinner.toolset.image.testui.menuitem.OpenFileMenuItem;
import io.vincentwinner.toolset.image.testui.menuitem.ReOpenMenuItem;
import io.vincentwinner.toolset.image.testui.menuitem.SaveFileMenuItem;
import io.vincentwinner.toolset.image.testui.menuitem.filter.CustomConvolutionFilterMenuItem;
import io.vincentwinner.toolset.image.testui.menuitem.filter.TranslateColorFilterMenuItems;
import io.vincentwinner.toolset.image.testui.menuitem.filter.blur.*;
import io.vincentwinner.toolset.image.testui.menuitem.filter.stylize.EdgeExtractMenuItems;

import javax.swing.*;

/**
 * 效果菜单栏
 *
 * 文件      滤镜
 *  ├打开    ├自定义卷积滤镜
 *  ├保存    ├反色
 *  └复位    ├去色
 *          ├模糊
 *          │ ├均值模糊
 *          │ ├方框模糊
 *          │ ├高斯模糊
 *          │ └中值模糊
 *          └风格化
 *            └边缘提取
 *              ├拉普拉斯
 *              ├索贝尔
 *              └精确
 */
public class EffectMenuBar extends JMenuBar {

    private final JMenu fileMenu = new JMenu("文件");
    private final JMenuItem openFile = new OpenFileMenuItem();
    private final JMenuItem saveFile = new SaveFileMenuItem();
    private final JMenuItem reOpen = new ReOpenMenuItem();

    private final JMenu filterMenu = new JMenu("滤镜");
    private final JMenuItem customConvolutionFilter = new CustomConvolutionFilterMenuItem();
    private final JMenuItem inverseColor = new TranslateColorFilterMenuItems.InverseColorMenuItem();
    private final JMenuItem deleteColor = new TranslateColorFilterMenuItems.DeleteColorMenuItem();

    private final JMenu blurMenu = new JMenu("模糊");
    private final JMenuItem averageBlur = new AverageBlurMenuItem();
    private final JMenuItem boxBlur = new BoxBlurMenuItem();
    private final JMenuItem gaussianBlur = new GaussianBlurMenuItem();
    private final JMenuItem medianBlur = new MedianBlurMenuItem();

    private final JMenu stylizeMenu = new JMenu("风格化");
    private final JMenu edgeExtractMenu = new JMenu("边缘提取");
    private final JMenuItem laplacianEdgeExtract = new EdgeExtractMenuItems.LaplacianEdgeExtractMenuItem();
    private final JMenuItem sobelEdgeExtract = new EdgeExtractMenuItems.SobelEdgeExtractMenuItem();
    private final JMenuItem cannyEdgeExtract = new EdgeExtractMenuItems.CannyEdgeExtractMenuItem();

    public EffectMenuBar(){
        initMenuBarUI();
    }

    @SuppressWarnings("all")
    private void initMenuBarUI(){
        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        fileMenu.add(reOpen);

        blurMenu.add(averageBlur);
        blurMenu.add(boxBlur);
        blurMenu.add(gaussianBlur);
        blurMenu.add(medianBlur);

        edgeExtractMenu.add(laplacianEdgeExtract);
        edgeExtractMenu.add(sobelEdgeExtract);
        edgeExtractMenu.add(cannyEdgeExtract);
        stylizeMenu.add(edgeExtractMenu);

        filterMenu.add(customConvolutionFilter);
        filterMenu.add(inverseColor);
        filterMenu.add(deleteColor);
        filterMenu.add(blurMenu);
        filterMenu.add(stylizeMenu);

        add(fileMenu);
        add(filterMenu);
    }

}
