package com.bjike.act.comment;

import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import com.bjike.dto.Restrict;
import com.bjike.dto.comment.ShopDTO;
import com.bjike.entity.comment.Shop;
import com.bjike.ser.comment.ShopSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 店铺
 *
 * @Author: [liguiqin]
 * @Date: [2017-06-28 14:07]
 * @Description: [店铺 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@LoginAuth
@RestController
@RequestMapping("shop")
public class ShopAct {
    @Autowired
    private ShopSer shopSer;

    /**
     * 附近店铺
     *
     * @return class ShopVO
     * @throws Exception
     * @version v1
     */
    @GetMapping("nearby")
    public Result nearby(ShopDTO dto) throws ActException {
        try {
            return ActResult.initialize(shopSer.nearby(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 店铺删除
     *  {name:'data',type:'string',defaultValue:'',description:'success.'}
     * @param pointId 店铺坐标id
     * @version v1
     */
    @DeleteMapping("del")
    public Result del(String pointId) throws ActException {
        try {
            ShopDTO dto = new ShopDTO();
            dto.getConditions().add(Restrict.eq("pointId", pointId));
            Shop shop = shopSer.findOne(dto);
            if (null != shop) {
                shopSer.remove(shop);
            }
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
