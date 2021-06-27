package com.apssdc.savenotes.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apssdc.savenotes.Model.Notes;
import com.apssdc.savenotes.R;
import com.apssdc.savenotes.ViewModel.NotesViewModel;
import com.apssdc.savenotes.databinding.ActivityUpdateNotesBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Date;

public class UpdateNotesActivity extends AppCompatActivity {
    ActivityUpdateNotesBinding binding;
    NotesViewModel notesViewModel;
    String priority = "1";
    String stitle,ssubtitle,spriority,snotes;
    int iid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityUpdateNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        notesViewModel= ViewModelProviders.of(this).get(NotesViewModel.class);

        stitle=getIntent().getStringExtra("title");
        ssubtitle=getIntent().getStringExtra("subtitle");
        iid=getIntent().getIntExtra("id",0);
        spriority=getIntent().getStringExtra("priority");
        snotes=getIntent().getStringExtra("note");


        binding.upTitle.setText(stitle);
        binding.upSubtitle.setText(ssubtitle);
        binding.upNotes.setText(snotes);
        if(spriority.equals("1")){
            binding.greenPriority.setImageResource(R.drawable.ic_baseline_done_24);
        }else if(spriority.equals("2")){
            binding.redPriority.setImageResource(R.drawable.ic_baseline_done_24);
        }
        else if(spriority.equals("3")){
            binding.yellowPriority.setImageResource(R.drawable.ic_baseline_done_24);
        }
        else if(spriority.equals("4")){
            binding.pinkPriority.setImageResource(R.drawable.ic_baseline_done_24);
        }


        binding.greenPriority.setOnClickListener(v -> {
            binding.greenPriority.setImageResource(R.drawable.ic_baseline_done_24);
            binding.redPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(0);
            binding.pinkPriority.setImageResource(0);
            priority="1";
        });
        binding.redPriority.setOnClickListener(v -> {
            binding.greenPriority.setImageResource(0);
            binding.redPriority.setImageResource(R.drawable.ic_baseline_done_24);
            binding.yellowPriority.setImageResource(0);
            binding.pinkPriority.setImageResource(0);
            priority="2";
        });
        binding.yellowPriority.setOnClickListener(v -> {
            binding.greenPriority.setImageResource(0);
            binding.redPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(R.drawable.ic_baseline_done_24);
            binding.pinkPriority.setImageResource(0);
            priority="3";
        });
        binding.pinkPriority.setOnClickListener(v -> {
            binding.greenPriority.setImageResource(0);
            binding.redPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(0);
            binding.pinkPriority.setImageResource(R.drawable.ic_baseline_done_24);
            priority="4";
        });
        binding.updateNotesBtn.setOnClickListener(v ->{
          String  title=binding.upTitle.getText().toString();
            String subtitle=binding.upSubtitle.getText().toString();
            String notes=binding.upNotes.getText().toString();

            UpdateNotes(title,subtitle,notes);

        });


    }

    private void UpdateNotes(String title, String subtitle, String notes) {
        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d,yyyy",date.getTime());
        Notes updateNotes=new Notes();

        updateNotes.id=iid;
        updateNotes.notesTitle=title;
        updateNotes.notesSubtitle=subtitle;
        updateNotes.notes=notes;
        updateNotes.notesPriority=priority;
        updateNotes.notesDate=sequence.toString();

        notesViewModel.updateNote(updateNotes);
        Toast.makeText(this, "Notes Updated Successfully", Toast.LENGTH_SHORT).show();

        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.delete_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.ic_delete){
            BottomSheetDialog sheetDialog=new BottomSheetDialog(UpdateNotesActivity.this,R.style.BottomSheetStyle);
            View view= LayoutInflater.from(UpdateNotesActivity.this).inflate
                    (R.layout.delete_bottom_sheet,(LinearLayout)findViewById(R.id.bottomSheet));
            sheetDialog.setContentView(view);
            TextView yes,no;
            yes=view.findViewById(R.id.delete_yes);
            no=view.findViewById(R.id.delete_no);

            yes.setOnClickListener(v ->{
                notesViewModel.deleteNote(iid);
                finish();
            });
            no.setOnClickListener(v ->{
              sheetDialog.dismiss();
            });

            sheetDialog.show();

        }
        return true;
    }
}