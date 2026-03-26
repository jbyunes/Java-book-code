package chapter.novelties.trywithresources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileCopyExample {
    public static void main(String[] args) {
//        InputStream in = null;
//        OutputStream out = null;
//        try {
//            in = new FileInputStream("source.txt");
//            out = new FileOutputStream("destination.txt");
//            int data;
//            while ( (data=in.read()) != -1) { out.write(data); }
//        } catch (Exception e) { e.printStackTrace(); }
//        finally {
//            if (in!=null)
//                try { in.close(); }
//                catch (Exception e) {  e.printStackTrace(); }
//            if (out!=null)
//                try { out.close(); }
//                catch (Exception e) { e.printStackTrace(); }
//        }
        try (InputStream in = new FileInputStream("source.txt");
             OutputStream out = new FileOutputStream("destination.txt") ) {
                int data;
                while ( (data=in.read()) != -1) { out.write(data); }
            }
            catch (Exception e) { e.printStackTrace(); }

    }
}
