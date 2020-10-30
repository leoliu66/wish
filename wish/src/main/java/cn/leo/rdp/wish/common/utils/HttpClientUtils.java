package cn.leo.rdp.wish.common.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @Auther: leo
 * @Date: 2020/9/13 16:40
 * @Description: http https 请求工具
 */
public final class HttpClientUtils {
    private static final String CHART_SET = "UTF-8";

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    public static String post(String url, String xml) {
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httppost = new HttpPost(url);
        HttpEntity eitity;
        try {
            eitity = new ByteArrayEntity(xml.getBytes("utf-8"), ContentType.TEXT_XML);
            httppost.setEntity(eitity);
            logger.info("executing request " + httppost.getURI());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String content = EntityUtils.toString(entity, "utf-8");
                    return content;
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            logger.error("发送post请求异常：", e);
        } catch (UnsupportedEncodingException e) {
            logger.error("发送post请求异常：", e);
        } catch (IOException e) {
            logger.error("发送post请求异常：", e);
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                logger.error("关闭post请求异常：", e);
            }
        }
        return "";
    }

    public static String post(String url, String xml,ContentType contentType) {
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httppost = new HttpPost(url);
        HttpEntity eitity;
        try {
            eitity = new ByteArrayEntity(xml.getBytes("utf-8"), contentType);
            httppost.setEntity(eitity);
            logger.info("executing request " + httppost.getURI());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String content = EntityUtils.toString(entity, "utf-8");
                    return content;
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            logger.error("发送post请求异常：", e);
        } catch (UnsupportedEncodingException e) {
            logger.error("发送post请求异常：", e);
        } catch (IOException e) {
            logger.error("发送post请求异常：", e);
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                logger.error("关闭post请求异常：", e);
            }
        }
        return "";
    }


    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        if(url.indexOf("ocr_idcardocr") == -1){
            logger.info("Http请求地址："+url+",请求参数："+param);
        }
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.error("发送请求失败，原因：", e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                logger.error("发送请求失败，原因：", ex);
            }
        }
        if(url.indexOf("ocr_idcardocr") == -1){
            logger.info("Http接收参数："+result);
        }
        return result;
    }

    public static String sendGet(String url, String params) {
        String readResult = null;
        URLConnection connection = null;
        String URL = url + "?" + params;
        logger.info(URL);
        BufferedReader br = null;
        try {
            connection = new URL(URL).openConnection();
            HttpURLConnection con = (HttpURLConnection) connection;
            con.setConnectTimeout(15000);
            con.setReadTimeout(15000);
            con.connect();
            int status = con.getResponseCode();
            if (HttpURLConnection.HTTP_OK == status) {
                InputStream fin = con.getInputStream();
                br = new BufferedReader(new InputStreamReader(fin, "utf-8"));
                StringBuffer buffer = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    buffer.append(temp);
                }

                readResult = buffer.toString();
                logger.info(readResult);
                return readResult;
            }
        } catch (SocketTimeoutException e) {
            logger.error("发送请求失败，原因：", e);
        } catch (java.net.UnknownHostException e) {
            logger.error("发送请求失败，原因：", e);
        } catch (IOException e) {
            logger.error("发送请求失败，原因：", e);
        } catch (Exception e) {
            logger.error("发送请求失败，原因：", e);
        }finally{
            if(br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    logger.error("发送请求失败，原因：", e);
                }
            }
        }
        return null;
    }


    /**
     *
     *
     * @Title:sendPostByJson:(方法名).
     * @Description:JSON字符串 CONTENT_TYPE:application/json
     * @Date: 2020年1月10日 下午4:32:17
     * @param urlAddress
     * @param param
     * @return
     */
    public static String sendPostByJson(String urlAddress, String param) {
        StringBuffer result = new StringBuffer("");
        logger.info("Http请求地址："+urlAddress+",请求参数："+param);
        try {

            URL url = new URL(urlAddress);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Charset", CHART_SET);

            byte[] data = param.getBytes();
            connection.setRequestProperty("Content-Length", String.valueOf(data.length));
            connection.connect();

            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.write(param.toString().getBytes(CHART_SET));
            out.flush();
            out.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), CHART_SET));
            String lines;

            while ((lines = reader.readLine()) != null) {
                result.append(lines);
            }

            reader.close();
            connection.disconnect();


        } catch (Exception e) {
            logger.error("发送请求失败，原因：", e);
        }
        logger.info("Http接收参数："+result.toString());
        return result.toString();
    }
    
    public static String postJSON(String urlAddress, String json) throws Exception{

       // logger.info("HttpUtils postJSON URL is {},  param are {}",urlAddress, json);

        StringBuffer result = new StringBuffer("");

        URL url = new URL(urlAddress);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(8000);  
        connection.setReadTimeout(8000);

        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Charset", "UTF-8");

        byte[] data = json.getBytes();
        connection.setRequestProperty("Content-Length", String.valueOf(data.length));
        connection.connect();
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.write(json.toString().getBytes("UTF-8"));
        out.flush();
        out.close();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String lines;

        while ((lines = reader.readLine()) != null) {
            result.append(lines);
        }

        reader.close();
        connection.disconnect();


       // logger.info("HttpUtils postJSON result are {}", result.toString());

        return result.toString();
    }


    /**
     *
     *
     * @Title:sendPostByFormData:(方法名).
     * @Description:表单数据类型CONTENT_TYPE:multipart/form-data
     * @Date: 2020年1月10日 下午4:32:51
     * @param url
     * @param param
     * @return
     */
    public static String sendPostByFormData(String url, List<BasicNameValuePair> param) {
        String result = "";
        logger.info("Http请求地址："+url+",请求参数："+param);
        try {
            HttpClient httpClient = HttpClients. createDefault();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(param,"utf-8"));
            HttpResponse response=httpClient.execute(httpPost);
            if(response != null && response.getStatusLine().getStatusCode() == 200) {
                result=EntityUtils.toString(response.getEntity());
            }
        } catch (Exception e) {
            logger.error("发送请求失败，原因：", e);
        }
        logger.info("Http接收参数："+result);
        return result;
    }
    
    /**
     * 请求webservice
     * @param url
     * @param xml
     * @return
     */
    public static String postWs(String url,String xml) throws Exception{
    	OutputStream os = null;
    	InputStream is = null;
    	HttpURLConnection conn = null;
    	try {
    		URL wsUrl = new URL(url);
    		conn = (HttpURLConnection) wsUrl.openConnection();
    		conn.setDoInput(true);
    		conn.setDoOutput(true);
    		conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
    		conn.setRequestMethod("POST");
    		conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
    		os = conn.getOutputStream();
    		os.write(xml.getBytes());
    		is = conn.getInputStream();
    		
    		byte[] b = new byte[1024];
    		int len = 0;
    		String result = "";
    		while((len = is.read(b)) != -1){
    			String s = new String(b,0,len,"UTF-8");
    			result += s;
    		}
    		return result;
		} finally {
			if(conn != null) {
				conn.disconnect();
			}
			if(is != null) {
				is.close();
			}
			if(os != null) {
				os.close();
			}
			
		}
    }
}

