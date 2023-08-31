package me.ding.easywareflow.service.impl;

import me.ding.easywareflow.entity.Page;
import me.ding.easywareflow.entity.Product;
import me.ding.easywareflow.entity.Result;
import me.ding.easywareflow.mapper.ProductMapper;
import me.ding.easywareflow.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    /*
    将配置文件的file.access-path属性值注入给service的accessPath属性,
   * 其为上传的图片保存到数据库中的访问地址的目录路径/img/upload/;
   */
    @Value("${file.access-path}")
    private String accessPath;

    //分页查询商品的业务方法
    @Override
    public Page queryProductPage(Page page, Product product) {

        //查询商品总行数
        int productCount = productMapper.selectProductCount(product);

        //分页查询商品
        List<Product> productList = productMapper.selectProductPage(page, product);

        //将查询到的总行数和当前页数据组装到Page对象
        page.setTotalNum(productCount);
        page.setResultList(productList);

        return page;
    }

    //添加商品的业务方法
    @Override
    public Result saveProduct(Product product) {
        //处理上传的图片的访问地址 -- /img/upload/图片名称
        product.setImgs(accessPath + product.getImgs());
        //添加商品
        int i = productMapper.insertProduct(product);
        if (i > 0) return Result.ok("添加商品成功！");
        return Result.err(Result.CODE_ERR_BUSINESS, "添加商品失败！");
    }

    //修改商品上下架状态的业务方法
    @Override
    public Result updateProductState(Product product) {
        //根据商品id修改商品上下架状态
        int i = productMapper.updateStateById(product);
        if (i > 0) {
            return Result.ok("修改成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "修改失败！");
    }


}
