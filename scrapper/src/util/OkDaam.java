package util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import domain.Products;
import service.ProductProcess;

import java.util.ArrayList;

public class OkDaam {
    public void fetchUrl(String searchKey)throws Exception {
        String url = "https://www.okdam.com/search?k=" + searchKey;

        ArrayList<Products> productsArrayList = new ArrayList<>();
        try {
            Document d1 = Jsoup.connect(url).get();

            Elements test = d1.select("div.col-6.col-md-6.col-lg-3.pro-wrap");

            for (Element e : test) {
                Products products = new Products();
                products.setImageUrl(e.select("div.product-box").select("img").attr("src"));
                products.setName(e.select("div.product_name").text());
                products.setPrice(e.select("div.product-box").select("span.og-price").text());
                products.setLink(e.select("a").attr("href"));
                productsArrayList.add(products);
            }
        }catch (Exception e){
            System.out.println("OkDaam is not Working");
        }

        ProductProcess productProcess = new ProductProcess();
        productProcess.addProducts(productsArrayList);

    }
}
