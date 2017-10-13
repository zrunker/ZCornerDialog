package cc.ibooker.zcornerdialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;

/**
 * 自定义Dialog
 * Created by 邹峰立 on 2017/10/13 0013.
 */
public class CornerDialog extends Dialog {

    public CornerDialog(@NonNull Context context) {
        this(context, R.style.cornerdialog);
    }

    public CornerDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        init();
    }

    // 初始化
    private void init() {
        this.setContentView(R.layout.dialog_layout);
    }

}
