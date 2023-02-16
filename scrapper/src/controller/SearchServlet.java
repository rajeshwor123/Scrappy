package controller;

import domain.Products;
import service.CallerClass;
import service.ProductProcess;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        int page = Integer.parseInt(request.getParameter("page"));
        int firstVist = Integer.parseInt(request.getParameter("firstVisit"));
        String searchKey = request.getParameter("searchKey");
        System.out.println("we are in page "+page);

        ProductProcess productProcess = null;
        try {
            productProcess = new ProductProcess();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int first = 0;
        int last = 12;

        if(page ==1&&firstVist==1){
            productProcess.clearDatabase();
            CallerClass callerClass = new CallerClass();
            try {
                callerClass.caller(searchKey);
            } catch (Exception e) {
                e.printStackTrace();
            }
        firstVist = firstVist-1;
        }
        else{
            first = page*12-12;
        }

        int pagesRequired = productProcess.totalRows()/12+1;
        request.setAttribute("pagesRequired",pagesRequired);
        request.setAttribute("searchKey",searchKey);
        System.out.println(pagesRequired);
        System.out.println("element from index-"+first+" number of elements "+last);

        ArrayList<Products> productsArrayList= new ArrayList<>();
        productProcess.getSortedProducts();
        productsArrayList =  productProcess.getProducts(first,last);

        request.setAttribute("productsArrayList",productsArrayList);
        request.getRequestDispatcher("searchPage.jsp").forward(request,response);

    }
}
