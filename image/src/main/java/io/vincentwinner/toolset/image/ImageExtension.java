package io.vincentwinner.toolset.image;

public enum ImageExtension {

    PNG(".png"),
    JPG(".jpg")
    ;

    private String extensionName;

    ImageExtension(String extensionName){
        this.extensionName = extensionName;
    }

    public String value(){
        return this.extensionName;
    }

}
