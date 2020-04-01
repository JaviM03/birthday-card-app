/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.christmascards.util;

import com.christmascards.domain.ReferredOccasion;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;

/**
 *
 * @author HP PC
 */
public class PaginAndSorting {
    
    public static Map<String,Object> dashboardPagingAndSorting(Page<ReferredOccasion> usersReferred, HttpServletRequest request, String dateRange){
        Map<String,Object> valuesMap = new HashMap();
        Integer resultsPage = usersReferred.getNumber();
        Integer totalPageCount = usersReferred.getTotalPages();
        /*Este es if es en el caso que la página sea la primera, revisamos si hay más páginas y si es así agregamos los resultados al mapa*/
        if(resultsPage==0){
            valuesMap.put("firstPage", true);
            valuesMap.put("firstPageNumber",1);
            if(totalPageCount.equals(2)){
                valuesMap.put("secondPageExist", true);
                valuesMap.put("secondPageNumber", 2);
                valuesMap.put("nextPageExist",true);
                valuesMap.put("secondPageUrl",request.getContextPath()+"/dashboard?dateRange="+dateRange+"&page=1");
                valuesMap.put("nextPageUrl",request.getContextPath()+"/dashboard?dateRange="+dateRange+"&page=1");
            }
            else if(totalPageCount>2){
                valuesMap.put("thirdPageExist", true);
                valuesMap.put("nextPageExist",true);
                valuesMap.put("secondPageExist", true);
                valuesMap.put("secondPageNumber", 2);
                valuesMap.put("thirdPageNumber",3);
                valuesMap.put("secondPageUrl",request.getContextPath()+"/dashboard?dateRange="+dateRange+"&page=1");
                valuesMap.put("thirdPageUrl",request.getContextPath()+"/dashboard?dateRange="+dateRange+"&page=2");
                valuesMap.put("nextPageUrl",request.getContextPath()+"/dashboard?dateRange="+dateRange+"&page=1");
            }
        }
        /*Else para indicar que estamos hablando de una entrada posterior a la primera página*/
        else{
            valuesMap.put("firstPage", false);
            //Estamos al final de las páginas, en la posición de las páginas 3
            if(totalPageCount.equals(resultsPage+1) && totalPageCount>2){
                valuesMap.put("secondPageExist",true);
                valuesMap.put("thirdPage",true);
                valuesMap.put("thirdPageExist",true);
                valuesMap.put("lastPageUrl",request.getContextPath()+"/dashboard?dateRange="+dateRange+"&page="+(resultsPage-1));
                valuesMap.put("secondPageUrl",request.getContextPath()+"/dashboard?dateRange="+dateRange+"&page="+(resultsPage-1));
                valuesMap.put("firstPageUrl",request.getContextPath()+"/dashboard?dateRange="+dateRange+"&page="+(resultsPage-2));
                valuesMap.put("thirdPageNumber",resultsPage+1);
                valuesMap.put("secondPageNumber",resultsPage);
                valuesMap.put("firstPageNumber",resultsPage-1);
            }
            //Estamos al final de las páginas, en la posición de las páginas 2
            else if(totalPageCount.equals(resultsPage+1) && totalPageCount.equals(2)){
                valuesMap.put("secondPage",true);
                valuesMap.put("secondPageExist",true);
                valuesMap.put("lastPageUrl",request.getContextPath()+"/dashboard?dateRange="+dateRange+"&page="+(resultsPage-1));
                valuesMap.put("firstPageUrl",request.getContextPath()+"/dashboard?dateRange="+dateRange+"&page="+(resultsPage-1));
                valuesMap.put("secondPageNumber",resultsPage+1);
                valuesMap.put("firstPageNumber",resultsPage);
            }
            //Estamos a la mitad de las páginas, en la posición de las páginas 2
            else if(resultsPage+1 < totalPageCount && resultsPage!=0){
                valuesMap.put("secondPageExist",true);
                valuesMap.put("thirdPageExist",true);
                valuesMap.put("secondPage",true);
                valuesMap.put("lastPage",true);
                valuesMap.put("nextPageExist",true);
                valuesMap.put("lastPageUrl",request.getContextPath()+"/dashboard?dateRange="+dateRange+"&page="+(resultsPage-1));
                valuesMap.put("firstPageUrl",request.getContextPath()+"/dashboard?dateRange="+dateRange+"&page="+(resultsPage-1));
                valuesMap.put("nextPageUrl",request.getContextPath()+"/dashboard?dateRange="+dateRange+"&page="+(resultsPage+1));
                valuesMap.put("thirdPageUrl",request.getContextPath()+"/dashboard?dateRange="+dateRange+"&page="+(resultsPage+1));
                valuesMap.put("thirdPageNumber",resultsPage+2);
                valuesMap.put("secondPageNumber",resultsPage+1);    
                valuesMap.put("firstPageNumber",resultsPage);
            }
        }
        
        return valuesMap;
    }
    
}
