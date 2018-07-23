package util;

public class StringUtl {
    public static String toConversion(long size){
        int count = 0;
        String suffix = "";
        double s = (double)size;
        while (s >= 1024) {
            s /= 1024;
            count++;
        }
        switch (count) {
            case 0:
                suffix = "B";
                break;
            case 1:
                suffix = "KB";
                break;
            case 2:
                suffix = "MB";
                break;
            case 3:
                suffix = "GB";
                break;
            case 4:
                suffix = "TB";
                break;
            default:
                break;
        }
        return s+suffix;
    }
}
