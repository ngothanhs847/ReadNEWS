package vn.edu.tdc.readnews.Models;

public class ItemSlideMenu {
    private int imgId;
    private String title;

    public ItemSlideMenu(int imgId, String title) {
        this.imgId = imgId;
        this.title = title;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImgId() {

        return imgId;
    }

    public String getTitle() {
        return title;
    }
}
