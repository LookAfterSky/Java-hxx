package test;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: check-data
 * @description: �ļ����������ࣨһ����ȡ�����ļ��Լ�ɾ��Ŀ¼���������ݵĲ���
 * @author: HXX
 * @create: 2019-06-19 15:11
 **/
public class FileUtil {
    //˽�й���
    private FileUtil(){

    }
    //��ȡȫ���ļ�
    public static List<File> findAllFiles(File dir){
        List<File> fileList = new ArrayList<>();
        if (!dir.exists()){
            throw new IllegalArgumentException("Ŀ¼"+dir+"�����ڣ�");
        }
        if (!dir.isDirectory()){
            throw new IllegalArgumentException(dir+"����Ŀ¼!");
        }
        List<File> dirList = new LinkedList<>();
        File[] files = dir.listFiles();
        FileUtil.getDirList(files,dirList);
        FileUtil.getFileList(files,fileList);
        File dirTemp;
        while (!dirList.isEmpty()){
            dirTemp = ((LinkedList<File>) dirList).removeFirst();
            files = dirTemp.listFiles();
            getDirList(files,dirList);
            getFileList(files,fileList);
        }
        return fileList;
    }
    public static void getDirList(File[] files,List<File> dirList){
        if (files!=null&&files.length>0){
            for (File file : files) {
                if (file.isDirectory()){
                    dirList.add(file);
                }
            }
        }
    }
    public static void getFileList(File[] files,List<File> fileList){
        if (files!=null&&files.length>0){
            for (File file : files) {
                if (file.isFile()){
                    fileList.add(file);
                }
            }
        }
    }

    //ɾ�������ļ����ļ���
    public static void deleteAllDir(File dir){
        LinkedList<File> dirList = new LinkedList<>();
        List<File> fileList = FileUtil.findAllFiles(dir);
        for (File file : fileList) {
            file.delete();
        }
        File[] files = dir.listFiles();
        getDirList(files,dirList);
        while (!dirList.isEmpty()){
            File file = dirList.removeFirst();
            files = file.listFiles();
            if (files!=null&&files.length>0){
                dirList.add(file);
                getDirList(files,dirList);
            }else{
                file.delete();
            }
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        File file = new File("C:\\Users\\HXX\\Desktop\\����\\data");
        deleteAllDir(file);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
