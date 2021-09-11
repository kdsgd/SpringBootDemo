package com.tbyf.demo.springbootdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.wxpay.sdk.WXPayUtil;
import com.tbyf.util.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class WxJsApiController {


    private static final Logger log = LoggerFactory.getLogger(WxJsApiController.class);



    /**
     * 统一下单必填参数
     * appid  公众账号ID
     * mch_id  商户号
     * nonce_str 随机字符串
     * sign  签名
     * body  商品描述
     * out_trade_no  商户订单号
     * total_fee  标价金额
     * spbill_create_ip  终端ip
     * notify_url 通知地址
     * trade_type 交易类型
     * openid  用户标识
     */
    private String appid = "wx9dac5249c5a41f4f";
    private String mch_id = "20001223";
    private String key = "M86l522AV6q613Ii4W6u8K48uW8vM1N6bFgyv769220MdYe9u37N4y7rI5mQ";
    private String redirect_uri4code = "http://guopengxiang.iask.in/wx/getOpenId";
    private String appsecret = "412d2a50479a4c50fd745a15691e8973";
    private String body = "巨龙撞击";
    private String spbill_create_ip = "127.0.0.1";
    private String trade_type = "JSAPI";


    //获取code
    @RequestMapping("/getCode")
    public void getCode(HttpServletResponse response) throws Exception {
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_base&state=STATE#wechat_re";
        url = String.format(url, appid, redirect_uri4code);
        response.sendRedirect(url);
    }


    //微信统一下单
//    @RequestMapping("/placeOrder")
//    @ResponseBody
//    public String placeOrder() throws Exception{
//
//        HashMap <String,String>map = new HashMap();
//        //生成订单号
//        String out_trade_no = String.valueOf(System.currentTimeMillis());
//        //随机字符串
//        String nonce_Str = WXPayUtil.generateNonceStr();
//
//
//        //总共是封装11个参数，这里先封10个，然后调用统一下单接口
//        map.put("appid",appid);
//        map.put("mch_id", mch_id);
//        map.put("nonce_str", nonce_Str);
//        map.put("body", body);
//        map.put("out_trade_no", out_trade_no);
//        map.put("total_fee", "1");
//        map.put("spbill_create_ip", spbill_create_ip);
//        map.put("notify_url", "http://jinzw.natapp1.cc/NotifyServlet");
//        map.put("trade_type", trade_type);
//        map.put("openid","o3bP5071bMr4ZHlOTHjcKQeEXiZI");
//        log.info(String.valueOf(map));
//
//
//        //生成签名，将10个参数封装好带着key加密一遍，然后把加密好的signature(10个参数)也放进map
//        String signature = WXPayUtil.generateSignature(map, "TBYFSOFTTBYFSOFTTBYFSOFTTBYFSOFT", WXPayConstants.SignType.MD5);
//        map.put("sign",signature);
//        System.out.println("map==========================================================");
//        log.info(String.valueOf(map));
//
//
//        //将参数发送到统一下单接口，解析返回值,获取预支付交易会话标识 prepay_id
//        String reqXml = WXPayUtil.mapToXml(map);
//        log.info("reqXml",reqXml);
//        String resXml = Http.sendPost("https://api.mch.weixin.qq.com/pay/unifiedorder",reqXml);
//        log.info("resXml",resXml);
//        Map toMap = WXPayUtil.xmlToMap(resXml);
//        log.info("toMap",toMap);
//        HashMap<String,String> h5m = new HashMap();
//        toMap.put("timeStamp",WXPayUtil.getCurrentTimestamp());
//
//        //将返回值解析后封装发到前台
//        h5m.put("appId",(String) toMap.get("appid"));
//        h5m.put("timeStamp",String.valueOf(WXPayUtil.getCurrentTimestamp()));
//        h5m.put("nonceStr",WXPayUtil.generateNonceStr());
//        h5m.put("pack","prepay_id="+toMap.get("prepay_id"));
//        h5m.put("signType","MD5");
//        String paySin = WXPayUtil.generateSignature(h5m, "TBYFSOFTTBYFSOFTTBYFSOFTTBYFSOFT");
//        h5m.put("paySign",paySin);
//
//        System.out.println(JSONObject.toJSONString(h5m));
//        return JSONObject.toJSONString(h5m);
//    }


    /**
     * @Description 微信浏览器内微信支付/公众号支付(JSAPI)
     * @param request
     * @param code
     * @return Map
     */
    @RequestMapping(value="orders", method = RequestMethod.GET)
    @ResponseBody
    public Map orders(HttpServletRequest request, String code) {

        //生成订单号
        String out_trade_no = String.valueOf(System.currentTimeMillis());
        //随机字符串
        String nonce_Str = WXPayUtil.generateNonceStr();

        try {
            //页面获取openId接口
            String getopenid_url = "https://api.weixin.qq.com/sns/oauth2/access_token";
            String  param=
                    "appid="+appid+"&secret="+appsecret+"&code="+code+"&grant_type=authorization_code";
            //向微信服务器发送get请求获取openIdStr
            String openIdStr = HttpRequest.sendGet(getopenid_url, param);
            //转成Json格式
            JSONObject json = JSONObject.parseObject(openIdStr);
            //获取openId
            String openId = json.getString("openid");

            //拼接统一下单地址参数
            Map<String, String> paraMap = new HashMap<String, String>();
            //获取请求ip地址
            String ip = request.getHeader("x-forwarded-for");
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
                ip = request.getHeader("Proxy-Client-IP");
            }
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
                ip = request.getRemoteAddr();
            }
            if(ip.indexOf(",")!=-1){
                String[] ips = ip.split(",");
                ip = ips[0].trim();
            }

            paraMap.put("appid", appid);
            paraMap.put("body", "尧舜商城-订单结算");
            paraMap.put("mch_id", mch_id);
            paraMap.put("nonce_str", WXPayUtil.generateNonceStr());
            paraMap.put("openid", openId);
            //订单号
            paraMap.put("out_trade_no", out_trade_no);
            paraMap.put("spbill_create_ip", ip);
            paraMap.put("total_fee","1");
            paraMap.put("trade_type", "JSAPI");
            // 此路径是微信服务器调用支付结果通知路径随意写
            paraMap.put("notify_url","http://jinzw.natapp1.cc/NotifyServlet");
            String sign = WXPayUtil.generateSignature(paraMap, "TBYFSOFTTBYFSOFTTBYFSOFTTBYFSOFT");
            paraMap.put("sign", sign);
            //将所有参数(map)转xml格式
            String xml = WXPayUtil.mapToXml(paraMap);

            // 统一下单 https://api.mch.weixin.qq.com/pay/unifiedorder
            String unifiedorder_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";

            //发送post请求"统一下单接口"返回预支付id:prepay_id
            String xmlStr = HttpRequest.sendPost(unifiedorder_url, xml);

            //以下内容是返回前端页面的json数据
            //预支付id
            String prepay_id = "";
            if (xmlStr.indexOf("SUCCESS") != -1) {
                Map<String, String> map = WXPayUtil.xmlToMap(xmlStr);
                prepay_id = (String) map.get("prepay_id");
            }
            Map<String, String> payMap = new HashMap<String, String>();
            payMap.put("appId", appid);
            payMap.put("timeStamp", WXPayUtil.getCurrentTimestamp()+"");
            payMap.put("nonceStr", WXPayUtil.generateNonceStr());
            payMap.put("signType", "MD5");
            payMap.put("package", "prepay_id=" + prepay_id);
            String paySign = WXPayUtil.generateSignature(payMap, "TBYFSOFTTBYFSOFTTBYFSOFTTBYFSOFT");
            payMap.put("paySign", paySign);
            return payMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
