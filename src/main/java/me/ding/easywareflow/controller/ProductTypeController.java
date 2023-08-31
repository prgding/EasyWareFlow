package me.ding.easywareflow.controller;

import me.ding.easywareflow.entity.ProductType;
import me.ding.easywareflow.entity.Result;
import me.ding.easywareflow.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/productCategory")
@RestController
public class ProductTypeController {
    @Autowired
    private ProductTypeService productTypeService;

    /**
     * 查询商品分类树的url接口/productCategory/product-category-tree
     * 返回值Result对象给客户端响应查询到的所有商品分类树List<ProductType>;
     */
    @RequestMapping("/product-category-tree")
    public Result productCategoryTree(){
        //执行业务
        List<ProductType> productTypeList = productTypeService.allProductTypeTree();
        //响应
        return Result.ok(productTypeList);
    }


    /**
     * 校验分类编码是否已存在的url接口/productCategory/verify-type-code
     */
    @RequestMapping("/verify-type-code")
    public Result checkTypeCode(String typeCode){
        //执行业务
        return productTypeService.queryTypeByCode(typeCode);
    }

    /**
     * 添加商品分类的url接口/productCategory/type-add
     */
    @RequestMapping("/type-add")
    public Result addProductType(@RequestBody ProductType productType){
        //执行业务
        return productTypeService.saveProductType(productType);
    }
    /**
     * 删除商品分类的url接口/productCategory/type-delete/{typeId}
     */
    @RequestMapping("/type-delete/{typeId}")
    public Result deleteType(@PathVariable Integer typeId){
        //执行业务
        return productTypeService.removeProductType(typeId);
    }
    /**
     * 修改商品分类的url接口/productCategory/type-update
     */
    @RequestMapping("/type-update")
    public Result updateType(@RequestBody ProductType productType){
        //执行业务
        return productTypeService.updateProductType(productType);
    }

}