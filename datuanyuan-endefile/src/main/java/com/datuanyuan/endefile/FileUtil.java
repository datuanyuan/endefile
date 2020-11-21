package com.datuanyuan.endefile;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 * 文件分片加密解密
 *
 * @author weiyuan
 * @version 1.0
 */
public class FileUtil {

    private static String SEPARATOR = "******file_encode_separator******";//文件分段加密分隔符

    private static int EN_LIMIT_SIZE = 1024 * 1024;//加密文件临界大小，1M.

    private static int DE_LIMIT_SIZE = 2 * 1024 * 1024;//解密文件临界大小，2M.

    /**
     * 加密文件
     *
     * @return
     * @throws Exception
     */
    public static byte[] encryptFile(byte[] data) throws Exception {
        if (data.length > EN_LIMIT_SIZE) {//大于1M
            /**
             * 取前1M并且加密
             */
            byte[] data_1m = Arrays.copyOf(data, EN_LIMIT_SIZE);
            byte[] data_1m_en = SM2Util.encrypt(SM2Util.PUBLIC_KEY, data_1m);
            /**
             * 读取剩余的文件
             */
            byte[] data_left = Arrays.copyOfRange(data, EN_LIMIT_SIZE, data.length);
            /**
             * 合并加密的1M文件和分隔符
             */
            byte[] data_1m_en_separator = ArrayUtils.byteMerger(data_1m_en, SEPARATOR.getBytes());
            /**
             * 填充至2M
             */
            byte[] data_padding = new byte[DE_LIMIT_SIZE - data_1m_en_separator.length];
            Arrays.fill(data_padding, (byte) 1);
            byte[] data_2m = ArrayUtils.byteMerger(data_1m_en_separator, data_padding);
            /**
             *生成最终加密文件
             */
            return ArrayUtils.byteMerger(data_2m, data_left);
        } else {//小于等于1M，全部加密
            return SM2Util.encrypt(SM2Util.PUBLIC_KEY, data);
        }
    }

    /**
     * 解密文件
     *
     * @return
     */
    public static byte[] decryptFile(byte[] data) throws Exception {
        if (data.length > DE_LIMIT_SIZE) {//大于2M
            /**
             * 取前2M并且解密
             */
            byte[] data_2m = Arrays.copyOf(data, DE_LIMIT_SIZE);
            int index_separator = ArrayUtils.searchInByteArray(data_2m, SEPARATOR.getBytes());
            if (index_separator > 0) {//存在分隔符
                byte[] data_pre_en = Arrays.copyOf(data_2m, index_separator);
                byte[] data_pre = SM2Util.decrypt(SM2Util.PRIVATE_KEY, data_pre_en);
                byte[] data_left = Arrays.copyOfRange(data, DE_LIMIT_SIZE, data.length);//取2M后的剩余部分文件
                return ArrayUtils.byteMerger(data_pre, data_left);//生成最终解密文件
            } else {
                return SM2Util.decrypt(SM2Util.PRIVATE_KEY, data);
            }
        } else {//文件小于2M，采用全部解密
            return SM2Util.decrypt(SM2Util.PRIVATE_KEY, data);
        }
    }

    /**
     * 读取文件
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static byte[] readFile(String filePath) throws IOException {
        RandomAccessFile raf = null;
        byte[] data;
        try {
            raf = new RandomAccessFile(filePath, "r");
            data = new byte[(int) raf.length()];
            raf.read(data);
            return data;
        } finally {
            if (raf != null) {
                raf.close();
            }
        }
    }

    /**
     * 写入文件
     *
     * @param filePath
     * @param data
     * @throws IOException
     */
    public static void writeFile(String filePath, byte[] data) throws IOException {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(filePath, "rw");
            raf.write(data);
        } finally {
            if (raf != null) {
                raf.close();
            }
        }
    }


    public static void main(String[] args) throws Exception {
        String resource = "E:\\source.doc";

        String file_en = "E:\\source_en.doc";

        String file_de = "E:\\source_de.doc";

        writeFile(file_en, encryptFile(readFile(resource)));

        writeFile(file_de, decryptFile(readFile(file_en)));
    }
}
