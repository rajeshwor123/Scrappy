package util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import domain.Products;
import service.ProductProcess;

import java.util.ArrayList;

public class Dealayo {
    public void fetchUrl(String searchKey)throws Exception{
        String url = "https://www.dealayo.com/catalogsearch/result/?q="+searchKey;
        ArrayList<Products> productsArrayList = new ArrayList<>();


        try {
            Document d1 = Jsoup.connect(url).get();

            Elements test = d1.select("div.product-item-info");

            for (Element e : test) {
                Products products = new Products();
                products.setImageUrl(e.select("img").attr("src"));
                products.setName(e.select("div.product-item-details").select("a").attr("title"));
                products.setPrice(e.select("div.price-box").select("span.price").text());
                products.setLink(e.select("div.product-item-details").select("a").attr("href"));
                productsArrayList.add(products);
            }
        }catch (Exception e){
            System.out.println("Dealayo not working");
        }
        ProductProcess productProcess = new ProductProcess();
        productProcess.addProducts(productsArrayList);
    }
}
