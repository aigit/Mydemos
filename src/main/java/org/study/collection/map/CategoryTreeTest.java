package org.study.collection.map;

import lombok.extern.slf4j.Slf4j;
import org.study.dto.Category;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class CategoryTreeTest {

    public static void main(String[] args) {
        List<Category> categories =  getCateList();
        categories.forEach(System.out::println);
        CategoryTreeTest categoryTreeTest = new CategoryTreeTest();
        categoryTreeTest.findLeafs(categories);

        //categoryTreeTest.findPath(categories);

    }

    /**
     * 查找某个叶子的路径
     * @param categories
     */
    private void findPath(List<Category> categories){
        Map<Integer,Integer> parentMap = new HashMap<>();
        Map<Integer,Category> cateMap = new HashMap<>();
        categories.forEach(c->{
            parentMap.put(c.getId(),c.getPid());
            cateMap.put(c.getId(),c);
        });
        cateMap.forEach((k,v)->{
            log.info("k:{},v:{}",k,v);
        });
        Set<Integer> set = new LinkedHashSet<>();
        convertToOnePath(12,parentMap,set);
        String collect = set.stream().map(s -> {
            return cateMap.get(s).getName() + "";
        }).collect(Collectors.joining("/"));
        log.info("path:{}",collect);
    }

    private void convertToOnePath(Integer id, Map<Integer,Integer> parentMap, Set<Integer> set){
        if(parentMap.get(id)==null){
            return;
        }

        convertToOnePath(parentMap.get(id),parentMap,set);
        set.add(id);

    }

    private void findLeafs(List<Category> categories){
        Map<Integer,List<Category>> categoryMap = new HashMap<>();
        log.info("-----------start----------------");
        categories.forEach(c->{
            List<Category> categoryList = categoryMap.get(c.getPid());
            if(categoryList==null){
                categoryList = new ArrayList<>();
            }
            categoryList.add(c);
            categoryMap.put(c.getPid(),categoryList);
        });

        categoryMap.forEach((k,v)->{
            log.info("pid:{}",k);
            v.forEach(System.out::println);
            log.info("==============");
        });
        List<Category> resultCateList = new ArrayList<>();
        this.convertTreePath(1,categoryMap,resultCateList);
        resultCateList.forEach(System.out::println);
    }


    /**
     * 查询某个枝上的所有叶子
     * @param pid
     * @param categoryMap
     * @param categories
     */
    private void convertTreePath(Integer pid, Map<Integer,List<Category>> categoryMap,List<Category> categories){
        List<Category> categoriesList = categoryMap.get(pid);
        if(categoriesList==null){
            return;
        }

        categoriesList.forEach(c->{
            categories.add(c);
            convertTreePath(c.getId(),categoryMap,categories);
        });
    }


    private static List<Category> getCateList(){
        List<Category> categories = new ArrayList<>();
        Category.CategoryBuilder builder = Category.builder();
        Category build1 = builder.id(1).name(1).pid(0).build();
        Category build2 = builder.id(2).name(2).pid(0).build();
        Category build3 = builder.id(3).name(3).pid(0).build();
        Category build4 = builder.id(4).pid(1).name(4).build();
        Category build5 = builder.id(5).pid(1).name(5).build();
        Category build6 = builder.id(6).pid(1).name(6).build();
        Category build7 = builder.id(7).pid(2).name(7).build();
        Category build8 = builder.id(8).pid(2).name(8).build();
        Category build9 = builder.id(9).pid(2).name(9).build();
        Category build10 = builder.id(10).pid(2).name(10).build();
        Category build11 = builder.id(11).pid(3).name(11).build();
        Category build12 = builder.id(12).pid(4).name(12).build();
        Category build13 = builder.id(13).pid(5).name(13).build();
        Category build14 = builder.id(14).pid(3).name(14).build();
        Category build15 = builder.id(15).pid(3).name(15).build();
        Category build16 = builder.id(16).pid(3).name(16).build();
        categories.add(build1);
        categories.add(build2);
        categories.add(build3);
        categories.add(build4);
        categories.add(build5);
        categories.add(build6);
        categories.add(build7);
        categories.add(build8);
        categories.add(build9);
        categories.add(build10);
        categories.add(build11);
        categories.add(build12);
        categories.add(build13);
        categories.add(build14);
        categories.add(build15);
        categories.add(build16);
        return categories;
    }
}
