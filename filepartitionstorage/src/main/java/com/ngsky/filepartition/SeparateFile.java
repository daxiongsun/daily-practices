package com.ngsky.filepartition;

import com.ngsky.filepartition.utils.MD5Util;

import java.io.*;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.*;

public class SeparateFile {

    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /***
     * @desc 文件分块
     * @author sunyx
     * @date
     * @param filepath: file absolute path
     * @param cellSize: per cell size
     * @return file's md5 value
     */
    public String separate(String filepath, String savepath, int cellSize) {
        if (filepath == null || ("".equals(filepath)) || cellSize <= 0) return "";
        File file = new File(filepath);
        FileInputStream fis = null;
        FileOutputStream fos = null;
        String fileMd5 = MD5Util.getFileMD5(file);
        Properties properties = new Properties();
        properties.setProperty("create_time", sdf.format(new Date()));
        properties.setProperty("file_name", file.getName());
        try {
            fis = new FileInputStream(file);
            // 设定缓冲区
            byte[] buff = new byte[cellSize];
            int tempLength = 0;
            int offset = 0;
            // 循环读取文件
            while ((tempLength = fis.read(buff, 0, cellSize)) != -1) {
                // 计算文件块 hash 值
                String cellHash = MD5Util.MD5(ByteBuffer.wrap(buff));

                File cellFile = new File(savepath + cellHash);
                if (!cellFile.exists()) {
                    cellFile.createNewFile();
                }
                fos = new FileOutputStream(cellFile);
                fos.write(buff, 0, tempLength);
                fos.flush();
                fos.close();
                properties.setProperty(String.valueOf(offset), cellHash);
                offset += 1;
            }
            FileOutputStream propertiesFos = new FileOutputStream(savepath + "config.properties");
            properties.store(propertiesFos, "ConfigFile");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileMd5;
    }

    public boolean combineFile(String sourceFilename, String configFile, String datacenterurl) {
        File file = new File(sourceFilename);
        FileOutputStream fos = null;
        FileInputStream fis = null;
        Properties properties = null;

        if (!file.exists()) {
            try {
                file.createNewFile();
                fos = new FileOutputStream(file);
                properties = new Properties();
                FileInputStream configFis = new FileInputStream(new File(configFile));
                properties.load(configFis);
                Set<Object> keySet = properties.keySet();

                // sort
                Set<String> sortSet = sort(keySet);
                for (Object key : sortSet) {
                    File evlFile = new File(datacenterurl + properties.get(key));
                    fis = new FileInputStream(evlFile);
                    byte[] buff = new byte[(int) evlFile.length()];
                    int tempLength = 0;
                    while ((tempLength = fis.read(buff, 0, (int) evlFile.length())) != -1) {
                        fos.write(buff, 0, tempLength);
                        fos.flush();
                    }
                }
                fos.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }


    // sort
    private Set<String> sort(Set<Object> keys) {
        if (keys == null) return null;
        Set<String> temp = new HashSet<String>();
        Iterator iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            if (key != null && !("file_name").equals(key) && !("create_time").equals(key)) {
                temp.add(key);
            }
        }
        Set<String> result = new TreeSet<String>();
        if (result.addAll(temp)) {
            System.out.println("keys by sorted:" + result);
            return result;
        }
        return null;
    }


    public static void main(String[] args) {
        SeparateFile sf = new SeparateFile();
//        sf.separate("E:\\workspace\\github\\filepartitionstorage\\test\\数据结构与算法+Python语言描述_裘宗燕.pdf", "E:\\workspace\\github\\filepartitionstorage\\test\\", 1024 * 1024);

        sf.combineFile("E:\\workspace\\github\\filepartitionstorage\\test\\source\\hello.pdf",
                "E:\\workspace\\github\\filepartitionstorage\\test\\config.properties",
                "E:\\workspace\\github\\filepartitionstorage\\test\\");
    }
}
