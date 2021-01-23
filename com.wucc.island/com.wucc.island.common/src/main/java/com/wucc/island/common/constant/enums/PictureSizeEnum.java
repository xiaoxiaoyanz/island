package com.wucc.island.common.constant.enums;

public enum PictureSizeEnum {

    SIZE02(266, 137);

    private static final String MULT = "x";

    private Integer width;

    private Integer height;

    private PictureSizeEnum(Integer width, Integer height) {
        this.width = width;
        this.height = height;
    }

    public Integer width() {
        return width;
    }

    public Integer height() {
        return height;
    }

    public String toSize() {
        return width + MULT + height;
    }
}
