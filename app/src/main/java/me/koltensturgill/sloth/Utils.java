package me.koltensturgill.sloth;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import static android.content.Context.MODE_PRIVATE;


//This class has methods to set theme to activity by getting activity, checks if its main activity or not
//change theme private variable from settings when switched to/from dark theme
//two methods for switch that tracks the position of switch, returns true if checked
//changes private variable switchCheck to whatever boolean received
public class Utils
{
    private static int theme = R.style.AppTheme;
    private static boolean switchCheck = false;
    private static String sorter = "created_at";
    private static String order = "DESC";

    public static void setThemeToActivity(Activity activity, boolean isMain)
    {
        if (isMain)
        {
            if (theme == R.style.AppTheme)
            {
                activity.setTheme(R.style.AppTheme_NoActionBar);
            }
            else
            {
                activity.setTheme(R.style.AppDarkTheme_NoActionBar);
            }
        }
        else
        {
            activity.setTheme(theme);
        }
    }

    public static void changeTheme(int intTheme)
    {
        theme = intTheme;
    }


    public static boolean isSwitchChecked()
    {
        return switchCheck;
    }

    public static void changeSwitchCheck(boolean check)
    {
        switchCheck = check;
    }

    public static String getSorter()
    {
        return sorter;
    }

    public static String getOrder()
    {
        return order;
    }

    public static void setSorter(String sorter)
    {
        Utils.sorter = sorter;
    }

    public static void setOrder(String order)
    {
        Utils.order = order;
    }

    public static void savePreferenceToFile(Context context)
    {
        try
        {
            FileOutputStream fileOutputStream = context.openFileOutput("Data.txt", MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);

            outputStreamWriter.write(Integer.toString(theme) + "\n");
            outputStreamWriter.write(Boolean.toString(switchCheck) + "\n");
            outputStreamWriter.write(sorter + "\n");
            outputStreamWriter.write(order + "\n");

            outputStreamWriter.flush();
            outputStreamWriter.close();
        }
        catch (IOException e)
        {
            Toast.makeText(context, "Unable to Save Preferences", Toast.LENGTH_SHORT).show();
        }
    }

    public static void loadPreferenceFromFile(Context context)
    {
        File file = context.getApplicationContext().getFileStreamPath("Data.txt");

        String line;

        if (file.exists())
        {
            try
            {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.openFileInput("Data.txt")));

                if ((line = bufferedReader.readLine()) != null) { theme = Integer.parseInt(line);}
                if ((line = bufferedReader.readLine()) != null) { switchCheck = Boolean.parseBoolean(line);}
                if ((line = bufferedReader.readLine()) != null) { sorter = line;}
                if ((line = bufferedReader.readLine()) != null) { order = line;}
            }
            catch (IOException e)
            {
                return;
            }
        }
    }

}
