package com.ssm.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 *
 * gzip工具类
 *
 * @Author: Think
 * @Date: 2019/3/26
 * @Time: 9:48
 */
public class GzipUtil {

    public static final String GZIP_ENCODE_UTF_8 = "UTF-8";

    public static final String GZIP_ENCODE_ISO_8859_1 = "ISO-8859-1";

    private static final Logger logger = LoggerFactory.getLogger(GzipUtil.class);


    /**
     * gzip解压
     * @return
     */
    public static byte[] uncompress(byte[] compressBytes) {
        if (null == compressBytes || compressBytes.length == 0) {
            return null;
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(compressBytes);
        try {
            GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = gzipInputStream.read(buffer)) >= 0) {
                outputStream.write(buffer, 0, length);
            }
        } catch (Exception e) {
            logger.error("exception: {}", e.getMessage());
        }
        return outputStream.toByteArray();
    }

    /**
     * gzip压缩
     * @param str
     * @param encoding
     * @return
     */
    public static byte[] compress(String str, String encoding) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        GZIPOutputStream gzipOutputStream;
        try {
            gzipOutputStream = new GZIPOutputStream(outputStream);
            gzipOutputStream.write(str.getBytes(encoding));
            gzipOutputStream.close();
        } catch (Exception e) {
            logger.error("exception: {}", e.getMessage());
        }
        return outputStream.toByteArray();
    }

    public static void returnGzipResponse(HttpServletResponse response, String str, String encoding) {
        try {
            byte[] gzipBytes = compress(str, encoding);
            if (null != gzipBytes) {
                response.setHeader("Content-Encoding", "gzip");
                response.setContentType("text/html");
                OutputStream out = response.getOutputStream();
                out.write(gzipBytes);
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
