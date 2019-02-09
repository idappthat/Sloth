package me.koltensturgill.sloth;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import me.koltensturgill.sloth.Model.Note;
import me.koltensturgill.sloth.Model.NotesViewModel;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    public static int _CREATE = 1;

    EditText editText;

    private NotesViewModel notesViewModel;
    List<String> input; //hardcoded sample list for RecyclerView

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private RecyclerView.LayoutManager recyclerLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        final Intent intent = new Intent(this, Editor.class);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            // .setAction("Action", null).show();

            //recycler adding
            //recyclerAdapter = (RecyclerAdapter) recyclerView.getAdapter();
            //recyclerAdapter.getValues().add("New Item");
            //recyclerAdapter.notifyItemInserted(input.size()-1);

            startActivityForResult(intent, _CREATE);
            }
        });

        //Jerp stuff for RecyclerView =================

//        recyclerView = (RecyclerView)findViewById(R.id.recycler_main);
//        recyclerView.setHasFixedSize(true);
//        recyclerLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(recyclerLayoutManager);
//        input = new ArrayList<>();
//        for (int i = 0; i < 20; i++) input.add("Note " + i);
//        recyclerAdapter = new RecyclerAdapter(input);
//        recyclerView.setAdapter(recyclerAdapter);

        //=============================================

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Bind our view model
        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        // Set up the recycler
        RecyclerView recyclerView = findViewById(R.id.recyclerview_notes);
        final NoteListAdapter adapter = new NoteListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create an observer, and update our adapter
        notesViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                adapter.setNotes(notes);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            final Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    //ORIGINAL NAV HANDLER

    /*
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    */

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

//        if (id == R.id.nav_folderA)
//        {
//            List<String> newsublist = new ArrayList<>();
//            for(int i = 0; i < 5; i++) newsublist.add("List A note " + i);
//            recyclerView.setAdapter(new RecyclerAdapter(newsublist));
//        }
//        else if (id == R.id.nav_folderB)
//        {
//            List<String> newsublist = new ArrayList<>();
//            for(int i = 0; i < 2; i++) newsublist.add("List B note " + i);
//            recyclerView.setAdapter(new RecyclerAdapter(newsublist));
//        }
//        else if (id == R.id.nav_folderC)
//        {
//            List<String> newsublist = new ArrayList<>();
//            for(int i = 0; i < 7; i++) newsublist.add("List C note " + i);
//            recyclerView.setAdapter(new RecyclerAdapter(newsublist));
//        }
//        else if (id == R.id.nav_folderD)
//        {
//            List<String> newsublist = new ArrayList<>();
//            recyclerView.setAdapter(new RecyclerAdapter(newsublist));
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        try {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == _CREATE  && resultCode == RESULT_OK) {

                String requiredValue = data.getStringExtra("key");
            }
        } catch (Exception ex) {
            Toast.makeText(this, ex.toString(),
                    Toast.LENGTH_SHORT).show();
        }
    }
}
