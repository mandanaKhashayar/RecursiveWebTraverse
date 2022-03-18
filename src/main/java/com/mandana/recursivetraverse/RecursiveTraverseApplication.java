package com.mandana.recursivetraverse;

import serviceImpl.*;
import service.*;
import model.DirectoryAddress;
import repositoryImpl.FileDetailsRepository;

import java.io.IOException;
import java.util.List;
import java.util.Set;


public class RecursiveTraverseApplication {


    public static void main(String[] args) {
        Crawler crawler = new Crawler();
        List<DirectoryAddress> uniqueURL = crawler.get_links(Constants.BASE_URL_ADDRESS, Constants.BASE_SITE, "./", 0);

            for (DirectoryAddress directoryAddress : uniqueURL) {
                new Thread(new Runnable() {
                    public void run() {
                        DownloadController  downloader = new DownloadFileController(new HTMLContentDownloader(),
                                new DirectoryUtils(), new FileDetailsRepository());
                        try {
                        downloader.download(directoryAddress);
                     } catch (IOException e) {
                            e.printStackTrace();
                        }}
                }).start();
            }


    }


}
