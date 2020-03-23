package cn.jjdcn.etas.fdfs.utils;

import com.google.common.io.Files;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Map;

public class FastdfsClient {
    private static final Logger LOG = LoggerFactory.getLogger(FastdfsClient.class);
    private static StorageClient1 storageClient1 = null;
    private static String trackerServerUrl = null;

    //初始化FastDFS Client
    static {
        try {
            ClassPathResource classPathResource = new ClassPathResource("fdfs_client.conf");
            ClientGlobal.init(classPathResource.getClassLoader().getResource("fdfs_client.conf").toURI().getPath());
            TrackerClient trackerClient = new TrackerClient(ClientGlobal.g_tracker_group);
            TrackerServer trackerServer = trackerClient.getConnection();

            trackerServerUrl = trackerServer.getInetSocketAddress().getHostString();
            LOG.info("trackerServerUrl={}",trackerServerUrl);
            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);

            storageClient1 = new StorageClient1(trackerServer, storageServer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String uploadFile(File file , Map<String, String> meta) {
        try {
            byte[] buff = readFromFile(file);
            NameValuePair[] nameValuePairs = null;
            if (meta != null) {
                nameValuePairs = new NameValuePair[meta.size()];
                int index = 0;
                for (Map.Entry<String, String> entry : meta.entrySet()) {
                    String name = entry.getKey();
                    String value = entry.getValue();
                    nameValuePairs[index++] = new NameValuePair(name, value);
                }
            }
            String fileExt = Files.getFileExtension(file.getName()) ;
            return storageClient1.upload_file1(buff, fileExt, nameValuePairs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String uploadFile(byte[] bytes ,String fileExt, Map<String, String> meta) {
        try {
//            byte[] buff = readFromFile(file);
            NameValuePair[] nameValuePairs = null;
            if (meta != null) {
                nameValuePairs = new NameValuePair[meta.size()];
                int index = 0;
                for (Map.Entry<String, String> entry : meta.entrySet()) {
                    String name = entry.getKey();
                    String value = entry.getValue();
                    nameValuePairs[index++] = new NameValuePair(name, value);
                }
            }
//            String fileExt = Files.getFileExtension(file.getName()) ;
            return storageClient1.upload_file1(bytes, fileExt, nameValuePairs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static int deleteFile(String fileid) {
        try {
            return storageClient1.delete_file1(fileid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int downloadFile(String fileId, String filePath) {
        FileOutputStream fos = null;
        try {
            byte[] content = storageClient1.download_file1(fileId);
            writeToFile(content, filePath);
            return 0;
        } catch (Exception e) {
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
        return -1;
    }


    private static byte[] readFromFile(File file) throws Exception {
        try (FileInputStream inputStream = new FileInputStream(file)) {

            FileChannel fileChannel=inputStream.getChannel();
            int fileSize = (int)fileChannel.size() ;
            ByteBuffer buffer = ByteBuffer.allocate(fileSize);
            fileChannel.read(buffer);
            return buffer.array();
        }
    }


    public static void writeToFile(byte[] content, String filePath) throws Exception {
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            outputStream.write(content);
        }
    }
}
