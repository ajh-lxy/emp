package com.fh.utils.imgUtil;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.fh.utils.redis.RedisPools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lenovo
 * @title: SendSms
 * @projectName shop_admin_web
 * @description: TODO
 * @date 2019/12/2014:02
 */
public class SendSms {
    private static Logger logger = LoggerFactory.getLogger(SendSms.class);
    public static boolean sendSms(String phone,String code) {
        boolean b = false;
        DefaultProfile profile = DefaultProfile.getProfile("b", "LTAI2ofFrEY4bUTn", "DKsGvH3DZeMeLzBc8cjoatBX2BByyJ");
        IAcsClient client = new DefaultAcsClient(profile);
        Map map =new HashMap();
        map.put("code",code);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "b");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "飞狐教育");
        request.putQueryParameter("TemplateCode", "SMS_180765716");
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(map));
        try {
            CommonResponse response = client.getCommonResponse(request);
            ALIYUN aliyun = JSONObject.parseObject(response.getData(), ALIYUN.class);
            if(aliyun.getCode().equals("OK")){
                b=true;
                Jedis jeDis = RedisPools.getJeDis();
                jeDis.setex("msg_"+phone,1800,code);
                RedisPools.returnJeDis(jeDis);
            }else {
                b=false;
            }
            logger.info(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return b;
    }
}
