package serviceImpl;

import org.apache.commons.io.IOUtils;
import service.ContentDownloader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;


public class HTMLContentDownloader implements ContentDownloader {
    public String downloadContent (String urlAddress) {
        BufferedReader reader=null;
        String context="";
        try {
            URL url = new URL(urlAddress);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            context = IOUtils.toString(reader);
        } catch (IOException e) {
            System.out.println(e.getMessage() + e);
        }
        finally {
            if(reader!=null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return context;
                }
            }
            return context;
        }
    }

}
