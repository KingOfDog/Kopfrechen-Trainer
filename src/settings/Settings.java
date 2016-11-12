package settings;

import com.google.gson.annotations.Expose;
import properties.BooleanProperty;
import properties.IntegerProperty;

import java.util.Locale;

public class Settings {
    private static Settings instance;

    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    private Settings() {
    }

    @Expose public BooleanProperty add = new BooleanProperty("add", true);
    @Expose public BooleanProperty sub = new BooleanProperty("sub", true);
    @Expose public BooleanProperty mul = new BooleanProperty("mul", true);
    @Expose public BooleanProperty div = new BooleanProperty("div", true);
    @Expose public BooleanProperty subNeg = new BooleanProperty("subNeg", true);
    @Expose public BooleanProperty divDec = new BooleanProperty("divDec", false);

    public BooleanProperty[] operationSettings = {add, sub, mul, div, subNeg, divDec};

    @Expose public IntegerProperty addMin = new IntegerProperty("addMin", 1);
    @Expose public IntegerProperty addMax = new IntegerProperty("addMax", 1000);
    @Expose public IntegerProperty subMin = new IntegerProperty("subMin", 1);
    @Expose public IntegerProperty subMax = new IntegerProperty("subMax", 1000);
    @Expose public IntegerProperty mulMin = new IntegerProperty("mulMin", 0);
    @Expose public IntegerProperty mulMax = new IntegerProperty("mulMax", 25);
    @Expose public IntegerProperty divMin = new IntegerProperty("divMin", 1);
    @Expose public IntegerProperty divMax = new IntegerProperty("divMax", 25);

    public IntegerProperty[] minMaxSettings = {addMin, addMax, subMin, subMax, mulMin, mulMax, divMin, divMax};

    @Expose
    public IntegerProperty factorCount = new IntegerProperty("factorCount", 2);

    @Expose
    public Locale lang = new Locale("de", "DE");

    @Expose
    public boolean sounds = true;
    @Expose
    public double volume = 1;

    @Expose
    public boolean automaticUpdates = true;

    @Expose
    public boolean startMaximized = false;
    @Expose
    public double startWidth = 850;
    @Expose
    public double startHeight = 550;

    @Expose
    public String exerciseCorrect = "#2ecc71";
    @Expose
    public String exerciseWrong = "#e74c3c";

//    public void setAdd(boolean create, int min, int max) {
//        add = create;
//        addMin = min;
//        addMax = max;
//    }
//
//    public void setSub(boolean create, int min, int max, boolean neg) {
//        sub = create;
//        subMin = min;
//        subMax = max;
//        subNeg = neg;
//    }
//
//    public void setMul(boolean create, int min, int max) {
//        mul = create;
//        mulMin = min;
//        mulMax = max;
//    }
//
//    public void setDiv(boolean create, int min, int max, boolean comma) {
//        div = create;
//        divMin = min;
//        divMax = max;
//        divComma = comma;
//    }

    public void setLang(Locale locale) {
        lang = locale;
    }

    public void setSounds(boolean activate, double vol) {
        sounds = activate;
        volume = vol;
    }

    public void setUpdates(boolean activate) {
        automaticUpdates = activate;
    }

    public void setWindowSize(boolean maximized, double width, double height) {
        startMaximized = maximized;
        startWidth = width;
        startHeight = height;
    }

    public void setColors(String correct, String wrong) {
        exerciseCorrect = correct;
        exerciseWrong = wrong;
    }
}
