package uz.gita.puzzle15dracula;

import android.app.Application;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LocalStorge.init(this);
    }


}
