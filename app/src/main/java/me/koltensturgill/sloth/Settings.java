package me.koltensturgill.sloth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;

public class Settings extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{

    Switch switchTheme;
    Spinner spnSorter;
    RadioGroup rdGrpOrders;
    RadioButton rdAsc, rdDesc;
    ArrayAdapter<CharSequence> spnAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Utils.setThemeToActivity(this, false);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        switchTheme = findViewById(R.id.swDarkTheme);
        switchTheme.setChecked(Utils.isSwitchChecked());
        switchTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if (b)
                {
                    Utils.changeTheme(R.style.AppDarkTheme);
                }
                else
                {
                    Utils.changeTheme(R.style.AppTheme);
                }
                Utils.changeSwitchCheck(b);
                //recreate to apply effects immediately
                recreate();
            }
        });

        spnSorter = (Spinner) findViewById(R.id.spnSorter);

        spnAdapter = ArrayAdapter.createFromResource(this,
                R.array.Sorting, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        spnAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spnSorter.setAdapter(spnAdapter);
        String s = Utils.getSorter().toString();
        if (s.equals("created_at"))
        {
            spnSorter.setSelection(spnAdapter.getPosition("Sort By Order"));
        }
        else if (s.equals("title"))
        {
            spnSorter.setSelection(spnAdapter.getPosition("Sort By Title"));
        }

        spnSorter.setOnItemSelectedListener(this);
//        spnSorter.setOnItemClickListener(new AdapterView.OnItemClickListener()
//        {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
//            {
//                String s = adapterView.getItemAtPosition(pos).toString();
//                if (s.equals("Sort By Date"))
//                {
//                    spnSorter.setSelection(spnAdapter.getPosition(s));
//                    Utils.setSorter("created_at");
//                }
//                else if (s.equals("Sort By Title"))
//                {
//                    spnSorter.setSelection(spnAdapter.getPosition(s));
//                    Utils.setSorter("title");
//                }
//            }
//        });


        rdGrpOrders = (RadioGroup) findViewById(R.id.rdGrpOrders);
        rdAsc = (RadioButton) findViewById(R.id.rdAsc);
        rdDesc = (RadioButton) findViewById(R.id.rdDesc);
        switch (Utils.getOrder())
        {
            case "ASC":
                rdAsc.setChecked(true);
                rdDesc.setChecked(false);
                break;
            case "DESC":
                rdDesc.setChecked(true);
                rdAsc.setChecked(false);
                break;
        }


        rdGrpOrders.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i)
            {
                RadioButton radioButton = findViewById(i);
                radioButton.setChecked(true);
                boolean checked = radioButton.isChecked();
                switch(i) {
                    case R.id.rdAsc:
                        if (checked)
                            Utils.setOrder("ASC");
                        break;
                    case R.id.rdDesc:
                        if (checked)
                            Utils.setOrder("DESC");
                        break;
                }
            }
        });
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        String s = parent.getItemAtPosition(pos).toString();
        if (s.equals("Sort By Date"))
        {
            spnSorter.setSelection(spnAdapter.getPosition(s));
            Utils.setSorter("created_at");
        }
        else if (s.equals("Sort By Title"))
        {
            spnSorter.setSelection(spnAdapter.getPosition(s));
            Utils.setSorter("title");
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
//        Utils.setSorter("created_at");
    }

    //save preferences when user goes out of settings page
    @Override
    protected void onPause()
    {
        super.onPause();
        Utils.savePreferenceToFile(this);
    }
}
