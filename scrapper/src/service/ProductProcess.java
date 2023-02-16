package service;

import domain.Products;
import util.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductProcess extends DatabaseConnection {

    public ProductProcess() throws ClassNotFoundException, SQLException {
    }

    public void addProducts(ArrayList<Products> productsArrayList){
        String insertQuery = "insert into products (imageUrl, name, price, link) values(?,?,?,?)";
        try{
        PreparedStatement preparedStatement = getPreparedStatement(insertQuery);
            for (Products products:productsArrayList) {
                preparedStatement.setString(1, products.getImageUrl());
                preparedStatement.setString(2, products.getName());
                preparedStatement.setString(3, products.getPrice());
                preparedStatement.setString(4, products.getLink());
                preparedStatement.executeUpdate();
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("addProducts is not working");
        }

    }

    public int totalRows(){
        String query = "select count(*) from products";
        int count = 0;
        try {
            PreparedStatement preparedStatement = getPreparedStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            count = rs.getInt(1);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("product count not working");
        }
        return (count);
    }

    public ArrayList<Products> getProducts(int first, int last){
        ArrayList<Products> productsArrayList = new ArrayList<>();
        String query = "select * from products limit "+first+","+last;
        try {
            PreparedStatement preparedStatement = getPreparedStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Products products = new Products();
                products.setId(resultSet.getString("id"));
                products.setImageUrl(resultSet.getString("imageUrl"));
                products.setName(resultSet.getString("name"));
                products.setPrice(resultSet.getString("price"));
                products.setLink(resultSet.getString("link"));
                productsArrayList.add(products);
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("getProduct not working");
        }
        return productsArrayList;
    }

    public void getSortedProducts(){
        ArrayList<Products> productsArrayList = new ArrayList<>();
        ArrayList<Products> productsArrayList1 = new ArrayList<>();
        productsArrayList = getProducts(0,totalRows());
        productsArrayList1=quickSort(productsArrayList,0,totalRows()-1);
        clearDatabase();
        addProducts(productsArrayList);
    }

    public ArrayList<Products> quickSort(ArrayList<Products> productsArrayList,int startOfList,int endOfList){
        if(startOfList<endOfList){
            int partitionIndex = partition(productsArrayList, startOfList, endOfList);
            //System.out.println(partitionIndex);
           quickSort(productsArrayList,startOfList,partitionIndex-1);
           quickSort(productsArrayList,partitionIndex+1,endOfList);
        }
        return productsArrayList;
    }

    public int partition(ArrayList<Products> productsArrayList,int startOfList,int endOfList){
        Products pivot = productsArrayList.get(endOfList);
        int pivotPrice = stringToInt(pivot.getPrice());
        int partitionIndex=startOfList;
        for(int i=startOfList;i<=endOfList-1;i++){
            Products product = productsArrayList.get(i);
            int productPrice =  stringToInt(product.getPrice());
            if ( productPrice <= pivotPrice){
                Products productAtPartitionIndex = productsArrayList.get(partitionIndex);
                productsArrayList.set(partitionIndex,product);
                productsArrayList.set(i,productAtPartitionIndex);
                partitionIndex=partitionIndex+1;
            }
        }
        Products finalProductAtPartitionIndex = productsArrayList.get(partitionIndex);
        productsArrayList.set(partitionIndex,pivot);
        productsArrayList.set(endOfList,finalProductAtPartitionIndex);
        return partitionIndex;
    }

    public int stringToInt(String price){
        int priceInt = 0;
        String inputPattern="[0-9,]+";
        try {
            Pattern checkPattern = Pattern.compile(inputPattern);
            Matcher checkMatcher = checkPattern.matcher(price);
            checkMatcher.find();
            String str = checkMatcher.group().trim().replaceAll("[,]","");
            priceInt=Integer.parseInt(str);

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("StringToInt not working");
        }
        return priceInt;
    }

    public void clearDatabase(){
        String query = "delete from products";
        try {
            PreparedStatement preparedStatement = getPreparedStatement(query);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println("clearDatabase not working");
            e.printStackTrace();
        }
    }
}
