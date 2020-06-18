package com.msb.controller.system;

import com.msb.constant.Constants;


import com.msb.core.domain.AjaxResult;
import com.msb.utils.Base64;
import com.msb.utils.RedisUtils;
import com.msb.utils.VerifyCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 验证码控制层
 */
@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("/getCaptcha")
    public AjaxResult getCaptcha(HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException {
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        String uuid = UUID.randomUUID().toString();
        redisUtils.set(Constants.VERIFY_CODE_KEY + uuid , verifyCode, Constants.VERIFY_CODE_EXPIRE);
        ByteArrayOutputStream outPut = new ByteArrayOutputStream();
        //生成图片
        int w = 111; int h = 36;
        try {
            VerifyCodeUtils.outputImage(w, h, outPut, verifyCode);
        } catch (IOException e) {
            e.printStackTrace();
            AjaxResult.error(Constants.ERROR_CODE, e.getMessage());
        } finally {
            outPut.close();
        }
        char[] datas = Base64.encode(outPut.toByteArray());
        Map<String, Object> map = new HashMap<>();
        map.put("image", new String(datas));
        map.put("uuid", uuid);
        return AjaxResult.success(Constants.SUCCESS_CODE, "", map);
    }
}
