package com.ssm;

import com.ssm.utils.GzipUtil;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;

/**
 * @Author: Think
 * @Date: 2019/3/26
 * @Time: 10:35
 */
public class GzipTest extends BaseTest {


    @Test
    public void compressTest() {
    byte[] compressByte = GzipUtil.compress("F:\\private_workspace", GzipUtil.GZIP_ENCODE_UTF_8);
        if (null != compressByte) {
            System.out.println(new String(compressByte));
        } else {
            System.out.println("compressByte is null");
        }
    }

    @Test
    public void unCompressTest() throws Exception {
        File file = new File("F:\\private_workspace\\压缩测试文件.txt");
//        byte[] compressByte = GzipUtil.uncompress(GzipUtil.compress(FileUtils.readFileToString(file, GzipUtil.GZIP_ENCODE_UTF_8), GzipUtil.GZIP_ENCODE_UTF_8));
        byte[] compressByte = GzipUtil.compress(FileUtils.readFileToString(file, GzipUtil.GZIP_ENCODE_UTF_8), GzipUtil.GZIP_ENCODE_UTF_8);
        if (null != compressByte) {
            FileUtils.writeByteArrayToFile(new File("F:\\private_workspace\\压缩后文件.zip"), compressByte);
        } else {
            System.out.println("compressByte is null");
        }
    }

}
