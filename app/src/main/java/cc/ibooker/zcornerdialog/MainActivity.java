package cc.ibooker.zcornerdialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showMyDialog(View view) {
        Dialog myDialog = new Dialog(this, R.style.MyDialog);
        myDialog.show();
    }

    public void showCornerDialog(View view) {
        CornerDialog cornerDialog = new CornerDialog(this);
        cornerDialog.show();
    }

    public void showDiyDialog(View view) {
        View diyDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_diy_layout, null);
        DiyDialog diyDialog = new DiyDialog(this, diyDialogView);
        diyDialog.showDiyDialog();
    }
}
