import com.mandana.recursivetraverse.Constants;
import model.DirectoryAddress;
import model.FileDetails;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.rules.TemporaryFolder;
import service.ContentDownloader;
import serviceImpl.Crawler;
import serviceImpl.HTMLContentDownloader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class RecursiveTraverseApplicationTest {
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
    private File tempSource;
    private BufferedWriter bufferedWriter = null;
    @Before
    public void setUp() throws Exception {
        temporaryFolder.create();
        tempSource = temporaryFolder.newFile("temp-file-in-plugin-monitor-test");
        bufferedWriter = new BufferedWriter(new FileWriter(tempSource));
    }

    @Test
    public void givenUrlAndBaseSiteParentDirectoryDepth_whenDepthIsMoreThanMax_thenReturnNull() throws Exception {
        Crawler crawler = new Crawler();
        List<DirectoryAddress> uniqueURL = crawler.get_links(Constants.BASE_URL_ADDRESS, Constants.BASE_SITE, "D://", 0);
        DirectoryAddress directoryAddress = uniqueURL.get(0);
        ContentDownloader downloader = new HTMLContentDownloader();
        FileDetails fileDetails = new FileDetails();
        fileDetails.setContent(downloader.downloadContent(directoryAddress.getUrlAddress()).toString());
        fileDetails.setDirectory(Paths.get(directoryAddress.getNodeDirectory()));
        bufferedWriter.write(fileDetails.getContent());
        assertTrue(tempSource.exists());
        assertTrue(tempSource.isFile());
        //TODO: check the contain of file

    }

    @After
    public void tearDown() throws IOException {
        tempSource.deleteOnExit();
        temporaryFolder.delete();
        bufferedWriter.close();
    }
}
