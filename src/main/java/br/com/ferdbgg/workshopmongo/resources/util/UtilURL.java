package br.com.ferdbgg.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class UtilURL {

    private UtilURL() {

    }

    public static String decodificarParametroURL(String palavra) {
        try {
            return URLDecoder.decode(palavra, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.err.println(e.getMessage());
            return "";
        }
    }

    public static Date converterDataURL(String dataString) {
        return UtilURL.converterDataURL(dataString, new Date());
    }

    public static Date converterDataURL(String dataString, Date padrao) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        try {
            return simpleDateFormat.parse(dataString);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            return padrao;
        }
    }

}
