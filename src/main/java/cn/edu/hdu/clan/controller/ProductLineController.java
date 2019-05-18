package cn.edu.hdu.clan.controller;

import cn.edu.hdu.clan.entity.sys.ProductLine;
import cn.edu.hdu.clan.service.sys.ProductLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("ProductLine")
public class ProductLineController extends BaseController {

    @Autowired
    private ProductLineService ProductLineService;
    @RequestMapping("add")
    public String add(@RequestBody ProductLine ProductLine) {
        ProductLineService.add(ProductLine);
        return success();
    }

    @RequestMapping("delete")
    public String delete(@RequestBody Map<String,String> param) {
        ProductLineService.delete(param.get("id"));
        return success();
    }

    @RequestMapping("update")
    public String update(@RequestBody ProductLine ProductLine){
        ProductLineService.update(ProductLine);
        return success();
    }

    @RequestMapping("list")
    public String list(@RequestBody Map<String, Integer> param) {
        return success(ProductLineService.list(param.get("pageNum"), param.get("pageSize")));
    }

    @RequestMapping("getById")
    public String getById(@RequestBody Map<String,String> param) {
        return success(ProductLineService.getById(param.get("id")));
    }
}