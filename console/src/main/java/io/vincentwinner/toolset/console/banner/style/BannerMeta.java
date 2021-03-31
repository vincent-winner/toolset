package io.vincentwinner.toolset.console.banner.style;

import java.util.Map;

public class BannerMeta {
    private BannerFont font;
    private BannerOption option;
    private Map<Integer, String[]> figletMap;
    private String comment;

    public BannerMeta(BannerFont font, BannerOption option, Map<Integer, String[]> figletMap, String comment) {
        this.font = font;
        this.option = option;
        this.figletMap = figletMap;
        this.comment = comment;
    }

    public BannerFont getFont() {
        return font;
    }

    public void setFont(BannerFont font) {
        this.font = font;
    }

    public BannerOption getOption() {
        return option;
    }

    public void setOption(BannerOption option) {
        this.option = option;
    }

    public Map<Integer, String[]> getFigletMap() {
        return figletMap;
    }

    public void setFigletMap(Map<Integer, String[]> figletMap) {
        this.figletMap = figletMap;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
