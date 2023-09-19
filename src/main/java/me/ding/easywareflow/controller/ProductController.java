package me.ding.easywareflow.controller;

import me.ding.easywareflow.entity.*;
import me.ding.easywareflow.service.*;
import me.ding.easywareflow.utils.TokenUtils;
import me.ding.easywareflow.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RequestMapping("/product")
@RestController
public class ProductController {

    //注入StoreService
    @Autowired
    private StoreService storeService;
    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private ProductService productService;
    /**
     * 将配置文件的file.upload-path属性值注入给控制器的uploadPath属性,
     * 其为图片上传到项目的目录路径(类路径classes即resources下的static/img/upload);
     */
    @Value("${file.upload-path}")
    private String uploadPath;
    @Autowired
    private TokenUtils tokenUtils;

    /**
     * 查询所有仓库的url接口/product/store-list
     * 返回值Result对象给客户端响应查询到的List<Store>;
     */
    @RequestMapping("/store-list")
    public Result storeList() {
        //执行业务
        List<Store> storeList = storeService.queryAllStore();
        //响应
        return Result.ok(storeList);
    }



    /**
     * 查询所有商品分类树的url接口/product/category-tree
     * 返回值Result对象给客户端响应查询到的所有商品分类树List<ProductType>;
     */
    @RequestMapping("/category-tree")
    public Result categoryTree() {
        //执行业务
        List<ProductType> typeTreeList = productTypeService.allProductTypeTree();
        //响应
        return Result.ok(typeTreeList);
    }



    /**
     * 分页查询商品的url接口/product/product-page-list
     * 参数Page对象用于接收请求参数页码pageNum、每页行数pageSize;
     * 参数Product对象用于接收请求参数仓库id storeId、商品名称productName、
     * 品牌名称brandName、分类名称typeName、供应商名称supplyName、产地名称
     * placeName、上下架状态upDownState、是否过期isOverDate;
     * 返回值Result对象向客户端响应组装了所有分页信息的Page对象;
     */
    @RequestMapping("/product-page-list")
    public Result productPageList(Page page, Product product) {
        //执行业务
        page = productService.queryProductPage(page, product);
        //响应
        return Result.ok(page);
    }

    /**
     * 上传图片的url接口/product/img-upload
     * 参数MultipartFile file对象封装了上传的图片;
     */
    @CrossOrigin
    @RequestMapping("/img-upload")
    public Result uploadImg(MultipartFile file) {
        try {
            //拿到图片上传到的目录(类路径classes下的static/img/upload)的File对象
            File uploadDirFile = ResourceUtils.getFile(uploadPath);
            //拿到图片上传到的目录的磁盘路径
            String uploadDirPath = uploadDirFile.getAbsolutePath();
            //拿到图片保存到的磁盘路径
            String fileUploadPath = uploadDirPath + "/" + file.getOriginalFilename();
            System.out.println("fileUploadPath = " + fileUploadPath);
            //保存图片
            file.transferTo(new File(fileUploadPath));
            //成功响应
            return Result.ok("图片上传成功！");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            //失败响应
            return Result.err(Result.CODE_ERR_BUSINESS, "图片上传失败！");
        }
    }

    /**
     * 添加商品的url接口/product/product-add
     * 将请求头Token的值即客户端归还的token赋值给参数变量token;
     */
    @RequestMapping("/product-add")
    public Result addProduct(@RequestBody Product product, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token) {
        //获取当前登录的用户
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        //获取当前登录的用户id,即添加商品的用户id
        int createBy = currentUser.getUserId();
        product.setCreateBy(createBy);

        //响应
        return productService.saveProduct(product);
    }

    /**
     * 修改商品上下架状态的url接口/product/state-change
     */
    @RequestMapping("/state-change")
    public Result changeProductState(@RequestBody Product product) {
        return productService.updateProductState(product);
    }

    /**
     * 删除商品的url接口/product/product-delete/{productId}
     */
    @RequestMapping("/product-delete/{productId}")
    public Result deleteProduct(@PathVariable Integer productId) {
        //响应
        return productService.deleteProduct(productId);
    }

    /**
     * 修改商品的url接口/product/product-update
     * 将请求头Token的值即客户端归还的token赋值给参数变量token;
     */
    @RequestMapping("/product-update")
    public Result updateProduct(@RequestBody Product product, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token) {
        //获取当前登录的用户
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        //获取当前登录的用户id,即修改商品的用户id
        int updateBy = currentUser.getUserId();
        product.setUpdateBy(updateBy);

        //响应
        return productService.updateProduct(product);
    }

}
