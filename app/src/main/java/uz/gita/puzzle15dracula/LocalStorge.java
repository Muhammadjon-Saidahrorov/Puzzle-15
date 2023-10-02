package uz.gita.puzzle15dracula;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalStorge {
    private static LocalStorge localStorge;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public LocalStorge(Context context) {
        preferences = context.getSharedPreferences("PUZZLE", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public static LocalStorge getInstance() {
        return localStorge;
    }

    public static void init(Context context) {
        if (localStorge == null)
            localStorge = new LocalStorge(context);
    }

    public void saveRecord(String s, int count) {
        editor.putInt(s, count);
        editor.apply();
    }

    public int getInt(String s) {
        return preferences.getInt(s, 0);
    }

    public void saveBoolean(String s, boolean b) {
        editor.putBoolean(s, b);
        editor.apply();
    }

    public boolean getBoolean(String s) {
        return preferences.getBoolean(s, true);
    }

    public void saveLong(String s, Long time){
        editor.putLong(s,time);
        editor.apply();
    }

    public long getLong(String s){
        return preferences.getLong(s,0);
    }

   public void saveString(String s, String str){
        editor.putString(s,str);
        editor.apply();
   }

   public String getString(String s){
        return preferences.getString(s,"");
   }

}
