package dataframe.observer;

import java.util.List;

public class LogObserver implements IObserver{
    String log = "";

    @Override
    public void update(String message) {
        log = log + message + "\n";
    }

    public String getLog(){
        return log;
    }
}
