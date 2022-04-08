package ir.hamedgramzi.lib;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

public class FontManager {

    private static HashMap<String, FontManager> instances = new HashMap<>();
    private static String DEFAULT_FONT;
    private static Context context;
    private static int[] fontNames;

    private Typeface customTypeface;


    /**
     * you should call this method once at startup App
     * you should put fonts in assets/fonts folder
     * for use this class yout must define font names in string resource file
     *
     * @param context
     * @param resDefaultFontString default font for your app resource string file
     */
    public static void init(Context context, int resDefaultFontString) {
        FontManager.context = context;
        DEFAULT_FONT = context.getString(resDefaultFontString);
    }

    /**
     * @return get instance of FontManager
     */
    public static FontManager instance() {
        FontManager fontManager = instances.get(DEFAULT_FONT);
        if (fontManager == null) {
            instances.put(DEFAULT_FONT, new FontManager(DEFAULT_FONT));
        }
        return instances.get(DEFAULT_FONT);
    }

    /**
     * @param resfontName address of font name in string resource file
     * @return get instance of FontManager
     */
    public static FontManager instance(int resfontName) {
        String key = context.getString(resfontName);
        FontManager fontManager = instances.get(key);
        if (fontManager == null) {
            instances.put(key, new FontManager(key));
        }
        return instances.get(key);
    }

    /**
     * @param fontName a name of the font from assets/fonts
     * @return get instance of FontManager
     */
    public static FontManager instance(String fontName) {
        FontManager fontManager = instances.get(fontName);
        if (fontManager == null) {
            instances.put(fontName, new FontManager(fontName));
        }
        return instances.get(fontName);
    }

    public static FontManager instance(Typeface typeface) {
        FontManager fontManager = instances.get(typeface.toString());
        if (fontManager == null) {
            instances.put(typeface.toString(), new FontManager(typeface));
        }
        return instances.get(typeface.toString());
    }

    private FontManager(String name) {
        customTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/" + name);
    }

    private FontManager(Typeface typeface) {
        customTypeface = typeface;
    }

    /**
     * if you want set typeface yourself, use this method and get it
     *
     * @return
     */
    public Typeface getTypeface() {
        return customTypeface;
    }

    /**
     * set typeface for this view and childs immediate
     *
     * @param view
     */
    public void setTypefaceImmediate(View view) {
        if (customTypeface != null && view != null) {
            if (view instanceof TextView)
                ((TextView) view).setTypeface(customTypeface);
            else if (view instanceof Button)
                ((Button) view).setTypeface(customTypeface);
            else if (view instanceof EditText)
                ((EditText) view).setTypeface(customTypeface);
            else if (view instanceof ViewGroup)
                setTypefaceImmediate((ViewGroup) view);
        }
    }

    /**
     * set typeface for this Layout and childs immediate
     *
     * @param viewGroup
     */
    public void setTypefaceImmediate(ViewGroup viewGroup) {
        if (customTypeface != null && viewGroup != null) {
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                setTypefaceImmediate(viewGroup.getChildAt(i));
            }
        }
    }

    /**
     * set typeface for this Layout and childs immediate
     *
     * @param activity
     */
    public void setTypefaceImmediate(Activity activity) {
        setTypefaceImmediate(activity.getWindow().getDecorView());
    }


    /**
     * set typeface for this Layout and childs after 10 milisecond
     * this method good for activity,fragments and etc because they need time for loading complete and then set typeface
     *
     * @param viewGroup
     */
    public void setTypeface(final ViewGroup viewGroup) {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                setTypefaceImmediate(viewGroup);
            }
        }, 10);
    }

    /**
     * set typeface for this view and childs after 10 milisecond
     * this method good for activity,fragments and etc because they need time for loading complete and then set typeface
     *
     * @param view
     */
    public void setTypeface(final View view) {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                setTypefaceImmediate(view);
            }
        }, 10);
    }

    /**
     * set typeface for this activity and childs after 10 milisecond
     * this method good for activity,fragments and etc because they need time for loading complete and then set typeface
     *
     * @param activity
     */
    public void setTypeface(final Activity activity) {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                setTypefaceImmediate(activity.getWindow().getDecorView());
            }
        }, 10);
    }

}