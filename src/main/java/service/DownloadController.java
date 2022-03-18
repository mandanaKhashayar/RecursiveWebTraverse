package service;

import model.DirectoryAddress;

import java.io.IOException;

public interface DownloadController {
     void download(DirectoryAddress directoryAddress) throws IOException;
}
