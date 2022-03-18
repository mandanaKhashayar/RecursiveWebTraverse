package com.mandana.recursivetraverse;


import java.util.ArrayList;
import java.util.List;

public final class Constants {
    public final static String BASE_URL_ADDRESS="https://tretton37.com";
    public final static String BASE_SITE="https://tretton37.com";
    public static final List<Character> INVALID_WINDOWS_SPECIFIC_CHARS =
            new ArrayList<>(){
                {add('"');
                add('*');
                add(':');
                add('<');
                add('>');
                add('?');
                add('\\');
                add('|');
                add('.');
                add((char)0x7F);
           }};
    public final static String EMAIL_ICON="@";
    public final static String regexURl = "((http|https)://)(www.)?[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z] {2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)";
    public final static int MAX_DEPTH = 2;


    public static final String HTML_FILE_EXTENSION = ".html";
}
