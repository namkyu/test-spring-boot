package com.kyu.boot.controller;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Http {
    private static final String DEFAULT_ENCODING = "UTF-8";

    private String url;
    private String encoding;
    private Map param;


    public Http(String url) {
        this(url, null);
    }

    public Http(String url, String encoding) {
        this.encoding = encoding == null ? DEFAULT_ENCODING : encoding;
        this.url = url;

        param = new HashMap();
    }

    /**
     * Http 전송
     *
     * @return
     */
    public String submit() {
        String result = null;

        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(url);

        try {
            Part[] parts = new Part[param.size()];
            Iterator it = param.keySet().iterator();
            int index = 0;
            while (it.hasNext()) {
                String key = (String) it.next();
                Object value = param.get(key);

                if (value instanceof File) {
                    parts[index++] = new FilePart(
                            key,
                            ((File) value).getName(),
                            (File) value,
                            null,
                            encoding);
                } else {
                    parts[index++] = new StringPart(key, (String) value, encoding);
                }
            }
            method.setRequestEntity(new MultipartRequestEntity(parts, method.getParams()));

            client.executeMethod(method);

            // read result
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(method.getResponseBodyAsStream(), encoding));
            String buffer = null;
            StringBuffer tmp = new StringBuffer();

            while ((buffer = br.readLine()) != null) {
                tmp.append(buffer).append("\r\n");
            }

            result = tmp.toString();
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            method.releaseConnection();
        }

        return result;
    }

    /**
     * 일반 파라메터 추가
     *
     * @param name  파라메터 이름
     * @param value 값
     * @return
     */
    public Http addParam(String name, String value) {
        param.put(name, value);

        return this;
    }

    /**
     * 업로드할 파일을 파라메터로 추가
     *
     * @param name
     * @param file
     * @return
     */
    public Http addParam(String name, File file) {
        param.put(name, file);

        return this;
    }

}



