package com.kintexgroup.hkpsi.common.util;

import com.kintexgroup.hkpsi.common.constants.ResponseCode;
import com.kintexgroup.hkpsi.common.exception.BusinessException;
import com.kintexgroup.hkpsi.information.model.LogisticsResult;
import lombok.NonNull;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

/**
 * 快递鸟api
 *
 * @author LMAO
 * @since 2020/12/7 16:41
 */
public final class KdnUtil {
    private KdnUtil() {
    }

    /**
     * 快递鸟物流中心,在途监控模块
     *
     * @param shipperCode  快递公司编码
     * @param logisticCode 物流单号
     * @param requestType  操作代码 8001-即时查询 8008-物流订阅
     * @return LogisticsResult
     * @throws Exception
     */
    public static LogisticsResult kdnApiLogistics(String shipperCode, String logisticCode, String requestType) throws Exception {
        return switch (requestType) {
            case "8001" -> orderTracesSubByJson(shipperCode, logisticCode, requestType);
            case "8008" -> subscribe(shipperCode, logisticCode, requestType);
            default -> new LogisticsResult();
        };
    }

    private static LogisticsResult subscribe(String shipperCode, String logisticCode, String requestType) {

        return null;
    }


    /**
     * Json方式  即时物流查询
     *
     * @param shipperCode  快递公司编码
     * @param logisticCode 物流单号
     * @return LogisticsResult
     * @throws Exception
     */
    private static LogisticsResult orderTracesSubByJson(String shipperCode, String logisticCode, String requestType) throws Exception {
        HashMap<String, String> map = new HashMap<>(16);
        map.put("LogisticCode", logisticCode);
        map.put("ShipperCode", shipperCode);

        String requestData = JsonUtil.toJsonString(map);
        Map<String, String> params = new HashMap<>(5);
        params.put("RequestData", urlEncoder(requestData));
        params.put("EBusinessID", "1689142");
        params.put("RequestType", requestType);
        String dataSign = encrypt(requestData, "8f98cda6-f6c5-4d76-8392-d1eeca67e72f");
        params.put("DataSign", urlEncoder(dataSign));
        params.put("DataType", "2-json");

        String resultStr = sendPost(params);

        return JsonUtil.toLogisticsResult(resultStr);
    }


    public static LogisticsResult wrapperResult(LogisticsResult raw, String shipperName) {

        raw.setStateEx(wrapperCode(raw.getStateEx()));

        var list = raw.getTraces();
        list.forEach(action -> {
                if (action.getAction() != null) {
                    action.setAction(wrapperCode(action.getAction()));
                }
            }
        );
        raw.setTraces(list);
        raw.setShipperCode(shipperName);
        raw.setState(wrapperCode(raw.getState()));
        return raw;
    }

    private static String wrapperCode(@NonNull String code) {
        return switch (code) {
            case "0" -> "暂无轨迹信息";
            case "1" -> "已揽收";
            case "2" -> "在途中";
            case "201" -> "到达派件城市";
            case "202" -> "派件中";
            case "211" -> "已放入快递柜或驿站";
            case "3" -> "已签收";
            case "301" -> "正常签收";
            case "302" -> "派件异常后最终签收";
            case "304" -> "代收签收";
            case "311" -> "快递柜或驿站签收";
            case "4" -> "问题件";
            case "401" -> "发货无信息";
            case "402" -> "超时未签收";
            case "403" -> "超时未更新";
            case "404" -> "拒收(退件)";
            default -> throw new BusinessException(ResponseCode.FAIL);
        };
    }

    /**
     * MD5加密
     *
     * @param str 内容
     * @throws Exception
     */
    @SuppressWarnings("unused")
    private static String md5(String str) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes(StandardCharsets.UTF_8));
        byte[] result = md.digest();
        StringBuilder sb = new StringBuilder(32);
        for (byte b : result) {
            int val = b & 0xff;
            if (val <= 0xf) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(val));
        }
        return sb.toString().toLowerCase();
    }

    /**
     * base64编码
     *
     * @param str 内容
     */
    private static String base64(String str) {
        return base64Encode(str.getBytes(StandardCharsets.UTF_8));
    }

    @SuppressWarnings("unused")
    private static String urlEncoder(String str) {
        return URLEncoder.encode(str, StandardCharsets.UTF_8);
    }

    /**
     * 电商Sign签名生成
     *
     * @param content  内容
     * @param keyValue Appkey
     * @return DataSign签名
     * @throws UnsupportedEncodingException ,Exception
     */
    @SuppressWarnings("unused")
    private static String encrypt(String content, String keyValue) throws Exception {
        if (keyValue != null) {
            return base64(md5(content + keyValue));
        }
        return base64(md5(content));
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param params 请求的参数集合
     * @return 远程资源的响应结果
     */
    @SuppressWarnings("unused")
    private static String sendPost(Map<String, String> params) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URL realUrl = new URL("https://api.kdniao.com/api/dist");
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // POST方法
            conn.setRequestMethod("POST");
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.connect();
            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), StandardCharsets.UTF_8);
            // 发送请求参数            
            if (params != null) {
                StringBuilder param = new StringBuilder();
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    if (param.length() > 0) {
                        param.append("&");
                    }
                    param.append(entry.getKey());
                    param.append("=");
                    param.append(entry.getValue());
                }
                out.write(param.toString());
            }
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result.toString();
    }

    private static final char[] BASE64_ENCODE_CHARS = new char[]{
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
        'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
        'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
        'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
        'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
        'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
        'w', 'x', 'y', 'z', '0', '1', '2', '3',
        '4', '5', '6', '7', '8', '9', '+', '/'};

    private static String base64Encode(byte[] data) {
        StringBuilder sb = new StringBuilder();
        int len = data.length;
        int i = 0;
        int b1;
        int b2;
        int b3;
        while (i < len) {
            b1 = data[i++] & 0xff;
            if (i == len) {
                sb.append(BASE64_ENCODE_CHARS[b1 >>> 2]);
                sb.append(BASE64_ENCODE_CHARS[(b1 & 0x3) << 4]);
                sb.append("==");
                break;
            }
            b2 = data[i++] & 0xff;
            int i1 = ((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4);
            if (i == len) {
                sb.append(BASE64_ENCODE_CHARS[b1 >>> 2]);
                sb.append(BASE64_ENCODE_CHARS[i1]);
                sb.append(BASE64_ENCODE_CHARS[(b2 & 0x0f) << 2]);
                sb.append("=");
                break;
            }
            b3 = data[i++] & 0xff;
            sb.append(BASE64_ENCODE_CHARS[b1 >>> 2]);
            sb.append(BASE64_ENCODE_CHARS[i1]);
            sb.append(BASE64_ENCODE_CHARS[((b2 & 0x0f) << 2) | ((b3 & 0xc0) >>> 6)]);
            sb.append(BASE64_ENCODE_CHARS[b3 & 0x3f]);
        }
        return sb.toString();
    }

}


