package testServiceImpl;

import org.junit.Assert;
import org.junit.Test;
import serviceImpl.DirectoryUtils;

public class DirectoryUtilesTest {

    @Test
    public void givenLinkWithoutPath_whenGetPath_thenReturnDirectoryPath() {
        var actualArea = DirectoryUtils.getPath("https://www.tretton37.com");
        var expectedArea = "www-tretton37-com";
        Assert.assertEquals(expectedArea, actualArea);
    }

    @Test
    public void givenLinkWithoutPathWithPostFix_whenGetPath_thenReturnDirectoryPath() {
        var actualArea = DirectoryUtils.getPath("http://www.tretton37.com/");
        var expectedArea = "www-tretton37-com";
        Assert.assertEquals(expectedArea, actualArea);
    }

    @Test
    public void givenLinkWithPath_whenGetPath_thenReturnDirectoryPath() {
        var actualArea = DirectoryUtils.getPath("https://de-de.facebook.com/share.php?u=https%3A%2F%2Fwww.tretton37.com%2Fjobs%2F1640413-talent-acquisition-specialist.html#");
        var expectedArea = "share-php-u=https%3A%2F%2Fwww-tretton37-com%2Fjobs%2F1640413-talent-acquisition-specialist-html#";
        Assert.assertEquals(expectedArea, actualArea);
    }

    @Test
    public void giveStringToGetPath_whenHasForbiddenCharactersWithSlashInTheLast_thenReplaceForbiddenWithHyphenAndLastSlash() {
        var actualArea = DirectoryUtils.getPath("https://de-de.facebook.com/.share.*php<>?u=https%3A%2F%2Fwww.tretton37.com%2Fjobs%2F1640413-talent-acquisition-specialist.html#");
        var expectedArea = "-share--php---u=https%3A%2F%2Fwww-tretton37-com%2Fjobs%2F1640413-talent-acquisition-specialist-html#";
        Assert.assertEquals(expectedArea, actualArea);
    }

    @Test
    public void giveStringToGetPath_whenDoesNotHaveForbiddenCharactersWithSlashInTheMiddle_thenReturnThePartAfterTheLastSlash() {
        var actualArea = DirectoryUtils.getPath("https://de-de.facebook.com/.share.*php<>?u=https%3A%2F%2Fwww.tretton37.com%2Fjobs%2F1640413-talent-acquisition-specialist.html#");
        var expectedArea = "-share--php---u=https%3A%2F%2Fwww-tretton37-com%2Fjobs%2F1640413-talent-acquisition-specialist-html#";
        Assert.assertEquals(expectedArea, actualArea);
    }

}
