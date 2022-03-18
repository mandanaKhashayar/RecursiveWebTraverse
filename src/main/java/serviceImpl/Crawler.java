package serviceImpl;

import com.mandana.recursivetraverse.Constants;
import model.DirectoryAddress;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Safelist;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Crawler {
    //HashSet contains unique elements only so we dont need to lock it when we add to it
    private static List<DirectoryAddress> addresses = new ArrayList<>();
    private static Set<String> uniqueURLs = new HashSet<String>();

    public static void resetCrawler() {
        addresses =  new ArrayList<>();
        uniqueURLs = new HashSet<String>();

    }

    public List<DirectoryAddress> get_links(String url, String baseSite, String parentDirectory, int depth) {
        try {
            if (depth > Constants.MAX_DEPTH) {
                return null;
            }
            if (depth == 0) {
                uniqueURLs.add(url);
                addDirectoryAddress(parentDirectory + "/" + DirectoryUtils.getPath(url),url);

            }
            var level = depth + 1;
            Document doc = Jsoup.connect(url).get();
            Elements links = doc.select("a[href]");
            if (links.isEmpty()) {
                return null;
            }
            links.parallelStream().map((link) -> link.attr("abs:href"))
                    .forEachOrdered((this_url) -> {
                        boolean canAdd;
                        synchronized (this) {// we need to lock it since two threads my have the same url
                            canAdd = uniqueURLs.add(this_url);

                        }
                        if (canAdd && validUrl(this_url, baseSite)) {
                            System.out.println(this_url);
                            var nodeDirectory = parentDirectory + "/" + DirectoryUtils.getPath(url);
                            get_links(this_url, baseSite, nodeDirectory, level);
                            addDirectoryAddress(nodeDirectory,this_url);

                        }
                    });
            return addresses;
        } catch (IOException ex) {

            return null;
        }

    }

    private boolean validUrl(String this_url, String baseSite) {

        return this_url.contains(baseSite) && !this_url.contains(Constants.EMAIL_ICON) &&
                Jsoup.isValid(baseSite, Safelist.basicWithImages());
    }
    private void addDirectoryAddress(String nodeDirectory,String url){

        DirectoryAddress directoryAddress = new DirectoryAddress();
        directoryAddress.setNodeDirectory(nodeDirectory);
        directoryAddress.setUrlAddress(url);
        addresses.add(directoryAddress);
         }
}


