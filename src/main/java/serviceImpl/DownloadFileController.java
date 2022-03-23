package serviceImpl;


import repository.Repository;

import model.DirectoryAddress;
import model.FileDetails;
import service.ContentDownloader;
import service.DownloadController;

import java.nio.file.Paths;


public class DownloadFileController implements DownloadController {
    ContentDownloader downloader;
    DirectoryUtils directoryCreator;
    Repository repository;

    public DownloadFileController(ContentDownloader downloader,
                                  DirectoryUtils directoryCreator,
                                  Repository repository) {
        this.downloader = downloader;
        this.directoryCreator = directoryCreator;
        this.repository = repository;
    }

    public void download(DirectoryAddress directoryAddress) {
        FileDetails fileDetails = new FileDetails();
        DirectoryUtils.create(Paths.get(directoryAddress.getNodeDirectory()));
        fileDetails.setContent(downloader.downloadContent(directoryAddress.getUrlAddress()).toString());
        fileDetails.setDirectory(Paths.get(directoryAddress.getNodeDirectory()));
        repository.save(fileDetails);
    }
}



