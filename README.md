# ZCornerDialog
圆角Dialog的实现，在理解Android中&lt;shape>和&lt;style>的同时，来拓展如何实现自定义Dialog，三种方式。

>作者：邹峰立，微博：zrunker，邮箱：zrunker@yahoo.com，微信公众号：书客创作，个人平台：[www.ibooker.cc](http://www.ibooker.cc)。

>本文选自[书客创作](http://www.ibooker.cc)平台第42篇文章。[阅读原文](http://www.ibooker.cc/article/42/detail) 。

![书客创作](http://upload-images.jianshu.io/upload_images/3480018-cf0f54895dc89b60..jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

Dialog是Android应用程序上在常见不过的一个控件，圆角Dialog的实现对于大多数开发者来说也是非常容易。最终只有一个目的就是将显示Dialog的视图显示成圆角即可。而对视图的布局样式设置，可以通过XML文件进行定义，在Android系统中提供< shape>来设置视图的基本布局样式。

**一、理解shape**

```
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android">

    <!--大小
        height：高度值。
        width：宽度值。
    -->
    <size
        android:height="100dp"
        android:width="100dp"/>

    <!--内部填充色
        color：颜色值。
    -->
    <solid
        android:color="#FFFFFF" />

    <!--边框
        color：边框颜色。
        width：边框宽度。
        dashGap：虚线的间隔。
        dashWidth：虚线宽度。
        如果不设置dashGap，dashWidth默认是实线。
    -->
    <stroke
        android:color="#DEDEDE"
        android:width="1dp"
        android:dashGap="5dp"
        android:dashWidth="2dp"/>

    <!--圆角
        radius：设置四角半径。
        bottomLeftRadius：左下角半径。
        bottomRightRadius：右下角半径。
        topLeftRadius：左上角半径。
        topRightRadius：右上角半径。
    -->
    <corners
        android:radius="5dp"
        android:bottomLeftRadius="5dp"
        android:bottomRightRadius="5dp"
        android:topLeftRadius="5dp"
        android:topRightRadius="5dp"/>

    <!--颜色渐变
        angle：integer类型，渐变角度，0表示从左到右，90表示从下到上，必须是45的整数倍，默认0。
        startColor：开启渐变颜色。
        endColor：结束渐变颜色。
        centerColor：中间渐变颜色。
        centerX：float(0 ~ 1.0) 相对X的渐变位置。
        centerY：float(0 - 1.0) 相对Y的渐变位置。
        gradientRadius：float类型，渐变颜色的半径，单位应该是像素点。需要android:type="radial"。如果没有设置android:gradientRadius，将会报错，error inflating class。
        type：渐变类型：
            linear：线性渐变，可以理解为y=kx+b。
            radial：圆形渐变，起始颜色从cenralX,centralY点开始。
            sweep：扫线梯度渐变。
    -->
    <gradient
        android:angle="0"
        android:startColor="#FFFFFF"
        android:endColor="#FF00FF00"
        android:centerColor="#00FFFF"
        android:centerX="0.5"
        android:centerY="0.5"
        android:gradientRadius="20"
        android:type="radial" />

    <!--内间距
        bottom：距离底部内间距。
        left：距离左边内间距。
        right：距离右侧内间距。
        top：距离顶部内间距。
    -->
    <padding
        android:bottom="5dp"
        android:left="5dp"
        android:right="5dp"
        android:top="5dp"/>

</shape>
```
**二、理解style**

在Android的style.xml中，可以通过< style>标签设置窗体的主题样式，对于Dialog窗体常常会用到以下七种属性。
```
<style name="diydialog" parent="@android:style/Theme.Dialog">
    <item name="android:windowFrame">窗体边框(resource)</item>
    <item name="android:windowIsFloating">窗体是否浮现在activity之上(true/false)</item>
    <item name="android:windowIsTranslucent">窗体是否半透明(true/false)</item>
    <item name="android:windowNoTitle">窗体是否无标题(true/false)</item>
    <item name="android:windowBackground">窗体背景(resource)</item>
    <item name="android:backgroundDimEnabled">背景是否为半透明(true/false)</item>
    <item name="android:windowContentOverlay">窗体是否有遮盖(resource)</item>
</style>
```
**三、实现圆角Dialog**

**方式一：通过style设置属性windowBackground为圆角背景。**

1、在style.xml文件中自定义MyDialog。
```
<!--自定义MyDialog样式-->
<style name="MyDialog" parent="Theme.AppCompat.Dialog">
    <!-- 边框 -->
    <item name="android:windowFrame">@null</item>
    <!-- 无标题 -->
    <item name="android:windowNoTitle">true</item>
    <!-- 背景透明 -->
    <item name="android:windowBackground">@drawable/bg_mydialog</item>
    <!-- 悬浮在Activity上 -->
    <item name="android:windowIsFloating">true</item>
    <!-- 对话框是否有遮盖 -->
    <item name="android:windowContentOverlay">@null</item>
</style>
```
2、在drawable文件中创建bg_mydialog.xml。
```
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android">
    <solid android:color="#FFFFFF" />
    <stroke android:color="#DEDEDE" android:width="1dp"/>
    <corners android:radius="5dp"/>
    <padding android:bottom="5dp" android:left="5dp" android:right="5dp" android:top="5dp"/>
    <size android:width="200dp" android:height="200dp"/>
</shape>
```
3、展示MyDialog
```
Dialog myDialog = new Dialog(this, R.style.MyDialog);
myDialog.show();
```
**方式二：设置Dialog显示View为圆角背景。**

1、在style.xml文件中自定义cornerdialog。
```
<!--自定义cornerdialog样式-->
<style name="cornerdialog" parent="Theme.AppCompat.Dialog">
    <!-- 边框 -->
    <item name="android:windowFrame">@null</item>
    <!-- 无标题 -->
    <item name="android:windowNoTitle">true</item>
    <!-- 背景透明 -->
    <item name="android:windowBackground">@android:color/transparent</item>
    <!-- 悬浮在Activity上 -->
    <item name="android:windowIsFloating">true</item>
    <!-- 对话框是否有遮盖 -->
    <item name="android:windowContentOverlay">@null</item>
</style>
```
2、布局文件：dialog_layout.xml
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_gravity="center"
    android:background="@drawable/bg_corners"
    android:minWidth="200dp"
    android:orientation="vertical">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:padding="30dp"
        android:text="@string/app_name"
        android:textColor="#555" />

</LinearLayout>
```
背景文件bg_corners.xml：
```
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android">
    <corners android:bottomLeftRadius="5dp" android:bottomRightRadius="5dp" android:topLeftRadius="5dp" android:topRightRadius="5dp" />
    <solid android:color="#FFFFFF" />
    <stroke android:color="#DEDEDE" android:width="1dp"/>
    <corners android:radius="5dp"/>
    <padding android:bottom="5dp" android:left="5dp" android:right="5dp" android:top="5dp"/>
</shape>
```

3、自定义Dialog，加载布局文件。
```
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
        // 其他设置
    }
}
```
4、展示CornerDialog
```
CornerDialog cornerDialog = new CornerDialog(this);
cornerDialog.show();
```
**方式三：封装Dialog，自定义Dialog相关属性方法。**

1、在style.xml文件中自定义diydialog。
```
<!-- 自定义diydialog样式 -->
<style name="diydialog" parent="@android:style/Theme.Dialog">
    <item name="android:windowFrame">@null</item>
    <!-- 边框 -->
    <item name="android:windowIsFloating">true</item>
    <!-- 是否浮现在activity之上 -->
    <item name="android:windowIsTranslucent">true</item>
    <!-- 半透明 -->
    <item name="android:windowNoTitle">true</item>
    <!-- 无标题 -->
    <item name="android:windowBackground">@drawable/bg_white_gray_border</item>
    <!-- 背景 -->
    <item name="android:backgroundDimEnabled">true</item>
    <!-- 是否为半透明 -->
</style>
```
背景文件bg_white_gray_border.xml：
```
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android">
    <!--内容-->
    <solid android:color="#FFFFFF" />
    <!--四角-->
    <corners android:radius="5dp" />
    <!--边框-->
    <stroke
        android:width="0.6dp"
        android:color="#CCCCCC" />
</shape>
```
2、自定义DiyDialog，封装Dialog相关方法。
```
/**
 * 自定义Dialog
 * Created by 邹峰立 on 2017/7/6.
 */
public class DiyDialog {
    private Context context;
    private Dialog dialog;

    public enum DiyDialogGravity {
        GRAVITY_CENTER,
        GRAVITY_LEFT,
        GRAVITY_RIGHT,
        GRAVITY_TOP,
        GRAVITY_BOTTOM
    }

    public DiyDialog(@NonNull Context context, @NonNull View view) {
        this(context, R.style.diydialog, view);
    }

    public DiyDialog(@NonNull Context context, @StyleRes int themeResId, @NonNull View view) {
        dialog = new Dialog(context, themeResId);
        this.context = context;
        init(view);
    }

    /**
     * 初始化控件
     */
    private void init(View view) {
        dialog.setContentView(view);
        // 按返回键是否取消
        dialog.setCancelable(true);
        // 点击Dialog外围是否取消
        dialog.setCanceledOnTouchOutside(true);
        // 设置默认透明度0.2f
        this.setDimAmount(0.2f);
        // 设置默认居中显示
        this.setDiyDialogGravity(DiyDialogGravity.GRAVITY_CENTER);
    }

    /**
     * 设置Dialog显示内容
     *
     * @param view 内容View
     */
    private void setContentView(View view) {
        if (view != null)
            dialog.setContentView(view);
    }

    /**
     * 按返回键是否取消
     *
     * @param cancelable true 取消 false 不取消  默认true
     */
    public DiyDialog setCancelable(boolean cancelable) {
        if (dialog != null)
            dialog.setCancelable(cancelable);
        return this;
    }

    /**
     * 点击Dialog外围是否取消
     *
     * @param cancelable true 取消 false 不取消  默认false
     */
    public DiyDialog setCanceledOnTouchOutside(boolean cancelable) {
        if (dialog != null)
            dialog.setCanceledOnTouchOutside(cancelable);
        return this;
    }

    /**
     * 设置取消事件
     *
     * @param onCancelListener 取消事件
     */
    public DiyDialog setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        if (dialog != null)
            dialog.setOnCancelListener(onCancelListener);
        return this;
    }

    /**
     * 设置Dialog宽度
     *
     * @param proportion 和屏幕的宽度比(10代表10%) 0~100
     */
    public DiyDialog setDiyDialogWidth(int proportion) {
        if (dialog != null) {
            Window window = dialog.getWindow();
            if (window != null) {
                WindowManager.LayoutParams lp = window.getAttributes();
                lp.width = getScreenW(context) * proportion / 100;
                window.setAttributes(lp);
            }
        }
        return this;
    }

    /**
     * 设置Dialog高度
     *
     * @param proportion 和屏幕的高度比(10代表10%) 0~100
     */
    public DiyDialog setDiyDialogHeight(int proportion) {
        if (dialog != null) {
            Window window = dialog.getWindow();
            if (window != null) {
                WindowManager.LayoutParams lp = window.getAttributes();
                lp.height = getScreenH(context) * proportion / 100;
                window.setAttributes(lp);
            }
        }
        return this;
    }

    /**
     * 设置Dialog显示位置
     *
     * @param diyDialogGravity 左上右下中
     */
    public DiyDialog setDiyDialogGravity(DiyDialogGravity diyDialogGravity) {
        if (dialog != null) {
            Window window = dialog.getWindow();
            int gravity = Gravity.CENTER;
            if (diyDialogGravity == DiyDialogGravity.GRAVITY_BOTTOM) {
                gravity = Gravity.BOTTOM;
            } else if (diyDialogGravity == DiyDialogGravity.GRAVITY_CENTER) {
                gravity = Gravity.CENTER;
            } else if (diyDialogGravity == DiyDialogGravity.GRAVITY_LEFT) {
                gravity = Gravity.START;
            } else if (diyDialogGravity == DiyDialogGravity.GRAVITY_RIGHT) {
                gravity = Gravity.END;
            } else if (diyDialogGravity == DiyDialogGravity.GRAVITY_TOP) {
                gravity = Gravity.TOP;
            }
            if (window != null)
                window.getAttributes().gravity = gravity;
        }
        return this;
    }

    /**
     * 设置背景层透明度
     *
     * @param dimAmount 0~1
     */
    public DiyDialog setDimAmount(float dimAmount) {
        if (dialog != null) {
            Window window = dialog.getWindow();
            if (window != null) {
                WindowManager.LayoutParams lp = window.getAttributes();
                // 设置背景层透明度
                lp.dimAmount = dimAmount;
                window.setAttributes(lp);
            }
        }
        return this;
    }

    /**
     * 设置Window动画
     *
     * @param style R文件
     */
    public DiyDialog setWindowAnimations(int style) {
        if (dialog != null) {
            Window window = dialog.getWindow();
            if (window != null) {
                window.setWindowAnimations(style);
            }
        }
        return this;
    }

    /**
     * 展示Dialog
     */
    public void showDiyDialog() {
        if (dialog != null)
            dialog.show();
    }

    /**
     * 关闭Dialog
     */
    public void closeDiyDialog() {
        if (dialog != null) {
            dialog.cancel();
        }
    }

    /**
     * 获取屏幕宽度
     */
    private int getScreenW(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 获取屏幕的高度
     */
    private int getScreenH(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }
}
```
3、展示DiyDialog，显示布局文件。布局文件dialog_diy_layout.xml：
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_gravity="center"
    android:minWidth="200dp"
    android:orientation="vertical">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:padding="30dp"
        android:text="@string/app_name"
        android:textColor="#555" />

</LinearLayout>
```
```
View diyDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_diy_layout, null);
DiyDialog diyDialog = new DiyDialog(this, diyDialogView);
diyDialog.showDiyDialog();
```
以上三种方式都可以实现圆角Dialog，但论复用性，推荐使用方式三。方式三中可以任意显示任何布局文件，并且不会对已经设置好的布局样式进行影响。自定义方法采用构建者模式，对外提供链式结构方法调用。

[Github地址](https://github.com/zrunker/ZCornerDialog)
[阅读原文](http://www.ibooker.cc/article/42/detail)

----------
![微信公众号：书客创作](http://upload-images.jianshu.io/upload_images/3480018-45714aed000520c0..jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
