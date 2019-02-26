package Controller;

import Po.Goods;
import Po.Record;
import Service.GoodsService;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;
import com.github.pagehelper.PageHelper;
import jdk.nashorn.internal.runtime.JSONListAdapter;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "goods/findByNameLike", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String findGoodsByNameLike(String name){
        PageHelper.startPage(1,5);
        List<Goods> goods = goodsService.findGoodsByNameLike(name);
        JSONArray jsonArray = JSONArray.fromObject(goods);
        return jsonArray.toString();
    }

    @RequestMapping(value = "goods/findAll", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String findAll(int pageNum,int pageSize){
        int sum = goodsService.findAllSum();
        String orderBy="type asc";
        PageHelper.startPage(pageNum,pageSize,orderBy);
        List<Goods> goods = goodsService.findAll();
        JSONArray jsonArray = JSONArray.fromObject(goods);
        //return jsonArray.toString();
        return "{\"total\":"+sum+",\"rows\":"+jsonArray.toString()+"}";
    }

    @RequestMapping(value = "goods/addGoods", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String findAll(String name,String type){
        Goods goods = new Goods();
        goods.setName(name);
        goods.setType(type);
        if (goodsService.addGoods(goods)>0){
            return "ok";
        }
        return "error";
    }

    @RequestMapping(value = "goods/delGoods", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String delGoods(int id){
        if (goodsService.delGoodsById(id)>0){
            return "ok";
        }
        return "error";
    }

    @RequestMapping(value = "goods/updGoods", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String updGoods(int id,String name, String type){
        Goods goods = new Goods();
        goods.setId(id);
        goods.setName(name);
        goods.setType(type);
        if (goodsService.updGoods(goods)>0){
            return "ok";
        }
        return "error";
    }


}