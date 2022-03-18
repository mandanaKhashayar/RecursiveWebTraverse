package testServiceImpl;

import model.DirectoryAddress;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import serviceImpl.Crawler;
import serviceImpl.DirectoryUtils;

public class CrawlerTest {
    Crawler crawler;
    @Before
    public final void setUp() { crawler=new Crawler(); }

    @After// we need to do that because these usecases have side effects on each other
    public final void tearDown() { Crawler.resetCrawler();}
    @Test
    public void givenUrlAndBaseSiteParentDirectoryDepth_whenDepthIsLessThanMax_thenReturnAllLinks() {
        Crawler crawler=new Crawler();
        var actualArea = crawler.get_links("https://www.tretton37.com","https://tretton37.com","directory",0);
        Assert.assertEquals(47, actualArea.size());
        Assert.assertTrue(actualArea.stream().anyMatch(directoryAddress -> "directory/www-tretton37-com".equals(directoryAddress.getNodeDirectory()))
                );
    }
    @Test
    public void givenUrlAndBaseSiteParentDirectoryDepth_whenDepthIsMoreThanMax_thenReturnNull() {
        Crawler crawler=new Crawler();
        var actualArea = crawler.get_links("https://www.tretton37.com","www.tretton37.com","directory",3);
        Assert.assertEquals(null, actualArea);
      }
    @Test
    public void givenUrlAndBaseSiteParentDirectoryDepth_whenDepthIsEqualThanMax_thenNull() {
      Crawler crawler=new Crawler();
        var actualArea = crawler.get_links("https://www.tretton37.com","www.tretton37.com","directory",2);
        Assert.assertEquals(0, actualArea.size());
    }
}
