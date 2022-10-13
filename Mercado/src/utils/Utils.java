package utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class Utils {

    static NumberFormat formatoNumero =
            new DecimalFormat("R$ #, ##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));        //Formatando valores para reais $$

    public static String doubleParaString (Double valor) {          //Recebe valor em double e retorna em String
        return formatoNumero.format(valor);
    }



}
