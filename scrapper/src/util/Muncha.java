package util;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import domain.Products;
import service.ProductProcess;

import java.util.ArrayList;

public class Muncha {
    public void fetchUrl(String searchKey) throws Exception {
        String url = "https://muncha.com/Shop/Search?merchantID=1&CategoryID=0&q=" + searchKey;

        ArrayList<Products> productsArrayList = new ArrayList<>();
        try {
            Document d1 = Jsoup.connect(url).get();

            Elements test = d1.select("div.product");

            for (Element e : test) {
                Products products = new Products();
                products.setImageUrl(e.select("img.product-img-primary").attr("src"));
                products.setName(e.select("h5.product-caption-title-sm").text());
                products.setPrice(e.select("span.product-caption-price-new").text());
                products.setLink("https://muncha.com/" + e.select("a").attr("href"));
                productsArrayList.add(products);
            }
        }catch (Exception e){
            System.out.println("Muncha not working");
        }
        ProductProcess productProcess = new ProductProcess();
        productProcess.addProducts(productsArrayList);
    }
}

