package com.scholarship.demo.utils;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;

public class sendMessage {
    /**
     * 腾讯云短信,100条一个月
     * 方法说明
     *
     * @param phone
     * @return void
     * @Discription:扩展说明
     */
    public static void sendMsgByTxPlatform(String phone) throws Exception {

        // 短信应用SDK AppID
        // 1400开头
        int appId = 1400200780;

        // 短信应用SDK AppKey
        String appKey = "8bb5eeab4662d34f6b48b6354c0510ef";

        // 需要发送短信的手机号码
        // String[] phoneNumbers = {"15212111830"};

        // 短信模板ID，需要在短信应用中申请
        //NOTE: 这里的模板ID`7839`只是一个示例，真实的模板ID需要在短信控制台中申请
        int templateId = 148464;

        // 签名
        // NOTE: 这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台中申请，另外签名参数使用的是`签名内容`，而不是`签名ID`
        String smsSign = "我的小碗汤";

        SmsSingleSender sSender = new SmsSingleSender(appId, appKey);
        //第一个参数0表示普通短信,1表示营销短信
        SmsSingleSenderResult result = sSender.send(0, "86",
                phone,
                getNewRandomCode(6) + "为您的登录验证码，请于" + 3 + "分钟内填写。如非本人操作，请忽略本短信。", "", "");

        if (result.result != 0) {
            throw new Exception("send phone validateCode is error" + result.errMsg);
        }
    }

    public static String getNewRandomCode(int codeLen) {
        java.util.Random randomCode = new java.util.Random();
        String strCode = "";
        while (codeLen > 0) {
            int charCode = randomCode.nextInt(9);
            System.out.println(charCode);
            strCode += charCode;
            codeLen--;
        }
        return strCode;
    }
}
