package io.vincentwinner.toolset.console.banner.style;

public enum BannerLayout {
    DEFAULT(-1),
    FULL(0),
    FITTED(1),
    SMUSH_U(2),
    SMUSH_R(3);

    private final int code;

    BannerLayout(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static BannerLayout get(Integer code) {
        if (code == null) {
            return null;
        }
        for (BannerLayout e : values()) {
            if (code == e.code) {
                return e;
            }
        }
        return null;
    }

}
