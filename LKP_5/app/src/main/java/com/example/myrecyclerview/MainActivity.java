package com.example.myrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvCategory;
    private ArrayList<President> list = new ArrayList<>();
    final String STATE_TITTLE = "state_string";
    final String STATE_LIST = "state_list";
    final String STATE_MODE = "state_mode";
    int mode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvCategory = findViewById(R.id.rv_category);
        rvCategory.setHasFixedSize(true);

        list = new ArrayList<>();

        if (savedInstanceState == null){
            setActionBarTittle("Mode List");
            list.addAll(PresidentData.getListData());
            showRecyclerList();
            mode = R.id.action_list;
        } else {
            String stateTittle = savedInstanceState.getString(STATE_TITTLE);
            //ArrayList<President> stateList = savedInstanceState.getParcelableArrayList(STATE_LIST);
            int stateMode = savedInstanceState.getInt(STATE_MODE);
            setActionBarTittle(stateTittle);
            //list.addAll(stateList);
            setMode(stateMode);
        }
    }
    private void showSelectedPresident(President president){
        Toast.makeText(this, "Kamu Memilih " + president.getName(), Toast.LENGTH_SHORT).show();
    }
    private void showRecyclerList(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        ListPresidentAdapter listPresidentAdapter = new ListPresidentAdapter(this);
        listPresidentAdapter.setListPresident(list);
        rvCategory.setAdapter(listPresidentAdapter);

        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener(){
            @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v){
                        showSelectedPresident(list.get(position));
            }
        });
    }
    private void showRecyclerGrid(){
        rvCategory.setLayoutManager(new GridLayoutManager(this,2));
        GridPresidentAdapter gridPresidentAdapter = new GridPresidentAdapter(this);
        gridPresidentAdapter.setListPresident(list);
        rvCategory.setAdapter(gridPresidentAdapter);

        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener(){
                    public void onItemClicked(RecyclerView recyclerView, int position, View v){
                        showSelectedPresident(list.get(position));
            }
        });
    }
    private void showRecyclerCardView(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        CardViewPresidentAdapter cardViewPresidentAdapter = new CardViewPresidentAdapter(this);
        cardViewPresidentAdapter.setListPresident(list);
        rvCategory.setAdapter(cardViewPresidentAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }
    private void setActionBarTittle(String tittle){
        getSupportActionBar().setTitle(tittle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }
    public void setMode(int selectedMode){
        String tittle = null;
        switch (selectedMode){
            case R.id.action_list:
                tittle = "Mode List";
                showRecyclerList();
                break;
            case R.id.action_grid:
                tittle = "Mode Grid";
                showRecyclerGrid();
                break;
            case R.id.action_cardview:
                tittle = "Mode CardView";
                showRecyclerCardView();
                break;
        }
        mode = selectedMode;
        setActionBarTittle(tittle);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString(STATE_TITTLE,getSupportActionBar().getTitle().toString());
        //outState.putParcelable(STATE_TITTLE,list);
        outState.putInt(STATE_MODE,mode);
    }
}