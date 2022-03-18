package serviceImpl;

import com.mandana.recursivetraverse.Constants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class DirectoryUtils {

    public static void create(Path directory)  {
        try {
            if (Files.notExists(directory)) {
                System.out.println("Trying to create directory: " + directory);
                Files.createDirectories(directory);
                System.out.println("Directory: "+ directory +" is created succefully." );
            }
        }catch (IOException e){
            System.out.println("There is a problem in creating directory: " + directory + e.toString());
        }

    }

    public static String getPath(String urlString)  {
        if((urlString.lastIndexOf("/")+1== urlString.length()))
            urlString=urlString.substring(0,urlString.length()-1);
        urlString=urlString.substring(urlString.lastIndexOf("/")+1);
        urlString = urlString.replaceAll(
                Constants.INVALID_WINDOWS_SPECIFIC_CHARS.stream()
                        .map(String::valueOf).map(Pattern::quote)
                        .collect(Collectors.joining("|")),
                "-");
        return urlString;
    }
}

