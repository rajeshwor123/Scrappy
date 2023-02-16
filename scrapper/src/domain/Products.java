package domain;

public class Products {
    private String imageUrl = null;
    private String name = null;
    private String price = null;
    private String link = null;
    private String id = null;

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setId(String id){this.id = id;}

    public String getId(){ return id; }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getLink() {
        return link;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

}
