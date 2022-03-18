package repositoryImpl;

import com.mandana.recursivetraverse.Constants;
import model.FileDetails;
import repository.Repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileDetailsRepository implements Repository<FileDetails> {
    @Override
    public void save(FileDetails fileDetails) {
        BufferedWriter bufferedWriter = null;
        try {
           var directoryName= fileDetails.getDirectory().toString();
           var fileName=
                   "/"+ directoryName.substring(directoryName.lastIndexOf("\\")+1)+ Constants.HTML_FILE_EXTENSION;
            bufferedWriter = new BufferedWriter(new FileWriter(directoryName + fileName));
            bufferedWriter.write(fileDetails.getContent());
            System.out.println("HTML Page downloaded: " + fileDetails.getDirectory());
        } catch (IOException e) {
            System.out.println(e.getMessage() + e);
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    // This is unrecoverable. Just report it and move on
                    e.printStackTrace();
                }
            }

        }
    }
}

