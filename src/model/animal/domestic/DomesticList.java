package model.animal.domestic;

import java.util.regex.Pattern;

public enum DomesticList {
    CHICKEN("model.animal.domestic.Chicken", "Chicken", "^(?i)\\s*chicken\\s*$", 100),
    SHEEP("model.animal.domestic.Sheep", "Sheep", "^(?i)\\s*sheep\\s*$", 200);

    private final String packageName;
    private final String className;
    private final Pattern classPattern;
    private final int price;

    DomesticList(String packageName, String className, String classPattern, int price) {
        this.packageName = packageName;
        this.className = className;
        this.classPattern = Pattern.compile(classPattern);
        this.price = price;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getClassName() {
        return className;
    }

    public int getPrice() {
        return price;
    }

    public static DomesticList getDomestic(String name) {
        for (DomesticList value : DomesticList.values()) {
            if (value.classPattern.matcher(name).matches())
                return value;
        }
        return null;
    }
}
