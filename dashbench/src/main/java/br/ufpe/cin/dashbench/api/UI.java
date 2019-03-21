package br.ufpe.cin.dashbench.api;

import android.widget.TextView;

public class UI {
    private static final UI ourInstance = new UI();
    private TextView infoText;
    public static UI getInstance() {
        return ourInstance;
    }

    private UI() {
    }

    public void setInfoTextComponent(TextView infoText){
        this.infoText=infoText;
    }

    public void setInfoText(String text){
        if(this.infoText!=null){
            this.infoText.setText(text);
        }
    }

    public void appendInfoText(String text){
        if(this.infoText!=null) {
            String currentText = this.infoText.getText()!=null?this.infoText.getText().toString():"";
            this.infoText.setText(currentText+"\n"+text);
        }
    }
}
