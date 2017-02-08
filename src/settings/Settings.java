package settings;

import com.google.gson.annotations.Expose;
import properties.BooleanProperty;
import properties.IntegerProperty;
import properties.LocaleProperty;

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
    @Expose public BooleanProperty pow = new BooleanProperty("pow", true);
    @Expose public BooleanProperty root = new BooleanProperty("root", true);
    @Expose public BooleanProperty subNeg = new BooleanProperty("subNeg", true);
    @Expose public BooleanProperty divDec = new BooleanProperty("divDec", false);
    @Expose public BooleanProperty rootDec = new BooleanProperty("rootDec", false);

    @Expose public IntegerProperty addMin = new IntegerProperty("addMin", 1);
    @Expose public IntegerProperty addMax = new IntegerProperty("addMax", 1000);
    @Expose public IntegerProperty subMin = new IntegerProperty("subMin", 1);
    @Expose public IntegerProperty subMax = new IntegerProperty("subMax", 1000);
    @Expose public IntegerProperty mulMin = new IntegerProperty("mulMin", 0);
    @Expose public IntegerProperty mulMax = new IntegerProperty("mulMax", 25);
    @Expose public IntegerProperty divMin = new IntegerProperty("divMin", 1);
    @Expose public IntegerProperty divMax = new IntegerProperty("divMax", 25);
    @Expose public IntegerProperty powMinExpo = new IntegerProperty("powMinExpo", 1);
    @Expose public IntegerProperty powMaxExpo = new IntegerProperty("powMaxExpo", 3);
    @Expose public IntegerProperty powMinBase = new IntegerProperty("powMinBase", 0);
    @Expose public IntegerProperty powMaxBase = new IntegerProperty("powMaxBase", 25);
    @Expose public IntegerProperty rootMinExpo = new IntegerProperty("rootMinExpo", 1);
    @Expose public IntegerProperty rootMaxExpo = new IntegerProperty("rootMaxExpo", 3);
    @Expose public IntegerProperty rootMinRad = new IntegerProperty("rootMinRad", 1);
    @Expose public IntegerProperty rootMaxRad = new IntegerProperty("rootMaxRad", 100);

    @Expose public IntegerProperty factorCount = new IntegerProperty("factorCount", 2);

    @Expose public LocaleProperty lang = new LocaleProperty("language", new Locale("de", "DE"));

    @Expose public BooleanProperty sounds = new BooleanProperty("sounds", true);
    @Expose public double volume = 0.75;

    @Expose public BooleanProperty automaticUpdates = new BooleanProperty("automaticUpdates", true);

    @Expose public BooleanProperty startMaximized = new BooleanProperty("windowMaximized", false);
    @Expose public IntegerProperty startWidth = new IntegerProperty("windowWidth", 850);
    @Expose public IntegerProperty startHeight = new IntegerProperty("windowHeight", 550);

    @Expose public String exerciseCorrect = "#2ecc71";
    @Expose public String exerciseWrong = "#e74c3c";

    public BooleanProperty[] booleanSettings = {add, sub, mul, div, pow, root, subNeg, divDec, rootDec, startMaximized, automaticUpdates};
    public IntegerProperty[] integerSettings = {addMin, addMax, subMin, subMax, mulMin, mulMax, divMin, divMax, powMinBase, powMaxBase, powMinExpo, powMaxExpo, rootMinRad, rootMaxRad, rootMinExpo, rootMaxExpo};
    public IntegerProperty[] sliderSettings = {factorCount, startWidth, startHeight};
}
