package com.example.numad22sp_yinanwang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import android.widget.EditText;
import android.app.AlertDialog;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.regex.Pattern;


public class LinkCollector extends AppCompatActivity {
    //Creating the essential parts needed for a Recycler view to work: RecyclerView, Adapter, LayoutManager
    private ArrayList<LinkCard> linkList = new ArrayList<>();
    ;

    private RecyclerView recyclerView;
    private RviewAdapter rviewAdapter;
    private RecyclerView.LayoutManager rLayoutManger;
    private FloatingActionButton addButton;

    private static final String KEY_OF_INSTANCE = "KEY_OF_INSTANCE";
    private static final String NUMBER_OF_ITEMS = "NUMBER_OF_ITEMS";

    protected void dialog(){
        final EditText linkName=new EditText(this);
        final EditText linkUrl=new EditText(this);
        Pattern pattern;

        LayoutInflater factory = LayoutInflater.from(LinkCollector.this);
        //得到自定义对话框
        final View DialogView = factory.inflate(R.layout.dialog, null);
        //创建对话框
        AlertDialog dlg = new AlertDialog.Builder(LinkCollector.this)
                .setTitle("Input Url")
                .setView(DialogView)
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        })
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                EditText linkName = (EditText) DialogView.findViewById(R.id.name);
                                String strLinkName = linkName.getText().toString();
                                EditText linkUrl = (EditText) DialogView.findViewById(R.id.url);
                                String strLinkUrl = linkUrl.getText().toString();
                                if (android.util.Patterns.WEB_URL.matcher(strLinkUrl).matches()) {
                                    LinkCard item = new LinkCard(strLinkName, strLinkUrl);
                                    linkList.add(item);
                                    Snackbar snackbar = Snackbar.make(recyclerView,"Add an item",Snackbar.LENGTH_SHORT);
                                    snackbar.show();
                                }
                                else{
                                    Snackbar snackbar = Snackbar.make(recyclerView, "Invalid Url", Snackbar.LENGTH_SHORT);
                                    snackbar.show();
                                }


                            }
                        })
                .create();//创建弹出框
        dlg.show();//显示

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linkcollector);

        init(savedInstanceState);

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog();
            }
        });

        //Specify what action a specific gesture performs, in this case swiping right or left deletes the entry
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Toast.makeText(LinkCollector.this, "Delete an item", Toast.LENGTH_SHORT).show();
                int position = viewHolder.getLayoutPosition();
                linkList.remove(position);

                rviewAdapter.notifyItemRemoved(position);

            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }


    // Handling Orientation Changes on Android
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {


        int size = linkList == null ? 0 : linkList.size();
        outState.putInt(NUMBER_OF_ITEMS, size);

        // Need to generate unique key for each item
        // This is only a possible way to do, please find your own way to generate the key
        for (int i = 0; i < size; i++) {
            // put itemName information into instance
            outState.putString(KEY_OF_INSTANCE + i + "0", linkList.get(i).getLinkName());
            // put itemDesc information into instance
            outState.putString(KEY_OF_INSTANCE + i + "1", linkList.get(i).getLinkUrl());
        }
        super.onSaveInstanceState(outState);

    }

    private void init(Bundle savedInstanceState) {

        initialItemData(savedInstanceState);
        createRecyclerView();
    }

    private void initialItemData(Bundle savedInstanceState) {

        // Not the first time to open this Activity
        if (savedInstanceState != null && savedInstanceState.containsKey(NUMBER_OF_ITEMS)) {
            if (linkList == null || linkList.size() == 0) {

                int size = savedInstanceState.getInt(NUMBER_OF_ITEMS);

                // Retrieve keys we stored in the instance
                for (int i = 0; i < size; i++) {
                    String linkName = savedInstanceState.getString(KEY_OF_INSTANCE + i + "0");
                    String linkUrl = savedInstanceState.getString(KEY_OF_INSTANCE + i + "1");

                    LinkCard linkCard = new LinkCard(linkName, linkUrl);

                    linkList.add(linkCard);
                }
            }
        }
        // The first time to opne this Activity
        //else {
            //ItemCard item1 = new ItemCard(R.drawable.pic_gmail_01, "Gmail", "Example description", false);
            //ItemCard item2 = new ItemCard(R.drawable.pic_google_01, "Google", "Example description", false);
            //ItemCard item3 = new ItemCard(R.drawable.pic_youtube_01, "Youtube", "Example description", false);
            //itemList.add(item1);
            //itemList.add(item2);
            //itemList.add(item3);
        //}

    }

    private void createRecyclerView() {


        rLayoutManger = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        rviewAdapter = new RviewAdapter(linkList);
        LinkClickListener linkClickListener = new LinkClickListener() {
            @Override
            public void onLinkClick(int position) {
                //attributions bond to the item has been changed
                //linkList.get(position).onLinkClick(position);
                String url=linkList.get(position).getLinkUrl();
                if (!url.startsWith("http://")&&!url.startsWith("https://"))
                    url="http://" +url;
                Uri uri=Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                //rviewAdapter.notifyItemChanged(position);
            }

        };
        rviewAdapter.setOnItemClickListener(linkClickListener);
        recyclerView.setAdapter(rviewAdapter);
        recyclerView.setLayoutManager(rLayoutManger);


    }
}